package com.example.tpproyectofinal.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Contrato;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.InmuebleFoto;
import com.example.tpproyectofinal.modelos.Inquilino;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.TipoInmueble;

import java.util.ArrayList;

public class DetalleInmuebleViewModel extends ViewModel {

    MutableLiveData<InmuebleFoto> inmuebleVM;
    MutableLiveData<Integer> flag;
    private int cont = 0;

    //private ArrayList<Contrato> listaContratos = new ArrayList<>();
    private ArrayList<Contrato> listaContratos = null;
    private TipoInmueble tipo1 =  new TipoInmueble(1, "casa");
    private Propietario p = new Propietario(1, "32826861", "Gaston", "López", "gaston@mail.com", "1154008019","222");
    private Inmueble inmueble1 = new Inmueble(1,"Chile 2053", 1, "aaaa", 5000, 2,true, 1, 2, p, tipo1);
    private Inquilino i = new Inquilino(1,"50110392", "Thiago", "López", "thiago@mail.com", "2664614213", "chile2053");

    public LiveData<InmuebleFoto> getInmueble(){
        if (inmuebleVM==null){
            inmuebleVM = new MutableLiveData<InmuebleFoto>();
        }
        return inmuebleVM;
    }

    public LiveData<Integer> getFlag(){
        if (flag==null){
            flag = new MutableLiveData<Integer>();
        }
        return flag;
    }

    public void setInmueble(InmuebleFoto inmueble){
        // Con el id del inmueble busco si tiene contratos y el tamaño de listaContratos es != de 0
        // si tiene contratos por lo que modifica el observable flag que luego desabilita la opcion
        // de cambiar la disponibilidad del inmueble

        listaContratos = new ArrayList<>();
        listaContratos.add(new Contrato("13-05-2019", "13-05-2021", 10000, "33766055", "Cuevas Gabriela", "2664614213", "gabriela@mail.com", i.getId(), inmueble1.getId(), i, inmueble1));
        if (listaContratos.size() != 0){
            flag.setValue(cont + 1);
            cont = 0;
        }
        inmuebleVM.setValue(inmueble);
    }
}
