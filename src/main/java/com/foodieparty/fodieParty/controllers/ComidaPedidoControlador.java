package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.ComidaPedidoDTO;
import com.foodieparty.fodieParty.services.ComidaPedidoServicio;
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
public class ComidaPedidoControlador {
    @Autowired
    private ComidaPedidoServicio comidaPedidoServicio;

    @GetMapping("/comidaPedidos")
    public List<ComidaPedidoDTO> getComidaPedidos(){
        return comidaPedidoServicio.getComidaPedidos();
    }

    @GetMapping("/comidaPedidos/{id}")
    public Optional<ComidaPedidoDTO> getComidaPedidoPorId(@PathVariable Long id){
        return comidaPedidoServicio.getComidaPedidoPorId(id);

    }
}