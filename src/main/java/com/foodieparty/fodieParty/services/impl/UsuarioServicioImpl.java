package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.UsuarioDTO;
import com.foodieparty.fodieParty.models.Estado;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import com.foodieparty.fodieParty.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    public List<UsuarioDTO> getUsuario() {
        return usuarioRepositorio.findAll().stream().map(UsuarioDTO::new).collect(toList());
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    @Override
    public ResponseEntity<Object> borrarUsuario(long id) {
        Usuario usuario=usuarioRepositorio.findById(id).orElse(null);
        usuario.setEstado(Estado.DESACTIVADA);
        usuarioRepositorio.save(usuario);
        return new ResponseEntity<>("Usuario borrado", HttpStatus.ACCEPTED);

    }

    @Override
    public Optional<UsuarioDTO> getUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id).map(UsuarioDTO::new);
    }

    @Override
    public ResponseEntity<Object> registrarUsuario(String nombre, String apellido, String email, String pasword, String telefono) {
        Usuario usuario=usuarioRepositorio.findByEmail(email);
        if (usuario!=null){
            return new ResponseEntity<>("ya existe una cuenta con ese email", HttpStatus.BAD_REQUEST);
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
