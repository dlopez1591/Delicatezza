package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.BebidaPedidoDTO;
import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.BebidaPedido;

import java.util.List;
import java.util.Optional;

public interface BebidaPedidoServicio {
    List<BebidaPedidoDTO> getBebidaPedidos();
    Optional<BebidaPedidoDTO> getBebidaPedidoPorId( Long id);
    void save(BebidaPedido bebidaPedido);
}
