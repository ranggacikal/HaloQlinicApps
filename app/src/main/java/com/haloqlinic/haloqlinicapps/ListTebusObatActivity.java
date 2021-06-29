package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.TebusObatAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.databinding.ActivityDetailHistoryBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityListTebusObatBinding;
import com.haloqlinic.haloqlinicapps.model.listPesanan.DataItem;
import com.haloqlinic.haloqlinicapps.model.listPesanan.ResponseListPesanan;
import com.haloqlinic.haloqlinicapps.model.listTebusObat.ResponseListTebusObat;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class ListTebusObatActivity extends AppCompatActivity {

    private ActivityListTebusObatBinding binding;

    String id_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListTebusObatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        PushDownAnim.setPushDownAnimTo(binding.imgBackListTebusObat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        id_transaksi = getIntent().getStringExtra("id_transaksi");

        binding.recyclerTebusObat.setHasFixedSize(true);
        binding.recyclerTebusObat.setLayoutManager(new LinearLayoutManager(ListTebusObatActivity.this));

        loadTebusObat();

        PushDownAnim.setPushDownAnimTo(binding.btnTebusObat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(ListTebusObatActivity.this,
                                CheckoutProdukActivity.class);
                        intent.putExtra("id_transaksi", id_transaksi);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void loadTebusObat() {

        android.app.ProgressDialog progressDialog = new ProgressDialog(ListTebusObatActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();


        ConfigRetrofit.service.listPesanan(id_transaksi).enqueue(new Callback<ResponseListPesanan>() {
            @Override
            public void onResponse(Call<ResponseListPesanan> call, Response<ResponseListPesanan> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataTebusObat = response.body().getData();
                    TebusObatAdapter adapter = new TebusObatAdapter(ListTebusObatActivity.this, dataTebusObat);
                    binding.recyclerTebusObat.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(ListTebusObatActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListPesanan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListTebusObatActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}