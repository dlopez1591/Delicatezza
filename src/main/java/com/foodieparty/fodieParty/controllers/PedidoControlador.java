package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.Utilidades.FilaTabla;
import com.foodieparty.fodieParty.Utilidades.GenerardorPdf;
import com.foodieparty.fodieParty.Utilidades.HeaderFooterPageEvent;
import com.foodieparty.fodieParty.dtos.DetallePedidoDTO;
import com.foodieparty.fodieParty.dtos.PedidoDTO;

import com.foodieparty.fodieParty.models.*;
import com.foodieparty.fodieParty.repositories.*;

import com.foodieparty.fodieParty.services.PedidosServicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;


import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class PedidoControlador {
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
    @Autowired
    private PedidosServicio pedidosServicio;

        @GetMapping("/pedidos")
    public List<PedidoDTO> getPedidos(){
        return pedidosServicio.getPedidos();
    }

//    @GetMapping("/pedidos")
//    public List<PedidoDTO> getPedidos(){
//        return pedidoRepositorio.findAll().stream().map(PedidoDTO::new).collect(toList());
//    }

    @GetMapping("/pedido/{id}")
    public Optional<PedidoDTO> getPedidoPorId(@PathVariable Long id){
        return pedidosServicio.getPedidoPorId(id);
    }
    @GetMapping("/usuario/autenticado/pedido")
    public List<PedidoDTO> getPedidosUsuario(Authentication authentication){
         return pedidosServicio.getPedidosUsuario(authentication);
    }

    @PostMapping("/crear/pedido/usuario")
    public ResponseEntity<Object> crearPedido(
            @RequestBody DetallePedidoDTO detallePedidoDTO,
            Authentication authentication) throws IOException, DocumentException {
            return pedidosServicio.crearPedido(detallePedidoDTO,authentication);
       // Usuario usuario = usuarioRepositorio.findByEmail(authentication.getName());
        //detallePedidoDTO:
        //  listaComidasId ----> [0]=idComida, [1]=cantidadSolicitada.
        //  listaBebidasId ----> [0]=idBebida, [1]=cantidadSolicitada.

        //Crear un nuevo pedido.
       // Pedido pedido = new Pedido(
           //     detallePedidoDTO.getTipoRetiro(),
          //      detallePedidoDTO.getDireccion(),
         //       usuario);
        //Preparar variables para contabilizar el total y concatenar detalles del ticket.
        //List<FilaTabla> filaTablas = new ArrayList<>();
        //List<String> detalleTicket=new ArrayList<>();
        //Double total = 0.0;
        //Recorrer la lista idComidaYCantidad
        //Comida comida;
        //ComidaPedido comidaPedido;
        //for(long[] idComidaYCantidad: detallePedidoDTO.getListaComidasId()){
            //comida = comidaRepositorio.findById(idComidaYCantidad[0]).get();
            //comidaPedido = new ComidaPedido((int)idComidaYCantidad[1],comida.getPrecio());
            //comida.agregarComidaPedido(comidaPedido);
           // pedido.agregarComidaPedido(comidaPedido);
            //comidaPedidoRepositorio.save(comidaPedido);
            //total+=comidaPedido.getPrecioPorCantidad();
           // detalleTicket.add(comida.getNombre()+" x "+idComidaYCantidad[1]+" = $"+comida.getPrecio()*idComidaYCantidad[1]);
           // FilaTabla fila = new FilaTabla(
             //       String.valueOf(idComidaYCantidad[1]),
            //        comida.getNombre(),
            //        String.valueOf(comida.getPrecio()),
           //         String.valueOf(comidaPedido.getPrecioPorCantidad()));
          //  filaTablas.add(fila);
        //}

        //Recorrer la lista idBebidaYCantidad
        //Bebida bebida;
        //BebidaPedido bebidaPedido;
        //for(long[] idBebidaYCantidad: detallePedidoDTO.getListaBebidasId()){
            //bebida = bebidaRepositorio.findById(idBebidaYCantidad[0]).get();
            //bebidaPedido = new BebidaPedido((int)idBebidaYCantidad[1],bebida.getPrecio());
            //Si hay stock suficiente de bebida, reducir stock, sino error.
            //if(bebida.tieneStock(idBebidaYCantidad[1])){
             //   bebida.reducirStock(idBebidaYCantidad[1]);
            //}else{
             //   return new ResponseEntity<>("Stock insuficiente de "+bebida.getNombre(),HttpStatus.FORBIDDEN);
           // }
            //bebida.agregarBebidaPedido(bebidaPedido);
           // pedido.agregarBebidaPedido(bebidaPedido);
           // bebidaPedidoRepositorio.save(bebidaPedido);
          //  total+=bebidaPedido.getPrecioPorCantidad();
            //detalleTicket.add(bebida.getNombre()+" x "+idBebidaYCantidad[1]+" = $"+bebida.getPrecio()*idBebidaYCantidad[1]);
           // FilaTabla fila = new FilaTabla(
                  //  String.valueOf(idBebidaYCantidad[1]),
                //    bebida.getNombre(),
              //      String.valueOf(bebida.getPrecio()),
            //        String.valueOf(bebidaPedido.getPrecioPorCantidad()));
          //  filaTablas.add(fila);
        //}

        //Crear un ticket para el pedido
        //TicketPedido ticketPedido = new TicketPedido(detalleTicket,total);
        //pedido.agregarTicketPedido(ticketPedido);
        //pedido.setPrecioTotal(total);
        //usuario.agregarPedido(pedido);
        //ticketPedidoRepositorio.save(ticketPedido);
        //pedidoRepositorio.save(pedido);
        //pedido.getComidaPedidos().forEach(c-> System.out.println(c.getComida().getNombre()));

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

        //GenerardorPdf generardorPdf = new GenerardorPdf();
      //  generardorPdf.generarPdf(filaTablas,String.valueOf(total));

    //    return new ResponseEntity<>("Pedido exitoso", HttpStatus.CREATED);
    }

    @PostMapping("/pedido/editarEstado")
    public ResponseEntity<Object> editarEstado(
            @RequestParam long id,
            @RequestParam EstadoPedido nuevoEstado
    ){
            return pedidosServicio.editarEstado(id,nuevoEstado);
//        Pedido pedido = pedidoRepositorio.findById(id).orElse(null);
//        if(pedido==null){
//            return new ResponseEntity<>("El pedido no existe",HttpStatus.FORBIDDEN);
//        }
//        EstadoPedido anteriorEstado = pedido.getEstadoPedido();
//        pedido.setEstadoPedido(nuevoEstado);
//        pedidoRepositorio.save(pedido);
//        return new ResponseEntity<>("Estado editado: de "+anteriorEstado+" a "+nuevoEstado,HttpStatus.ACCEPTED);
    }




}
