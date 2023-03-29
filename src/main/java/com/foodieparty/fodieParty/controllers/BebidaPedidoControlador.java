package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.BebidaPedidoDTO;
import com.foodieparty.fodieParty.services.BebidaPedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BebidaPedidoControlador {
    @Autowired
    private BebidaPedidoServicio bebidaServicio;

    @GetMapping("/bebidaPedidos")
    public List<BebidaPedidoDTO> getBebidaPedidos(){
        return bebidaServicio.getBebidaPedidos();
    }
    @GetMapping("/bebidaPedidos/{id}")
    public Optional<BebidaPedidoDTO> getBebidaPedidoPorId(@PathVariable Long id){
        return bebidaServicio.getBebidaPedidoPorId(id);
    }

}