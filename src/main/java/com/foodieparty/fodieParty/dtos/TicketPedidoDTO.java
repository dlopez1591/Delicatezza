package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Pedido;
import com.foodieparty.fodieParty.models.TicketPedido;

import javax.persistence.OneToOne;
import java.util.List;

public class TicketPedidoDTO {

    private long id;
    private String detalle;
    private Double total;

    private PedidoDTO pedido;

    public TicketPedidoDTO(TicketPedido ticketPedido){
        this.id = ticketPedido.getId();
        this.detalle = ticketPedido.getDetalle();
        this.total = ticketPedido.getTotal();
        this.pedido = new PedidoDTO(ticketPedido.getPedido());
    };

    public long getId() {
        return id;
    }

    public String getDetalle() {
        return detalle;
    }

    public Double getTotal() {
        return total;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }
}
