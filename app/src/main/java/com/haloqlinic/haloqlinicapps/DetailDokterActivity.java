package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.adapter.JadwalDetailDokterAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.detailDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResponseDetailDokter;
import com.haloqlinic.haloqlinicapps.model.detailDokter.ResultItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDokterActivity extends AppCompatActivity {

    Button btnChat;
    String id_dokter;
    ProgressDialog progressDialog;
    TextView namaDokter, spesialisDokter, tentangDokter;
    ImageView imgDokter;
    RecyclerView rvJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

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

        rvJadwal.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailDokterActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        rvJadwal.setLayoutManager(linearLayoutManager);

        loadDataDetailDokter(id_dokter);

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
                    spesialisDokter.setText(spesialis);
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

        alertDialog.show();

    }
}