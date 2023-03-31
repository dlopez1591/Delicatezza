package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.ReservaDTO;
import com.foodieparty.fodieParty.models.Capacidad;
import com.foodieparty.fodieParty.models.Reserva;
import com.foodieparty.fodieParty.models.TicketReserva;
import com.foodieparty.fodieParty.models.Usuario;

import com.foodieparty.fodieParty.repositories.CapacidadRepositorio;
import com.foodieparty.fodieParty.repositories.ReservaRepositorio;
import com.foodieparty.fodieParty.repositories.TicketReservaRepositorio;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;

import com.foodieparty.fodieParty.services.ReservaServicio;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @Autowired
    private CapacidadRepositorio capacidadRepositorio;

    @Autowired
    private TicketReservaRepositorio ticketReservaRepositorio;

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
    @Transactional
    @PostMapping("/crear/reserva")
    public ResponseEntity<Object> crearReserva(
            Authentication authentication,
            @RequestParam Integer cantidadPersonas,
            @RequestParam String fechaString
    ){
        return reservaServicio.crearReserva(authentication,cantidadPersonas,fechaString);
    }
}
