package com.foodieparty.fodieParty.dtos;
import com.foodieparty.fodieParty.models.Bebida;
import com.foodieparty.fodieParty.models.TipoBebida;
import java.util.List;
import java.util.stream.Collectors;

public class BebidaDTO {

    private long id;
    private String nombre, descripcion, imagen;
    private TipoBebida tipoBebida;
    private int disponibilidad;
    private Double precio;


    public BebidaDTO(Bebida bebida){
        this.id = bebida.getId();
        this.nombre= bebida.getNombre();
        this.descripcion= bebida.getDescripcion();
        this.imagen=bebida.getImagen();
        this.tipoBebida=bebida.getTipoBebida();
        this.disponibilidad=bebida.getDisponibilidad();
        this.precio= bebida.getPrecio();
    }

    public long getId() {
        return id;
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
