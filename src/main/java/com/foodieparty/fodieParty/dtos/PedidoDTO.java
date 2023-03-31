package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class PedidoDTO {
    private long id;
    private LocalDateTime localDateTime;
    private double precioTotal;
    private TipoRetiro tipoRetiro;
    private String direccion;
    private EstadoPedido estadoPedido;
    private Set<ComidaPedidoDTO> comidaPedidos;
    private Set<BebidaPedidoDTO> bebidaPedidos;

    private List<String> nombreComidas;
    private long ticketPedido_id;
    public PedidoDTO(Pedido pedido){
        this.id= pedido.getId();
        this.localDateTime=pedido.getFecha();
        this.precioTotal= pedido.getPrecioTotal();
        this.tipoRetiro=pedido.getTipoRetiro();
        this.direccion= pedido.getDireccion();
        this.estadoPedido=pedido.getEstadoPedido();
        this.comidaPedidos=pedido.getComidaPedidos().stream().map(ComidaPedidoDTO::new).collect(Collectors.toSet());
        this.bebidaPedidos=pedido.getBebidaPedidos().stream().map(BebidaPedidoDTO::new).collect(Collectors.toSet());
        this.ticketPedido_id=pedido.getTicketPedido().getId();
        this.nombreComidas=pedido.getComidaPedidos().stream().map(c->c.getComida().getNombre()).collect(Collectors.toList());

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

    public Set<ComidaPedidoDTO> getComidaPedidos() {
        return comidaPedidos;
    }

    public Set<BebidaPedidoDTO> getBebidaPedidos() {
        return bebidaPedidos;
    }

    public long getTicketPedido_id() {
        return ticketPedido_id;
    }

    public List<String> getNombreComidas() {
        return nombreComidas;
    }
}
