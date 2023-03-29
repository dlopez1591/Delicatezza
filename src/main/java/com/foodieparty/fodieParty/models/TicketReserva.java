package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Entity
public class TicketReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private  String detalle;
    private Double total;
    @OneToOne
    @JoinColumn(name="reserva_id")
    private Reserva reserva;

    public  TicketReserva(String detalle, Double total){
        this.detalle=detalle;
        this.total=total;
    }

    public long getId() {
        return id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
