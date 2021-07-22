
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.databinding.ActivityDetailProdukMitraBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityJadwalKonsultasiBinding;
import com.haloqlinic.haloqlinicapps.model.detailProdukMitra.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailProdukMitra.ResponseDetailProdukMitra;
import com.haloqlinic.haloqlinicapps.model.detailProdukMitra.VariasiItem;
import com.haloqlinic.haloqlinicapps.model.tambahKeranjang.ResponseTambahKeranjang;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class DetailProdukMitraActivity extends AppCompatActivity {

    private ActivityDetailProdukMitraBinding binding;

    List<VariasiItem> dataVariasi;
    private SharedPreferencedConfig preferencedConfig;
    String id_variasi, stok, variasi, id_product;
    String id_member = "";
    String berat = "";
    String harga = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProdukMitraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(this);

        PushDownAnim.setPushDownAnimTo(binding.imgBackDetailProdukMitra)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.imgKeranjangDetailProdukMitra)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DetailProdukMitraActivity.this,
                                KeranjangActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.btnTambahKeranjangProdukMitra)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Integer.parseInt(stok) < 1) {

                            Toast.makeText(DetailProdukMitraActivity.this,
                                    "Stock Kosong, tidak bisa menambahkan ke keranjang",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            tambahKeranjang();
                        }
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.btnCheckoutDetailProdukMitra)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt(stok) < 1) {

                            Toast.makeText(DetailProdukMitraActivity.this,
                                    "Stock Kosong, tidak bisa melakukan proses checkout",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            tambahCheckout();
                        }
                    }
                });

        binding.spinnerPilihVariasiProdukMitra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_variasi = dataVariasi.get(position).getId();
                stok = dataVariasi.get(position).getStok();
                variasi = dataVariasi.get(position).getVariasi();

                if (!variasi.equals("")||!variasi.isEmpty()) {
                    binding.linearVariasiProdukMitra.setVisibility(View.VISIBLE);
                    binding.linearStockProdukMitra.setVisibility(View.VISIBLE);
                    binding.textVariasiProdukMitra.setText(variasi);
                    binding.textStockProdukMitra.setText(stok);
                }else{
                    binding.linearVariasiProdukMitra.setVisibility(View.GONE);
                    binding.linearStockProdukMitra.setVisibility(View.VISIBLE);
                    binding.linearSpinnerVariasiProdukMitra.setVisibility(View.GONE);
                    binding.textStockProdukMitra.setText(stok);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadDataDetailProduk();

    }

    private void loadDataDetailProduk() {

        String id_produk = getIntent().getStringExtra("id_produk");
        Log.d("checkIdProduk", "loadDataDetailProduk: " + id_produk);

        ProgressDialog progressDialog = new ProgressDialog(DetailProdukMitraActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        ConfigRetrofit.service.detailProdukMitra(id_produk).enqueue(new Callback<ResponseDetailProdukMitra>() {
            @Override
            public void onResponse(Call<ResponseDetailProdukMitra> call, Response<ResponseDetailProdukMitra> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();

                    List<DataItem> dataItems = response.body().getData();
                    String url_image = "";
                    String nama_produk = "";
                    String harga_produk = "";
                    String deskripsi_produk = "";
                    String diskon = "";
                    String harga_jual = "";
                    String harga_diskon = "";


                    for (int i = 0; i < dataItems.size(); i++) {
                        url_image = dataItems.get(i).getImg();
                        nama_produk = dataItems.get(i).getNamaProduk();
                        harga_produk = dataItems.get(i).getHarga();
                        deskripsi_produk = dataItems.get(i).getDeskripsi();
                        id_product = dataItems.get(i).getIdProduk();
                        id_member = dataItems.get(i).getIdMember();
                        berat = dataItems.get(i).getBerat();
                        harga = dataItems.get(i).getHarga();
                        dataVariasi = dataItems.get(i).getVariasi();
                        diskon = dataItems.get(i).getDisc();
                        harga_jual = dataItems.get(i).getHargaJual();
                        harga_diskon = dataItems.get(i).getHargaPromo();
                    }

                    if (dataItems.size() < 1) {
                        binding.linearSpinnerVariasiProdukMitra.setVisibility(View.GONE);
                    }

                    Log.d("checkDataVariasiItems", "onResponse: " + dataVariasi.toString());

                    List<String> listSpinnerVariasi = new ArrayList<>();
                    for (int i = 0; i < dataVariasi.size(); i++) {
                        listSpinnerVariasi.add(dataVariasi.get(i).getVariasi());
                    }

                    ArrayAdapter<String> adapterVariasi = new ArrayAdapter<String>(DetailProdukMitraActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerVariasi);
                    adapterVariasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerPilihVariasiProdukMitra.setAdapter(adapterVariasi);

                    Glide.with(DetailProdukMitraActivity.this)
                            .load(url_image)
                            .error(R.mipmap.ic_launcher)
                            .into(binding.imgDetailProdukMitra);

                    binding.textNamaProdukDetailMitra.setText(nama_produk);
                    binding.textHargaDetailProdukAwalMitra
                            .setPaintFlags(binding.textHargaDetailProdukAwalMitra.getPaintFlags()
                                    | Paint.STRIKE_THRU_TEXT_FLAG);
                    binding.textHargaDetailProdukAwalMitra.setText("Rp" + NumberFormat.getInstance().
                            format(Integer.parseInt(harga)));
                    binding.textHargaDetailProdukDiskonMitra.setText("Rp" + NumberFormat.getInstance().
                            format(Integer.parseInt(harga_diskon)));

                    binding.textDeskripsiProdukMitra.setText(Html.fromHtml(deskripsi_produk));

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(DetailProdukMitraActivity.this, "Gagal Memuat Data",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailProdukMitra> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailProdukMitraActivity.this, "Error: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(DetailProdukMitraActivity.this,
                            "Berhasil menambahkan produk", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(DetailProdukMitraActivity.this,
                            KeranjangActivity.class));
                    finish();

                }else{
                    Toast.makeText(DetailProdukMitraActivity.this,
                            "Gagal menambah produk", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahKeranjang> call, Throwable t) {
                Toast.makeText(DetailProdukMitraActivity.this,
                        "Terjadi kesalahan di server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tambahKeranjang() {

        String jumlah = "1";

        ConfigRetrofit.service.tambahKeranjang(preferencedConfig.getPreferenceIdCustomer(), id_product, id_member, berat, jumlah,
                harga, id_variasi, variasi).enqueue(new Callback<ResponseTambahKeranjang>() {
            @Override
            public void onResponse(Call<ResponseTambahKeranjang> call, Response<ResponseTambahKeranjang> response) {
                if (response.isSuccessful()){

                    Toast.makeText(DetailProdukMitraActivity.this, "Berhasil menambahkan ke keranjang",
                            Toast.LENGTH_SHORT).show();

                    Log.d("testData", "onResponse: "+response.message());

                }else{
                    Toast.makeText(DetailProdukMitraActivity.this,
                            "Gagal menambah ke keranjang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahKeranjang> call, Throwable t) {
                Toast.makeText(DetailProdukMitraActivity.this,
                        "Terjadi kesalahan di server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}