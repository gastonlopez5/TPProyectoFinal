package com.example.tpproyectofinal.modelos;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Inmueble {
    private int id;
    private String direccion;
    private int tipo;
    private String uso;
    private int ambientes;
    private double costo;
    private Boolean disponible;
    private int propietarioId;
    private Propietario duenio;
    private TipoInmueble tipoInmueble;

    public Inmueble() {
    }

    public Inmueble(int id, String direccion, int tipo, String uso, int ambientes, double costo, Boolean disponible, int propietarioId, Propietario duenio, TipoInmueble tipoInmueble) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.uso = uso;
        this.ambientes = ambientes;
        this.costo = costo;
        this.disponible = disponible;
        this.propietarioId = propietarioId;
        this.duenio = duenio;
        this.tipoInmueble = tipoInmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }
}
