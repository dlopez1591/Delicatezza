package com.foodieparty.fodieParty.dtos;


import com.foodieparty.fodieParty.models.Comida;
import com.foodieparty.fodieParty.models.ComidaPedido;
import com.foodieparty.fodieParty.models.Pedido;

public class ComidaPedidoDTO {
    private long id;
    private int cantidad;
    private Double precioPorCantidad;

    public ComidaPedidoDTO(ComidaPedido comidaPedido){
        this.id=comidaPedido.getId();
        this.cantidad=comidaPedido.getCantidad();
        this.precioPorCantidad= comidaPedido.getPrecioPorCantidad();
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
}
