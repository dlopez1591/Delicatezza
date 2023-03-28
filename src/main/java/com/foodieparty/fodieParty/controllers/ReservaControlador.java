package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.ReservaDTO;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.ReservaRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ReservaControlador {
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/reserva")
    public List<ReservaDTO> getReserva(){
        return reservaRepositorio.findAll().stream().map(ReservaDTO::new).collect(toList());
    }
    @GetMapping("/reservas/{id}")
    public Optional<ReservaDTO> getReservaPorId(@PathVariable Long id){
        return reservaRepositorio.findById(id).map(ReservaDTO::new);
    }
    @GetMapping("/usuario/autenticado/reserva")
    public List<ReservaDTO> getReservasUsuario(Authentication authentication){
          Usuario usuario=usuarioRepositorio.findByEmail(authentication.name());
          return usuario.getReservas().stream().map(ReservaDTO::new).collect(toList());
    }
//    @PostMapping("/crear/reserva")
//    public ResponseEntity<Object> crearReserva(Authentication authentication,@RequestBody ReservaUsuarioDTO reservaUsuario){
//        Usuario usuario=usuarioRepositorio.findByEmail(authentication.name());
//        Mesa mesa=mesaRepositorio
//
//    }
}
