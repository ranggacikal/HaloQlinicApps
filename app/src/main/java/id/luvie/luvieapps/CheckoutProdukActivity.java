package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.KategoriBayarAdapter;
import id.luvie.luvieapps.adapter.MitraCheckoutAdapter;
import id.luvie.luvieapps.adapter.MitraTindakanAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.api.apiRajaOngkir.ConfigRajaOngkir;
import id.luvie.luvieapps.model.biayaAdmin.ResponseBiayaAdmin;
import id.luvie.luvieapps.model.checkRecipe.ResponseCheckRecipe;
import id.luvie.luvieapps.model.ewallet.Actions;
import id.luvie.luvieapps.model.ewallet.ResponseEwallet;
import id.luvie.luvieapps.model.ewalletOvo.ResponseOvo;
import id.luvie.luvieapps.model.kategoriXendit.DataItem;
import id.luvie.luvieapps.model.kategoriXendit.ResponseKategoriXendit;
import id.luvie.luvieapps.model.kecamatanRajaOngkir.ResponseKecamatanRajaOngkir;
import id.luvie.luvieapps.model.kelurahan.ResponseItem;
import id.luvie.luvieapps.model.kelurahan.ResponseKelurahan;
import id.luvie.luvieapps.model.kotaRajaOngkir.ResponseKotaRajaOngkir;
import id.luvie.luvieapps.model.listPesanan.ProdukItem;
import id.luvie.luvieapps.model.listPesanan.ResponseListPesanan;
import id.luvie.luvieapps.model.provinsiRajaOngkir.ResponseProvinsiRajaOngkir;
import id.luvie.luvieapps.model.provinsiRajaOngkir.ResultsItem;
import id.luvie.luvieapps.model.resepTindakan.ResponseResepTindakan;
import id.luvie.luvieapps.model.resepTindakan.TindakanItem;
import id.luvie.luvieapps.model.xenditQris.ResponseQris;

import com.google.android.material.math.MathUtils;
import com.xendit.Models.Card;
import com.xendit.Models.Token;
import com.xendit.Models.XenditError;
import com.xendit.TokenCallback;
import com.xendit.Xendit;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutProdukActivity extends AppCompatActivity {

    Spinner spinnerProvinsi, spinnerKota, spinnerKecamatan, spinnerKelurahan;
    Button btnLanjutkanDataPenerima;
    EditText edtNamaPenerima, edtAlamat, edtKodepos, edtKelurahan;
    ScrollView scrollDataPenerima, scrollPembayaran;
    RecyclerView rvPesananCheckout, rvPilihKurir, rvKategoriBayar, rvTindakanCheckout;
    LinearLayout linearKurir, linearMetodeBayarDipilih;
    RelativeLayout relativeBiayaAdmin, relativeHargaTindakan;
    TextView txtOngkir, txtTotalPesanan, txtBiayaAdmin, txtTotalBayar, txtKategoriMetodeBayar, txtNamaOpsiBayar, txtGantiMetodeBayar;
    TextView txtHargaTindakan;
    Button btnCheckout;
    ImageView imgBack, imgOpsiBayar;

    List<id.luvie.luvieapps.model.listPesanan.DataItem> dataItems;
    List<id.luvie.luvieapps.model.listPesanan.DataItem> dataPesanan;

    public ArrayList<Object> dataKurir = new ArrayList<>();

    List<ResultsItem> dataProvinsi;
    List<id.luvie.luvieapps.model.kotaRajaOngkir.ResultsItem> dataKota;
    List<id.luvie.luvieapps.model.kecamatanRajaOngkir.ResultsItem> dataKecamatan;
    List<ResponseItem> dataKelurahan;

    ProgressDialog progressDialog;

    String namaPenerima, id_provinsi, id_kota, alamat, kodePos, kelurahan, nomerTelepon;

    public static final String PUBLISHABLE_KEY = "xnd_public_development_ol47f4f0kEfPw8dc7ZifHxwGaBJhGsDQCqG0sdPKkw50VSWSarz9ubK71YsksPG";

    String key = "9bc530edfde2ffdba603d779832349cf";

    public String id_transaksi;

    public String id_kecamatan;

    public String biayaAdmin = "";

    int sumOngkir = 0;
    int sumTotalBelanja = 0;
    int sumBiayaAdmin = 0;
    int totalSeluruh = 0;
    int harga_tindakan = 0;
    int tindakan;

    public String kategori_bayar;

    ArrayList<Integer> ongkirData;
    ArrayList<Integer> totalPesanan = new ArrayList<Integer>();

    public CheckoutProdukActivity() {

    }

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_produk);

        preferencedConfig = new SharedPreferencedConfig(this);

        spinnerProvinsi = findViewById(R.id.spinner_provinsi_pembayaran);
        spinnerKota = findViewById(R.id.spinner_kabupaten_pembayaran);
        spinnerKecamatan = findViewById(R.id.spinner_kecamatan_pembayaran);
        btnLanjutkanDataPenerima = findViewById(R.id.btn_lanjutkan_data_penerima);
        edtNamaPenerima = findViewById(R.id.edt_nama_pembayaran);
        edtAlamat = findViewById(R.id.edt_alamat_pembayaran);
        edtKodepos = findViewById(R.id.edt_kode_pos_pembayaran);
        scrollDataPenerima = findViewById(R.id.scrollViewDataPenerima);
        scrollPembayaran = findViewById(R.id.scrollViewMetodeBayar);
        rvPesananCheckout = findViewById(R.id.recycler_pesanan_checkout);
        linearKurir = findViewById(R.id.linear_kurir);
        rvPilihKurir = findViewById(R.id.recycler_kurir);
        txtOngkir = findViewById(R.id.text_biaya_ongkir);
        rvKategoriBayar = findViewById(R.id.recycler_metode_pembayaran);
        txtTotalPesanan = findViewById(R.id.text_total_harga_pesanan);
        txtHargaTindakan = findViewById(R.id.text_total_harga_tindakan);
        relativeHargaTindakan = findViewById(R.id.relative_harga_tindakan);
        relativeBiayaAdmin = findViewById(R.id.relative_biaya_admin);
        txtBiayaAdmin = findViewById(R.id.text_biaya_admin);
        txtTotalBayar = findViewById(R.id.text_total_bayar_pembayaran);
        btnCheckout = findViewById(R.id.btn_checkout_produk);
