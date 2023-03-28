package com.foodieparty.fodieParty.controllers;
import com.foodieparty.fodieParty.dtos.CapacidadDTO;
import com.foodieparty.fodieParty.repositories.CapacidadRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CapacidadControlador {
    @Autowired
    private CapacidadRepositorio capacidadRepositorio;

    @PostMapping("/capacidad/editarPrecioReserva")
    public ResponseEntity<Object> editarPrecioReserva(
            @RequestParam Double nuevoPrecioReserva
    ){
        capacidadRepositorio.findAll().get(0).setPrecioPorReserva(nuevoPrecioReserva);
        return new ResponseEntity<>("nuevo precio por reserva: $"+nuevoPrecioReserva,HttpStatus.ACCEPTED);
    }

    @PostMapping("/capacidad/editarCapacidad")
    public ResponseEntity<Object> editarCapacidad(
            @RequestParam byte nuevaCapacidad
    ){
        capacidadRepositorio.findAll().get(0).setCapacidad(nuevaCapacidad);
        return new ResponseEntity<>("nuevo capacidad: "+nuevaCapacidad,HttpStatus.ACCEPTED);
    }

    @GetMapping("/capacidad")
    public CapacidadDTO getCapacidad(){
        return new CapacidadDTO(capacidadRepositorio.findAll().get(0));
    }

}
