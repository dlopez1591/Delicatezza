package com.foodieparty.fodieParty.controllers;


import com.foodieparty.fodieParty.dtos.UsuarioDTO;
import com.foodieparty.fodieParty.models.Estado;
import com.foodieparty.fodieParty.models.Pedido;
import com.foodieparty.fodieParty.models.Reserva;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.PedidoRepositorio;
import com.foodieparty.fodieParty.repositories.ReservaRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/api")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @GetMapping("/usuario")
    public List<UsuarioDTO> getUsuario(){
        return usuarioServicio.getUsuario();
    }
    @GetMapping("/usuario/{id}")
    public Optional<UsuarioDTO> getUsuarioPorId(@PathVariable Long id){
        return usuarioServicio.getUsuarioPorId(id);
    }
    //@GetMapping("/usuario/autenticado/pedido")
    //public List<UsuarioDTO> getUsuarioAutenticado(Authentication authentication){
    //     Usuario usuario=reservaRepositorio.findByEmail(authentication.getName());
    //      return usuario;
    //}
    @PostMapping("/crear/usuario")
    public ResponseEntity<Object> registrarUsuario(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam String password,@RequestParam String telefono) {
            return usuarioServicio.registrarUsuario(nombre,apellido,email,password,telefono);

    }

    @PostMapping("/borrar/usuario")
    public ResponseEntity<Object> borrarUsuario(@RequestParam long id){
        usuarioServicio.borrarUsuario(id);
        return new ResponseEntity<>("Usuario borrado", HttpStatus.ACCEPTED);
    }
}
