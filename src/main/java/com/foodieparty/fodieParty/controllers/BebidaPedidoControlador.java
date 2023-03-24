package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.BebidaDTO;
import com.foodieparty.fodieParty.dtos.BebidaPedidoDTO;
import com.foodieparty.fodieParty.repositories.BebidaPedidoRepositorio;
import com.foodieparty.fodieParty.repositories.BebidaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BebidaPedidoControlador {
    @Autowired
    private BebidaPedidoRepositorio bebidaPedidoRepositorio;

    @GetMapping("/bebidaPedidos")
    public List<BebidaPedidoDTO> getBebidaPedidos(){
        return bebidaPedidoRepositorio.findAll().stream().map(bp->new BebidaPedidoDTO(bp)).collect(Collectors.toList());
    }

    @GetMapping("/bebidaPedidos/{id}")
    public Optional<BebidaPedidoDTO> getBebidaPedidoPorId(@PathVariable Long id){
        return  bebidaPedidoRepositorio.findById(id).map(bp->new BebidaPedidoDTO(bp));
    }

}