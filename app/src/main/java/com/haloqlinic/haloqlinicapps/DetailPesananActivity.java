package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.DetailPesananAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.listPesanan.DataItem;
import com.haloqlinic.haloqlinicapps.model.listPesanan.ProdukItem;
import com.haloqlinic.haloqlinicapps.model.listPesanan.ResponseListPesanan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPesananActivity extends AppCompatActivity {

    ImageView imgBack;
    RecyclerView rvDetailPesanan;
    Button btnLanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan);
        imgBack = findViewById(R.id.img_back_detail_pesanan);
        rvDetailPesanan = findViewById(R.id.recycler_detail_pesanan);
        btnLanjut = findViewById(R.id.btn_lanjut_bayar);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPesananActivity.this, CheckoutProdukActivity.class);
                intent.putExtra("id_transaksi", getIntent().getStringExtra("id_transaksi"));
                startActivity(intent);
                finish();
            }
        });

        loadDataDetailPesanan();


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataDetailPesanan();
    }

    private void loadDataDetailPesanan() {

        ProgressDialog progressDialog = new ProgressDialog(DetailPesananActivity.this);
        progressDialog.setMessage("Memuat data");
        progressDialog.show();

        ConfigRetrofit.service.listPesanan(getIntent().getStringExtra("id_transaksi")).enqueue(new Callback<ResponseListPesanan>() {
            @Override
            public void onResponse(Call<ResponseListPesanan> call, Response<ResponseListPesanan> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    List<DataItem> dataPesanan = response.body().getData();
                    List<ProdukItem> dataProduk = null;
                    List<ProdukItem> testArray = new ArrayList<ProdukItem>();

                    for (int i = 0; i<dataPesanan.size(); i++){
                        dataProduk = dataPesanan.get(i).getProduk();

                        for (int b = 0; b<dataProduk.size(); b++){

                            String test = dataProduk.get(b).toString();
                            Log.d("testDataProduk", "onResponse: "+test);
                            testArray.add(dataProduk.get(b));
                            Log.d("testArrayData", "onResponse: "+testArray);

                        }

                    }


                    DetailPesananAdapter adapter = new DetailPesananAdapter(DetailPesananActivity.this, testArray);
                    rvDetailPesanan.setHasFixedSize(true);
                    rvDetailPesanan.setLayoutManager(new LinearLayoutManager(DetailPesananActivity.this));
                    rvDetailPesanan.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DetailPesananActivity.this, "Data Kosong atau terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListPesanan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailPesananActivity.this, "Terjadi kesalahan di server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}