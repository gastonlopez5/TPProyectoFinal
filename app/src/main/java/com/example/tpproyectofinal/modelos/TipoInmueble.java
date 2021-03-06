package com.example.tpproyectofinal.modelos;

public class TipoInmueble {
    private int id;
    private String tipo;

    public TipoInmueble() {
    }

    public TipoInmueble(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
