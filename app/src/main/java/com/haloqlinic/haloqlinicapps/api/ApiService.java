package com.haloqlinic.haloqlinicapps.api;

import com.haloqlinic.haloqlinicapps.model.biayaAdmin.ResponseBiayaAdmin;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.cariProduk.ResponseCariProduk;
import com.haloqlinic.haloqlinicapps.model.daftar.ResponseDaftar;
import com.haloqlinic.haloqlinicapps.model.dataEkspedisi.ResponseDataEkspedisi;
import com.haloqlinic.haloqlinicapps.model.dataKeranjang.ResponseDataKeranjang;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResponseDetailDokter;
import com.haloqlinic.haloqlinicapps.model.detailProduk.ResponseDetailProduk;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ResponseDetailTransaksi;
import com.haloqlinic.haloqlinicapps.model.editAkun.ResponseEditAkun;
import com.haloqlinic.haloqlinicapps.model.ewallet.ResponseEwallet;
import com.haloqlinic.haloqlinicapps.model.ewalletOvo.ResponseOvo;
import com.haloqlinic.haloqlinicapps.model.hapusKeranjang.ResponseHapusKeranjang;
import com.haloqlinic.haloqlinicapps.model.historyTransaksi.ResponseHistoryTransaksi;
import com.haloqlinic.haloqlinicapps.model.invoice.ResponseInvoice;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ResponseJadwalDokter;
import com.haloqlinic.haloqlinicapps.model.kategoriXendit.ResponseKategoriXendit;
import com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseDataKecamatan;
import com.haloqlinic.haloqlinicapps.model.kota.ResponseDataKota;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseDataDokter;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;
import com.haloqlinic.haloqlinicapps.model.listPesanan.ResponseListPesanan;
import com.haloqlinic.haloqlinicapps.model.loginapi.ResponseLoginUser;
import com.haloqlinic.haloqlinicapps.model.logingoogle.Response;
import com.haloqlinic.haloqlinicapps.model.logingoogle.ResponseLoginGoogle;
import com.haloqlinic.haloqlinicapps.model.loginmesibo.ResponseLoginMesibo;
import com.haloqlinic.haloqlinicapps.model.opsiBayar.ResponseOpsiBayar;
import com.haloqlinic.haloqlinicapps.model.produk.ResponseDataProduk;
import com.haloqlinic.haloqlinicapps.model.provinsi.ResponseDataProvinsi;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.ResponseStatusTransaksi;
import com.haloqlinic.haloqlinicapps.model.tambahKeranjang.ResponseTambahKeranjang;
import com.haloqlinic.haloqlinicapps.model.updateKeranjang.ResponseUpdateKeranjang;
import com.haloqlinic.haloqlinicapps.model.updateKurir.ResponseUpdateKurir;
import com.haloqlinic.haloqlinicapps.model.userMesibo.ResponseGetUserMesibo;
import com.haloqlinic.haloqlinicapps.model.xenditQris.ResponseQris;

