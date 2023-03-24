package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.BebidaPedido;


public class BebidaPedidoDTO {

    private long id;
    private int cantidad;
    private Double precioPorCantidad;
    private Bebida bebida;
    private Pedido pedido;

    public BebidaPedidoDTO(BebidaPedido bebidaPedido){
        this.id = bebidaPedido.getId();
        this.cantidad= bebidaPedido.getCantidad();
        this.precioPorCantidad= bebidaPedido.getPrecioPorCantidad();
        this.bebida=bebidaPedido.getBebida();
        this.pedido= bebidaPedido.getPedido();
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
}
