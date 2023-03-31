package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.UsuarioDTO;
import com.foodieparty.fodieParty.models.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {
    List<UsuarioDTO> getUsuario();
    Optional<UsuarioDTO> getUsuarioPorId( Long id);
    ResponseEntity<Object> registrarUsuario( String nombre,  String apellido,
                                             String email,  String pasword, String telefono);
    Usuario findByEmail(String email);
    void save(Usuario usuario);
    ResponseEntity<Object> borrarUsuario(long id);
    UsuarioDTO getUsuarioAutenticado(Authentication authentication);
    ResponseEntity<Object> actualizarUsuario(@RequestBody Usuario usuario);

}
