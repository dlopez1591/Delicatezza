package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Capacidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private byte capacidad;

    private Double precioPorReserva;
    public Capacidad(){};
    public Capacidad(byte capacidad, Double precioPorReserva) {
        this.capacidad = capacidad;
        this.precioPorReserva=precioPorReserva;
    }

    public long getId() {
        return id;
    }

    public byte getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(byte capacidad) {
        this.capacidad = capacidad;
    }

    public Double getPrecioPorReserva() {
        return precioPorReserva;
    }

    public void setPrecioPorReserva(Double precioPorReserva) {
        this.precioPorReserva = precioPorReserva;
    }

    public Boolean tieneCapacidad(Integer personasConReserva, Integer personasAReservar){
        return capacidad-personasConReserva>=personasAReservar;
    }

}
