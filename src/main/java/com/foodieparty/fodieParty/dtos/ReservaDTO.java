package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Reserva;

import java.time.LocalDateTime;

public class ReservaDTO {
    private long id;
    private LocalDateTime horarioDeEntrada;
    private LocalDateTime horarioDeSalida;
    private LocalDateTime cantidad_De_Personas;
    private Double precio;
    public ReservaDTO(Reserva reserva){
        this.id=reserva.getId();
        this.cantidad_De_Personas=reserva.getCantidad_De_Personas();
        this.horarioDeSalida=reserva.getHorarioDeSalida();
        this.horarioDeEntrada=reserva.getHorarioDeEntrada();
        this.precio=reserva.getPrecio();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getHorarioDeEntrada() {
        return horarioDeEntrada;
    }

    public LocalDateTime getHorarioDeSalida() {
        return horarioDeSalida;
    }

    public LocalDateTime getCantidad_De_Personas() {
        return cantidad_De_Personas;
    }

    public Double getPrecio() {
        return precio;
    }
}
