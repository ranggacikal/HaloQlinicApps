package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.KeranjangAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.dataKeranjang.DataItem;
import com.haloqlinic.haloqlinicapps.model.dataKeranjang.ResponseDataKeranjang;
import com.haloqlinic.haloqlinicapps.model.updateKeranjang.ResponseUpdateKeranjang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangActivity extends AppCompatActivity {

    RecyclerView rvKeranjang;
    private SharedPreferencedConfig preferencedConfig;
    ImageView imgBack;
    TextView txtKeranjangKosong;
    Button btnCheckout;

    List<DataItem> keranjangItems;

    public KeranjangActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        rvKeranjang = findViewById(R.id.recycler_keranjang);
        imgBack = findViewById(R.id.img_back_keranjang);
        txtKeranjangKosong = findViewById(R.id.text_keranjang_kosong);
        btnCheckout = findViewById(R.id.btn_checkout_keranjang);

        preferencedConfig = new SharedPreferencedConfig(this);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvKeranjang.setHasFixedSize(true);
        rvKeranjang.setLayoutManager(new LinearLayoutManager(KeranjangActivity.this));

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOut();
            }
        });

        loadDataKeranjang();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataKeranjang();
    }

    private void checkOut() {

        ArrayList<String> id_pesan = new ArrayList<>();
        ArrayList<String> jumlah = new ArrayList<>();
        ArrayList<String> harga = new ArrayList<>();
        ArrayList<String> berat_item = new ArrayList<>();

        for (int a = 0; a<keranjangItems.size(); a++){

            id_pesan.add(keranjangItems.get(a).getIdPesan());
            jumlah.add(keranjangItems.get(a).getJumlah());
            harga.add(keranjangItems.get(a).getHargaJual());
            berat_item.add(keranjangItems.get(a).getBeratItem());

        }

        Log.d("checkDataKirim", "id_pesan: "+id_pesan);
        Log.d("checkDataKirim", "jumlah: "+jumlah);
        Log.d("checkDataKirim", "harga: "+harga);
        Log.d("checkDataKirim", "berat_item: "+berat_item);

        ProgressDialog progressDialog = new ProgressDialog(KeranjangActivity.this);
        progressDialog.setMessage("Checkout Pesanan Anda");
        progressDialog.show();


        ConfigRetrofit.service.updateKeranjang(preferencedConfig.getPreferenceIdCustomer(), id_pesan, jumlah, harga, berat_item)
                .enqueue(new Callback<ResponseUpdateKeranjang>() {
                    @Override
                    public void onResponse(Call<ResponseUpdateKeranjang> call, Response<ResponseUpdateKeranjang> response) {
                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(KeranjangActivity.this, "berhasil Checkout", Toast.LENGTH_SHORT).show();
                            String id_transaksi = response.body().getIdTransaksi();
                            Intent intent = new Intent(KeranjangActivity.this, DetailPesananActivity.class);
                            Log.d("checkDataKirim", "id_transaksi: "+id_transaksi);
                            intent.putExtra("id_transaksi", id_transaksi);
                            startActivity(intent);
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(KeranjangActivity.this, "Gagal Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUpdateKeranjang> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(KeranjangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void loadDataKeranjang() {

        ProgressDialog progressDialog = new ProgressDialog(KeranjangActivity.this);
        progressDialog.setMessage("Memuat data keranjang");
        progressDialog.show();

        ConfigRetrofit.service.dataKeranjang(preferencedConfig.getPreferenceIdCustomer()).enqueue(new Callback<ResponseDataKeranjang>() {
            @Override
            public void onResponse(Call<ResponseDataKeranjang> call, Response<ResponseDataKeranjang> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    keranjangItems = response.body().getData();
                    KeranjangAdapter adapter = new KeranjangAdapter(KeranjangActivity.this, keranjangItems, KeranjangActivity.this);
                    Log.d("checkDataKeranjang", "onResponse: "+keranjangItems);
                    rvKeranjang.setAdapter(adapter);

                    if (keranjangItems.size()<1){
                        progressDialog.dismiss();
                        rvKeranjang.setVisibility(View.GONE);
                        btnCheckout.setVisibility(View.GONE);
                        txtKeranjangKosong.setVisibility(View.VISIBLE);
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(KeranjangActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKeranjang> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KeranjangActivity.this, "Terjadi kesalahan di server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}