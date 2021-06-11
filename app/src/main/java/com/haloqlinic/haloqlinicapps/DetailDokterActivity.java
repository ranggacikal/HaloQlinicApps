package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.JadwalDetailDokterAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.detailDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResponseDetailDokter;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResultItem;
import com.haloqlinic.haloqlinicapps.model.konsultasi.ResponseKonsultasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDokterActivity extends AppCompatActivity {

    Button btnChat;
    String id_dokter;
    ProgressDialog progressDialog;
    TextView namaDokter, spesialisDokter, tentangDokter;
    ImageView imgDokter, imgBack;
    RecyclerView rvJadwal;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        preferencedConfig = new SharedPreferencedConfig(DetailDokterActivity.this);

        btnChat = findViewById(R.id.btn_chat_detail_dokter);
        progressDialog = new ProgressDialog(DetailDokterActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        id_dokter = getIntent().getStringExtra("id_dokter");
        namaDokter = findViewById(R.id.text_nama_detail_dokter);
        spesialisDokter = findViewById(R.id.text_spesialis_detail_dokter);
        tentangDokter = findViewById(R.id.text_tentang_detai_dokter);
        imgDokter = findViewById(R.id.img_detail_dokter);
        rvJadwal = findViewById(R.id.rv_jadwal_detail_dokter);
        imgBack = findViewById(R.id.img_back_detail_dokter);

        rvJadwal.setHasFixedSize(true);
        rvJadwal.setLayoutManager(new LinearLayoutManager(DetailDokterActivity.this));

        loadDataDetailDokter(id_dokter);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilDialog();
            }
        });
    }

    private void loadDataDetailDokter(String id_dokter) {

        ConfigRetrofit.service.detailDokter(id_dokter).enqueue(new Callback<ResponseDetailDokter>() {
            @Override
            public void onResponse(Call<ResponseDetailDokter> call, Response<ResponseDetailDokter> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    List<ResultItem> dataDetail = response.body().getResult();
                    List<DataItem> dataJadwal = null;

                    String nama_dokter = "";
                    String spesialis = "";
                    String img = "";
                    String tentang = "";

                    for (int i = 0; i<dataDetail.size(); i++){
                        nama_dokter = dataDetail.get(i).getNama();
                        spesialis = dataDetail.get(i).getSpesialis();
                        img = dataDetail.get(i).getImg();
                        dataJadwal = dataDetail.get(i).getData();
                    }

                    JadwalDetailDokterAdapter adapter = new JadwalDetailDokterAdapter(DetailDokterActivity.this, dataJadwal);
                    rvJadwal.setAdapter(adapter);

                    final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/";

                    namaDokter.setText(nama_dokter);
                    spesialisDokter.setText("Spesialis "+spesialis);
                    tentangDokter.setText(tentang);

                    Glide.with(DetailDokterActivity.this)
                            .load(url_image+img)
                            .error(R.mipmap.ic_launcher)
                            .into(imgDokter);
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DetailDokterActivity.this, "Gagal Muat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailDokter> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailDokterActivity.this, "Terjadi kesalhan di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tampilDialog() {

        Dialog alertDialog = new Dialog(this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.dialog_chat_detail_dokter);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        RelativeLayout relativeBuatJadwal = alertDialog.findViewById(R.id.relative_buat_jadwal);
        RelativeLayout relativeKonsultasiSekarang = alertDialog.findViewById(R.id.relative_konsultasi_sekarang);

        alertDialog.show();

        relativeBuatJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailDokterActivity.this, AturJadwalActivity.class);
                intent.putExtra("id_dokter", id_dokter);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });

        relativeKonsultasiSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konsultasiSekarang();
                alertDialog.dismiss();
            }
        });

    }

    private void konsultasiSekarang() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ProgressDialog progressKonsultasi = new ProgressDialog(DetailDokterActivity.this);
        progressKonsultasi.setMessage("Membuat Permintaan Konsultasi");
        progressKonsultasi.show();

        ConfigRetrofit.service.postKonsultasi(id_customer, "", id_dokter, "", "1")
                .enqueue(new Callback<ResponseKonsultasi>() {
                    @Override
                    public void onResponse(Call<ResponseKonsultasi> call, Response<ResponseKonsultasi> response) {
                        if (response.isSuccessful()){

                            progressKonsultasi.dismiss();
                            String id_transaksi = response.body().getIdTransaksi();

                            Log.d("getIdTransaksi", "onResponse: "+id_transaksi);

                            Toast.makeText(DetailDokterActivity.this, "Berhasil membuat permintaan konsultasi", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DetailDokterActivity.this, TungguAccActivity.class);
                            intent.putExtra("id_transaksi", id_transaksi);
                            startActivity(intent);
                            finish();

                        }else{
                            progressKonsultasi.dismiss();
                            Toast.makeText(DetailDokterActivity.this, "Gagal membuat permintaan konsultasi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKonsultasi> call, Throwable t) {
                        progressKonsultasi.dismiss();
                        Toast.makeText(DetailDokterActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}