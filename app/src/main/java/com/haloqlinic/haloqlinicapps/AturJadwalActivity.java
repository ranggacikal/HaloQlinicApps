package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.DokterAturJadwalAdapter;
import com.haloqlinic.haloqlinicapps.adapter.WaktuAturJadwalAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ListItem;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ResponseJadwalDokter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AturJadwalActivity extends AppCompatActivity {

    RecyclerView rvDokter, rvJadwal;
    TextView txtKonsultasiDengan;
    ImageView imgBack;

    String id_dokter, nama_dokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_jadwal);

        id_dokter = getIntent().getStringExtra("id_dokter");

        rvDokter = findViewById(R.id.recycler_dokter_atur_jadwal);
        txtKonsultasiDengan = findViewById(R.id.text_konsultasi_dengan);
        rvJadwal = findViewById(R.id.recycler_jadwal_konsultasi_atur_jadwal);
        imgBack = findViewById(R.id.img_back_atur_jadwal);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvDokter.setHasFixedSize(true);
        rvDokter.setLayoutManager(new LinearLayoutManager(AturJadwalActivity.this));

        rvJadwal.setHasFixedSize(true);
        rvJadwal.setLayoutManager(new LinearLayoutManager(AturJadwalActivity.this));

        loadDokter();
        loadJadwal();
    }

    private void loadJadwal() {

        ConfigRetrofit.service.jadwalDokter(id_dokter).enqueue(new Callback<ResponseJadwalDokter>() {
            @Override
            public void onResponse(Call<ResponseJadwalDokter> call, Response<ResponseJadwalDokter> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataItems = response.body().getData();
                    List<ListItem> dataJadwal = null;

                    for (int a = 0; a<dataItems.size(); a++){

                        dataJadwal = dataItems.get(a).getList();

                    }

                    WaktuAturJadwalAdapter adapteJadwal = new WaktuAturJadwalAdapter(AturJadwalActivity.this, dataJadwal);
                    rvJadwal.setAdapter(adapteJadwal);

                }else{
                    Toast.makeText(AturJadwalActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwalDokter> call, Throwable t) {
                Toast.makeText(AturJadwalActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokter() {

        ConfigRetrofit.service.jadwalDokter(id_dokter).enqueue(new Callback<ResponseJadwalDokter>() {
            @Override
            public void onResponse(Call<ResponseJadwalDokter> call, Response<ResponseJadwalDokter> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataDokter = response.body().getData();

                    for (int i =0; i<dataDokter.size(); i++){

                        nama_dokter = dataDokter.get(i).getNama();

                    }

                    txtKonsultasiDengan.setText("Konsultasi dengan Dr. "+nama_dokter);

                    DokterAturJadwalAdapter adapterDokter = new DokterAturJadwalAdapter(AturJadwalActivity.this, dataDokter);
                    rvDokter.setAdapter(adapterDokter);

                }else{
                    Toast.makeText(AturJadwalActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwalDokter> call, Throwable t) {
                Toast.makeText(AturJadwalActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}