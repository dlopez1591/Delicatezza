package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.EstadoPedido;
import com.foodieparty.fodieParty.models.Pedido;
import com.foodieparty.fodieParty.models.TipoRetiro;

import java.time.LocalDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class PedidoDTO {
    private long id;
    private LocalDateTime localDateTime;
    private double precioTotal;
    private TipoRetiro tipoRetiro;
    private String direccion;
    private EstadoPedido estadoPedido;
    private Set<ComidaPedidoDTO> comidaPedido;
    public PedidoDTO(Pedido pedido){
        this.id= pedido.getId();
        this.localDateTime=pedido.getLocalDateTime();
        this.precioTotal= pedido.getPrecioTotal();
        this.tipoRetiro=pedido.getTipoRetiro();
        this.direccion= pedido.getDireccion();
        this.estadoPedido=pedido.getEstadoPedido();
        this.comidaPedido=pedido.getComidaPedido().stream().map(ComidaPedidoDTO::new).collect(toSet());
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public TipoRetiro getTipoRetiro() {
        return tipoRetiro;
    }

    public String getDireccion() {
        return direccion;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public Set<ComidaPedidoDTO> getComidaPedido() {
        return comidaPedido;
    }
}