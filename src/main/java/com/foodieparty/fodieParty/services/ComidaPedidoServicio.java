package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.ComidaPedidoDTO;
import com.foodieparty.fodieParty.models.ComidaPedido;


import java.util.List;
import java.util.Optional;

public interface ComidaPedidoServicio {
    List<ComidaPedidoDTO> getComidaPedidos();
    Optional<ComidaPedidoDTO> getComidaPedidoPorId(Long id);
    void save(ComidaPedido comidaPedido);
}
