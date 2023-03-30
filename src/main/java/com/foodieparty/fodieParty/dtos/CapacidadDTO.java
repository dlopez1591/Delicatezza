package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Capacidad;

public class CapacidadDTO {
    private long id;
    private byte capacidad;
    private Double precioPorReserva;

    public CapacidadDTO(Capacidad capacidad){
        this.capacidad= capacidad.getCapacidad();
        this.precioPorReserva= capacidad.getPrecioPorReserva();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCapacidad(byte capacidad) {
        this.capacidad = capacidad;
    }

    public void setPrecioPorReserva(Double precioPorReserva) {
        this.precioPorReserva = precioPorReserva;
    }
}
