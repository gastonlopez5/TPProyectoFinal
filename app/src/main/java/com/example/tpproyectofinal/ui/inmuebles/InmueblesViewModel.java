package com.example.tpproyectofinal.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Inmueble;
import com.example.tpproyectofinal.modelos.InmuebleFoto;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.TipoInmueble;
import com.example.tpproyectofinal.request.ApiClient;
import com.example.tpproyectofinal.ui.perfil.PerfilViewModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private Context context;
    MutableLiveData<ArrayList<InmuebleFoto>> listaInmuebles;
    private ArrayList<InmuebleFoto> listaInmueblesFoto = new ArrayList<>();
    private ArrayList<String> listaUrls= new ArrayList<>();
    private Bitmap bitmap;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<ArrayList<InmuebleFoto>> getListaInmuebles(){
        if (listaInmuebles==null){
            listaInmuebles = new MutableLiveData<>();
        }
        return listaInmuebles;
    }

    public void cargarDatos(){
        Call<ArrayList<InmuebleFoto>> dato= ApiClient.getMyApiClient().listarInmuebles(obtenerToken());
        dato.enqueue(new Callback<ArrayList<InmuebleFoto>>() {
            @Override
            public void onResponse(Call<ArrayList<InmuebleFoto>> call, Response<ArrayList<InmuebleFoto>> response) {
                if (response.isSuccessful()){
                    listaInmueblesFoto = response.body();
                    listaInmuebles.setValue(listaInmueblesFoto);
                } else {
                    Log.d("salida",response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<InmuebleFoto>> call, Throwable t) {
                Log.d("salida",t.getMessage());
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
