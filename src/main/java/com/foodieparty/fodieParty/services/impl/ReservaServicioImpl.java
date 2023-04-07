package com.foodieparty.fodieParty.services.impl;

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

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
 public class ReservaServicioImpl implements ReservaServicio {
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private TicketReservaRepositorio ticketReservaRepositorio;
    @Autowired
    private CapacidadRepositorio capacidadRepositorio;
    @Override
    public List<ReservaDTO> getReserva() {
        return reservaRepositorio.findAll().stream().map(ReservaDTO::new).collect(toList());
    }

    @Override
    public Optional<ReservaDTO> getReservaPorId(Long id) {
        return reservaRepositorio.findById(id).map(ReservaDTO::new);
    }

    @Override
    public List<ReservaDTO> getReservasUsuario(Authentication authentication) {
        Usuario usuario=usuarioRepositorio.findByEmail(authentication.getName());
        return usuario.getReserva().stream().map(ReservaDTO::new).collect(toList());
    }

    @Override
    public ResponseEntity<Object> crearReserva(Authentication authentication, Integer cantidadPersonas, String fechaString) {
        Usuario usuario = usuarioRepositorio.findByEmail(authentication.getName());
        LocalDate fecha = LocalDate.parse(fechaString);
        if (fecha.isBefore(LocalDate.now())||fecha.equals(LocalDate.now())){
            return new ResponseEntity<>("las reservas se realizan con 1 dia de anticipacion",HttpStatus.FORBIDDEN);
        }
        Integer capacidadOcupada = 0;
        Capacidad capacidad = capacidadRepositorio.findAll().get(0);
        List<Reserva> reservaALaFecha = reservaRepositorio.findAll()
                .stream()
                .filter(r-> r.getFecha().getDayOfYear() == fecha.getDayOfYear())
                .collect(toList());
        for(Reserva reserva: reservaALaFecha){capacidadOcupada+=reserva.getCantidad_De_Personas();}
        if(!capacidadRepositorio.findAll().get(0).tieneCapacidad(capacidadOcupada,cantidadPersonas)){
            return new ResponseEntity<>("No hay capacidad disponible", HttpStatus.FORBIDDEN);
        }

        Reserva reserva = new Reserva(fecha,cantidadPersonas.byteValue(),true);
        TicketReserva ticketReserva = new TicketReserva(
                "fecha: "+fecha+", personas: "+cantidadPersonas, capacidad.getPrecioPorReserva()
        );

        reserva.agregarTicketReserva(ticketReserva);
        usuario.agregarReserva(reserva);
        ticketReservaRepositorio.save(ticketReserva);
        reservaRepositorio.save(reserva);
        return new ResponseEntity<>("Reserva realizada con exito",HttpStatus.ACCEPTED);

    }

    @Override
    public void save(Reserva reserva) {
        reservaRepositorio.save(reserva);
    }

    @Override
    public ResponseEntity<String> actualizarEstadoReserva(Long id) {
        Optional<Reserva> optionalReserva = reservaRepositorio.findById(id);

        if (optionalReserva.isPresent()) {
            Reserva reserva = optionalReserva.get();
            reserva.setEstado(!reserva.getEstado());
            reservaRepositorio.save(reserva);
            return new ResponseEntity<>("Estado de reserva actualizado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la reserva con id " + id, HttpStatus.NOT_FOUND);
        }
    }
}
