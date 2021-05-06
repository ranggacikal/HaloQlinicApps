package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.PenerimaDetailTransaksiAdapter;
import com.haloqlinic.haloqlinicapps.adapter.ProdukDetailTransaksiAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ProdukItem;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ResponseDetailTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends AppCompatActivity {

    RecyclerView rvProdukDetailTransaksi, rvPenerimaDetailTransaksi;

    String id_transaksi, id_member;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        rvProdukDetailTransaksi = findViewById(R.id.recycler_produk_detail_transaksi);
        rvPenerimaDetailTransaksi = findViewById(R.id.recycler_data_penerima_detail_transaksi);
        imgBack = findViewById(R.id.img_back_detail_transaksi);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id_transaksi = getIntent().getStringExtra("id_transaksi");
        id_member = getIntent().getStringExtra("id_member");

        rvProdukDetailTransaksi.setHasFixedSize(true);
        rvProdukDetailTransaksi.setLayoutManager(new LinearLayoutManager(DetailTransaksiActivity.this));

        rvPenerimaDetailTransaksi.setHasFixedSize(true);
        rvPenerimaDetailTransaksi.setLayoutManager(new LinearLayoutManager(DetailTransaksiActivity.this));

        loadProdukDetail();
        loadPenerimaDetail();
    }

    private void loadPenerimaDetail() {

        ConfigRetrofit.service.detailTransaksi(id_transaksi, id_member).enqueue(new Callback<ResponseDetailTransaksi>() {
            @Override
            public void onResponse(Call<ResponseDetailTransaksi> call, Response<ResponseDetailTransaksi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataPenerima = response.body().getData();
                    PenerimaDetailTransaksiAdapter adapterPenerima = new PenerimaDetailTransaksiAdapter(DetailTransaksiActivity.this, dataPenerima);
                    rvPenerimaDetailTransaksi.setAdapter(adapterPenerima);

                }else{
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadProdukDetail() {

        ConfigRetrofit.service.detailTransaksi(id_transaksi, id_member).enqueue(new Callback<ResponseDetailTransaksi>() {
            @Override
            public void onResponse(Call<ResponseDetailTransaksi> call, Response<ResponseDetailTransaksi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataTransaksi = response.body().getData();
                    List<ProdukItem> produkItems = null;
                    
                    for (int i = 0; i<dataTransaksi.size(); i++){
                        
                        produkItems = dataTransaksi.get(i).getProduk();
                        
                    }
                    ProdukDetailTransaksiAdapter adapterProduk = new ProdukDetailTransaksiAdapter(DetailTransaksiActivity.this, produkItems);
                    rvProdukDetailTransaksi.setAdapter(adapterProduk);
                }else{
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}