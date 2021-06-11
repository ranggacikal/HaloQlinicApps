package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.checkStatus.ResponseCheckStatus;
import com.haloqlinic.haloqlinicapps.model.updateKonsultasi.ResponseUpdateKonsultasi;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TungguAccActivity extends AppCompatActivity {

    TextView txtWaktu;

    String id_transaksi, id_dokter, biaya;
    int status;
    CountDownTimer countDownTimer;
    private long timeMillisSecond = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tunggu_acc);
        txtWaktu = findViewById(R.id.text_waktu_tunggu);

        id_transaksi = getIntent().getStringExtra("id_transaksi");



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