package com.foodieparty.fodieParty.dtos;

import com.foodieparty.fodieParty.models.Usuario;

import java.util.stream.Collectors;

public class UsuarioDTO {

    private long id;
    private String nombre,apellido,email,contraseña,telefono;

    private List<PedidoDTO> pedidos;

    public UsuarioDTO(Usuario usuario){
        this.id=usuario.getId();
        this.nombre= usuario.getNombre();
        this.apellido= usuario.getApellido();
        this.email= usuario.getEmail();
        this.contraseña= usuario.getContraseña();
        this.telefono= usuario.getTelefono();
        this.pedidos=usuario.getPedidos().stream().map(p->new PedidoDTO(p)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

}
