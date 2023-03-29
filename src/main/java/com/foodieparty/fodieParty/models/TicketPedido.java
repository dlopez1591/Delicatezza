package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity
public class TicketPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ElementCollection
    private List<String> detalle;

    private Double total;
    @OneToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    public TicketPedido(){}

    public TicketPedido(List<String> detalle, Double total) {
        this.detalle = detalle;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public List<String> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<String> detalle) {
        this.detalle = detalle;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
