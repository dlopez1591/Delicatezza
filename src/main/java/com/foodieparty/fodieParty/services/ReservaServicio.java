package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.ReservaDTO;
import com.foodieparty.fodieParty.models.Reserva;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface ReservaServicio {
    List<ReservaDTO> getReserva();
    Optional<ReservaDTO> getReservaPorId( Long id);
    List<ReservaDTO> getReservasUsuario(Authentication authentication);
    ResponseEntity<Object> crearReserva(
            Authentication authentication,
             Integer cantidadPersonas,
             String fechaString
    );
    void save(Reserva reserva);
}
