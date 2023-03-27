package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.PedidoDTO;
import com.foodieparty.fodieParty.dtos.ReservaDTO;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.PedidoRepositorio;

import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class PedidoControlador {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/pedidos")
    public List<PedidoDTO> getPedido(){
        return pedidoRepositorio.findAll().stream().map(PedidoDTO::new).collect(toList());
    }
    @GetMapping("/pedido/{id}")
    public Optional<PedidoDTO> getPedidoPorId(@PathVariable Long id){
        return pedidoRepositorio.findById(id).map(PedidoDTO::new);
    }
    @GetMapping("/usuario/autenticado/pedido")
    public List<PedidoDTO> getPedidosUsuario(Authentication authentication){
         Usuario usuario=usuarioRepositorio.findByEmail(authentication.name());
          return usuario.getPedidos().stream().map(PedidoDTO::new).collect(toList());
    }
//    @PostMapping("/crear/pedido/usuario")
//    public ResponseEntity<Object> crearPedido(Authentication authentication){}
}
