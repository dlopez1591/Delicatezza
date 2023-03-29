package com.foodieparty.fodieParty.controllers;
import com.foodieparty.fodieParty.dtos.ComidaDTO;
import com.foodieparty.fodieParty.models.TipoComida;
import com.foodieparty.fodieParty.services.ComidaServicio;
import com.foodieparty.fodieParty.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ComidaControlador {
    @Autowired
    private ComidaServicio comidaServicio;

    @GetMapping("/comidas")
    public List<ComidaDTO> getComidas(){
        return comidaServicio.getComidas();
    }

    @GetMapping("/comidas/{id}")
    public Optional<ComidaDTO> getComidaPorId(@PathVariable Long id){
        return  comidaServicio.getComidaPorId(id);
    }

    @PostMapping("/crear/comida")
    public ResponseEntity<Object> crearComida(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("tipoComida") TipoComida tipoComida,
            @RequestParam("disponibilidad") Boolean disponibilidad,
            @RequestParam("precio") Double precio
    ){
        return comidaServicio.crearComida(nombre,descripcion,imagen,tipoComida,disponibilidad,precio);
    }

}