import java.util.ArrayList;

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

    @FormUrlEncoded
    @POST("add_cart.php")
    Call<ResponseTambahKeranjang> tambahKeranjang(@Field("id_customer") String id_customer,
                                                  @Field("id_produk") String id_produk,
                                                  @Field("id_member") String id_member,
                                                  @Field("berat") String berat,
                                                  @Field("jumlah") String jumlah,
                                                  @Field("harga") String harga,
                                                  @Field("id") String id,
                                                  @Field("variasi") String variasi);

    @FormUrlEncoded
    @POST("cart.php")
    Call<ResponseDataKeranjang> dataKeranjang(@Field("id_customer") String id_customer);

    @FormUrlEncoded
    @POST("update_cart.php")
    Call<ResponseUpdateKeranjang> updateKeranjang(@Field("id_customer") String id_customer,
                                                  @Field("id_pesan[]") ArrayList<String> id_pesan,
                                                  @Field("jumlah[]") ArrayList<String> jumlah,
                                                  @Field("harga[]") ArrayList<String> harga,
                                                  @Field("berat_item[]") ArrayList<String> berat_item);

    @FormUrlEncoded
    @POST("list_pesan.php")
    Call<ResponseListPesanan> listPesanan(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("delete_item.php")
    Call<ResponseHapusKeranjang> hapusKeranjang(@Field("id_pesan") String id_pesan);

    @GET("list_ekspedisi.php")
    Call<ResponseDataEkspedisi> dataEkspedisi();

    @FormUrlEncoded
    @POST("update_kurir.php")
    Call<ResponseUpdateKurir> updateKurir(@Field("id_transaksi") String id_transaksi,
                                          @Field("id_member") String id_member,
                                          @Field("kurir") String kurir,
                                          @Field("ongkir") String ongkir,
                                          @Field("layanan_kurir") String layanan_kurir,
                                          @Field("ekspedisi") String ekspedisi);

    @GET("kategori_xendit.php")
    Call<ResponseKategoriXendit> kategoriXendit();

    @FormUrlEncoded
    @POST("opsi_bayar.php")
    Call<ResponseOpsiBayar> opsiBayar(@Field("id") String id);

    @FormUrlEncoded
    @POST("biaya_admin.php")
    Call<ResponseBiayaAdmin> biayaAdmin(@Field("opsi_bayar") String id,
                                        @Field("total") String total);

    @FormUrlEncoded
    @POST("checkout_ewallet.php")
    Call<ResponseOvo> checkOutOvo(@Field("id_customer") String id_customer,
                                  @Field("id_transaksi") String id_transaksi,
                                  @Field("total") String total,
                                  @Field("kode") String kode,
                                  @Field("nama_penerima") String nama_penerima,
                                  @Field("alamat") String alamat,
                                  @Field("kelurahan") String kelurahan,
                                  @Field("kecamatan") String kecamatan,
                                  @Field("kota") String kota,
                                  @Field("provinsi") String provinsi,
                                  @Field("kode_pos") String kode_pos,
                                  @Field("nomor") String nomor,
                                  @Field("keterangan") String keterangan,
                                  @Field("id_member[]") ArrayList<String> id_member,
                                  @Field("total_belanja[]") ArrayList<String> total_belanja,
                                  @Field("total_berat[]") ArrayList<String> total_berat,
                                  @Field("kurir[]") ArrayList<String> id_kurir,
                                  @Field("layanan_kurir[]") ArrayList<String> layanan_kurir,
                                  @Field("ongkir[]") ArrayList<String> ongkir,
                                  @Field("biaya_admin") String biaya_admin);

    @FormUrlEncoded
    @POST("checkout_ewallet.php")
    Call<ResponseEwallet> checkoutEwallet(@Field("id_customer") String id_customer,
                                          @Field("id_transaksi") String id_transaksi,
                                          @Field("total") String total,
                                          @Field("kode") String kode,
                                          @Field("nama_penerima") String nama_penerima,
                                          @Field("alamat") String alamat,
                                          @Field("kelurahan") String kelurahan,
                                          @Field("kecamatan") String kecamatan,
                                          @Field("kota") String kota,
                                          @Field("provinsi") String provinsi,
                                          @Field("kode_pos") String kode_pos,
                                          @Field("nomor") String nomor,
                                          @Field("keterangan") String keterangan,
                                          @Field("id_member[]") ArrayList<String> id_member,
                                          @Field("total_belanja[]") ArrayList<String> total_belanja,
                                          @Field("total_berat[]") ArrayList<String> total_berat,
                                          @Field("kurir[]") ArrayList<String> id_kurir,
                                          @Field("layanan_kurir[]") ArrayList<String> layanan_kurir,
                                          @Field("ongkir[]") ArrayList<String> ongkir,
                                          @Field("biaya_admin") String biaya_admin);

    @FormUrlEncoded
    @POST("invoice.php")
    Call<ResponseInvoice> invoice(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("checkout_qrcode.php")
    Call<ResponseQris> checkoutQris(@Field("id_customer") String id_customer,
                                    @Field("id_transaksi") String id_transaksi,
                                    @Field("total") String total,
                                    @Field("nama_penerima") String nama_penerima,
                                    @Field("alamat") String alamat,
                                    @Field("kelurahan") String kelurahan,
                                    @Field("kecamatan") String kecamatan,
                                    @Field("kota") String kota,
                                    @Field("provinsi") String provinsi,
                                    @Field("kode_pos") String kode_pos,
                                    @Field("no_hp") String no_hp,
                                    @Field("keterangan") String keterangan,
                                    @Field("id_member[]") ArrayList<String> id_member,
                                    @Field("total_belanja[]") ArrayList<String> total_belanja,
                                    @Field("total_berat[]") ArrayList<String> total_berat,
                                    @Field("kurir[]") ArrayList<String> kurir,
                                    @Field("layanan_kurir[]") ArrayList<String> layanan_kurir,
                                    @Field("ongkir[]") ArrayList<String> ongkir,
                                    @Field("biaya_admin") String biaya_admin);

    @FormUrlEncoded
    @POST("history_transaksi.php")
    Call<ResponseStatusTransaksi> historyTransaksi(@Field("id_customer") String id_customer,
                                                   @Field("status") String status);

    @FormUrlEncoded
    @POST("detail_transaksi.php")
    Call<ResponseDetailTransaksi> detailTransaksi(@Field("id_transaksi") String id_transaksi,
                                                  @Field("id_member") String id_member);

    @FormUrlEncoded
    @POST("jadwal_dokter.php")
    Call<ResponseJadwalDokter> jadwalDokter(@Field("id_dokter") String id_dokter);

}
