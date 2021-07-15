package com.haloqlinic.haloqlinicapps.api;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    private static ConfigRetrofit mInstance;


    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://aplikasicerdas.net/haloqlinic/android/customer/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService service = retrofit.create(ApiService.class);
    

}
