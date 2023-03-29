package com.foodieparty.fodieParty.services;

import com.foodieparty.fodieParty.dtos.BebidaPedidoDTO;

import java.util.List;
import java.util.Optional;

public interface BebidaPedidoServicio {
    List<BebidaPedidoDTO> getBebidaPedidos();
    Optional<BebidaPedidoDTO> getBebidaPedidoPorId( Long id);
}
