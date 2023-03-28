package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private LocalDateTime fecha;
    private byte cantidad_De_Personas;
    private Double precio;

    private Boolean estado;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    public Reserva(){}

    public Reserva(LocalDateTime fecha, byte cantidad_De_Personas, Double precio, Boolean estado) {
        this.fecha = fecha;
        this.cantidad_De_Personas = cantidad_De_Personas;
        this.precio = precio;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
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

    public void setFecha(LocalDateTime fecha) {
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
}
