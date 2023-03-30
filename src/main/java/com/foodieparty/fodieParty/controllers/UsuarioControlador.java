package com.foodieparty.fodieParty.controllers;


import com.foodieparty.fodieParty.dtos.UsuarioDTO;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

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
    public ResponseEntity<Object> borrarUsuario(@RequestParam String email){
        usuarioRepositorio.deleteByEmail(email);
        return new ResponseEntity<>("Usuario borrado", HttpStatus.ACCEPTED);
    }

    //metodo para editar un usuario OJO REVISAR Y VERIFICAR SERVICIOS E IMPLEMENTACION:

    @PutMapping("/actualizar/usuario")
    public ResponseEntity<Object> actualizarUsuario(@RequestParam Long id, @RequestParam String nombre, @RequestParam String apellido,@RequestParam String email, @RequestParam String telefono) {
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findById(id);
        if(usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setTelefono(telefono);
            usuarioRepositorio.save(usuario);
            return new ResponseEntity<>("Usuario actualizado", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
    }



}
