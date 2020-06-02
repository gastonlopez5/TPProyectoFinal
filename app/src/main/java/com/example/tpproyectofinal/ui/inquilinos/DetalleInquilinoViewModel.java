package com.example.tpproyectofinal.ui.inquilinos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.modelos.Inquilino;

public class DetalleInquilinoViewModel extends ViewModel {
    MutableLiveData<Inquilino> inquilinoMLD;
    private Inquilino i = new Inquilino("50110392", "Thiago", "López", "thiago@mail.com", "1154008019", "Calle 2053");

    public LiveData<Inquilino> getInquilinoMLD(){
        if (inquilinoMLD==null){
            inquilinoMLD = new MutableLiveData<Inquilino>();
        }
        return inquilinoMLD;
    }

    public void cargarInquilino(){
        inquilinoMLD.setValue(i);
    }
}
