package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.BebidaPedido;
import com.foodieparty.fodieParty.models.Pedido;


public class BebidaPedidoDTO {

    private long id;
    private int cantidad;
    private Double precioPorCantidad;
    private String bebida;
    private long pedido_id;

    public BebidaPedidoDTO(BebidaPedido bebidaPedido){
        this.id = bebidaPedido.getId();
        this.cantidad= bebidaPedido.getCantidad();
        this.precioPorCantidad= bebidaPedido.getPrecioPorCantidad();
        this.bebida=bebidaPedido.getBebida().getNombre();
        this.pedido_id= bebidaPedido.getPedido().getId();
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

    public String getBebida() {
        return bebida;
    }

    public long getPedido_id() {
        return pedido_id;
    }
}
