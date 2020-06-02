package com.example.tpproyectofinal.modelos;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Inmueble implements Serializable {
    private int id;
    private String direccion;
    private String uso;
    private int ambientes;
    private int costo;
    private Boolean disponible;
    private int foto;
    private int propietarioId;
    private int tipoId;
    private Propietario propietario;
    private TipoInmueble tipoInmueble;

    public Inmueble() {
    }

    public Inmueble(int id, String direccion, String uso, int ambientes, int costo, Boolean disponible, int propietarioId, int tipoId, Propietario propietario, TipoInmueble tipoInmueble, int foto) {
        this.id = id;
        this.direccion = direccion;
        this.uso = uso;
        this.ambientes = ambientes;
        this.costo = costo;
        this.disponible = disponible;
        this.foto = foto;
        this.propietarioId = propietarioId;
        this.tipoId = tipoId;
        this.propietario = propietario;
        this.tipoInmueble = tipoInmueble;
    }

    public Inmueble(String direccion, String uso, int ambientes, int costo, Boolean disponible, int foto, int propietarioId, int tipoId, Propietario propietario, TipoInmueble tipoInmueble) {
        this.direccion = direccion;
        this.uso = uso;
        this.ambientes = ambientes;
        this.costo = costo;
        this.disponible = disponible;
        this.foto = foto;
        this.propietarioId = propietarioId;
        this.tipoId = tipoId;
        this.propietario = propietario;
        this.tipoInmueble = tipoInmueble;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
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

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
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

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }
}
