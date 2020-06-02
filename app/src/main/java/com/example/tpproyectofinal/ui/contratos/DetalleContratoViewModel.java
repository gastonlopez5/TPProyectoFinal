package com.example.tpproyectofinal.ui.contratos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.modelos.Contrato;

public class DetalleContratoViewModel extends ViewModel {

    MutableLiveData<Contrato> contratoMLD;

    public LiveData<Contrato> getContratoMLD(){
        if (contratoMLD==null){
            contratoMLD = new MutableLiveData<Contrato>();
        }
        return contratoMLD;
    }

    public void setContratoMLD(Contrato c){
        contratoMLD.setValue(c);
    }
}
