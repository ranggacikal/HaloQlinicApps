package id.luvie.luvieapps.api;

import id.luvie.luvieapps.model.artikel.ResponseArtikel;
import id.luvie.luvieapps.model.batalkanKonsultasi.ResponseBatalkanKonsultasi;
import id.luvie.luvieapps.model.biayaAdmin.ResponseBiayaAdmin;
import id.luvie.luvieapps.model.cariDokter.ResponseCariDokter;
import id.luvie.luvieapps.model.cariProduk.ResponseCariProduk;
import id.luvie.luvieapps.model.cariProdukKategori.ResponseCariProdukKategori;
import id.luvie.luvieapps.model.cekKonsultasi.ResponseCekKonsultasi;
import id.luvie.luvieapps.model.chatOnline.ResponseChatOnline;
import id.luvie.luvieapps.model.checkRecipe.ResponseCheckRecipe;
import id.luvie.luvieapps.model.checkStatus.ResponseCheckStatus;
import id.luvie.luvieapps.model.daftar.ResponseDaftar;
import id.luvie.luvieapps.model.dataEkspedisi.ResponseDataEkspedisi;
import id.luvie.luvieapps.model.dataKeranjang.ResponseDataKeranjang;
import id.luvie.luvieapps.model.detailAlergiObat.ResponseDetailAlergiObat;
import id.luvie.luvieapps.model.detailArtikel.ResponseDetailArtikel;
import id.luvie.luvieapps.model.detailDokter.ResponseDetailDokter;
import id.luvie.luvieapps.model.detailHistory.ResponseDetailHistory;
import id.luvie.luvieapps.model.detailProduk.ResponseDetailProduk;
import id.luvie.luvieapps.model.detailProdukMitra.ResponseDetailProdukMitra;
import id.luvie.luvieapps.model.detailTransaksi.ResponseDetailTransaksi;
import id.luvie.luvieapps.model.dokterMitra.ResponseDokterMitra;
import id.luvie.luvieapps.model.dokterOnTersedia.ResponseDokterOnTersedia;
import id.luvie.luvieapps.model.dokterSpesialis.ResponseDokterSpesialis;
import id.luvie.luvieapps.model.editAkun.ResponseEditAkun;
import id.luvie.luvieapps.model.ewallet.ResponseEwallet;
import id.luvie.luvieapps.model.ewalletKonsultasi.ResponseEwalletKonsultasi;
import id.luvie.luvieapps.model.ewalletOvo.ResponseOvo;
import id.luvie.luvieapps.model.getPlayerId.ResponseGetPlayerId;
import id.luvie.luvieapps.model.hapusKeranjang.ResponseHapusKeranjang;
import id.luvie.luvieapps.model.invoice.ResponseInvoice;
import id.luvie.luvieapps.model.invoiceKonsultasi.ResponseInvoiceKonsultasi;
import id.luvie.luvieapps.model.invoiceKonsultasiQR.ResponseInvoiceKonsultasiQr;
import id.luvie.luvieapps.model.jadwalDokter.ResponseJadwalDokter;
import id.luvie.luvieapps.model.jamDokter.ResponseJamDokter;
import id.luvie.luvieapps.model.kategoriProduk.ResponseKategoriProduk;
import id.luvie.luvieapps.model.kategoriXendit.ResponseKategoriXendit;
import id.luvie.luvieapps.model.kebijakanPrivasi.ResponseKebijakanPrivasi;
import id.luvie.luvieapps.model.kecamatan.ResponseDataKecamatan;
import id.luvie.luvieapps.model.kelurahan.ResponseKelurahan;
import id.luvie.luvieapps.model.konsultasi.ResponseKonsultasi;
import id.luvie.luvieapps.model.kota.ResponseDataKota;
import id.luvie.luvieapps.model.listAlergiObat.ResponseListAlergiObat;
import id.luvie.luvieapps.model.listDokter.ResponseListDokter;
import id.luvie.luvieapps.model.listDokterAktif.ResponseDataDokterAktif;
import id.luvie.luvieapps.model.listDokterAktifHome.ResponseDokterAktifHome;
import id.luvie.luvieapps.model.listDokterTersedia.ResponseDokterTersedia;
import id.luvie.luvieapps.model.listDokterumum.ResponseDataDokterUmum;
import id.luvie.luvieapps.model.listKonsultasi.ResponseListKonsultasi;
import id.luvie.luvieapps.model.listPesanan.ResponseListPesanan;
import id.luvie.luvieapps.model.listRecipe.ResponseListRecipe;
import id.luvie.luvieapps.model.listTebusObat.ResponseListTebusObat;
import id.luvie.luvieapps.model.loginapi.ResponseLoginUser;
import id.luvie.luvieapps.model.logingoogle.ResponseLoginGoogle;
import id.luvie.luvieapps.model.loginmesibo.ResponseLoginMesibo;
import id.luvie.luvieapps.model.lupaPassword.ResponseLupaPassword;
import id.luvie.luvieapps.model.mitraKlinik.ResponseDataMitra;
import id.luvie.luvieapps.model.notifChat.ResponseNotif;
import id.luvie.luvieapps.model.opsiBayar.ResponseOpsiBayar;
import id.luvie.luvieapps.model.produk.ResponseDataProduk;
import id.luvie.luvieapps.model.produkKategori.ResponseProdukKategori;
import id.luvie.luvieapps.model.produkMitra.ResponseProdukMitra;
import id.luvie.luvieapps.model.profileMitra.ResponseProfileMitra;
import id.luvie.luvieapps.model.promoMitra.ResponsePromoMitra;
import id.luvie.luvieapps.model.provinsi.ResponseDataProvinsi;
import id.luvie.luvieapps.model.qriskonsultasi.ResponseQrisKonsultasi;
import id.luvie.luvieapps.model.resepTindakan.ResponseResepTindakan;
import id.luvie.luvieapps.model.statusTransaksiModel.ResponseStatusTransaksi;
import id.luvie.luvieapps.model.summary.ResponseSummary;
import id.luvie.luvieapps.model.tambahAlergiObat.ResponseTambahAlergiObat;
import id.luvie.luvieapps.model.tambahKeranjang.ResponseTambahKeranjang;
import id.luvie.luvieapps.model.ubahPassword.ResponseUbahPassword;
import id.luvie.luvieapps.model.updateKeranjang.ResponseUpdateKeranjang;
import id.luvie.luvieapps.model.updateKonsultasi.ResponseUpdateKonsultasi;
import id.luvie.luvieapps.model.updateKurir.ResponseUpdateKurir;
import id.luvie.luvieapps.model.updatePengiriman.ResponseUpdatePengiriman;
import id.luvie.luvieapps.model.updatePhoto.ResponseUpdatePhoto;
import id.luvie.luvieapps.model.userMesibo.ResponseGetUserMesibo;
import id.luvie.luvieapps.model.xenditQris.ResponseQris;

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

    @FormUrlEncoded
    @POST("cari_produk.php")
    Call<ResponseCariProdukKategori> cariProdukKategori(@Field("nama_produk") String nama_produk,
                                                        @Field("id_kategori") String id_kategori);

    @FormUrlEncoded
    @POST("list_alergi.php")
    Call<ResponseListAlergiObat> dataAlergiObat(@Field("id_customer") String id_customer);

    @FormUrlEncoded
    @POST("form_alergi.php")
    Call<ResponseTambahAlergiObat> tambahAlergiObat(@Field("id_customer") String id_customer,
                                                    @Field("obat") String obat,
                                                    @Field("merek") String merek,
                                                    @Field("golongan") String golongan,
                                                    @Field("efek") String efek);

    @GET("terms_conditions.php")
    Call<ResponseKebijakanPrivasi> getKebijakanPrivasi();

    @FormUrlEncoded
    @POST("detail_alergi.php")
    Call<ResponseDetailAlergiObat> detailAlergiObat(@Field("id") String id);

    @FormUrlEncoded
    @POST("summary.php")
    Call<ResponseSummary> dataSummary(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("list_tindakan.php")
    Call<ResponseResepTindakan> resepTindakan(@Field("id_transaksi") String id_transaksi);

    @GET("list_dokter_aktif.php")
    Call<ResponseDokterOnTersedia> dokterOnTersedia(@Query("tipe") String tipe,
                                                    @Query("status") String status,
                                                    @Query("page") String page);

    @FormUrlEncoded
    @POST("cek_konsultasi.php")
    Call<ResponseCekKonsultasi> cekKonsultasi(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("check_recipe.php")
    Call<ResponseCheckRecipe> checkRecipe(@Field("id_transaksi") String id_transaksi);

    @FormUrlEncoded
    @POST("village.php")
    Call<ResponseKelurahan> dataKelurahan(@Field("subdistrict_id") String subdistrict_id);

}
