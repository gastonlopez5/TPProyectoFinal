package com.example.tpproyectofinal.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.modelos.Propietario;

public class PerfilViewModel extends ViewModel {
    MutableLiveData<Propietario> propietarioLD;
    private Propietario p = new Propietario("32826861", "Gaston", "López", "gaston@mail.com", "1154008019", "123");


    public LiveData<Propietario> getPropietarioLD(){
        if (propietarioLD==null){
            propietarioLD = new MutableLiveData<Propietario>();
        }
        return propietarioLD;
    }

    public void cargarUsuario(){
        propietarioLD.setValue(p);
    }
}
