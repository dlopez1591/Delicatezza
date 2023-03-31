package com.foodieparty.fodieParty.Utilidades;

public class FilaTabla {
    private String cantidad;
    private String descripcion;
    private String precio;

    private String total;

    public FilaTabla(String cantidad, String descripcion, String precio, String total) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.total = total;

    }

    public String getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getTotal() {
        return total;
    }

}
