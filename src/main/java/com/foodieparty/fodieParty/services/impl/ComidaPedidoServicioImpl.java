package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.ComidaPedidoDTO;
import com.foodieparty.fodieParty.repositories.ComidaPedidoRepositorio;
import com.foodieparty.fodieParty.services.ComidaPedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComidaPedidoServicioImpl implements ComidaPedidoServicio {
    @Autowired
    private ComidaPedidoRepositorio comidaPedidoRepositorio;
    @Override
    public List<ComidaPedidoDTO> getComidaPedidos() {
        return comidaPedidoRepositorio.findAll().stream().map(cp->new ComidaPedidoDTO(cp)).collect(Collectors.toList());

    }

    @Override
    public Optional<ComidaPedidoDTO> getComidaPedidoPorId(Long id) {
        return comidaPedidoRepositorio.findById(id).map(ComidaPedidoDTO::new);
    }
}
