package com.example.tpproyectofinal.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.InmuebleFoto;
import com.example.tpproyectofinal.modelos.TipoInmueble;
import com.example.tpproyectofinal.request.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AltaInmuebleViewModel extends AndroidViewModel {
    private Context context;
    MutableLiveData<ArrayList<TipoInmueble>> tiposInmueblesVM;

    public AltaInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<TipoInmueble>> getTiposInmuebles(){
        if (tiposInmueblesVM==null){
            tiposInmueblesVM = new MutableLiveData<ArrayList<TipoInmueble>>();
        }
        return tiposInmueblesVM;
    }

    public void guardarInmueble(Inmueble i){
        Call<InmuebleFoto> dato= ApiClient.getMyApiClient().altaInmueble(obtenerToken(), i);
        dato.enqueue(new Callback<InmuebleFoto>() {
            @Override
            public void onResponse(Call<InmuebleFoto> call, Response<InmuebleFoto> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Inmueble agregado correctamente!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InmuebleFoto> call, Throwable t) {
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
