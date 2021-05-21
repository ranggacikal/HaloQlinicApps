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

import com.haloqlinic.haloqlinicapps.adapter.CariDokterTersediaAdapter;
import com.haloqlinic.haloqlinicapps.adapter.DokterTersediaAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterTersediaActivity extends AppCompatActivity {

    RecyclerView rvDokterTersedia;
    ImageView imgBack;
    SearchView cariDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_tersedia);

        rvDokterTersedia = findViewById(R.id.recycler_dokter_tersedia);
        imgBack = findViewById(R.id.img_back_dokter_tersedia);
        cariDokter = findViewById(R.id.cari_dokter_tersedia);

        cariDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cariDokter.setQueryHint("cari dokter umum");
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
                loadCariDataDokter(newText);
                return true;
            }
        });

        rvDokterTersedia.setHasFixedSize(true);
        rvDokterTersedia.setLayoutManager(new LinearLayoutManager(DokterTersediaActivity.this));

        loadDokterTersedia();
    }

    private void loadCariDataDokter(String newText) {

        ConfigRetrofit.service.cariDokter("0", newText).enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariDokterTersediaAdapter adapterCari = new CariDokterTersediaAdapter(DokterTersediaActivity.this, dataCari);
                    rvDokterTersedia.setAdapter(adapterCari);

                }else{
                    Toast.makeText(DokterTersediaActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(DokterTersediaActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokterTersedia() {

        String status = "1";

        ProgressDialog progressDialog = new ProgressDialog(DokterTersediaActivity.this);
        progressDialog.setMessage("Memuat semua data dokter tersedia");
        progressDialog.show();

        ConfigRetrofit.service.dataDokterAktif(status).enqueue(new Callback<ResponseDataDokterAktif>() {
            @Override
            public void onResponse(Call<ResponseDataDokterAktif> call, Response<ResponseDataDokterAktif> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    List<DataItem> dataDokter = response.body().getData();
                    DokterTersediaAdapter adapter = new DokterTersediaAdapter(DokterTersediaActivity.this, dataDokter);
                    rvDokterTersedia.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DokterTersediaActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataDokterAktif> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DokterTersediaActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}