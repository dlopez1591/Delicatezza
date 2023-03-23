package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDateTime localDateTime;
    private double precioTotal;
    private TipoRetiro tipoRetiro;
    private String direccion;
    private EstadoPedido estadoPedido;
    @OneToMany(mappedBy = "comida",fetch = FetchType.EAGER)
    private Set<ComidaPedido> comidaPedido=new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    public Pedido(){
    }

    public Pedido(LocalDateTime localDateTime, double precioTotal, TipoRetiro tipoRetiro, String direccion, EstadoPedido estadoPedido) {
        this.localDateTime = localDateTime;
        this.precioTotal = precioTotal;
        this.tipoRetiro = tipoRetiro;
        this.direccion = direccion;
        this.estadoPedido = estadoPedido;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public TipoRetiro getTipoRetiro() {
        return tipoRetiro;
    }

    public void setTipoRetiro(TipoRetiro tipoRetiro) {
        this.tipoRetiro = tipoRetiro;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Set<ComidaPedido> getComidaPedido() {
        return comidaPedido;
    }

    public void setComidaPedido(Set<ComidaPedido> comidaPedido) {
        this.comidaPedido = comidaPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void agregarComidaPedido(ComidaPedido comidaPedido1){
        comidaPedido1.setPedido(this);
        comidaPedido.add(comidaPedido1);
    }
}
