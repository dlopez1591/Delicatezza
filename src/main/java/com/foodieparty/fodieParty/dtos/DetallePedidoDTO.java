package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.TipoRetiro;

import java.util.List;

public class DetallePedidoDTO {
    private TipoRetiro tipoRetiro;

    private String direccion;
    private List<long[]> listaComidasId;
    private List<long[]> listaBebidasId;

    public DetallePedidoDTO(TipoRetiro tipoRetiro, String direccion, List<long[]> listaComidasId, List<long[]> listaBebidasId) {
        this.tipoRetiro = tipoRetiro;
        this.direccion = direccion;
        this.listaComidasId = listaComidasId;
        this.listaBebidasId = listaBebidasId;
    }

    public TipoRetiro getTipoRetiro() {
        return tipoRetiro;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<long[]> getListaComidasId() {
        return listaComidasId;
    }

    public List<long[]> getListaBebidasId() {
        return listaBebidasId;
    }
}
