package com.foodieparty.fodieParty.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class BebidaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,  generator = "native")
    @GenericGenerator(name="native", strategy="native")
    private long id;
    private int cantidad;
    private Double precioPorCantidad;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bebida_id")
    private Bebida bebida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pedido_id")
    private Pedido pedido;

    public BebidaPedido(){};

    public BebidaPedido(int cantidad, Double precioUnitario){
        this.cantidad=cantidad;
        this.precioPorCantidad= cantidad*precioUnitario;
    }

    public long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Double getPrecioPorCantidad() {
        return precioPorCantidad;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioPorCantidad(Double precioPorCantidad) {
        this.precioPorCantidad = precioPorCantidad;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
