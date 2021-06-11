package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.PenerimaDetailTransaksiAdapter;
import com.haloqlinic.haloqlinicapps.adapter.ProdukDetailTransaksiAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.detailHistory.ResponseDetailHistory;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ProdukItem;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ResponseDetailTransaksi;
import com.thekhaeng.pushdownanim.PushDownAnim;

import net.glxn.qrgen.android.QRCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class DetailTransaksiActivity extends AppCompatActivity {

    RecyclerView rvProdukDetailTransaksi, rvPenerimaDetailTransaksi;

    String id_transaksi, id_member, fragmentStatus, tipe, kode_bayar;
    ImageView imgBack, imgQrCode;
    Button btnSelesaikanTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        rvProdukDetailTransaksi = findViewById(R.id.recycler_produk_detail_transaksi);
        rvPenerimaDetailTransaksi = findViewById(R.id.recycler_data_penerima_detail_transaksi);
        imgBack = findViewById(R.id.img_back_detail_transaksi);
        btnSelesaikanTransaksi = findViewById(R.id.btn_selesaikan_pembayaran_detail);
        imgQrCode = findViewById(R.id.img_qr_string_detail_transaksi);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id_transaksi = getIntent().getStringExtra("id_transaksi");
        id_member = getIntent().getStringExtra("id_member");
        fragmentStatus = getIntent().getStringExtra("fragmentStatusTransaksi");

        rvProdukDetailTransaksi.setHasFixedSize(true);
        rvProdukDetailTransaksi.setLayoutManager(new LinearLayoutManager(DetailTransaksiActivity.this));

        rvPenerimaDetailTransaksi.setHasFixedSize(true);
        rvPenerimaDetailTransaksi.setLayoutManager(new LinearLayoutManager(DetailTransaksiActivity.this));

        if (fragmentStatus != null){

            if (fragmentStatus.equals("belumbayar")){

                btnSelesaikanTransaksi.setVisibility(View.VISIBLE);
                imgQrCode.setVisibility(View.VISIBLE);

            }

        }

        PushDownAnim.setPushDownAnimTo(btnSelesaikanTransaksi)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getUrl();
                    }
                });


        loadDataPembayaran();
        loadProdukDetail();
        loadPenerimaDetail();
    }

    private void getUrl() {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(kode_bayar));
        startActivity(i);

    }

    private void loadDataPembayaran() {

        ConfigRetrofit.service.detailHistory(id_transaksi, "0").enqueue(new Callback<ResponseDetailHistory>() {
            @Override
            public void onResponse(Call<ResponseDetailHistory> call, Response<ResponseDetailHistory> response) {
                if (response.isSuccessful()){


                    tipe = response.body().getTipe();
                    kode_bayar = response.body().getKodeBayar();

                    if (tipe.equals("qr_string")){
                        btnSelesaikanTransaksi.setVisibility(View.GONE);
                        Bitmap myBitmap = QRCode.from(kode_bayar).bitmap();
                        imgQrCode.setImageBitmap(myBitmap);
                    }

                }else{
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal memuat data pembayaran", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailHistory> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadPenerimaDetail() {

        ConfigRetrofit.service.detailTransaksi(id_transaksi, id_member).enqueue(new Callback<ResponseDetailTransaksi>() {
            @Override
            public void onResponse(Call<ResponseDetailTransaksi> call, Response<ResponseDetailTransaksi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataPenerima = response.body().getData();
                    PenerimaDetailTransaksiAdapter adapterPenerima = new PenerimaDetailTransaksiAdapter(DetailTransaksiActivity.this, dataPenerima);
                    rvPenerimaDetailTransaksi.setAdapter(adapterPenerima);

                }else{
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadProdukDetail() {

        ConfigRetrofit.service.detailTransaksi(id_transaksi, id_member).enqueue(new Callback<ResponseDetailTransaksi>() {
            @Override
            public void onResponse(Call<ResponseDetailTransaksi> call, Response<ResponseDetailTransaksi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataTransaksi = response.body().getData();
                    List<ProdukItem> produkItems = null;
                    
                    for (int i = 0; i<dataTransaksi.size(); i++){
                        
                        produkItems = dataTransaksi.get(i).getProduk();
                        
                    }
                    ProdukDetailTransaksiAdapter adapterProduk = new ProdukDetailTransaksiAdapter(DetailTransaksiActivity.this, produkItems);
                    rvProdukDetailTransaksi.setAdapter(adapterProduk);
                }else{
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}