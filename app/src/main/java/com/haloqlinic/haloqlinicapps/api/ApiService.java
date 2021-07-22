package com.haloqlinic.haloqlinicapps.api;

import com.haloqlinic.haloqlinicapps.model.artikel.ResponseArtikel;
import com.haloqlinic.haloqlinicapps.model.batalkanKonsultasi.ResponseBatalkanKonsultasi;
import com.haloqlinic.haloqlinicapps.model.biayaAdmin.ResponseBiayaAdmin;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.cariProduk.ResponseCariProduk;
import com.haloqlinic.haloqlinicapps.model.chatOnline.ResponseChatOnline;
import com.haloqlinic.haloqlinicapps.model.checkStatus.ResponseCheckStatus;
import com.haloqlinic.haloqlinicapps.model.daftar.ResponseDaftar;
import com.haloqlinic.haloqlinicapps.model.dataEkspedisi.ResponseDataEkspedisi;
import com.haloqlinic.haloqlinicapps.model.dataKeranjang.ResponseDataKeranjang;
import com.haloqlinic.haloqlinicapps.model.detailArtikel.ResponseDetailArtikel;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResponseDetailDokter;
import com.haloqlinic.haloqlinicapps.model.detailHistory.ResponseDetailHistory;
import com.haloqlinic.haloqlinicapps.model.detailProduk.ResponseDetailProduk;
import com.haloqlinic.haloqlinicapps.model.detailProdukMitra.ResponseDetailProdukMitra;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ResponseDetailTransaksi;
import com.haloqlinic.haloqlinicapps.model.dokterMitra.ResponseDokterMitra;
import com.haloqlinic.haloqlinicapps.model.dokterSpesialis.ResponseDokterSpesialis;
import com.haloqlinic.haloqlinicapps.model.editAkun.ResponseEditAkun;
import com.haloqlinic.haloqlinicapps.model.ewallet.ResponseEwallet;
import com.haloqlinic.haloqlinicapps.model.ewalletKonsultasi.ResponseEwalletKonsultasi;
import com.haloqlinic.haloqlinicapps.model.ewalletOvo.ResponseOvo;
import com.haloqlinic.haloqlinicapps.model.getPlayerId.ResponseGetPlayerId;
import com.haloqlinic.haloqlinicapps.model.hapusKeranjang.ResponseHapusKeranjang;
import com.haloqlinic.haloqlinicapps.model.historyTransaksi.ResponseHistoryTransaksi;
import com.haloqlinic.haloqlinicapps.model.invoice.ResponseInvoice;
import com.haloqlinic.haloqlinicapps.model.invoiceKonsultasi.ResponseInvoiceKonsultasi;
import com.haloqlinic.haloqlinicapps.model.invoiceKonsultasiQR.ResponseInvoiceKonsultasiQr;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ResponseJadwalDokter;
import com.haloqlinic.haloqlinicapps.model.jamDokter.ResponseJamDokter;
import com.haloqlinic.haloqlinicapps.model.kategoriProduk.ResponseKategoriProduk;
import com.haloqlinic.haloqlinicapps.model.kategoriXendit.ResponseKategoriXendit;
import com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseDataKecamatan;
import com.haloqlinic.haloqlinicapps.model.konsultasi.ResponseKonsultasi;
import com.haloqlinic.haloqlinicapps.model.kota.ResponseDataKota;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseListDokter;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;
import com.haloqlinic.haloqlinicapps.model.listDokterAktifHome.ResponseDokterAktifHome;
import com.haloqlinic.haloqlinicapps.model.listDokterTersedia.ResponseDokterTersedia;
import com.haloqlinic.haloqlinicapps.model.listDokterumum.ResponseDataDokterUmum;
import com.haloqlinic.haloqlinicapps.model.listKonsultasi.ResponseListKonsultasi;
import com.haloqlinic.haloqlinicapps.model.listPesanan.ResponseListPesanan;
import com.haloqlinic.haloqlinicapps.model.listRecipe.ResponseListRecipe;
import com.haloqlinic.haloqlinicapps.model.listTebusObat.ResponseListTebusObat;
import com.haloqlinic.haloqlinicapps.model.loginapi.ResponseLoginUser;
import com.haloqlinic.haloqlinicapps.model.logingoogle.Response;
import com.haloqlinic.haloqlinicapps.model.logingoogle.ResponseLoginGoogle;
import com.haloqlinic.haloqlinicapps.model.loginmesibo.ResponseLoginMesibo;
import com.haloqlinic.haloqlinicapps.model.lupaPassword.ResponseLupaPassword;
import com.haloqlinic.haloqlinicapps.model.mitraKlinik.ResponseDataMitra;
import com.haloqlinic.haloqlinicapps.model.notifChat.ResponseNotif;
import com.haloqlinic.haloqlinicapps.model.opsiBayar.ResponseOpsiBayar;
import com.haloqlinic.haloqlinicapps.model.produk.ResponseDataProduk;
import com.haloqlinic.haloqlinicapps.model.produkKategori.ResponseProdukKategori;
import com.haloqlinic.haloqlinicapps.model.produkMitra.ResponseProdukMitra;
import com.haloqlinic.haloqlinicapps.model.profileMitra.ResponseProfileMitra;
import com.haloqlinic.haloqlinicapps.model.promoMitra.ResponsePromoMitra;
import com.haloqlinic.haloqlinicapps.model.provinsi.ResponseDataProvinsi;
import com.haloqlinic.haloqlinicapps.model.qriskonsultasi.ResponseQrisKonsultasi;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.ResponseStatusTransaksi;
import com.haloqlinic.haloqlinicapps.model.tambahKeranjang.ResponseTambahKeranjang;
import com.haloqlinic.haloqlinicapps.model.ubahPassword.ResponseUbahPassword;
import com.haloqlinic.haloqlinicapps.model.updateKeranjang.ResponseUpdateKeranjang;
import com.haloqlinic.haloqlinicapps.model.updateKonsultasi.ResponseUpdateKonsultasi;
import com.haloqlinic.haloqlinicapps.model.updateKurir.ResponseUpdateKurir;
import com.haloqlinic.haloqlinicapps.model.updatePengiriman.ResponseUpdatePengiriman;
import com.haloqlinic.haloqlinicapps.model.updatePhoto.ResponseUpdatePhoto;
import com.haloqlinic.haloqlinicapps.model.userMesibo.ResponseGetUserMesibo;
import com.haloqlinic.haloqlinicapps.model.xenditQris.ResponseQris;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
                                  @Field("password") String password,
                                  @Field("player_id") String player_id);

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
                                          @Field("email") String email,
                                          @Field("player_id") String player_id);

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
    Call<ResponseListDokter> dataDokter(@Query("status") String status,
                                        @Query("page") String page);

    @FormUrlEncoded
    @POST("cari_dokter.php")
    Call<ResponseCariDokter> cariDokter(@Field("nama") String nama,
                                        @Field("status") String status,
                                        @Field("jenis") String jenis);

    @GET("list_dokter_aktif.php")
    Call<ResponseDataDokterAktif> dataDokterAktif(@Query("status") String status,
                                                  @Query("page") String page);
    @FormUrlEncoded
    @POST("detail_dokter.php")
    Call<ResponseDetailDokter> detailDokter(@Field("id_dokter") String id_dokter);

    @GET("produk.php")
    Call<ResponseDataProduk> dataProduk(@Query("page") String page);

    @GET("produk_mitra.php")
    Call<ResponseProdukMitra> dataProdukMitra(@Query("id_member") String id_member,
                                              @Query("page") String page);

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

    @FormUrlEncoded
    @POST("konsultasi_ewallet.php")
    Call<ResponseEwalletKonsultasi> ewalletKonsultasi(@Field("id_transaksi") String id_transaksi,
                                                      @Field("id_customer") String id_customer,
                                                      @Field("total") String total,
                                                      @Field("kode") String kode,
                                                      @Field("nomor") String nomor,
                                                      @Field("biaya_admin") String biaya_admin,
                                                      @Field("id_dokter") String id_dokter,
                                                      @Field("id_kategori") String id_kategori,
                                                      @Field("jadwal") String jadwal,
                                                      @Field("status") String status,
                                                      @Field("external_id") String external_id);

    @FormUrlEncoded
    @POST("list_mitra.php")
    Call<ResponseDataMitra> dataMitra(@Field("status") String status);

    @FormUrlEncoded
    @POST("konsultasi_qrcode.php")
    Call<ResponseQrisKonsultasi> qrisKonsultasi(@Field("id_transaksi") String id_transaksi,
                                                @Field("id_customer") String id_customer,
                                                @Field("total") String total,
                                                @Field("biaya_admin") String biaya_admin,
                                                @Field("id_dokter") String id_dokter,
                                                @Field("id_kategori") String id_kategori,
                                                @Field("jadwal") String jadwal,
                                                @Field("status") String status,
                                                @Field("external_id") String external_id);

    @FormUrlEncoded
    @POST("invoice_konsultasi.php")
    Call<ResponseInvoiceKonsultasi> invoiceKonsultasi(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("invoice_konsultasi.php")
    Call<ResponseInvoiceKonsultasiQr> invoiceKonsultasiQr(@Field("external_id") String external_id);

    @FormUrlEncoded
    @POST("konsultasi.php")
    Call<ResponseKonsultasi> postKonsultasi(@Field("id_customer") String id_customer,
                                            @Field("id") String id,
                                            @Field("id_dokter") String id_dokter,
                                            @Field("jadwal") String jadwal,
                                            @Field("status") String status);

    @FormUrlEncoded
    @POST("history_konsultasi.php")
    Call<ResponseListKonsultasi> listKonsultasi(@Field("id_customer") String id_customer,
                                                @Field("status") String status);

    @FormUrlEncoded
    @POST("cart_recipe.php")
    Call<ResponseListRecipe> listRecipe(@Field("id_customer") String id_customer);

    @FormUrlEncoded
    @POST("check_status.php")
    Call<ResponseCheckStatus> checkStatus(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("update_konsultasi.php")
    Call<ResponseUpdateKonsultasi> updateKonsultasi(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("cancel_payment.php")
    Call<ResponseBatalkanKonsultasi> batalkanKonsultasi(@Field("id_transaksi") String id_transaksi);

    @Multipart
    @POST("update_photo.php")
    Call<ResponseUpdatePhoto> updatePhoto(@Part("id_customer") RequestBody id_customer,
                                          @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("payment_code.php")
    Call<ResponseDetailHistory> detailHistory(@Field("id_transaksi") String id_transaksi,
                                              @Field("status") String status);

    @FormUrlEncoded
    @POST("notif.php")
    Call<ResponseNotif> notifChat(@Field("player_id") String player);

    @GET("kategori.php")
    Call<ResponseKategoriProduk> kategoriProduk();

    @GET("produk.php")
    Call<ResponseProdukKategori> produkKategori(@Query("page") String page,
                                                @Query("id_kategori") String id_kategori);

    @FormUrlEncoded
    @POST("profile_mitra.php")
    Call<ResponseProfileMitra> profileMitra(@Field("id_member") String id_member);

    @GET("list_dokter_aktif.php")
    Call<ResponseDokterAktifHome> dokterAktifHome(@Query("tipe") String tipe,
                                                  @Query("status") String status,
                                                  @Query("page") String page);

    @GET("list_dokter_aktif.php")
    Call<ResponseDokterTersedia> dokterTersedia(@Query("tipe") String tipe,
                                                @Query("status") String status,
                                                @Query("page") String page);

    @GET("list_dokter.php")
    Call<ResponseDokterSpesialis> dokterSpesialis(@Query("status") String status,
                                                  @Query("page") String page);

    @FormUrlEncoded
    @POST("list_jadwal.php")
    Call<ResponseJamDokter> jamDokter(@Field("id_dokter") String id_dokter,
                                      @Field("tanggal") String tanggal);

    @FormUrlEncoded
    @POST("get_playerId.php")
    Call<ResponseGetPlayerId> getPlayerId(@Field("id_customer") String id_customer,
                                          @Field("player_id") String player_id);

    @FormUrlEncoded
    @POST("chat_online.php")
    Call<ResponseChatOnline> chatOnline(@Field("id_dokter") String id_dokter);

    @FormUrlEncoded
    @POST("list_pesan.php")
    Call<ResponseListTebusObat> listTebusObat(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("update_pengiriman.php")
    Call<ResponseUpdatePengiriman> updatePengiriman(@Field("id_pengiriman") String id_pengiriman);

    @GET("list_artikel.php")
    Call<ResponseArtikel> getArtikel(@Query("jenis") String jenis,
                                     @Query("page") String page);

    @FormUrlEncoded
    @POST("detail_artikel.php")
    Call<ResponseDetailArtikel> detailArtikel(@Field("id_artikel") String id_artikel);

    @FormUrlEncoded
    @POST("update_password.php")
    Call<ResponseUbahPassword> ubahPassword(@Field("id_customer") String id_customer,
                                            @Field("password1") String password1,
                                            @Field("password2") String password2);

    @GET("list_dokter.php")
    Call<ResponseDataDokterUmum> dataDokterUmum(@Query("status") String status,
                                                @Query("page") String page);

    @GET("list_dokter_mitra.php")
    Call<ResponseDokterMitra> dataDokterMitra(@Query("kode") String kode);

    @FormUrlEncoded
    @POST("forgot_password.php")
    Call<ResponseLupaPassword> lupaPassword(@Field("email") String email);

    @GET("promo.php")
    Call<ResponsePromoMitra> promoMitra(@Query("id_member") String id_member);

    @FormUrlEncoded
    @POST("detail_produk_promo.php")
    Call<ResponseDetailProdukMitra> detailProdukMitra(@Field("id_produk") String id_produk);

}
