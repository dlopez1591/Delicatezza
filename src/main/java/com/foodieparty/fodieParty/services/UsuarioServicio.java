package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {
    List<UsuarioDTO> getUsuario();
    Optional<UsuarioDTO> getUsuarioPorId( Long id);
    ResponseEntity<Object> registrarUsuario( String nombre,  String apellido,
                                             String email,  String pasword, String telefono);
}
