package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.BebidaPedidoDTO;
import com.foodieparty.fodieParty.repositories.BebidaPedidoRepositorio;
import com.foodieparty.fodieParty.services.BebidaPedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BebidaPedidoServicioImpl implements BebidaPedidoServicio {
    @Autowired
    private BebidaPedidoRepositorio bebidaPedidoRepositorio;
    @Override
    public List<BebidaPedidoDTO> getBebidaPedidos(){
        return bebidaPedidoRepositorio.findAll().stream().map(bp->new BebidaPedidoDTO(bp)).collect(Collectors.toList());
    }
    @Override
    public Optional<BebidaPedidoDTO> getBebidaPedidoPorId( Long id){
        return  bebidaPedidoRepositorio.findById(id).map(bp->new BebidaPedidoDTO(bp));
    }
}
