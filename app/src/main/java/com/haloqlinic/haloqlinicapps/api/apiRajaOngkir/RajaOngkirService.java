package com.haloqlinic.haloqlinicapps.api.apiRajaOngkir;

import com.haloqlinic.haloqlinicapps.model.kecamatanRajaOngkir.ResponseKecamatanRajaOngkir;
import com.haloqlinic.haloqlinicapps.model.kotaRajaOngkir.ResponseKotaRajaOngkir;
import com.haloqlinic.haloqlinicapps.model.provinsiRajaOngkir.ResponseProvinsiRajaOngkir;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RajaOngkirService {

    @GET("province")
    Call<ResponseProvinsiRajaOngkir> getProvinsi(@Header("key") String key);


    @GET("city?")
    Call<ResponseKotaRajaOngkir> getKota(@Header("key") String key,
                                         @Query("province") String province);

    @GET("subdistrict?")
    Call<ResponseKecamatanRajaOngkir> getKecamatan(@Header("key") String key,
                                                   @Query("city") String city);


}
