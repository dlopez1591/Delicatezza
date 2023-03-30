package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.ReservaDTO;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ReservaServicio {
    List<ReservaDTO> getReserva();
    Optional<ReservaDTO> getReservaPorId( Long id);
    List<ReservaDTO> getReservasUsuario(Authentication authentication);
}
