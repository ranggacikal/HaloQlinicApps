package id.luvie.luvieapps.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    private static ConfigRetrofit mInstance;


    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://luvie.co.id/android/customer/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService service = retrofit.create(ApiService.class);
    

}
