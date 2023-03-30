package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Reserva;
import com.foodieparty.fodieParty.models.TicketReserva;
import com.foodieparty.fodieParty.models.Usuario;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaDTO {
    private long id;
    private LocalDate fecha;
    private byte cantidad_De_Personas;
    private Double precio;

    private Boolean estado;

    private UsuarioDTO usuario;

    private TicketReservaDTO ticketReserva;

    public ReservaDTO(Reserva reserva) {
        this.id = reserva.getId();
        this.fecha = reserva.getFecha();
        this.cantidad_De_Personas = reserva.getCantidad_De_Personas();
        this.precio = reserva.getPrecio();
        this.estado = reserva.getEstado();
        this.usuario = new UsuarioDTO(reserva.getUsuario());
        this.ticketReserva = new TicketReservaDTO(reserva.getTicketReserva());
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }
}
