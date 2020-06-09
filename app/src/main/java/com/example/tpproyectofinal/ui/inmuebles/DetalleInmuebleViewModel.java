package com.example.tpproyectofinal.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
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
import com.example.tpproyectofinal.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    MutableLiveData<InmuebleFoto> inmuebleVM;
    MutableLiveData<Integer> flag;
    private Context context;
    private int cont = 0;

    //private ArrayList<Contrato> listaContratos = new ArrayList<>();
    private ArrayList<Contrato> listaContratos = null;

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

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

    public void setInmueble(InmuebleFoto i){
        Call<ArrayList<Contrato>> dato= ApiClient.getMyApiClient().listarContratos(obtenerToken(), i.getInmueble().getId());
        dato.enqueue(new Callback<ArrayList<Contrato>>() {
            @Override
            public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                if (response.isSuccessful()){
                    listaContratos = response.body();

                    if (listaContratos.size() != 0){
                        flag.setValue(cont + 1);
                        cont = 0;
                    }

                } else {
                    Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contrato>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        inmuebleVM.setValue(i);
    }

    private String obtenerToken(){
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        String tokenFull = "Bearer " + token;

        return  tokenFull;
    }
}
