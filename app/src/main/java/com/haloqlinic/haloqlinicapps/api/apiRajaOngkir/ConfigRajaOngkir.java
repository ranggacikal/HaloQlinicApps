package com.haloqlinic.haloqlinicapps.api.apiRajaOngkir;

import com.haloqlinic.haloqlinicapps.api.ApiService;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRajaOngkir {

    private static ConfigRajaOngkir mInstance;

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://pro.rajaongkir.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static RajaOngkirService serviceRajaOngkir = retrofit.create(RajaOngkirService.class);

}
