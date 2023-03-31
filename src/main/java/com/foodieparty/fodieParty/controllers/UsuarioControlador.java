package com.foodieparty.fodieParty.controllers;


import com.foodieparty.fodieParty.dtos.UsuarioDTO;

import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.PedidoRepositorio;
import com.foodieparty.fodieParty.repositories.ReservaRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/usuario")
    public List<UsuarioDTO> getUsuario(){
        return usuarioServicio.getUsuario();
    }


    @GetMapping("/usuario/{id}")
    public Optional<UsuarioDTO> getUsuarioPorId(@PathVariable Long id){
        return usuarioServicio.getUsuarioPorId(id);
    }


    @GetMapping("/usuario/autenticado")
    public UsuarioDTO getUsuarioAutenticado(Authentication authentication){
         return new UsuarioDTO(usuarioRepositorio.findByEmail(authentication.getName()));
    }


    @PostMapping("/crear/usuario")
    public ResponseEntity<Object> registrarUsuario(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam String password,@RequestParam String telefono) {
            return usuarioServicio.registrarUsuario(nombre,apellido,email,passwordEncoder.encode(password),telefono);

    }


    @PatchMapping("/borrar/usuario")
    public ResponseEntity<Object> borrarUsuario(@RequestParam long id) {
        usuarioServicio.borrarUsuario(id);
        return new ResponseEntity<>("Usuario borrado", HttpStatus.ACCEPTED);
    }

    @PutMapping("/actualizar/usuario")
    public ResponseEntity<Object> actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.actualizarUsuario(usuario);
    }


}
