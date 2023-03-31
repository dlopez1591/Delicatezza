package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.CapacidadDTO;
import com.foodieparty.fodieParty.repositories.CapacidadRepositorio;
import com.foodieparty.fodieParty.services.CapacidadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CapacidadServicioImpl implements CapacidadServicio {
    @Autowired
    private CapacidadRepositorio capacidadRepositorio;
    @Override
    public ResponseEntity<Object> editarPrecioReserva(Double nuevoPrecioReserva) {
        capacidadRepositorio.findAll().get(0).setPrecioPorReserva(nuevoPrecioReserva);
        return new ResponseEntity<>("nuevo precio por reserva: $"+nuevoPrecioReserva, HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<Object> editarCapacidad(byte nuevaCapacidad) {
        capacidadRepositorio.findAll().get(0).setCapacidad(nuevaCapacidad);
        return new ResponseEntity<>("nuevo capacidad: "+nuevaCapacidad,HttpStatus.ACCEPTED);
    }

    @Override
    public CapacidadDTO getCapacidad() {
        return new CapacidadDTO(capacidadRepositorio.findAll().get(0));
    }
}
