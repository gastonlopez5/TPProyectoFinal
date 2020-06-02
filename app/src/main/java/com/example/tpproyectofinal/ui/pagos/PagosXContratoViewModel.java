package com.example.tpproyectofinal.ui.pagos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.modelos.Pago;

import java.util.ArrayList;

public class PagosXContratoViewModel extends ViewModel {

    MutableLiveData<ArrayList<Pago>> listaPagosMLD;
    private ArrayList<Pago> listaPagos;

    public LiveData<ArrayList<Pago>> getListaMLD(){
        if (listaPagosMLD==null){
            listaPagosMLD = new MutableLiveData<ArrayList<Pago>>();
        }
        return listaPagosMLD;
    }

    public void cargarPagos(){
        listaPagos = new ArrayList<Pago>();
        listaPagos.add(new Pago(1, "08-03-2020", 5000));
        listaPagos.add(new Pago(2, "09-04-2020", 5000));
        listaPagos.add(new Pago(3, "10-05-2020", 5000));
        listaPagos.add(new Pago(4, "06-06-2020", 5000));
        listaPagosMLD.setValue(listaPagos);
    }
}
