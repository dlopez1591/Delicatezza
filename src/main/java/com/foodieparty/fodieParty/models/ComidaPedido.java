package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ComidaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private int cantidad;
    private Double precioPorCantidad;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comida_id")
    private Comida comida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    public ComidaPedido(){ }

    public ComidaPedido(int cantidad, Double precioUnitario) {
        this.cantidad = cantidad;
        this.precioPorCantidad = precioUnitario*cantidad;
    }

    public long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioPorCantidad() {
        return precioPorCantidad;
    }

    public void setPrecioPorCantidad(Double precioPorCantidad) {
        this.precioPorCantidad = precioPorCantidad;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
