package com.foodieparty.fodieParty.dtos;


import com.foodieparty.fodieParty.models.Comida;
import com.foodieparty.fodieParty.models.ComidaPedido;
import com.foodieparty.fodieParty.models.Pedido;

public class ComidaPedidoDTO {
    private long id;
    private int cantidad;
    private Double precioPorCantidad;

    private String comida;
    private long comida_id;

    public ComidaPedidoDTO(ComidaPedido comidaPedido){
        this.id=comidaPedido.getId();
        this.cantidad=comidaPedido.getCantidad();
        this.precioPorCantidad= comidaPedido.getPrecioPorCantidad();
        this.comida = comidaPedido.getComida().getNombre();
        this.comida_id= comidaPedido.getComida().getId();
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

    public String getComida() {
        return comida;
    }

    public long getComida_id() {
        return comida_id;
    }

}
