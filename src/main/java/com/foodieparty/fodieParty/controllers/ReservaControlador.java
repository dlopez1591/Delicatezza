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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

// verificar la respuesta de los controladores de reservas
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
//          Usuario usuario=usuarioRepositorio.findByEmail(authentication.getName());
//          return usuario.getReservas().stream().map(ReservaDTO::new).collect(toList());
            return reservaServicio.getReservasUsuario(authentication);
    }
    @Transactional
    @PostMapping("/crear/reserva")
    public ResponseEntity<Object> crearReserva(
            Authentication authentication,
            @RequestParam Integer cantidadPersonas,
            @RequestParam String fechaString
    ){

       /*
        preguntar return reservaServicio.crearReserva(authentication,cantidadPersonas,fechaString);
*/
//        Usuario usuario = usuarioRepositorio.findByEmail(authentication.getName());
//        LocalDate fecha = LocalDate.parse(fechaString);
//        Integer capacidadOcupada = 0;
//        Capacidad capacidad = capacidadRepositorio.findAll().get(0);
//        List<Reserva> reservaALaFecha = reservaRepositorio.findAll()
//                .stream()
//                .filter(r-> r.getFecha().getDayOfYear() == fecha.getDayOfYear())
//                .collect(toList());
//        for(Reserva reserva: reservaALaFecha){capacidadOcupada+=reserva.getCantidad_De_Personas();}
//        if(!capacidadRepositorio.findAll().get(0).tieneCapacidad(capacidadOcupada,cantidadPersonas)){
//            return new ResponseEntity<>("No hay capacidad disponible",HttpStatus.FORBIDDEN);
//        }
//
//        Reserva reserva = new Reserva(fecha,cantidadPersonas.byteValue(),true);
//        TicketReserva ticketReserva = new TicketReserva(
//          "fecha: "+fecha+", personas: "+cantidadPersonas, capacidad.getPrecioPorReserva()
//        );
//        reserva.agregarTicketReserva(ticketReserva);
//        usuario.agregarReserva(reserva);
//        ticketReservaRepositorio.save(ticketReserva);
//        reservaRepositorio.save(reserva);
//        usuarioRepositorio.save(usuario);
//        return new ResponseEntity<>("Reserva realizada con exito",HttpStatus.ACCEPTED);
    return reservaServicio.crearReserva(authentication,cantidadPersonas,fechaString);

    }

    // ojo nuevo metodo añadido para editar estado de reserva verificar postman antes de subirlo puede ser true or false
    @PutMapping("/reservas/{id}")

    public ResponseEntity<String> actualizarEstadoReserva(@PathVariable Long id) {
        return reservaServicio.actualizarEstadoReserva(id);
    }



}
