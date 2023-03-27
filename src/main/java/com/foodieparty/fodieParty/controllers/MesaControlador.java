package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.MesaDTO;
import com.foodieparty.fodieParty.repositories.MesaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class MesaControlador {
    @Autowired
    private MesaRepositorio mesaRepositorio;

    @GetMapping("/Mesas")
    public List<MesaDTO> getMesas(){
        return mesaRepositorio.findAll().stream().map(MesaDTO::new).collect(toList());
    }
    @GetMapping("/Mesa/{id}")
    public Optional<MesaDTO> getMesaPorId(@PathVariable Long id){
        return mesaRepositorio.findById(id).map(MesaDTO::new);
    }
}
