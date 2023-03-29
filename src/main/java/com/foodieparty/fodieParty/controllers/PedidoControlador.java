package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.PedidoDTO;
import com.foodieparty.fodieParty.models.Usuario;

import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.PedidosServicioImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class PedidoControlador {
    @Autowired
    private PedidosServicioImpl pedidosServicio;

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
//    @PostMapping("/crear/pedido/usuario")
//    public ResponseEntity<Object> crearPedido(Authentication authentication){}
}
