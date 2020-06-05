package com.example.tpproyectofinal.ui.inmuebles;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.TipoInmueble;

import java.util.ArrayList;
import java.util.List;

public class InmueblesViewModel extends ViewModel {

    MutableLiveData<ArrayList<Inmueble>> listaInmuebles;
    private ArrayList<Inmueble> lista = new ArrayList<>();
    private Propietario p = new Propietario(1, "32826861", "Gaston", "López", "gaston@mail.com", "1154008019","222");
    private TipoInmueble tipo1 =  new TipoInmueble(1, "casa");
    private TipoInmueble tipo2 =  new TipoInmueble(2, "departamento");


    public LiveData<ArrayList<Inmueble>> getListaInmuebles(){
        if (listaInmuebles==null){
            listaInmuebles = new MutableLiveData<>();
        }
        return listaInmuebles;
    }

    public void cargarDatos(){
        lista.add(new Inmueble("Chile 2053", "Residencial", 3, 5000, true, R.drawable.casa1, 1, tipo1.getId(), p, tipo1));
        lista.add(new Inmueble("Abelardo Figueroa 346", "Privado", 2, 10000, false, R.drawable.casa2, 1, tipo2.getId(), p, tipo2));
        lista.add(new Inmueble("Chile 2053", "Residencial", 3, 5000, true, R.drawable.casa3, 1, tipo1.getId(), p, tipo1));
        listaInmuebles.setValue(lista);
    }

}
