package com.example.tpproyectofinal.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.MainActivity;
import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Contrato;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.InmuebleFoto;
import com.example.tpproyectofinal.modelos.Inquilino;
import com.example.tpproyectofinal.modelos.Mensaje;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.TipoInmueble;
import com.example.tpproyectofinal.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    MutableLiveData<InmuebleFoto> inmuebleVM;
    MutableLiveData<ArrayList<TipoInmueble>> tiposInmueblesVM;
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

    public LiveData<ArrayList<TipoInmueble>> getTiposInmuebles(){
        if (tiposInmueblesVM==null){
            tiposInmueblesVM = new MutableLiveData<ArrayList<TipoInmueble>>();
        }
        return tiposInmueblesVM;
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

    public void actualizarInmueble(Inmueble i){
        Call<Inmueble> dato= ApiClient.getMyApiClient().actualizarInmueble(obtenerToken(), i);
        dato.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Datos guardados correctamente!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void eliminarInmueble(int id){
        Call<Mensaje> dato= ApiClient.getMyApiClient().eliminarInmueble(obtenerToken(), id);
        dato.enqueue(new Callback<Mensaje>() {
            @Override
            public void onResponse(Call<Mensaje> call, Response<Mensaje> response) {
                if (response.isSuccessful()){
                    Mensaje msg = response.body();
                    Toast.makeText(context, msg.getMsg(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "NO se puede eliminar el inmueble! Tiene contratos asociados", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Mensaje> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void obtenerTiposInmuebles(){
        Call<ArrayList<TipoInmueble>> dato= ApiClient.getMyApiClient().tiposInmuebles(obtenerToken());
        dato.enqueue(new Callback<ArrayList<TipoInmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<TipoInmueble>> call, Response<ArrayList<TipoInmueble>> response) {
                if (response.isSuccessful()){
                    tiposInmueblesVM.postValue(response.body());
                } else {
                    Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TipoInmueble>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private String obtenerToken(){
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        String tokenFull = "Bearer " + token;

        return  tokenFull;
    }
}
