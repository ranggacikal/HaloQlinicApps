package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.JadwalKonsultasiAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.databinding.ActivityHistoryKonsultasiBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityJadwalKonsultasiBinding;
import com.haloqlinic.haloqlinicapps.model.listKonsultasi.DataItem;
import com.haloqlinic.haloqlinicapps.model.listKonsultasi.ResponseListKonsultasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalKonsultasiActivity extends AppCompatActivity {

    private ActivityJadwalKonsultasiBinding binding;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJadwalKonsultasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(JadwalKonsultasiActivity.this);

        binding.imgBackJadwalKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.recyclerJadwalKonsultasiAnda.setHasFixedSize(true);
        binding.recyclerJadwalKonsultasiAnda.setLayoutManager(new LinearLayoutManager(JadwalKonsultasiActivity.this));

        loadJadwalKonsultasi();
    }

    private void loadJadwalKonsultasi() {

        ProgressDialog progressDialog = new ProgressDialog(JadwalKonsultasiActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        ConfigRetrofit.service.listKonsultasi(preferencedConfig.getPreferenceIdCustomer(), "1")
                .enqueue(new Callback<ResponseListKonsultasi>() {
                    @Override
                    public void onResponse(Call<ResponseListKonsultasi> call, Response<ResponseListKonsultasi> response) {
                        if (response.isSuccessful()){

                            progressDialog.dismiss();
                            List<DataItem> dataKonsultasi = response.body().getData();
                            JadwalKonsultasiAdapter adapter = new JadwalKonsultasiAdapter(JadwalKonsultasiActivity.this, dataKonsultasi);
                            binding.recyclerJadwalKonsultasiAnda.setAdapter(adapter);

                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(JadwalKonsultasiActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseListKonsultasi> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(JadwalKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}