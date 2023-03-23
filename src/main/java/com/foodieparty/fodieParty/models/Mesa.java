package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private long id;
    private Boolean disponibilidad;
    private byte capacidad;
    @OneToMany(mappedBy = "mesa", fetch=FetchType.EAGER)
    private Set<Reserva> reservas = new HashSet<>();

    public Mesa(){}

    public Mesa(Boolean disponibilidad, byte capacidad) {
        this.disponibilidad = disponibilidad;
        this.capacidad = capacidad;
    }

    public long getId() {
        return id;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public byte getCapacidad() {
        return capacidad;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setCapacidad(byte capacidad) {
        this.capacidad = capacidad;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void agregarReserva(Reserva reserva){
        reserva.setMesa(this);
        this.reservas.add(reserva);
    }

}