//        edtKelurahan = findViewById(R.id.edt_kelurahan_pembayaran);
        edtKelurahan = findViewById(R.id.edt_kelurahan_pembayaran);
        imgBack = findViewById(R.id.img_back_pembayaran);
        linearMetodeBayarDipilih = findViewById(R.id.linear_metode_bayar_dipilih);
        txtKategoriMetodeBayar = findViewById(R.id.text_kategori_metode_pembayaran);
        txtNamaOpsiBayar = findViewById(R.id.text_nama_opsi_bayar);
        imgOpsiBayar = findViewById(R.id.img_metode_bayar_dipilih);
        txtGantiMetodeBayar = findViewById(R.id.text_ganti_metode_bayar);
        rvTindakanCheckout = findViewById(R.id.recycler_pesanan_tindakan_checkout);

        initSpinnerProvinsi();

        initMetodeBayarDipilih();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
                startActivity(new Intent(CheckoutProdukActivity.this, KeranjangActivity.class));
                finish();
            }
        });

        Log.d("checkData", "id_customer: " + preferencedConfig.getPreferenceIdCustomer());

        id_transaksi = getIntent().getStringExtra("id_transaksi");

        Log.d("idTransaksiPembayaran", "onCreate: " + id_transaksi);

        Log.d("checkDataPreference", "idCustomer: " + preferencedConfig.getPreferenceIdCustomer());
        Log.d("checkDataPreference", "idOpsiBayar: " + preferencedConfig.getPreferenceIdOpsiBayar());

        progressDialog = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialog.setMessage("Memuat Data Provinsi");
        progressDialog.show();

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_provinsi = dataProvinsi.get(position).getProvinceId();
                Log.d("checkData", "provinsi: " + id_provinsi);
                initSpinnerKota(id_provinsi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_kota = dataKota.get(position).getCityId();
                Log.d("checkData", "kota: " + id_kota);
                initSpinnerKecamatan(id_kota);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_kecamatan = dataKecamatan.get(position).getSubdistrictId();
                Log.d("checkData", "kecamatan: " + id_kecamatan);
//                initSpinnerKelurahan(id_kecamatan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnLanjutkanDataPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiDataPenerima();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOut();
            }
        });

        txtGantiMetodeBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearMetodeBayarDipilih.setVisibility(View.GONE);
                rvKategoriBayar.setVisibility(View.VISIBLE);
            }
        });

        rvTindakanCheckout.setHasFixedSize(true);
        rvTindakanCheckout.setLayoutManager(new LinearLayoutManager(CheckoutProdukActivity.this));
        cekJumlahProduk();
        loadDataTindakan();
