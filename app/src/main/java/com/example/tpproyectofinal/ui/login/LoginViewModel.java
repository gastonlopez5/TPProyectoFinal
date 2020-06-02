package com.example.tpproyectofinal.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpproyectofinal.modelos.Usuario;
import com.example.tpproyectofinal.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    MutableLiveData<String> contraseniaVM;
    MutableLiveData<String> emailVM;
    MutableLiveData<String> msg1;
    MutableLiveData<String> msg2;
    private Context context;
    private String error1 = "Usuario o Password incorrectos";
    private String error2 = "Falla en la conección con la API";
    private String error3 = "Falla en la consulta";

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getContrasenia(){
        if (contraseniaVM == null){
            contraseniaVM = new MutableLiveData<String>();
        }
        return contraseniaVM;
    }

    public LiveData<String> getEmail(){
        if (emailVM == null){
            emailVM = new MutableLiveData<String>();
        }
        return emailVM;
    }

    public LiveData<String> getMsg1(){
        if (msg1 == null){
            msg1 = new MutableLiveData<String>();
        }
        return msg1;
    }

    public LiveData<String> getMsg2(){
        if (msg2 == null){
            msg2 = new MutableLiveData<String>();
        }
        return msg2;
    }

    public void validar(String user, String pass) {
        Usuario u = new Usuario(user, pass);
        Call<String> dato= ApiClient.getMyApiClient().login(u);
        dato.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    if(response.body().length()>0) {
                        String token = response.body();

                        SharedPreferences sp = context.getSharedPreferences("token",0);
                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("token",token);

                        editor.commit();

                        msg1.postValue(token);
                    }
                }else{
                    msg2.postValue(error1);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                msg2.postValue(t.getMessage());
                Log.d("salida Error",t.getMessage());
                Log.d("salida Error",call.request().body().toString());
                t.printStackTrace();
            }
        });
    }
}
