package com.example.tpproyectofinal.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
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
import androidx.navigation.Navigation;

import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.PropietarioFoto;
import com.example.tpproyectofinal.request.ApiClient;
import com.example.tpproyectofinal.ui.login.LoginActivity;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    MutableLiveData<PropietarioFoto> propietarioLD;
    MutableLiveData<String> msgLD;
    private PropietarioFoto propietarioFoto = null;
    private Bitmap bitmap;
    private String PATH="http://192.168.0.5:45455";

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<PropietarioFoto> getPropietarioLD(){
        if (propietarioLD==null){
            propietarioLD = new MutableLiveData<PropietarioFoto>();
        }
        return propietarioLD;
    }

    public LiveData<String> getMsgLD(){
        if (msgLD==null){
            msgLD = new MutableLiveData<String>();
        }
        return msgLD;
    }

    public void cargarUsuario(){
        Call<PropietarioFoto> dato= ApiClient.getMyApiClient().perfil(obtenerToken());
        dato.enqueue(new Callback<PropietarioFoto>() {
            @Override
            public void onResponse(Call<PropietarioFoto> call, Response<PropietarioFoto> response) {
                if (response.isSuccessful()){
                    Log.d("salida",response.body().getRuta());
                    String urlFoto = PATH + response.body().getRuta();
                    new DownloadImageTask(response).execute(urlFoto);
                }
            }

            @Override
            public void onFailure(Call<PropietarioFoto> call, Throwable t) {
                Log.d("salida Error",t.getMessage());
                Log.d("salida Error",call.request().body().toString());
                t.printStackTrace();
            }
        });

    }

    public void actualizarUsuario(Propietario p){
        if (p.getClave().length() > 0){
            Call<Propietario> dato= ApiClient.getMyApiClient().actualizar(obtenerToken(), p);
            dato.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()){
                        Intent i = new Intent(context, LoginActivity.class);
                        Toast.makeText(context, "Datos guardados correctamente!. Ingrese nuevamente.", Toast.LENGTH_LONG).show();
                        context.startActivity(i);
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(context, "No se pudieron guardar los datos", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            msgLD.postValue("Complete todos los campos!");
        }

    }

    private String obtenerToken(){
        SharedPreferences sp = context.getSharedPreferences("token",0);
        String token = sp.getString("token","-1");
        String tokenFull = "Bearer " + token;

        return  tokenFull;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        Response<PropietarioFoto> response;

        public DownloadImageTask(Response<PropietarioFoto> response) {


            this.response=response;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

            response.body().setBitmap(result);
            propietarioLD.postValue(response.body());
        }
    }
}
