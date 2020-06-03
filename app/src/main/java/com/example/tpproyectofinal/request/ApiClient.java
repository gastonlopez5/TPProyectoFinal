package com.example.tpproyectofinal.request;

import android.util.Log;

import com.example.tpproyectofinal.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class ApiClient {
    private static final String PATH="http://192.168.0.5:45461/api/";
    private static  MyApiInterface myApiInteface;
    private static String prueba;

    public static MyApiInterface getMyApiClient(){
        //Gson gson = new GsonBuilder().setLenient().create();
        //OkHttpClient.Builder client = new OkHttpClient.Builder();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                //.client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }

    public interface MyApiInterface {

        @POST("propietarios/login")
        Call<String> login(@Body Usuario user);

        // @GET("propietario")
        //Call<Propietario> leer(@Header("Authorization")String token);

        // @FormUrlEncoded
        // @PUT("propietario/{id}")
        //Call<Propietario> actualizar(@Header("Authorization")String token, @Path("id") int groupId, @Field("Nombre")String nombre,@Field("Apellido") String apellido,@Field("Dni") int dni,@Field("Correo")String correo,@Field("Clave")String clave,@Field("EstadoPropietario") int estado,@Field("Telefono")long telefono);
        // @GET("test")
        // Call<Data> leer();

        //listarClientes.php
        //@GET("listarClientes.php")
        //Call<List<Cliente>> getClientes();

        //@GET("insertarClientes.php")
        //Call<Cliente> createCliente(@Query("dni") int dni, @Query("apellido") String apellido, @Query("nombre") String nombre);
    }
}
