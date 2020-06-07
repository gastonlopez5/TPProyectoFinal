package com.example.tpproyectofinal.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.InmuebleFoto;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.TipoInmueble;

import java.util.ArrayList;
import java.util.List;

public class InmueblesViewModel extends AndroidViewModel {
    private Context contexto;
    MutableLiveData<ArrayList<InmuebleFoto>> listaInmuebles;
    private ArrayList<InmuebleFoto> lista = new ArrayList<>();
    private Bitmap bitmap;
    private String PATH="http://192.168.0.5:45455";

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        contexto = application.getApplicationContext();
    }


    public LiveData<ArrayList<InmuebleFoto>> getListaInmuebles(){
        if (listaInmuebles==null){
            listaInmuebles = new MutableLiveData<>();
        }
        return listaInmuebles;
    }

    public void cargarDatos(){
        listaInmuebles.setValue(lista);
    }

}
