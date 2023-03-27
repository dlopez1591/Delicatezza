package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.TipoBebida;

public class NuevaBebidaDTO {
    private String nombre, descripcion, imagen;
    private TipoBebida tipoBebida;
    private int disponibilidad;
    private Double precio;

    public NuevaBebidaDTO(String nombre, String descripcion, String imagen, TipoBebida tipoBebida, int disponibilidad, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.tipoBebida = tipoBebida;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
    }

    public NuevaBebidaDTO(){}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public Double getPrecio() {
        return precio;
    }
}
