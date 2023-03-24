package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Mesa;

import java.util.List;
import java.util.stream.Collectors;

public class MesaDTO {
    private long id;
    private Boolean disponibilidad;
    private byte capacidad;
    private List<ReservaDTO> reservas;

    public MesaDTO(Mesa mesa){
        this.id = mesa.getId();
        this.disponibilidad=mesa.getDisponibilidad();
        this.capacidad= mesa.getCapacidad();
        this.reservas=mesa.getReservas().stream().map(r->new ReservaDTO(r)).collect(Collectors.toList());
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

    public List<ReservaDTO> getReservas() {
        return reservas;
    }
}
