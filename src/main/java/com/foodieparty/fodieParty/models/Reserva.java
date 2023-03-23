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
    private LocalDateTime horarioDeEntrada;
    private LocalDateTime horarioDeSalida;
    private LocalDateTime cantidad_De_Personas;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    private Mesa mesa;
    public Reserva(){}

    public Reserva(LocalDateTime horarioDeEntrada, LocalDateTime horarioDeSalida, LocalDateTime cantidad_De_Personas) {
        this.horarioDeEntrada = horarioDeEntrada;
        this.horarioDeSalida = horarioDeSalida;
        this.cantidad_De_Personas = cantidad_De_Personas;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getHorarioDeEntrada() {
        return horarioDeEntrada;
    }

    public void setHorarioDeEntrada(LocalDateTime horarioDeEntrada) {
        this.horarioDeEntrada = horarioDeEntrada;
    }

    public LocalDateTime getHorarioDeSalida() {
        return horarioDeSalida;
    }

    public void setHorarioDeSalida(LocalDateTime horarioDeSalida) {
        this.horarioDeSalida = horarioDeSalida;
    }

    public LocalDateTime getCantidad_De_Personas() {
        return cantidad_De_Personas;
    }

    public void setCantidad_De_Personas(LocalDateTime cantidad_De_Personas) {
        this.cantidad_De_Personas = cantidad_De_Personas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
