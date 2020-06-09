package com.example.tpproyectofinal.request;

import android.util.Log;

import com.example.tpproyectofinal.modelos.InmuebleFoto;
import com.example.tpproyectofinal.modelos.Propietario;
import com.example.tpproyectofinal.modelos.PropietarioFoto;
import com.example.tpproyectofinal.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {
    private static final String PATH="http://192.168.1.102:45455/api/";
    private static  MyApiInterface myApiInteface;

    public static MyApiInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }

    public interface MyApiInterface {

        @POST("propietarios/login")
        Call<String> login(@Body Usuario user);

        @GET("propietarios")
        Call<PropietarioFoto> perfil(@Header("Authorization")  String token);

        @PUT("propietarios")
        Call<Propietario> actualizar(@Header("Authorization")String token, @Body Propietario p);

        @GET("inmuebles")
        Call<ArrayList<InmuebleFoto>> listarInmuebles(@Header("Authorization")  String token);

        //listarClientes.php
        //@GET("listarClientes.php")
        //Call<List<Cliente>> getClientes();

        //@GET("insertarClientes.php")
        //Call<Cliente> createCliente(@Query("dni") int dni, @Query("apellido") String apellido, @Query("nombre") String nombre);
    }
}
