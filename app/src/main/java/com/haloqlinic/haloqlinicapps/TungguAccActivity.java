package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.checkStatus.ResponseCheckStatus;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResponseDetailDokter;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResultItem;
import com.haloqlinic.haloqlinicapps.model.updateKonsultasi.ResponseUpdateKonsultasi;

import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TungguAccActivity extends AppCompatActivity {

    TextView txtWaktu, txtNama, txtSpesialis;
    ImageView imgDokter;

    String id_transaksi, id_dokter, biaya, id_detail_dokter, external_id;
    int status;
    CountDownTimer countDownTimer;
    private long timeMillisSecond = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tunggu_acc);
        txtWaktu = findViewById(R.id.text_waktu_tunggu);
        txtNama = findViewById(R.id.text_nama_dokter_tunggu_acc);
        txtSpesialis = findViewById(R.id.text_spesialis_tunggu_acc);
        imgDokter = findViewById(R.id.img_dokter_tunggu_acc);

        id_transaksi = getIntent().getStringExtra("id_transaksi");
        id_detail_dokter = getIntent().getStringExtra("id_dokter");
        external_id = getIntent().getStringExtra("external_id");

        getDetailDokter();

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String duration = String.valueOf(millisUntilFinished/1000);
                txtWaktu.setText(duration);
                getStatus();

                if (status==1){
                    countDownTimer.cancel();
                    Intent intent = new Intent(TungguAccActivity.this, CheckoutKonsultasiActivity.class);
                    intent.putExtra("id_transaksi", id_transaksi);
                    intent.putExtra("id_dokter", id_dokter);
                    intent.putExtra("biaya", biaya);
                    intent.putExtra("external_id", external_id);
                    intent.putExtra("jadwal_dokter", "");
                    intent.putExtra("status", "1");
                    startActivity(intent);
                    finish();
                }else if (status == 2){
                    countDownTimer.cancel();
                    tampilDialogGagalAcc();

                }
            }

            @Override
            public void onFinish() {
                updatKonsultasi();
            }
        }.start();

    }

    private void getDetailDokter() {

        ProgressDialog progressDialog = new ProgressDialog(TungguAccActivity.this);
        progressDialog.setMessage("Memuat Data Dokter");
        progressDialog.show();

        ConfigRetrofit.service.detailDokter(id_detail_dokter).enqueue(new Callback<ResponseDetailDokter>() {
            @Override
            public void onResponse(Call<ResponseDetailDokter> call, Response<ResponseDetailDokter> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();

                    List<ResultItem> dataDokter = response.body().getResult();
                    String link = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/";
                    String image = "";
                    String nama = "";
                    String spesialis = "";

                    for (int i = 0; i<dataDokter.size(); i++){

                        image = dataDokter.get(i).getImg();
                        nama = dataDokter.get(i).getNama();
                        spesialis = dataDokter.get(i).getSpesialis();

                    }

                    Glide.with(TungguAccActivity.this)
                            .load(link+image)
                            .error(R.drawable.icon_dokter)
                            .into(imgDokter);

                    txtNama.setText("Dr. "+nama);
                    txtSpesialis.setText("Spesialis "+spesialis);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(TungguAccActivity.this, "Gagal Load Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailDokter> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TungguAccActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updatKonsultasi() {

        ConfigRetrofit.service.updateKonsultasi(id_transaksi).enqueue(new Callback<ResponseUpdateKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseUpdateKonsultasi> call, Response<ResponseUpdateKonsultasi> response) {
                if (response.isSuccessful()){

                    Toast.makeText(TungguAccActivity.this, "Permintaan konsultasi anda gagal diterima", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Toast.makeText(TungguAccActivity.this, "Terjadi kesalahan saat update konsultasi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateKonsultasi> call, Throwable t) {
                Toast.makeText(TungguAccActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tampilDialogGagalAcc() {

        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Permintaan Konsultasi Anda Ditolak")
                .setMessage("")
                .setCancelable(false)
                .setPositiveButton("Kembali", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        finish();
                    }
                }).build();

        // Show Dialog
        mDialog.show();

    }

    private void getStatus() {

        ConfigRetrofit.service.checkStatus(id_transaksi). enqueue(new Callback<ResponseCheckStatus>() {
            @Override
            public void onResponse(Call<ResponseCheckStatus> call, Response<ResponseCheckStatus> response) {
                if (response.isSuccessful()){

                    status = response.body().getStatus();
                    id_dokter = response.body().getIdDokter();
                    biaya = response.body().getBiaya();
                    Log.d("testHitStatus", "onResponse: "+status);

                }
            }

            @Override
            public void onFailure(Call<ResponseCheckStatus> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Kembali?")
                .setMessage("Apakah anda yakin ingin kembali dan membatalkan konsultasi anda?")
                .setCancelable(false)
                .setPositiveButton("Iya", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        TungguAccActivity.super.onBackPressed();
                        countDownTimer.cancel();
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();
    }
}