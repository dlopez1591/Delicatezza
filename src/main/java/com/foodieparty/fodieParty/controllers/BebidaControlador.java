package com.foodieparty.fodieParty.controllers;
import com.foodieparty.fodieParty.dtos.BebidaDTO;
import com.foodieparty.fodieParty.dtos.ListaBebidasDTO;
import com.foodieparty.fodieParty.models.TipoBebida;
import com.foodieparty.fodieParty.repositories.BebidaRepositorio;
import com.foodieparty.fodieParty.services.BebidaServicio;
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
public class BebidaControlador {
    @Autowired
    private BebidaServicio bebidaServicio;

    @Autowired
    private BebidaRepositorio bebidaRepositorio;


    @GetMapping("/bebidas")
    public List<BebidaDTO> getBebidas(){
        return bebidaServicio.getBebidas();
    }

    @GetMapping("/bebidas/{id}")
    public Optional<BebidaDTO> getBebidaPorId(@PathVariable Long id){
            return  bebidaServicio.getBebidaPorId(id);
    }

    @PostMapping("/crear/bebida")
    public ResponseEntity<Object> crearBebida(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("tipoBebida")TipoBebida tipoBebida,
            @RequestParam("disponibilidad") int disponibilidad,
            @RequestParam("precio") Double precio
            ){return bebidaServicio.crearBebida(nombre,descripcion,imagen,tipoBebida,disponibilidad,precio);
    }
    @GetMapping("/bebida/stock")
    public Boolean stockListaBebidas(
            @RequestParam long id,
            @RequestParam long cant
            ){
            return bebidaRepositorio.findById(id).get().tieneStock(cant);
    }
}
