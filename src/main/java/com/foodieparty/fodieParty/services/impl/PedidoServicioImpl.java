package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.Utilidades.FilaTabla;
import com.foodieparty.fodieParty.Utilidades.GenerardorPdf;
import com.foodieparty.fodieParty.dtos.DetallePedidoDTO;
import com.foodieparty.fodieParty.dtos.PedidoDTO;
import com.foodieparty.fodieParty.models.*;
import com.foodieparty.fodieParty.repositories.*;
import com.foodieparty.fodieParty.services.PedidosServicio;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@Service
public class PedidoServicioImpl implements PedidosServicio {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private BebidaRepositorio bebidaRepositorio;
    @Autowired
    private ComidaRepositorio comidaRepositorio;
    @Autowired
    private ComidaPedidoRepositorio comidaPedidoRepositorio;
    @Autowired
    private BebidaPedidoRepositorio bebidaPedidoRepositorio;
    @Autowired
    private TicketPedidoRepositorio ticketPedidoRepositorio;


    @Override
    public List<PedidoDTO> getPedidos() {
        String string;
        return pedidoRepositorio.findAll().stream().map(PedidoDTO::new).collect(toList());
    }

    @Override
    public Optional<PedidoDTO> getPedidoPorId(Long id) {
        return pedidoRepositorio.findById(id).map(PedidoDTO::new);
    }

    @Override
    public List<PedidoDTO> getPedidosUsuario(Authentication authentication) {
        Usuario usuario=usuarioRepositorio.findByEmail(authentication.getName());
        return usuario.getPedidos().stream().map(PedidoDTO::new).collect(toList());
    }

    @Override
    public void save(Pedido pedido) {
        pedidoRepositorio.save(pedido);
    }

