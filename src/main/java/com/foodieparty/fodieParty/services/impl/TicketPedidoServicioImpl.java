package com.foodieparty.fodieParty.services.impl;

import com.foodieparty.fodieParty.dtos.TicketPedidoDTO;
import com.foodieparty.fodieParty.repositories.TicketPedidoRepositorio;
import com.foodieparty.fodieParty.services.TicketPedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketPedidoServicioImpl implements TicketPedidoServicio {
    @Autowired
    private TicketPedidoRepositorio ticketPedidoRepositorio;

    @Override
    public List<TicketPedidoDTO> getPedidos() {
        return ticketPedidoRepositorio.findAll().stream().map(tp->new TicketPedidoDTO(tp)).collect(Collectors.toList());
    }
}
