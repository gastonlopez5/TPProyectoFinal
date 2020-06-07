package com.example.tpproyectofinal.modelos;

import android.graphics.Bitmap;

public class InmuebleFoto {
    private Inmueble inmueble;
    private String ruta;
    private Bitmap bitmap;

    public InmuebleFoto() {
    }

    public InmuebleFoto(Inmueble inmueble, String ruta) {
        this.inmueble = inmueble;
        this.ruta = ruta;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
