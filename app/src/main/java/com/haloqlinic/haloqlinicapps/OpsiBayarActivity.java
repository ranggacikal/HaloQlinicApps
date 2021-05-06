package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.OpsiBayarAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.opsiBayar.DataItem;
import com.haloqlinic.haloqlinicapps.model.opsiBayar.ResponseOpsiBayar;
import com.haloqlinic.haloqlinicapps.recyclerClick.RecyclerItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpsiBayarActivity extends AppCompatActivity {

    TextView txtNamaKategori;
    RecyclerView rvOpsibayar;

    CheckoutProdukActivity checkoutProdukActivity;

    public OpsiBayarActivity(){

    }

    public String idOpsi="";

    public String nama, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_bayar);

        txtNamaKategori = findViewById(R.id.text_nama_kategori_opsi_bayar);
        rvOpsibayar = findViewById(R.id.recycler_opsi_bayar);

        nama = getIntent().getStringExtra("kategoriBayar");
        id = getIntent().getStringExtra("idKategori");

        Log.d("checkIdopsi", "onCreate: "+idOpsi);

        txtNamaKategori.setText(nama);

        loadOpsiBayar();
    }

    private void loadOpsiBayar() {

        ConfigRetrofit.service.opsiBayar(id).enqueue(new Callback<ResponseOpsiBayar>() {
            @Override
            public void onResponse(Call<ResponseOpsiBayar> call, Response<ResponseOpsiBayar> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataOpsi = response.body().getData();
                    OpsiBayarAdapter opsiBayarAdapter = new OpsiBayarAdapter(OpsiBayarActivity.this, dataOpsi, OpsiBayarActivity.this);
                    rvOpsibayar.setHasFixedSize(true);
                    rvOpsibayar.setLayoutManager(new LinearLayoutManager(OpsiBayarActivity.this));
                    rvOpsibayar.setAdapter(opsiBayarAdapter);

                }else{
                    Toast.makeText(OpsiBayarActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseOpsiBayar> call, Throwable t) {
                Toast.makeText(OpsiBayarActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}