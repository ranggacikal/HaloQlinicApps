package com.haloqlinic.haloqlinicapps.api;

import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.cariProduk.ResponseCariProduk;
import com.haloqlinic.haloqlinicapps.model.daftar.ResponseDaftar;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResponseDetailDokter;
import com.haloqlinic.haloqlinicapps.model.detailProduk.ResponseDetailProduk;
import com.haloqlinic.haloqlinicapps.model.editAkun.ResponseEditAkun;
import com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseDataKecamatan;
import com.haloqlinic.haloqlinicapps.model.kota.ResponseDataKota;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseDataDokter;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;
import com.haloqlinic.haloqlinicapps.model.loginapi.ResponseLoginUser;
import com.haloqlinic.haloqlinicapps.model.logingoogle.Response;
import com.haloqlinic.haloqlinicapps.model.logingoogle.ResponseLoginGoogle;
import com.haloqlinic.haloqlinicapps.model.loginmesibo.ResponseLoginMesibo;
import com.haloqlinic.haloqlinicapps.model.produk.ResponseDataProduk;
import com.haloqlinic.haloqlinicapps.model.provinsi.ResponseDataProvinsi;
import com.haloqlinic.haloqlinicapps.model.userMesibo.ResponseGetUserMesibo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api.php")
    Call<ResponseLoginMesibo> loginMesibo(@Query("op") String op,
                                          @Query("token") String token,
                                          @Query("addr") String addr);

    @GET("api.php")
    Call<ResponseGetUserMesibo> getUser(@Query("op") String op,
                                        @Query("token") String token);

    @FormUrlEncoded
    @POST("signin.php")
    Call<ResponseLoginUser> login(@Field("email") String email,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseDaftar> daftar(@Field("nama") String nama,
                                @Field("email") String email,
                                @Field("no_hp") String no_hp,
                                @Field("jk") String jk,
                                @Field("tgl_lahir") String tgl_lahir,
                                @Field("id_provinsi") String id_provinsi,
                                @Field("id_kota") String id_kota,
                                @Field("id_kecamatan") String id_kecamatan,
                                @Field("alamat") String alamat,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("signin-google.php")
    Call<ResponseLoginGoogle> loginGoogle(@Field("oauthpro") String oauthpro,
                                          @Field("oauthid") String oauthid,
                                          @Field("first_name") String first_name,
                                          @Field("last_name") String last_name,
                                          @Field("email") String email);

    @GET("province.php")
    Call<ResponseDataProvinsi> dataProvinsi();

    @FormUrlEncoded
    @POST("city.php")
    Call<ResponseDataKota> dataKota(@Field("province_id") String province_id);

    @FormUrlEncoded
    @POST("subdistrict.php")
    Call<ResponseDataKecamatan> dataKecamatan(@Field("city_id") String city_id);

    @FormUrlEncoded
    @POST("update_profile.php")
    Call<ResponseEditAkun> editAkun(@Field("id_customer") String id_customer,
                                    @Field("nama") String nama,
                                    @Field("alamat") String alamat,
                                    @Field("no_hp") String no_hp,
                                    @Field("jk") String jk,
                                    @Field("id_provinsi") String id_provinsi,
                                    @Field("id_kota") String id_kota,
                                    @Field("id_kecamatan") String id_kecamatan,
                                    @Field("tgl_lahir") String tgl_lahir);

    @GET("list_dokter.php")
    Call<ResponseDataDokter> dataDokter();

    @FormUrlEncoded
    @POST("cari_dokter.php")
    Call<ResponseCariDokter> cariDokter(@Field("nama") String nama);

    @GET("list_dokter_aktif.php")
    Call<ResponseDataDokterAktif> dataDokterAktif();

    @FormUrlEncoded
    @POST("detail_dokter.php")
    Call<ResponseDetailDokter> detailDokter(@Field("id_dokter") String id_dokter);

    @GET("produk.php")
    Call<ResponseDataProduk> dataProduk(@Query("page") String page);

    @FormUrlEncoded
    @POST("cari_produk.php")
    Call<ResponseCariProduk> cariProduk(@Field("nama_produk") String nama_produk);

    @FormUrlEncoded
    @POST("detail_produk.php")
    Call<ResponseDetailProduk> detailProduk(@Field("id_produk") String id_produk);
}
