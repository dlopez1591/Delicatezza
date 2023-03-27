package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.BebidaPedidoDTO;
import com.foodieparty.fodieParty.dtos.ComidaPedidoDTO;
import com.foodieparty.fodieParty.repositories.BebidaPedidoRepositorio;
import com.foodieparty.fodieParty.repositories.ComidaPedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ComidaPedidoControlador {
    @Autowired
    private ComidaPedidoRepositorio comidaPedidoRepositorio;

    @GetMapping("/comidaPedidos")
    public List<ComidaPedidoDTO> getComidaPedidos(){
        return comidaPedidoRepositorio.findAll().stream().map(cp->new ComidaPedidoDTO(cp)).collect(Collectors.toList());
    }

    @GetMapping("/comidaPedidos/{id}")
    public ComidaPedidoDTO getComidaPedidoPorId(@PathVariable Long id){
        try{
            return new ComidaPedidoDTO(comidaPedidoRepositorio.findById(id).orElse(null));
        }catch(NullPointerException exc){
            return null;
        }
    }
}