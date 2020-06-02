package com.example.tpproyectofinal.modelos;

public class Usuario {
    private String Usuario;
    private String Clave;

    public Usuario(String usuario, String clave) {
        this.Usuario = usuario;
        this.Clave = clave;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        this.Clave = clave;
    }
}
