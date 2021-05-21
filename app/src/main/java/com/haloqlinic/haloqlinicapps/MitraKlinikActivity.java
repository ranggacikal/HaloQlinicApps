package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.MitraKlinikAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.mitraKlinik.DataItem;
import com.haloqlinic.haloqlinicapps.model.mitraKlinik.ResponseDataMitra;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MitraKlinikActivity extends AppCompatActivity {

    ImageView imgBack;
    RecyclerView rvMitraKlinik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra_klinik);

        imgBack = findViewById(R.id.img_back_mitra_klinik);
        rvMitraKlinik = findViewById(R.id.recycler_mitra_klinik);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvMitraKlinik.setHasFixedSize(true);
        rvMitraKlinik.setLayoutManager(new LinearLayoutManager(MitraKlinikActivity.this));

        loadMitraKlinik();
    }

    private void loadMitraKlinik() {

        String status = "1";

        android.app.ProgressDialog progressDialog = new ProgressDialog(MitraKlinikActivity.this);
        progressDialog.setMessage("memuat list mitra klinik");
        progressDialog.show();

        ConfigRetrofit.service.dataMitra(status).enqueue(new Callback<ResponseDataMitra>() {
            @Override
            public void onResponse(Call<ResponseDataMitra> call, Response<ResponseDataMitra> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataMitra = response.body().getData();
                    MitraKlinikAdapter adapter = new MitraKlinikAdapter(MitraKlinikActivity.this, dataMitra);
                    rvMitraKlinik.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(MitraKlinikActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataMitra> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MitraKlinikActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}