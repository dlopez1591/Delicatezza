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
    private LocalDateTime fecha;
    private double precioTotal;
    private TipoRetiro tipoRetiro;
    private String direccion;
    private EstadoPedido estadoPedido;
    @OneToMany(mappedBy = "comida",fetch = FetchType.EAGER)
    private Set<ComidaPedido> comidaPedidos=new HashSet<>();

    @OneToMany(mappedBy = "bebida",fetch = FetchType.EAGER)
    private Set<BebidaPedido> bebidaPedidos=new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @OneToOne(mappedBy="pedido")
    private TicketPedido ticketPedido;

    public Pedido(){
    }

    public Pedido(LocalDateTime fecha, double precioTotal, TipoRetiro tipoRetiro, String direccion, EstadoPedido estadoPedido) {
        this.fecha = fecha;
        this.precioTotal = precioTotal;
        this.tipoRetiro = tipoRetiro;
        this.direccion = direccion;
        this.estadoPedido = estadoPedido;
    }

    public Pedido(TipoRetiro tipoRetiro, String direccion, Usuario usuario) {
        this.fecha = LocalDateTime.now();
        this.tipoRetiro = tipoRetiro;
        this.direccion = direccion;
        this.usuario = usuario;
        this.estadoPedido=EstadoPedido.EN_PROGRESO;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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

    public Set<ComidaPedido> getComidaPedidos() {
        return comidaPedidos;
    }

    public void setComidaPedidos(Set<ComidaPedido> comidaPedido) {
        this.comidaPedidos = comidaPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<BebidaPedido> getBebidaPedidos() {
        return bebidaPedidos;
    }

    public void setBebidaPedidos(Set<BebidaPedido> bebidaPedidos) {
        this.bebidaPedidos = bebidaPedidos;
    }

    public TicketPedido getTicketPedido() {
        return ticketPedido;
    }

    public void setTicketPedido(TicketPedido ticketPedido) {
        this.ticketPedido = ticketPedido;
    }

    public void agregarComidaPedido(ComidaPedido comidaPedido1){
        comidaPedido1.setPedido(this);
        comidaPedidos.add(comidaPedido1);
    }

    public void agregarBebidaPedido(BebidaPedido bebidaPedido) {
        bebidaPedido.setPedido(this);
        bebidaPedidos.add(bebidaPedido);
    }

    public void agregarTicketPedido(TicketPedido ticketPedido) {
        ticketPedido.setPedido(this);
        this.ticketPedido=ticketPedido;
    }

}
