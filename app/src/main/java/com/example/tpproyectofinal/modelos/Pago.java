package com.example.tpproyectofinal.modelos;

import java.text.DecimalFormat;
import java.util.Date;

public class Pago {
    
    private int id;
    private int nroPago;
    private String fecha;
    private int importe;
    private int contratoId;
    private Contrato contrato;

    public Pago() {
    }

    public Pago(int id, int nroPago, String fecha, int importe, int contratoId, Contrato contrato) {
        this.id = id;
        this.nroPago = nroPago;
        this.fecha = fecha;
        this.importe = importe;
        this.contratoId = contratoId;
        this.contrato = contrato;
    }

    public Pago(int nroPago, String fecha, int importe) {
        this.nroPago = nroPago;
        this.fecha = fecha;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
