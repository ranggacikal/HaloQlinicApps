package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.detailProduk.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailProduk.ResponseDetailProduk;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProdukActivity extends AppCompatActivity {

    ImageView imgDetailProduk, imgBack, imgKeranjang;
    TextView txtNamaProduk, txtHargaProduk, txtDeskripsiProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        imgBack = findViewById(R.id.img_back_detail_Produk);
        imgDetailProduk = findViewById(R.id.img_detail_produk);
        imgKeranjang = findViewById(R.id.img_keranjang_detail_Produk);
        txtNamaProduk = findViewById(R.id.text_nama__produk_detail_dokter);
        txtHargaProduk = findViewById(R.id.text_harga_detail_produk);
        txtDeskripsiProduk = findViewById(R.id.text_deskripsi_produk);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadDataDetailProduk();
    }

    private void loadDataDetailProduk() {

        String id_produk = getIntent().getStringExtra("id_produk");

        ProgressDialog progressDialog = new ProgressDialog(DetailProdukActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        ConfigRetrofit.service.detailProduk(id_produk).enqueue(new Callback<ResponseDetailProduk>() {
            @Override
            public void onResponse(Call<ResponseDetailProduk> call, Response<ResponseDetailProduk> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    List<DataItem> dataItems = response.body().getData();
                    String url_image = "";
                    String nama_produk = "";
                    String harga_produk = "";
                    String deskripsi_produk = "";

                    for (int i = 0; i<dataItems.size(); i++){
                        url_image = dataItems.get(i).getImg();
                        nama_produk = dataItems.get(i).getNamaProduk();
                        harga_produk = dataItems.get(i).getHarga();
                        deskripsi_produk = dataItems.get(i).getDeskripsi();
                    }

                    Glide.with(DetailProdukActivity.this)
                            .load(url_image)
                            .error(R.mipmap.ic_launcher)
                            .into(imgDetailProduk);

                    txtNamaProduk.setText(nama_produk);
                    txtHargaProduk.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(harga_produk)));
                    txtDeskripsiProduk.setText(deskripsi_produk);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DetailProdukActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailProduk> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailProdukActivity.this, "Terjadi Kesalahan Di Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}