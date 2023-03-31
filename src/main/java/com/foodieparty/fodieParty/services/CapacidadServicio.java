package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.CapacidadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface CapacidadServicio {
    ResponseEntity<Object> editarPrecioReserva(
            @RequestParam Double nuevoPrecioReserva
    );
    ResponseEntity<Object> editarCapacidad(
            @RequestParam byte nuevaCapacidad
    );
    CapacidadDTO getCapacidad();
}
