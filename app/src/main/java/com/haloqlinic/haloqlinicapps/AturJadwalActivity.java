package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
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
import com.haloqlinic.haloqlinicapps.adapter.DokterAturJadwalAdapter;
import com.haloqlinic.haloqlinicapps.adapter.WaktuAturJadwalAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ListItem;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ResponseJadwalDokter;
import com.haloqlinic.haloqlinicapps.model.konsultasi.ResponseKonsultasi;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AturJadwalActivity extends AppCompatActivity {

    RecyclerView rvDokter, rvJadwal;
    TextView txtKonsultasiDengan;
    ImageView imgBack;
    Button btnBuatJadwal;

    String id_dokter, nama_dokter, biaya;
    private SharedPreferencedConfig preferencedConfig;

    String token, token_from, user_id, user_id_from;


    public AturJadwalActivity(){

    }

    public String jadwal_dokter = "";
    public String id_jadwal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_jadwal);

        id_dokter = getIntent().getStringExtra("id_dokter");
        preferencedConfig = new SharedPreferencedConfig(AturJadwalActivity.this);

        rvDokter = findViewById(R.id.recycler_dokter_atur_jadwal);
        txtKonsultasiDengan = findViewById(R.id.text_konsultasi_dengan);
        rvJadwal = findViewById(R.id.recycler_jadwal_konsultasi_atur_jadwal);
        imgBack = findViewById(R.id.img_back_atur_jadwal);
        btnBuatJadwal = findViewById(R.id.btn_buat_jadwal);

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

        btnBuatJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (jadwal_dokter.equals("")){
                    Toast.makeText(AturJadwalActivity.this, "Anda belum memilih jadwal dokter", Toast.LENGTH_SHORT).show();
                }else {

//                    Intent intent = new Intent(AturJadwalActivity.this, CheckoutKonsultasiActivity.class);
//                    intent.putExtra("id_dokter", id_dokter);
//                    intent.putExtra("jadwal_dokter", jadwal_dokter);
//                    intent.putExtra("id_jadwal", id_jadwal);
//                    intent.putExtra("biaya", biaya);
//                    Log.d("checkDataJadwal", "onClick: "+id_dokter);
//                    Log.d("checkDataJadwal", "onClick: "+jadwal_dokter);
//                    startActivity(intent);

                    postKonsultasi();

                }
            }
        });

        loadDokter();
        loadJadwal();
    }

    private void postKonsultasi() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ProgressDialog progressDialog = new ProgressDialog(AturJadwalActivity.this);
        progressDialog.setMessage("Mengajukan Permintaan Konsultasi");
        progressDialog.show();

        ConfigRetrofit.service.postKonsultasi(id_customer, id_jadwal, id_dokter, jadwal_dokter).enqueue(new Callback<ResponseKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseKonsultasi> call, Response<ResponseKonsultasi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(AturJadwalActivity.this, "Berhasil mengajukan konsultasi", Toast.LENGTH_SHORT).show();
                    tampilDialog();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(AturJadwalActivity.this, "Gagal mengajulan konsultasi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AturJadwalActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tampilDialog() {

        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Berhasil Mengajukan Konsultasi")
                .setMessage("Silahkan buka menu history konsultasi untuk melihat status konsultasi anda")
                .setCancelable(false)
                .setPositiveButton("Lihat menu konsultasi", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        startActivity(new Intent(AturJadwalActivity.this, HistoryKonsultasiActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Kembali ke menu utama", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        startActivity(new Intent(AturJadwalActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();

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

                    WaktuAturJadwalAdapter adapteJadwal = new WaktuAturJadwalAdapter(AturJadwalActivity.this, dataJadwal,
                            AturJadwalActivity.this);
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
                        biaya = dataDokter.get(i).getBiaya();

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