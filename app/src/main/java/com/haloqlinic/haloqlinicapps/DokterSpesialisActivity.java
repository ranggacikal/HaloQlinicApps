package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.CariDokterSpesialisAdapter;
import com.haloqlinic.haloqlinicapps.adapter.DokterAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseListDokter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterSpesialisActivity extends AppCompatActivity {

    RecyclerView rvDokterSpesialis;
    ImageView imgBack;
    SearchView cariDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_spesialis);

        rvDokterSpesialis = findViewById(R.id.recycler_dokter_spesialis);
        imgBack = findViewById(R.id.img_back_dokter_spesialis);
        cariDokter = findViewById(R.id.cari_dokter_spesialis);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cariDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cariDokter.setQueryHint("cari dokter spesialis");
                cariDokter.setIconified(false);
            }
        });

        cariDokter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadCariDokter(newText);
                return true;
            }
        });

        rvDokterSpesialis.setHasFixedSize(true);
        rvDokterSpesialis.setLayoutManager(new LinearLayoutManager(DokterSpesialisActivity.this));

        loadDokterSpesialis();
    }

    private void loadCariDokter(String newText) {

        ConfigRetrofit.service.cariDokter("1", newText).enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariDokterSpesialisAdapter adapterCari = new CariDokterSpesialisAdapter(DokterSpesialisActivity.this, dataCari);
                    rvDokterSpesialis.setAdapter(adapterCari);

                }else{
                    Toast.makeText(DokterSpesialisActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(DokterSpesialisActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokterSpesialis() {

        String status = "1";

        ProgressDialog progressDialog = new ProgressDialog(DokterSpesialisActivity.this);
        progressDialog.setMessage("Memuat data dokter spesialis");
        progressDialog.show();

        ConfigRetrofit.service.dataDokter(status).enqueue(new Callback<ResponseListDokter>() {
            @Override
            public void onResponse(Call<ResponseListDokter> call, Response<ResponseListDokter> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataDokterSpesialis = response.body().getData();
                    DokterAdapter adapter = new DokterAdapter(DokterSpesialisActivity.this, dataDokterSpesialis);
                    rvDokterSpesialis.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DokterSpesialisActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListDokter> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(DokterSpesialisActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}