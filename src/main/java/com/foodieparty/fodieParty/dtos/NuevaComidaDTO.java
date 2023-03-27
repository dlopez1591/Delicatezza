package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.TipoComida;

public class NuevaComidaDTO {
    private String nombre, descripcion,imagen;
    private TipoComida tipoComida;
    private Double precio;
    private Boolean disponibilidad;

    public NuevaComidaDTO(String nombre, String descripcion, String imagen, TipoComida tipoComida, Double precio, Boolean disponibilidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.tipoComida = tipoComida;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public Double getPrecio() {
        return precio;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }
}
