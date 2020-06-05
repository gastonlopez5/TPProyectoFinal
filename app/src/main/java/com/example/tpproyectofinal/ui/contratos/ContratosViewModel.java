package com.example.tpproyectofinal.ui.contratos;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Contrato;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.Inquilino;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.TipoInmueble;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ContratosViewModel extends ViewModel {

    private Propietario p = new Propietario(1, "32826861", "Gaston", "López", "gaston@mail.com", "1154008019", "222");
    private Inquilino i = new Inquilino(1,"50110392", "Thiago", "López", "thiago@mail.com", "2664614213", "chile2053");
    private TipoInmueble tipo1 =  new TipoInmueble(1, "casa");
    private TipoInmueble tipo2 =  new TipoInmueble(2, "departamento");
    private Inmueble inmueble1 = new Inmueble("Chile 2053", "Residencial", 3, 5000, true,R.drawable.casa1, 1, tipo1.getId(), p, tipo1);
    private Inmueble inmueble2 = new Inmueble("Abelardo Figueroa 346", "Privado", 2, 10000, false, R.drawable.casa2, 1, tipo2.getId(), p, tipo2);
    private Inmueble inmueble3 =new Inmueble("Chile 2053", "Residencial", 3, 5000, true, R.drawable.casa3, 1, tipo1.getId(), p, tipo1);
    private ArrayList<Contrato> lista = new ArrayList<>();

    private MutableLiveData<ArrayList<Contrato>> listaMLD;

    public LiveData<ArrayList<Contrato>> getlistaMLD(){
        if(listaMLD==null){
            listaMLD = new MutableLiveData<ArrayList<Contrato>>();
        }
        return listaMLD;
    }



    public void cargarContratos(){
        lista.add(new Contrato("13-05-2019", "13-05-2021", 10000, "33766055", "Cuevas Gabriela", "2664614213", "gabriela@mail.com", i.getId(), inmueble1.getId(), i, inmueble1));
        lista.add(new Contrato("13-05-2018", "13-05-2022", 15000, "33766055", "Cuevas Gabriela", "2664614213", "gabriela@mail.com", i.getId(), inmueble1.getId(), i, inmueble2));
        lista.add(new Contrato("13-05-2018", "13-08-2023", 35000, "33766055", "Cuevas Gabriela", "2664614213", "gabriela@mail.com", i.getId(), inmueble3.getId(), i, inmueble3));
        listaMLD.setValue(lista);
    }
}
