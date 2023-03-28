package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDate fecha;
    private byte cantidad_De_Personas;
    private Double precio = 1500.0;

    private Boolean estado;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @OneToOne(mappedBy="reserva")
    private TicketReserva ticketReserva;
    public Reserva(){}

    public Reserva(LocalDate fecha, byte cantidad_De_Personas, Boolean estado) {
        this.fecha = fecha;
        this.cantidad_De_Personas = cantidad_De_Personas;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public byte getCantidad_De_Personas() {
        return cantidad_De_Personas;
    }

    public Double getPrecio() {
        return precio;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCantidad_De_Personas(byte cantidad_De_Personas) {
        this.cantidad_De_Personas = cantidad_De_Personas;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TicketReserva getTicketReserva() {
        return ticketReserva;
    }

    public void setTicketReserva(TicketReserva ticketReserva) {
        this.ticketReserva = ticketReserva;
    }
    public void agregarTicketReserva(TicketReserva ticketReserva) {
        ticketReserva.setReserva(this);
        this.ticketReserva=ticketReserva;
    }

}