    @Override
    public ResponseEntity<Object> crearPedido(DetallePedidoDTO detallePedidoDTO, Authentication authentication) {
        {
            Usuario usuario = usuarioRepositorio.findByEmail(authentication.getName());
            if (usuario==null){
                return new ResponseEntity<>("debe ingresar con su usuario para realizar un pedido",HttpStatus.FORBIDDEN);
            }
            if(detallePedidoDTO.getDireccion()==null&&detallePedidoDTO.getTipoRetiro()==TipoRetiro.DOMICILIO){
                return new ResponseEntity<>("no se ingreso la direccion a cual hay que enviarla",HttpStatus.FORBIDDEN);
            }
            if (detallePedidoDTO.getTipoRetiro()==null){
                return new ResponseEntity<>("no a seleccionado el tipo de retiro",HttpStatus.FORBIDDEN);
            }
            if (detallePedidoDTO.getListaBebidasId().size()==0&&detallePedidoDTO.getListaComidasId().size()==0){
                return new ResponseEntity<>("no se pueden realizar pedidos vacios",HttpStatus.FORBIDDEN);
            }
            //verificacion de stock comidas
            for (long[] comida:detallePedidoDTO.getListaComidasId()){
                if (comidaRepositorio.findById(comida[0]).get()==null){
                    return new ResponseEntity<>("la comida ingresada no existe",HttpStatus.FORBIDDEN);
                }
                if (comidaRepositorio.findById(comida[0]).get().getDisponibilidad()){
                    return new ResponseEntity<>("no hay disponibilidad para esta comida"+comidaRepositorio.findById(comida[0]).get().getNombre(),HttpStatus.FORBIDDEN);
                }
            }
            //verificacion de stock bebidas
            for (long[] bebida:detallePedidoDTO.getListaBebidasId()){
                if (bebidaRepositorio.findById(bebida[0]).get()==null){
                    return new ResponseEntity<>("la bebida ingresada no existe",HttpStatus.FORBIDDEN);
                }
                if (bebidaRepositorio.findById(bebida[0]).get().getDisponibilidad()<bebida[1]){
                    return new ResponseEntity<>("no hay disponibilidad para esta bebida"+bebidaRepositorio.findById(bebida[0]).get().getNombre(),HttpStatus.FORBIDDEN);
                }
            }
            //detallePedidoDTO:
            //  listaComidasId ----> [0]=idComida, [1]=cantidadSolicitada.
            //  listaBebidasId ----> [0]=idBebida, [1]=cantidadSolicitada.

            //Crear un nuevo pedido.
            Pedido pedido = new Pedido(
                    detallePedidoDTO.getTipoRetiro(),
                    detallePedidoDTO.getDireccion(),
                    usuario);
            //Preparar variables para contabilizar el total y concatenar detalles del ticket.
            List<FilaTabla> filaTablas = new ArrayList<>();
            List<String> detalleTicket = new ArrayList<>();
            Double total = 0.0;
            //Recorrer la lista idComidaYCantidad
            Comida comida;
            ComidaPedido comidaPedido;
            for (long[] idComidaYCantidad : detallePedidoDTO.getListaComidasId()) {
                comida = comidaRepositorio.findById(idComidaYCantidad[0]).get();
                comidaPedido = new ComidaPedido((int) idComidaYCantidad[1], comida.getPrecio());
                comida.agregarComidaPedido(comidaPedido);
                pedido.agregarComidaPedido(comidaPedido);
                comidaPedidoRepositorio.save(comidaPedido);
                total += comidaPedido.getPrecioPorCantidad();
                detalleTicket.add(comida.getNombre() + " x " + idComidaYCantidad[1] + " = $" + comida.getPrecio() * idComidaYCantidad[1]);
                FilaTabla fila = new FilaTabla(
                        String.valueOf(idComidaYCantidad[1]),
                        comida.getNombre(),
                        String.valueOf(comida.getPrecio()),
                        String.valueOf(comidaPedido.getPrecioPorCantidad()));
                filaTablas.add(fila);
            }

            //Recorrer la lista idBebidaYCantidad
            Bebida bebida;
            BebidaPedido bebidaPedido;
            for (long[] idBebidaYCantidad : detallePedidoDTO.getListaBebidasId()) {
                bebida = bebidaRepositorio.findById(idBebidaYCantidad[0]).get();
                bebidaPedido = new BebidaPedido((int) idBebidaYCantidad[1], bebida.getPrecio());
                //Si hay stock suficiente de bebida, reducir stock, sino error.
                if (bebida.tieneStock(idBebidaYCantidad[1])) {
                    bebida.reducirStock(idBebidaYCantidad[1]);
                } else {
                    return new ResponseEntity<>("Stock insuficiente de " + bebida.getNombre(), HttpStatus.FORBIDDEN);
                }
                bebida.agregarBebidaPedido(bebidaPedido);
                pedido.agregarBebidaPedido(bebidaPedido);
                bebidaPedidoRepositorio.save(bebidaPedido);
                total += bebidaPedido.getPrecioPorCantidad();
                detalleTicket.add(bebida.getNombre() + " x " + idBebidaYCantidad[1] + " = $" + bebida.getPrecio() * idBebidaYCantidad[1]);
                FilaTabla fila = new FilaTabla(
                        String.valueOf(idBebidaYCantidad[1]),
                        bebida.getNombre(),
                        String.valueOf(bebida.getPrecio()),
                        String.valueOf(bebidaPedido.getPrecioPorCantidad()));
                filaTablas.add(fila);
            }

            //Crear un ticket para el pedido
            TicketPedido ticketPedido = new TicketPedido(detalleTicket, total);
            pedido.agregarTicketPedido(ticketPedido);
            pedido.setPrecioTotal(total);
            usuario.agregarPedido(pedido);
            ticketPedidoRepositorio.save(ticketPedido);
            pedidoRepositorio.save(pedido);

            //Crea el documento
//        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HeaderFooter.pdf"));
//        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//        writer.setPageEvent(event);
//
//        PdfWriter.getInstance(document, new FileOutputStream("Ticket_Pedido.pdf"));
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//        for(String detalle: ticketPedido.getDetalle()){
//            Chunk chunk = new Chunk(detalle,font);
//            document.add(new Paragraph(chunk));
//        }
//        document.close();

            GenerardorPdf generardorPdf = new GenerardorPdf();
            generardorPdf.generarPdf(filaTablas, String.valueOf(total));

            return new ResponseEntity<>("Pedido exitoso", HttpStatus.CREATED);

        }
    }
    @Override
    public ResponseEntity<Object> editarEstado(long id, EstadoPedido nuevoEstado){
            Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
            if(pedido==null){
                return new ResponseEntity<>("El pedido no existe",HttpStatus.FORBIDDEN);
            }
            EstadoPedido anteriorEstado = pedido.getEstadoPedido();
            pedido.setEstadoPedido(nuevoEstado);
            pedidoRepositorio.save(pedido);
            return new ResponseEntity<>("Estado editado: de "+anteriorEstado+" a "+nuevoEstado,HttpStatus.ACCEPTED);
    }
}