//        loadDataProduk();
//        loadPilihKurir();
        loadDataMitra();
        loadKategoriBayar();
        loadBiayaAdmin();
    }

    private void initSpinnerKelurahan(String id_kecamatan) {

        ProgressDialog progressDialogKelurahan = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialogKelurahan.setMessage("Memuat data kelurahan");
        progressDialogKelurahan.show();

        Log.d("cekIdKecamatan", "initSpinnerKelurahan: "+id_kecamatan);

        ConfigRetrofit.service.dataKelurahan(id_kecamatan).enqueue(new Callback<ResponseKelurahan>() {
            @Override
            public void onResponse(Call<ResponseKelurahan> call, Response<ResponseKelurahan> response) {
                if (response.isSuccessful()) {
                    progressDialogKelurahan.dismiss();
                    dataKelurahan = response.body().getResponse();
                    List<String> listSpinnerKelurahan = new ArrayList<String>();

                    for (int i = 0; i < dataKelurahan.size(); i++) {
                        listSpinnerKelurahan.add(dataKelurahan.get(i).getVillageName());
                    }

                    ArrayAdapter<String> adapterKelurahan = new ArrayAdapter<String>(CheckoutProdukActivity.this,
                            R.layout.spinner_item, listSpinnerKelurahan);

                    adapterKelurahan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerKelurahan.setAdapter(adapterKelurahan);
                } else {
                    progressDialogKelurahan.dismiss();
                    Toast.makeText(CheckoutProdukActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKelurahan> call, Throwable t) {
                progressDialogKelurahan.dismiss();
                Toast.makeText(CheckoutProdukActivity.this, "Koneksi Error",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void cekJumlahProduk() {

        ConfigRetrofit.service.checkRecipe(id_transaksi).enqueue(new Callback<ResponseCheckRecipe>() {
            @Override
            public void onResponse(Call<ResponseCheckRecipe> call, Response<ResponseCheckRecipe> response) {
                if (response.isSuccessful()) {

                    tindakan = response.body().getTindakan();
                    int produk = response.body().getProduk();

                    if (tindakan == 0) {
                        rvTindakanCheckout.setVisibility(View.GONE);
                    } else if (produk == 0) {
                        rvPesananCheckout.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseCheckRecipe> call, Throwable t) {

            }
        });

    }

    private void loadDataTindakan() {

        android.app.ProgressDialog progressDialogTindakan = new
                ProgressDialog(CheckoutProdukActivity.this);
        progressDialogTindakan.setMessage("Memuat Data");
        progressDialogTindakan.show();

        ConfigRetrofit.service.resepTindakan(id_transaksi).enqueue(new Callback<ResponseResepTindakan>() {
            @Override
            public void onResponse(Call<ResponseResepTindakan> call, Response<ResponseResepTindakan> response) {
                if (response.isSuccessful()) {

                    progressDialogTindakan.dismiss();
                    List<id.luvie.luvieapps.model.resepTindakan.DataItem> dataTebusTindakan = response.body().getData();
                    List<TindakanItem> dataTindakan = null;
                    ArrayList<String> dataStringTindakan = new ArrayList<>();
                    List<TindakanItem> testArray = new ArrayList<TindakanItem>();
                    List<Integer> hargaTindakan = new ArrayList<>();

                    Log.d("cekDataList", "dataTebusObatSize: " + dataTebusTindakan.size());

                    for (int a = 0; a < dataTebusTindakan.size(); a++) {
                        dataTindakan = dataTebusTindakan.get(a).getTindakan();
                        for (int b = 0; b < dataTindakan.size(); b++) {

                            String test = dataTindakan.get(b).toString();
                            String harga = dataTindakan.get(b).getHarga().toString();
                            Log.d("testDataProduk", "onResponse: " + test);
                            testArray.add(dataTindakan.get(b));
                            hargaTindakan.add(Integer.parseInt(harga));
                            Log.d("testArrayData", "onResponse: " + testArray);

                        }
                    }

                    if (hargaTindakan.size()<1){
                        harga_tindakan = 0;
                        relativeHargaTindakan.setVisibility(View.GONE);
                    }else{
                        relativeHargaTindakan.setVisibility(View.VISIBLE);
                        for (int number : hargaTindakan){
                            harga_tindakan += number;
                        }
                    }

                    Log.d("cekHargaTindakan", "onResponse: "+harga_tindakan);
                    txtHargaTindakan.setText("Rp" + NumberFormat.getInstance().format(harga_tindakan));


                    Log.d("cekDataList", "dataProduk: " + dataStringTindakan);
                    Log.d("cekDataList", "dataProdukSize: " + dataStringTindakan.size());
                    MitraTindakanAdapter adapter = new MitraTindakanAdapter(CheckoutProdukActivity.this, dataTebusTindakan,
                            CheckoutProdukActivity.this);
                    rvTindakanCheckout.setAdapter(adapter);

                } else {
                    progressDialogTindakan.dismiss();
                    Toast.makeText(CheckoutProdukActivity.this, "Data Tindakan Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseResepTindakan> call, Throwable t) {
                progressDialogTindakan.dismiss();
                Toast.makeText(CheckoutProdukActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void loadDataMitra() {

        rvPesananCheckout.setHasFixedSize(true);
        rvPesananCheckout.setLayoutManager(new LinearLayoutManager(CheckoutProdukActivity.this));

        ConfigRetrofit.service.listPesanan(id_transaksi).enqueue(new Callback<ResponseListPesanan>() {
            @Override
            public void onResponse(Call<ResponseListPesanan> call, Response<ResponseListPesanan> response) {
                if (response.isSuccessful()) {

                    dataPesanan = response.body().getData();
                    dataItems = response.body().getData();
                    List<ProdukItem> dataProduk = null;

                    totalPesanan.clear();

                    for (int a = 0; a < dataPesanan.size(); a++) {
                        totalPesanan.add(Integer.parseInt(dataPesanan.get(a).getTotalBelanja()));
                        dataProduk = dataPesanan.get(a).getProduk();

                    }

                    sumOngkir = Integer.parseInt(response.body().getTotalOngkir());

                    Log.d("checkOngkirData", "onResponse: " + sumOngkir);

                    txtOngkir.setText("Rp" + NumberFormat.getInstance().format(sumOngkir));

//                    for (int numTotal : totalPesanan){
//                        sumTotalBelanja = sumTotalBelanja + numTotal;
//                    }

                    sumTotalBelanja = 0;
                    for (int i = 0; i < totalPesanan.size(); i++) {
                        sumTotalBelanja += totalPesanan.get(i);
                    }

                    Log.d("checkTotalPesanan", "size: " + sumTotalBelanja);
                    txtTotalPesanan.setText("Rp" + NumberFormat.getInstance().format(sumTotalBelanja));

                    MitraCheckoutAdapter adapterMitra = new
                            MitraCheckoutAdapter(CheckoutProdukActivity.this,
                            dataPesanan, CheckoutProdukActivity.this);

                    rvPesananCheckout.setAdapter(adapterMitra);


                } else {
                    Toast.makeText(CheckoutProdukActivity.this, "Gagal Memuat Data",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListPesanan> call, Throwable t) {
                Toast.makeText(CheckoutProdukActivity.this, "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initMetodeBayarDipilih() {

        String kategoriOpsiBayar = preferencedConfig.getPreferenceKategoriBayar();
        String urlImage = preferencedConfig.getPreferenceImageOpsiBayar();
        String namaOpsiBayar = preferencedConfig.getPreferenceNamaOpsiBayar();

        Log.d("dataKategoriBayar", "kategoriBayar: " + kategoriOpsiBayar);
        Log.d("dataKategoriBayar", "image: " + urlImage);
        Log.d("dataKategoriBayar", "namaOpsi: " + namaOpsiBayar);
        Log.d("dataKategoriBayar", "kodeOpsi: " + preferencedConfig.getPreferenceKodeOpsiBayar());

        if (!namaOpsiBayar.equals("") && !urlImage.equals("") && !kategoriOpsiBayar.equals("") &&
                !preferencedConfig.getPreferenceIdOpsiBayar().equals("") ||
                !preferencedConfig.getPreferenceKodeOpsiBayar().equals("")) {

            linearMetodeBayarDipilih.setVisibility(View.VISIBLE);
            rvKategoriBayar.setVisibility(View.GONE);
            txtKategoriMetodeBayar.setText(kategoriOpsiBayar);
            txtNamaOpsiBayar.setText(namaOpsiBayar);

            Glide.with(CheckoutProdukActivity.this).load(urlImage)
                    .into(imgOpsiBayar);

        } else {
            linearMetodeBayarDipilih.setVisibility(View.GONE);
            rvKategoriBayar.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
        finish();
    }

    private void checkOut() {

        String kategori_bayar = preferencedConfig.getPreferenceKategoriBayar();

        if (kategori_bayar.equals("")) {
            Toast.makeText(this, "Anda belum memilih kategori pembayaran", Toast.LENGTH_SHORT).show();
            return;
        }

        if (kategori_bayar.equals("E-Wallets")) {
            checkoutEwallet();
        } else if (kategori_bayar.equals("Kartu Kredit/Debit")) {

            tampilDialogKartuKredit();

        } else if (kategori_bayar.equals("QR Code")) {

            checkOutQris();

        }


    }

    private void checkOutQris() {

        ArrayList<String> id_member = new ArrayList<>();
        ArrayList<String> total_belanja = new ArrayList<>();
        ArrayList<String> total_berat = new ArrayList<>();
        ArrayList<String> kurir = new ArrayList<>();
        ArrayList<String> layanan_kurir = new ArrayList<>();
        ArrayList<String> ongkir = new ArrayList<>();

        for (int a = 0; a < dataItems.size(); a++) {

            id_member.add(dataItems.get(a).getIdMember());
            total_belanja.add(dataItems.get(a).getTotalBelanja());
            total_berat.add(dataItems.get(a).getTotalBerat());
            kurir.add(dataItems.get(a).getKurir());
            layanan_kurir.add(dataItems.get(a).getLayananKurir());
            ongkir.add(dataItems.get(a).getOngkir());

        }

        ProgressDialog progressDialogQris = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialogQris.setMessage("Melakukan checkout");
        progressDialogQris.show();

        Log.d("cekParamQris", "idCustomer: " + preferencedConfig.getPreferenceIdCustomer());
        Log.d("cekParamQris", "idTransaksi: " + id_transaksi);
        Log.d("cekParamQris", "totalSeluruh: " + totalSeluruh);
        Log.d("cekParamQris", "namaPenrima: " + namaPenerima);
        Log.d("cekParamQris", "alamat: " + alamat);
        Log.d("cekParamQris", "kelurahan: " + kelurahan);
        Log.d("cekParamQris", "id_kecamatan: " + id_kecamatan);
        Log.d("cekParamQris", "id_kota: " + id_kota);
        Log.d("cekParamQris", "id_provinsi: " + id_provinsi);
        Log.d("cekParamQris", "kodePos: " + kodePos);
        Log.d("cekParamQris", "id_member: " + id_member);
        Log.d("cekParamQris", "totalBelanja: " + total_belanja);
        Log.d("cekParamQris", "totalBerat: " + total_berat);
        Log.d("cekParamQris", "kurir: " + kurir);
        Log.d("cekParamQris", "layananKurir: " + layanan_kurir);
        Log.d("cekParamQris", "ongkir: " + ongkir);
        Log.d("cekParamQris", "biayaAdmin: " + sumBiayaAdmin);

        ConfigRetrofit.service.checkoutQris(preferencedConfig.getPreferenceIdCustomer(), id_transaksi, String.valueOf(totalSeluruh),
                namaPenerima, alamat, kelurahan, id_kecamatan, id_kota, id_provinsi, kodePos, "", "", id_member,
                total_belanja, total_berat, kurir, layanan_kurir, ongkir,
                String.valueOf(sumBiayaAdmin), String.valueOf(tindakan))
                .enqueue(new Callback<ResponseQris>() {
                    @Override
                    public void onResponse(Call<ResponseQris> call, Response<ResponseQris> response) {

                        if (response.isSuccessful()) {
                            progressDialogQris.dismiss();

                            String qr_string = response.body().getQrString();

                            Toast.makeText(CheckoutProdukActivity.this, "Berhasil checkout Qris", Toast.LENGTH_SHORT).show();
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");

                            Intent intent = new Intent(CheckoutProdukActivity.this, InvoiceActivity.class);
                            intent.putExtra("qr_string", qr_string);
                            intent.putExtra("id_transaksi", id_transaksi);
                            startActivity(intent);
                            finish();

                        } else {
                            progressDialogQris.dismiss();
                            Toast.makeText(CheckoutProdukActivity.this, "Gagal melakukan checkout", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseQris> call, Throwable t) {
                        progressDialogQris.dismiss();
                        Toast.makeText(CheckoutProdukActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void tampilDialogKartuKredit() {

        Dialog dialogKartuKredit = new Dialog(CheckoutProdukActivity.this);
        dialogKartuKredit.setContentView(R.layout.dialog_kartu_kredit);

        EditText edtCardNumber = dialogKartuKredit.findViewById(R.id.edt_card_number);
        EditText edtValidMonth = dialogKartuKredit.findViewById(R.id.edt_valid_month);
        EditText edtValidYears = dialogKartuKredit.findViewById(R.id.edt_valid_year);
        EditText edtCvv = dialogKartuKredit.findViewById(R.id.edt_cvv);
        Button btnBayarKartuKredit = dialogKartuKredit.findViewById(R.id.btn_bayar_kartu_kredit);

        dialogKartuKredit.show();

        btnBayarKartuKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cardNumber = edtCardNumber.getText().toString();
                String validMonth = edtValidMonth.getText().toString();
                String validYears = edtValidYears.getText().toString();
                String cvv = edtCvv.getText().toString();

                if (cardNumber.isEmpty()) {
                    edtCardNumber.setError("card number tidak boleh kosong");
                    edtCardNumber.requestFocus();
                    return;
                }

                if (validMonth.isEmpty()) {
                    edtValidMonth.setError("Valid month tidak boleh kosong");
                    edtValidMonth.requestFocus();
                    return;
                }

                if (validYears.isEmpty()) {
                    edtValidYears.setError("Valid years tidak boleh kosong");
                    edtValidYears.requestFocus();
                    return;
                }

                if (cvv.isEmpty()) {
                    edtCvv.setError("CVV tidak boleh kosong");
                    edtCvv.requestFocus();
                    return;
                }

                createToken(cardNumber, validMonth, validYears, cvv);

            }
        });

    }

    private void createToken(String cardNumber, String validMonth, String validYears, String cvv) {

        Xendit xendit = new Xendit(CheckoutProdukActivity.this, PUBLISHABLE_KEY);

        Card card = new Card(cardNumber, validMonth, validYears, cvv);

        Log.d("checkCard", "createToken: " + card);

        xendit.createSingleUseToken(card, totalSeluruh, true, "", new TokenCallback() {
            @Override
            public void onSuccess(Token token) {
                Toast.makeText(CheckoutProdukActivity.this, "token: " + token.getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(XenditError error) {
                Log.d("tokenXendit", "onError: " + error);
            }
        });

    }

    private void checkoutEwallet() {

        if (preferencedConfig.getPreferenceKodeOpsiBayar().equals("ID_OVO")) {

            tampilDialogNomor();

        } else {

            checkoutEwalletLainnya();

        }

    }

    private void checkoutEwalletLainnya() {

        String keterangan = "";
        ArrayList<String> id_member = new ArrayList<>();
        ArrayList<String> total_belanja = new ArrayList<>();
        ArrayList<String> total_berat = new ArrayList<>();
        ArrayList<String> kurir = new ArrayList<>();
        ArrayList<String> layanan_kurir = new ArrayList<>();
        ArrayList<String> ongkir = new ArrayList<>();

        for (int a = 0; a < dataItems.size(); a++) {

            id_member.add(dataItems.get(a).getIdMember());
            total_belanja.add(dataItems.get(a).getTotalBelanja());
            total_berat.add(dataItems.get(a).getTotalBerat());
            kurir.add(dataItems.get(a).getKurir());
            layanan_kurir.add(dataItems.get(a).getLayananKurir());
            ongkir.add(dataItems.get(a).getOngkir());

        }


        Log.d("checkDataKirim", "ongkir: " + ongkir);
        Log.d("checkDataKirim", "biayaAdmin: " + biayaAdmin);
        Log.d("checkDataKirim", "jumlahBayar: " + totalSeluruh);
        Log.d("checkDataKirim", "dataPesanan: " + dataPesanan);

        ProgressDialog progressDialog = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialog.setMessage("Melakukan checkout");
        progressDialog.show();

        ConfigRetrofit.service.checkoutEwallet(preferencedConfig.getPreferenceIdCustomer(), id_transaksi, String.valueOf(totalSeluruh),
                preferencedConfig.getPreferenceKodeOpsiBayar(), namaPenerima, alamat, kelurahan, id_kecamatan, id_kota, id_provinsi,
                kodePos, "0", "", id_member, total_belanja, total_berat, kurir, layanan_kurir, ongkir, String.valueOf(sumBiayaAdmin), String.valueOf(tindakan))
                .enqueue(new Callback<ResponseEwallet>() {
                    @Override
                    public void onResponse(Call<ResponseEwallet> call, Response<ResponseEwallet> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();

                            Actions actions = response.body().getActions();
                            String deeplink = (String) actions.getMobileDeeplinkCheckoutUrl();
                            String mobileUrl = actions.getMobileWebCheckoutUrl();

                            Log.d("checkUrlCheckout", "deeplink: " + deeplink);
                            Log.d("checkUrlCheckout", "mobileWeb: " + mobileUrl);

                            Toast.makeText(CheckoutProdukActivity.this, "Berhasil checkout", Toast.LENGTH_SHORT).show();
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                            Intent intent = new Intent(CheckoutProdukActivity.this, InvoiceActivity.class);
                            intent.putExtra("id_transaksi", id_transaksi);
                            intent.putExtra("mobile_web", mobileUrl);
                            intent.putExtra("mobile_deeplink", deeplink);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(CheckoutProdukActivity.this, "Checkout gagal, terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEwallet> call, Throwable t) {
                        Toast.makeText(CheckoutProdukActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void tampilDialogNomor() {

        Dialog dialog = new Dialog(CheckoutProdukActivity.this);
        dialog.setContentView(R.layout.dialog_nomor_telepon);

        EditText edtNomor = dialog.findViewById(R.id.edt_nomer_ovo);
        TextView txtKonfirmasi = dialog.findViewById(R.id.text_konfirmasi_dialog_selesai);

        dialog.show();

        txtKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomor = edtNomor.getText().toString();
                nomerTelepon = "+62" + edtNomor.getText().toString();

                if (nomor.isEmpty()) {
                    edtNomor.setError("nomor tidak boleh kosong");
                    edtNomor.requestFocus();
                    return;
                }

                checkoutEwalletOvo();
            }
        });

    }

    private void checkoutEwalletOvo() {

        String keterangan = "";
        ArrayList<String> id_member = new ArrayList<>();
        ArrayList<String> total_belanja = new ArrayList<>();
        ArrayList<String> total_berat = new ArrayList<>();
        ArrayList<String> kurir = new ArrayList<>();
        ArrayList<String> layanan_kurir = new ArrayList<>();
        ArrayList<String> ongkir = new ArrayList<>();

        for (int a = 0; a < dataItems.size(); a++) {

            id_member.add(dataItems.get(a).getIdMember());
            total_belanja.add(dataItems.get(a).getTotalBelanja());
            total_berat.add(dataItems.get(a).getTotalBerat());
            kurir.add(dataItems.get(a).getKurir());
            layanan_kurir.add(dataItems.get(a).getLayananKurir());
            ongkir.add(dataItems.get(a).getOngkir());

        }

        ProgressDialog progressDialog = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialog.setMessage("Melakukan checkout");
        progressDialog.show();

        Toast.makeText(this, "kodeBayar: " + preferencedConfig.getPreferenceKodeOpsiBayar(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "nomer: " + nomerTelepon, Toast.LENGTH_SHORT).show();

        ConfigRetrofit.service.checkOutOvo(preferencedConfig.getPreferenceIdCustomer(), id_transaksi, String.valueOf(totalSeluruh), preferencedConfig.getPreferenceKodeOpsiBayar(),
                namaPenerima, alamat, kelurahan, id_kecamatan, id_kota, id_provinsi, kodePos, nomerTelepon, keterangan, id_member,
                total_belanja, total_berat, kurir, layanan_kurir, ongkir, String.valueOf(sumBiayaAdmin), String.valueOf(tindakan)).enqueue(new Callback<ResponseOvo>() {
            @Override
            public void onResponse(Call<ResponseOvo> call, Response<ResponseOvo> response) {
                if (response.isSuccessful()) {

                    progressDialog.dismiss();

                    Toast.makeText(CheckoutProdukActivity.this, "Berhasil melakukan checkout", Toast.LENGTH_SHORT).show();
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                    Intent intent = new Intent(CheckoutProdukActivity.this, InvoiceActivity.class);
                    intent.putExtra("id_transaksi", id_transaksi);
                    startActivity(intent);
                    finish();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(CheckoutProdukActivity.this, "Checkout gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseOvo> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CheckoutProdukActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void loadBiayaAdmin() {

        int total = sumTotalBelanja + sumOngkir + harga_tindakan;
        String totalPost = String.valueOf(total);

        Log.d("cekBiayaAdmin", "idOpsiBayar: "+preferencedConfig.getPreferenceIdOpsiBayar());
        Log.d("cekBiayaAdmin", "totalPost: "+totalPost);
        ConfigRetrofit.service.biayaAdmin(preferencedConfig.getPreferenceIdOpsiBayar(), totalPost).enqueue(new Callback<ResponseBiayaAdmin>() {
            @Override
            public void onResponse(Call<ResponseBiayaAdmin> call, Response<ResponseBiayaAdmin> response) {
                if (response.isSuccessful()) {



                    sumBiayaAdmin = response.body().getBiayaAdmin();

                    if (sumBiayaAdmin != 0) {
                        relativeBiayaAdmin.setVisibility(View.VISIBLE);
                        txtBiayaAdmin.setText("Rp" + NumberFormat.getInstance().format(sumBiayaAdmin));
                    } else if (sumBiayaAdmin == 0) {
                        relativeBiayaAdmin.setVisibility(View.GONE);
                    }

                    totalSeluruh = sumTotalBelanja + sumBiayaAdmin + sumOngkir + harga_tindakan;
                    txtTotalBayar.setText("Rp" + NumberFormat.getInstance().format(totalSeluruh));
                } else {
                    Toast.makeText(CheckoutProdukActivity.this, "Gagal mengambil biaya admin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBiayaAdmin> call, Throwable t) {
                Toast.makeText(CheckoutProdukActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorBiayaAdmin", "onFailure: "+t.getMessage());
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        initMetodeBayarDipilih();
//        loadPilihKurir();
        loadDataMitra();
        loadKategoriBayar();
        loadBiayaAdmin();
        Log.d("checkDataPreference", "idCustomer: " + preferencedConfig.getPreferenceIdCustomer());
        Log.d("checkDataPreference", "idOpsiBayar: " + preferencedConfig.getPreferenceIdOpsiBayar());
    }

    private void loadKategoriBayar() {

        ConfigRetrofit.service.kategoriXendit().enqueue(new Callback<ResponseKategoriXendit>() {
            @Override
            public void onResponse(Call<ResponseKategoriXendit> call, Response<ResponseKategoriXendit> response) {
                if (response.isSuccessful()) {

                    List<DataItem> dataKategori = response.body().getData();
                    KategoriBayarAdapter kategoriBayarAdapter = new KategoriBayarAdapter(CheckoutProdukActivity.this, dataKategori);
                    rvKategoriBayar.setHasFixedSize(true);
                    rvKategoriBayar.setLayoutManager(new LinearLayoutManager(CheckoutProdukActivity.this));
                    rvKategoriBayar.setAdapter(kategoriBayarAdapter);

                } else {
                    Toast.makeText(CheckoutProdukActivity.this, "Gagal memuat data metode pembayaran", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKategoriXendit> call, Throwable t) {
                Toast.makeText(CheckoutProdukActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validasiDataPenerima() {

        namaPenerima = edtNamaPenerima.getText().toString();
        alamat = edtAlamat.getText().toString();
        kodePos = edtKodepos.getText().toString();
        kelurahan = edtKelurahan.getText().toString();

        if (namaPenerima.isEmpty()) {
            edtNamaPenerima.setError("Nama Penerima tidak boleh kosong");
            edtNamaPenerima.requestFocus();
            return;
        }

        if (alamat.isEmpty()) {
            edtAlamat.setError("Alamat tidak boleh kosong");
            edtAlamat.requestFocus();
            return;
        }

        if (kodePos.isEmpty()) {
            edtKodepos.setError("Kode pos tidak boleh kosong");
            edtKodepos.requestFocus();
            return;
        }

        if (kelurahan.isEmpty()) {
            edtKelurahan.setError("Kelurahan tidak boleh kosong");
            edtKelurahan.requestFocus();
            return;
        }

        scrollDataPenerima.setVisibility(View.GONE);
        scrollPembayaran.setVisibility(View.VISIBLE);

    }

//    private void loadPilihKurir() {
//
//        ConfigRetrofit.service.listPesanan(id_transaksi).enqueue(new Callback<ResponseListPesanan>() {
//            @Override
//            public void onResponse(Call<ResponseListPesanan> call, Response<ResponseListPesanan> response) {
//                if (response.isSuccessful()){
//
//                    dataItems = response.body().getData();
//
//                    PilihKurirAdapter pilihKurirAdapter = new PilihKurirAdapter(CheckoutProdukActivity.this, dataItems, CheckoutProdukActivity.this);
//                    rvPilihKurir.setHasFixedSize(true);
//                    rvPilihKurir.setLayoutManager(new LinearLayoutManager(CheckoutProdukActivity.this));
//                    rvPilihKurir.setAdapter(pilihKurirAdapter);
//
//                    sumOngkir = Integer.parseInt(response.body().getTotalOngkir());
//
//                    Log.d("checkOngkirData", "onResponse: "+sumOngkir);
//
//                    txtOngkir.setText("Rp" + NumberFormat.getInstance().format(sumOngkir));
//
//
//                }else{
//                    Toast.makeText(CheckoutProdukActivity.this, "Gagal load data pilih kurir", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseListPesanan> call, Throwable t) {
//                Toast.makeText(CheckoutProdukActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

//    private void loadDataProduk() {
//
//        rvPesananCheckout.setHasFixedSize(true);
//        rvPesananCheckout.setLayoutManager(new LinearLayoutManager(CheckoutProdukActivity.this));
//
//
//        totalPesanan.clear();
//
//        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        ProgressDialog progressDialogProduk = new ProgressDialog(CheckoutProdukActivity.this);
//        progressDialogProduk.setMessage("Memuat data pesanan anda");
//        progressDialogProduk.show();
//
//        ConfigRetrofit.service.listPesanan(id_transaksi).enqueue(new Callback<ResponseListPesanan>() {
//            @Override
//            public void onResponse(Call<ResponseListPesanan> call, Response<ResponseListPesanan> response) {
//                if (response.isSuccessful()){
//
//                    progressDialogProduk.dismiss();
//                    dataPesanan = response.body().getData();
//                    List<ProdukItem> dataProduk = null;
//                    List<ProdukItem> testArray = new ArrayList<ProdukItem>();
//
//                    for (int i = 0; i<dataPesanan.size(); i++){
//                        totalPesanan.add(Integer.parseInt(dataPesanan.get(i).getTotalBelanja()));
//                        dataProduk = dataPesanan.get(i).getProduk();
//
//                        for (int b = 0; b<dataProduk.size(); b++){
//
//                            String test = dataProduk.get(b).toString();
//                            Log.d("testDataProduk", "onResponse: "+test);
//                            testArray.add(dataProduk.get(b));
//                            Log.d("testArrayData", "onResponse: "+testArray);
//
//                        }
//
//                    }
//
//                    CheckoutProdukAdapter adapterProduk = new CheckoutProdukAdapter(CheckoutProdukActivity.this, testArray);
//
//                    rvPesananCheckout.setAdapter(adapterProduk);
//
//                    for (int numTotal : totalPesanan){
//                        sumTotalBelanja = sumTotalBelanja + numTotal;
//                    }
//                    txtTotalPesanan.setText("Rp" + NumberFormat.getInstance().format(sumTotalBelanja));
//
//                }else{
//                    progressDialogProduk.dismiss();
//                    Toast.makeText(CheckoutProdukActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseListPesanan> call, Throwable t) {
//                progressDialogProduk.dismiss();
//                Toast.makeText(CheckoutProdukActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    private void initSpinnerKecamatan(String id_kota) {
        ProgressDialog progressDialogKecamatan = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialogKecamatan.setMessage("Memuat data kecamatan");
        progressDialogKecamatan.show();

        ConfigRajaOngkir.serviceRajaOngkir.getKecamatan(key, id_kota).enqueue(new Callback<ResponseKecamatanRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseKecamatanRajaOngkir> call, Response<ResponseKecamatanRajaOngkir> response) {
                if (response.isSuccessful()) {
                    progressDialogKecamatan.dismiss();

                    int status = response.body().getRajaongkir().getStatus().getCode();

                    if (status == 200) {

                        progressDialogKecamatan.dismiss();
                        dataKecamatan = response.body().getRajaongkir().getResults();
                        List<String> listSpinnerKecamatan = new ArrayList<String>();

                        for (int i = 0; i < dataKecamatan.size(); i++) {
                            listSpinnerKecamatan.add(dataKecamatan.get(i).getSubdistrictName());
                        }

                        ArrayAdapter<String> adapterKecamatan = new ArrayAdapter<String>(CheckoutProdukActivity.this,
                                R.layout.spinner_item, listSpinnerKecamatan);

                        adapterKecamatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerKecamatan.setAdapter(adapterKecamatan);

                    } else {
                        progressDialogKecamatan.dismiss();
                        Toast.makeText(CheckoutProdukActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialogKecamatan.dismiss();
                    Toast.makeText(CheckoutProdukActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatanRajaOngkir> call, Throwable t) {
                progressDialogKecamatan.dismiss();
                Toast.makeText(CheckoutProdukActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initSpinnerKota(String id_provinsi) {
        ProgressDialog progressDialogKota = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialogKota.setMessage("Memuat data kota");
        progressDialogKota.show();

        ConfigRajaOngkir.serviceRajaOngkir.getKota(key, id_provinsi).enqueue(new Callback<ResponseKotaRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseKotaRajaOngkir> call, Response<ResponseKotaRajaOngkir> response) {
                if (response.isSuccessful()) {
                    progressDialogKota.dismiss();

                    int status = response.body().getRajaongkir().getStatus().getCode();

                    if (status == 200) {

                        dataKota = response.body().getRajaongkir().getResults();
                        List<String> listSpinnerKota = new ArrayList<String>();

                        for (int i = 0; i < dataKota.size(); i++) {
                            listSpinnerKota.add(dataKota.get(i).getType()+" "+dataKota.get(i).getCityName());
                        }

                        ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(CheckoutProdukActivity.this,
                                R.layout.spinner_item, listSpinnerKota);

                        adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerKota.setAdapter(adapterKota);

                    } else {
                        Toast.makeText(CheckoutProdukActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialogKota.dismiss();
                    Toast.makeText(CheckoutProdukActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKotaRajaOngkir> call, Throwable t) {
                progressDialogKota.dismiss();
                Toast.makeText(CheckoutProdukActivity.this, "error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerProvinsi() {


        ConfigRajaOngkir.serviceRajaOngkir.getProvinsi(key).enqueue(new Callback<ResponseProvinsiRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseProvinsiRajaOngkir> call, Response<ResponseProvinsiRajaOngkir> response) {
                if (response.isSuccessful()) {

                    int status = response.body().getRajaongkir().getStatus().getCode();

                    if (status == 200) {
                        progressDialog.dismiss();

                        dataProvinsi = response.body().getRajaongkir().getResults();
                        List<String> listSpinnerProvinsi = new ArrayList<String>();
                        for (int i = 0; i < dataProvinsi.size(); i++) {
                            listSpinnerProvinsi.add(dataProvinsi.get(i).getProvince());
                        }

                        ArrayAdapter<String> adapterProvinsi = new ArrayAdapter<String>(CheckoutProdukActivity.this,
                                R.layout.spinner_item, listSpinnerProvinsi);
                        adapterProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerProvinsi.setAdapter(adapterProvinsi);

                    } else {
                        Toast.makeText(CheckoutProdukActivity.this, "Data Gagal dimuat", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(CheckoutProdukActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProvinsiRajaOngkir> call, Throwable t) {
                Toast.makeText(CheckoutProdukActivity.this, "Terjadi Kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}