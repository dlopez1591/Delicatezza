package com.foodieparty.fodieParty.dtos;

import java.util.List;

public class ListaBebidasDTO {
    private List<long[]> idBebidasYCantidad;

    public ListaBebidasDTO(List<long[]> idBebidasYCantidad) {
        this.idBebidasYCantidad = idBebidasYCantidad;
    }

    public List<long[]> getIdBebidasYCantidad() {
        return idBebidasYCantidad;
    }
}
