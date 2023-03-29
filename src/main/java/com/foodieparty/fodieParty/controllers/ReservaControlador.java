package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.ReservaDTO;
import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.services.ReservaServicio;
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
    private ReservaServicio reservaServicio;

    @GetMapping("/reserva")
    public List<ReservaDTO> getReserva(){
        return reservaServicio.getReserva();
    }
    @GetMapping("/reservas/{id}")
    public Optional<ReservaDTO> getReservaPorId(@PathVariable Long id){
        return reservaServicio.getReservaPorId(id);
    }
    @GetMapping("/usuario/autenticado/reserva")
    public List<ReservaDTO> getReservasUsuario(Authentication authentication){
          return reservaServicio.getReservasUsuario(authentication);
    }
//    @PostMapping("/crear/reserva")
//    public ResponseEntity<Object> crearReserva(Authentication authentication,@RequestBody ReservaUsuarioDTO reservaUsuario){
//        Usuario usuario=usuarioRepositorio.findByEmail(authentication.name());
//        Mesa mesa=mesaRepositorio
//
//    }
}
