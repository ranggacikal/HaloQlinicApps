package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.detailProduk.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailProduk.ResponseDetailProduk;
import com.haloqlinic.haloqlinicapps.model.detailProduk.VariasiItem;
import com.haloqlinic.haloqlinicapps.model.tambahKeranjang.ResponseTambahKeranjang;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class DetailProdukActivity extends AppCompatActivity {

    ImageView imgDetailProduk, imgBack, imgKeranjang;
    TextView txtNamaProduk, txtHargaProduk, txtDeskripsiProduk;
    Button btnKeranjang, btnCheckout;
    Spinner spinnerPilihVariasi;
    LinearLayout linearVariasi, linearStock, linearSpinner, linearDiskon;
    List<VariasiItem> variasiItems = new ArrayList<>();
    TextView txtVariasiProduk, txtStockProduk, txtHargaDiskon, txtHargaAwal;
    String id_variasi, stok, variasi, id_product;
    String id_member = "";
    String berat = "";
    String harga = "";

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        imgBack = findViewById(R.id.img_back_detail_Produk);
        imgDetailProduk = findViewById(R.id.img_detail_produk);
        imgKeranjang = findViewById(R.id.img_keranjang_detail_Produk);
        txtNamaProduk = findViewById(R.id.text_nama__produk_detail_dokter);
        txtHargaProduk = findViewById(R.id.text_harga_detail_produk);
        txtDeskripsiProduk = findViewById(R.id.text_deskripsi_produk);
        btnKeranjang = findViewById(R.id.btn_tambah_keranjang);
        btnCheckout = findViewById(R.id.btn_checkout_detail_produk);
        linearVariasi = findViewById(R.id.linear_variasi_produk);
        spinnerPilihVariasi = findViewById(R.id.spinner_pilih_variasi);
        txtVariasiProduk = findViewById(R.id.text_variasi_produk);
        linearStock = findViewById(R.id.linear_stock_produk);
        txtStockProduk = findViewById(R.id.text_stock_produk);
        linearSpinner = findViewById(R.id.linear_spinner_variasi);
        linearDiskon = findViewById(R.id.linear_diskon);
        txtHargaDiskon = findViewById(R.id.text_harga_detail_produk_diskon);
        txtHargaAwal = findViewById(R.id.text_harga_detail_produk_awal);

        preferencedConfig = new SharedPreferencedConfig(this);

        PushDownAnim.setPushDownAnimTo(imgBack)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        PushDownAnim.setPushDownAnimTo(imgKeranjang)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DetailProdukActivity.this, KeranjangActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(btnKeranjang)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Integer.parseInt(stok)<1){

                            Toast.makeText(DetailProdukActivity.this,
                                    "Stock Kosong, tidak bisa menambahkan ke keranjang",
                                    Toast.LENGTH_SHORT).show();

                        }else{
                            tambahKeranjang();
                        }
                    }
                });

        PushDownAnim.setPushDownAnimTo(btnCheckout)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt(stok)<1){

                            Toast.makeText(DetailProdukActivity.this,
                                    "Stock Kosong, tidak bisa melakukan proses checkout",
                                    Toast.LENGTH_SHORT).show();

                        }else{
                            tambahCheckout();
                        }
                    }
                });

        spinnerPilihVariasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_variasi = variasiItems.get(position).getId();
                stok = variasiItems.get(position).getStok();
                variasi = variasiItems.get(position).getVariasi();

                if (!variasi.equals("")||!variasi.isEmpty()) {
                    linearVariasi.setVisibility(View.VISIBLE);
                    linearStock.setVisibility(View.VISIBLE);
                    txtVariasiProduk.setText(variasi);
                    txtStockProduk.setText(stok);
                }else{
                    linearVariasi.setVisibility(View.GONE);
                    linearStock.setVisibility(View.VISIBLE);
                    linearSpinner.setVisibility(View.GONE);
                    txtStockProduk.setText(stok);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadDataDetailProduk();
    }

    private void tambahKeranjang() {

        String jumlah = "1";

        ConfigRetrofit.service.tambahKeranjang(preferencedConfig.getPreferenceIdCustomer(), id_product, id_member, berat, jumlah,
                harga, id_variasi, variasi).enqueue(new Callback<ResponseTambahKeranjang>() {
            @Override
            public void onResponse(Call<ResponseTambahKeranjang> call, Response<ResponseTambahKeranjang> response) {
                if (response.isSuccessful()){

                    Toast.makeText(DetailProdukActivity.this, "Berhasil menambahkan ke keranjang",
                            Toast.LENGTH_SHORT).show();

                    Log.d("testData", "onResponse: "+response.message());

                }else{
                    Toast.makeText(DetailProdukActivity.this, "Gagal menambah ke keranjang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahKeranjang> call, Throwable t) {
                Toast.makeText(DetailProdukActivity.this, "Terjadi kesalahan di server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tambahCheckout() {

        String jumlah = "1";

        ConfigRetrofit.service.tambahKeranjang(preferencedConfig.getPreferenceIdCustomer(), id_product, id_member, berat, jumlah,
                harga, id_variasi, variasi).enqueue(new Callback<ResponseTambahKeranjang>() {
            @Override
            public void onResponse(Call<ResponseTambahKeranjang> call, Response<ResponseTambahKeranjang> response) {
                if (response.isSuccessful()){

                    Toast.makeText(DetailProdukActivity.this, "Berhasil menambahkan produk",
                            Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(DetailProdukActivity.this, KeranjangActivity.class));
                    finish();

                }else{
                    Toast.makeText(DetailProdukActivity.this, "Gagal menambah produk", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahKeranjang> call, Throwable t) {
                Toast.makeText(DetailProdukActivity.this, "Terjadi kesalahan di server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDataDetailProduk() {

        String id_produk = getIntent().getStringExtra("id_produk");
        Log.d("checkIdProduk", "loadDataDetailProduk: "+id_produk);

        ProgressDialog progressDialog = new ProgressDialog(DetailProdukActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        ConfigRetrofit.service.detailProduk(id_produk).enqueue(new Callback<ResponseDetailProduk>() {
            @Override
            public void onResponse(Call<ResponseDetailProduk> call, Response<ResponseDetailProduk> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    List<DataItem> dataItems = response.body().getData();
                    String url_image = "";
                    String nama_produk = "";
                    String harga_produk = "";
                    String deskripsi_produk = "";
                    String diskon = "";
                    String harga_jual = "";


                    for (int i = 0; i<dataItems.size(); i++){
                        url_image = dataItems.get(i).getImg();
                        nama_produk = dataItems.get(i).getNamaProduk();
                        harga_produk = dataItems.get(i).getHarga();
                        deskripsi_produk = dataItems.get(i).getDeskripsi();
                        id_product = dataItems.get(i).getIdProduk();
                        id_member = dataItems.get(i).getIdMember();
                        berat = dataItems.get(i).getBerat();
                        harga = dataItems.get(i).getHarga();
                        variasiItems = dataItems.get(i).getVariasi();
                        diskon = dataItems.get(i).getDisc();
                        harga_jual = dataItems.get(i).getHargaJual();
                    }

                    if (variasiItems.size()<1){
                        linearSpinner.setVisibility(View.GONE);
                    }

                    Log.d("checkDataVariasiItems", "onResponse: "+variasiItems.toString());

                    List<String> listSpinnerVariasi = new ArrayList<>();
                    for (int i = 0; i<variasiItems.size(); i++){
                        listSpinnerVariasi.add(variasiItems.get(i).getVariasi());
                    }

                    ArrayAdapter<String> adapterVariasi = new ArrayAdapter<String>(DetailProdukActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerVariasi);
                    adapterVariasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPilihVariasi.setAdapter(adapterVariasi);

                    Glide.with(DetailProdukActivity.this)
                            .load(url_image)
                            .error(R.mipmap.ic_launcher)
                            .into(imgDetailProduk);

                    txtNamaProduk.setText(nama_produk);

                    if (diskon.equals("0")) {
                        linearDiskon.setVisibility(View.GONE);
                        txtHargaProduk.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(harga_jual)));

                    }else{
                        linearDiskon.setVisibility(View.VISIBLE);
                        txtHargaProduk.setVisibility(View.GONE);
                        txtHargaAwal.setPaintFlags(txtHargaAwal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        txtHargaAwal.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(harga)));
                        txtHargaDiskon.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(harga_jual)));
                    }
                    txtDeskripsiProduk.setText(Html.fromHtml(deskripsi_produk));

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DetailProdukActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailProduk> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailProdukActivity.this, "Terjadi Kesalahan Di Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}