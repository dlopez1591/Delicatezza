package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.DetallePedidoDTO;
import com.foodieparty.fodieParty.dtos.PedidoDTO;

import com.foodieparty.fodieParty.models.*;
import com.foodieparty.fodieParty.repositories.*;

import com.foodieparty.fodieParty.services.PedidosServicio;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


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
    public List<PedidoDTO> getPedido(){
        return pedidosServicio.getPedido();
    }
    @GetMapping("/pedido/{id}")
    public Optional<PedidoDTO> getPedidoPorId(@PathVariable Long id){
        return pedidosServicio.getPedidoPorId(id);
    }
    @GetMapping("/usuario/autenticado/pedido")
    public List<PedidoDTO> getPedidosUsuario(Authentication authentication){
         return pedidosServicio.getPedidosUsuario(authentication);
    }

    @Transactional
    @PostMapping("/crear/pedido/usuario")
    public ResponseEntity<Object> crearPedido(
            @RequestBody DetallePedidoDTO detallePedidoDTO,
            Authentication authentication){
        Usuario usuario = usuarioRepositorio.findByEmail("pepe@pepe.com");
        //detallePedidoDTO:
        //  listaComidasId ----> [0]=idComida, [1]=cantidadSolicitada.
        //  listaBebidasId ----> [0]=idBebida, [1]=cantidadSolicitada.

        //Crear un nuevo pedido.
        Pedido pedido = new Pedido(
                detallePedidoDTO.getTipoRetiro(),
                detallePedidoDTO.getDireccion(),
                usuario);
        //Preparar variables para contabilizar el total y concatenar detalles del ticket.
        List<String> detalleTicket=new ArrayList<>();
        Double total = 0.0;
        //Recorrer la lista idComidaYCantidad
        for(long[] idComidaYCantidad: detallePedidoDTO.getListaComidasId()){
            Comida comida = comidaRepositorio.findById(idComidaYCantidad[0]).orElse(null);
            ComidaPedido comidaPedido = new ComidaPedido((int)idComidaYCantidad[1],comida.getPrecio());
            comida.agregarComidaPedido(comidaPedido);
            pedido.agregarComidaPedido(comidaPedido);
            comidaPedidoRepositorio.save(comidaPedido);
            total+=comidaPedido.getPrecioPorCantidad();
            detalleTicket.add(comida.getNombre()+" x "+idComidaYCantidad[1]+" = $"+comida.getPrecio()*idComidaYCantidad[1]);
        }
        //Recorrer la lista idBebidaYCantidad
        for(long[] idBebidaYCantidad: detallePedidoDTO.getListaBebidasId()){
            Bebida bebida = bebidaRepositorio.findById(idBebidaYCantidad[0]).orElse(null);
            BebidaPedido bebidaPedido = new BebidaPedido((int)idBebidaYCantidad[1],bebida.getPrecio());
            //Si hay stock suficiente de bebida, reducir stock, sino error.
            if(bebida.tieneStock(idBebidaYCantidad[1])){
                bebida.reducirStock(idBebidaYCantidad[1]);
            }else{
                return new ResponseEntity<>("Stock insuficiente de "+bebida.getNombre(),HttpStatus.FORBIDDEN);
            }
            bebida.agregarBebidaPedido(bebidaPedido);
            pedido.agregarBebidaPedido(bebidaPedido);
            bebidaPedidoRepositorio.save(bebidaPedido);
            total+=bebidaPedido.getPrecioPorCantidad();
            detalleTicket.add(bebida.getNombre()+" x "+idBebidaYCantidad[1]+" = $"+bebida.getPrecio()*idBebidaYCantidad[1]);
        }

        //Crear un ticket para el pedido
        TicketPedido ticketPedido = new TicketPedido(detalleTicket,total);

        pedido.agregarTicketPedido(ticketPedido);
        pedido.setPrecioTotal(total);
        usuario.agregarPedido(pedido);
        ticketPedidoRepositorio.save(ticketPedido);
        pedidoRepositorio.save(pedido);
        return new ResponseEntity<>("Pedido exitoso", HttpStatus.CREATED);
    }

    @PostMapping("/pedido/editarEstado")
    public ResponseEntity<Object> editarEstado(
            @RequestParam long id,
            @RequestParam EstadoPedido nuevoEstado
    ){
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
