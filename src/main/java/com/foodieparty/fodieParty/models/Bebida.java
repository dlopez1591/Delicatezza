package com.foodieparty.fodieParty.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Bebida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,  generator = "native")
    @GenericGenerator(name="native", strategy="native")
    private long id;
    private String nombre, descripcion, imagen;
    private TipoBebida tipo;
    private int disponibilidad;
    private Double precio;
    @OneToMany(mappedBy = "bebida", fetch=FetchType.EAGER)
    private Set<BebidaPedido> bebidaPedidos = new HashSet<>();

    public Bebida(String nombre, String descripcion, String imagen, TipoBebida tipo, int disponibilidad, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
        this.precio = precio;
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

    public TipoBebida getTipo() {
        return tipo;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public Set<BebidaPedido> getBebidaPedidos() {
        return bebidaPedidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setTipo(TipoBebida tipo) {
        this.tipo = tipo;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setBebidaPedidos(Set<BebidaPedido> bebidaPedidos) {
        this.bebidaPedidos = bebidaPedidos;
    }

    public void agregarBebidaPedido(BebidaPedido bebidaPedido){
        bebidaPedido.setBebida(this);
        this.bebidaPedidos.add(bebidaPedido);
    }

}
