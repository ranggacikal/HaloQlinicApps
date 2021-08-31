package id.luvie.luvieapps.api.apiRajaOngkir;

import id.luvie.luvieapps.model.kecamatanRajaOngkir.ResponseKecamatanRajaOngkir;
import id.luvie.luvieapps.model.kotaRajaOngkir.ResponseKotaRajaOngkir;
import id.luvie.luvieapps.model.layananEkspedisi.ResponseLayananEkspedisi;
import id.luvie.luvieapps.model.provinsiRajaOngkir.ResponseProvinsiRajaOngkir;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("cost")
    Call<ResponseLayananEkspedisi> layananEkspedisi(@Header("key") String key,
                                                    @Field("origin") String origin,
                                                    @Field("originType") String originType,
                                                    @Field("destination") String destination,
                                                    @Field("destinationType") String destinationType,
                                                    @Field("weight") String weight,
                                                    @Field("courier") String courier);


}
