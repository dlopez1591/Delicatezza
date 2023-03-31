package com.foodieparty.fodieParty.controllers;

import com.foodieparty.fodieParty.dtos.ReservaDTO;
import com.foodieparty.fodieParty.dtos.TicketPedidoDTO;
import com.foodieparty.fodieParty.repositories.TicketPedidoRepositorio;
import com.foodieparty.fodieParty.services.TicketPedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TicketPedidoControlador {
    @Autowired
    private TicketPedidoServicio ticketPedidoServicio;

    @GetMapping("/ticketPedidos")
    public List<TicketPedidoDTO> getPedidos(){
          return ticketPedidoServicio.getPedidos();
//        return ticketPedidoRepositorio.findAll().stream().map(tp->new TicketPedidoDTO(tp)).collect(Collectors.toList());
    }


}
