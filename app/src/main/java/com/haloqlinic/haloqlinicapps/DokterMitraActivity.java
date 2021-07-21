package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.DokterMitraAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.databinding.ActivityDokterMitraBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityJadwalKonsultasiBinding;
import com.haloqlinic.haloqlinicapps.model.dokterMitra.DataItem;
import com.haloqlinic.haloqlinicapps.model.dokterMitra.ResponseDokterMitra;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterMitraActivity extends AppCompatActivity {

    private ActivityDokterMitraBinding binding;

    String kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDokterMitraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        kode = getIntent().getStringExtra("kode");

        binding.recyclerDokterMitra.setHasFixedSize(true);
        binding.recyclerDokterMitra.setLayoutManager(new LinearLayoutManager(DokterMitraActivity.this));

        loadDokterMitra();

    }

    private void loadDokterMitra() {

        ProgressDialog progressDialog = new ProgressDialog(DokterMitraActivity.this);
        progressDialog.setMessage("load data dokter mitra");
        progressDialog.show();

        ConfigRetrofit.service.dataDokterMitra(kode).enqueue(new Callback<ResponseDokterMitra>() {
            @Override
            public void onResponse(Call<ResponseDokterMitra> call, Response<ResponseDokterMitra> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    if (response.body()!=null){

                        List<DataItem> dataDokter = response.body().getData();
                        DokterMitraAdapter adapter = new DokterMitraAdapter(DokterMitraActivity.this,
                                dataDokter);
                        binding.recyclerDokterMitra.setAdapter(adapter);

                    }else{
                        Toast.makeText(DokterMitraActivity.this, "Data Dokter Kosong",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DokterMitraActivity.this, "Response Error",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDokterMitra> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DokterMitraActivity.this, "Error: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}