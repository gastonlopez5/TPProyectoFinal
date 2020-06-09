package com.example.tpproyectofinal.modelos;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;

public class Contrato implements Serializable {
    private int id;
    private String fechaInicio;
    private String fechaFin;
    private double inporte;
    private String dniGarante;
    private String nombreCompletoGarante;
    private String telefonoGarante;
    private String emailGarante;
    private int inquilinoId;
    private int inmuebleId;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato() {
    }

    public Contrato(int id, String fechaInicio, String fechaFin, double inporte, String dniGarante, String nombreCompletoGarante, String telefonoGarante, String emailGarante, int inquilinoId, int inmuebleId, Inquilino inquilino, Inmueble inmueble) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inporte = inporte;
        this.dniGarante = dniGarante;
        this.nombreCompletoGarante = nombreCompletoGarante;
        this.telefonoGarante = telefonoGarante;
        this.emailGarante = emailGarante;
        this.inquilinoId = inquilinoId;
        this.inmuebleId = inmuebleId;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getInporte() {
        return inporte;
    }

    public void setInporte(double inporte) {
        this.inporte = inporte;
    }

    public String getDniGarante() {
        return dniGarante;
    }

    public void setDniGarante(String dniGarante) {
        this.dniGarante = dniGarante;
    }

    public String getNombreCompletoGarante() {
        return nombreCompletoGarante;
    }

    public void setNombreCompletoGarante(String nombreCompletoGarante) {
        this.nombreCompletoGarante = nombreCompletoGarante;
    }

    public String getTelefonoGarante() {
        return telefonoGarante;
    }

    public void setTelefonoGarante(String telefonoGarante) {
        this.telefonoGarante = telefonoGarante;
    }

    public String getEmailGarante() {
        return emailGarante;
    }

    public void setEmailGarante(String emailGarante) {
        this.emailGarante = emailGarante;
    }

    public int getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public int getInmuebleId() {
        return inmuebleId;
    }

    public void setInmuebleId(int inmuebleId) {
        this.inmuebleId = inmuebleId;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
}
