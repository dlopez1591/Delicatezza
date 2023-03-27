package com.foodieparty.fodieParty.controllers;


import com.foodieparty.fodieParty.dtos.UsuarioDTO;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class UsuarioControlador {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/usuario")
    public List<UsuarioDTO> getUsuario(){
        return usuarioRepositorio.findAll().stream().map(UsuarioDTO::new).collect(toList());
    }
    @GetMapping("/usuario/{id}")
    public Optional<UsuarioDTO> getUsuarioPorId(@PathVariable Long id){
        return usuarioRepositorio.findById(id).map(UsuarioDTO::new);
    }
    //@GetMapping("/usuario/autenticado/pedido")
    //public List<UsuarioDTO> getUsuarioAutenticado(Authentication authentication){
    //     Usuario usuario=reservaRepositorio.findByEmail(authentication.getName());
    //      return usuario;
    //}
    @PostMapping("/crear/usuario")
    public ResponseEntity<Object> registrarUsuario(@RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String email, @RequestParam String pasword,@RequestParam String telefono) {
            Usuario usuario=usuarioRepositorio.findByEmail(email);
            if (usuario!=null){
                return new ResponseEntity<>("ya existe una cuenta con ese email",HttpStatus.BAD_REQUEST);
            }
            if(nombre.isEmpty()){
                return new ResponseEntity<>("no se ingreso el nombre",HttpStatus.BAD_REQUEST);
            }
        if(apellido.isEmpty()){
            return new ResponseEntity<>("no se ingreso el apellido",HttpStatus.BAD_REQUEST);
        }if(email.isEmpty()){
            return new ResponseEntity<>("no se ingreso el email",HttpStatus.BAD_REQUEST);
        }if(pasword.isEmpty()){
            return new ResponseEntity<>("no se ingreso la contraseña",HttpStatus.BAD_REQUEST);
        }if(telefono.isEmpty()){
            return new ResponseEntity<>("no se ingreso el numero de telefono",HttpStatus.BAD_REQUEST);
        }
            Usuario nuevoUsuario=new Usuario(nombre,apellido,email,pasword,telefono);
            usuarioRepositorio.save(nuevoUsuario);
            return new ResponseEntity<>("Usuario creado correctamente", HttpStatus.CREATED);

    }
}
