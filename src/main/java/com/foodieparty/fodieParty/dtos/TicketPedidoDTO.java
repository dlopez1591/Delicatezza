package com.foodieparty.fodieParty.dtos;
import com.foodieparty.fodieParty.models.TicketPedido;
import java.util.List;

public class TicketPedidoDTO {

    private long id;
    private List<String> detalle;
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

    public List<String> getDetalle() {
        return detalle;
    }

    public Double getTotal() {
        return total;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }
}
