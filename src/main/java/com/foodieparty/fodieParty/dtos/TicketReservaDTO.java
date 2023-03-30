package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Reserva;
import com.foodieparty.fodieParty.models.TicketReserva;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class TicketReservaDTO {
    private long id;
    private  String detalle;
    private Double total;
    private ReservaDTO reserva;

    public TicketReservaDTO(TicketReserva ticketReserva){
        this.id = ticketReserva.getId();
        this.detalle=ticketReserva.getDetalle();
        this.total=ticketReserva.getTotal();
        this.reserva=new ReservaDTO(ticketReserva.getReserva());
    }

    public long getId() {
        return id;
    }

    public String getDetalle() {
        return detalle;
    }

    public Double getTotal() {
        return total;
    }

    public ReservaDTO getReserva() {
        return reserva;
    }
}
