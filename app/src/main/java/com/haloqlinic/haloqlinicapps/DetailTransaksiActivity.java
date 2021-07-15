package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.PenerimaDetailTransaksiAdapter;
import com.haloqlinic.haloqlinicapps.adapter.ProdukDetailTransaksiAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.detailHistory.ResponseDetailHistory;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ProdukItem;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ResponseDetailTransaksi;
import com.haloqlinic.haloqlinicapps.model.updatePengiriman.ResponseUpdatePengiriman;
import com.thekhaeng.pushdownanim.PushDownAnim;

import net.glxn.qrgen.android.QRCode;

import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class DetailTransaksiActivity extends AppCompatActivity {

    RecyclerView rvProdukDetailTransaksi, rvPenerimaDetailTransaksi;

    String id_transaksi, id_member, fragmentStatus, tipe, kode_bayar, id_pengiriman;
    ImageView imgBack, imgQrCode;
    Button btnSelesaikanTransaksi, btnPesananDiterima;
    LinearLayout linearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        rvProdukDetailTransaksi = findViewById(R.id.recycler_produk_detail_transaksi);
        rvPenerimaDetailTransaksi = findViewById(R.id.recycler_data_penerima_detail_transaksi);
        imgBack = findViewById(R.id.img_back_detail_transaksi);
        btnSelesaikanTransaksi = findViewById(R.id.btn_selesaikan_pembayaran_detail);
        imgQrCode = findViewById(R.id.img_qr_string_detail_transaksi);
        btnPesananDiterima = findViewById(R.id.btn_pesanan_diterima_detail);
        linearButton = findViewById(R.id.linear_button_detail_transaksi);

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

                linearButton.setVisibility(View.VISIBLE);
                btnSelesaikanTransaksi.setVisibility(View.VISIBLE);
                imgQrCode.setVisibility(View.VISIBLE);

            }else if (fragmentStatus.equals("dikirim")){

                id_pengiriman = getIntent().getStringExtra("id_pengiriman");
                linearButton.setVisibility(View.VISIBLE);
                btnSelesaikanTransaksi.setVisibility(View.GONE);
                btnPesananDiterima.setVisibility(View.VISIBLE);

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

        PushDownAnim.setPushDownAnimTo(btnPesananDiterima)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tampilDialog();
                    }
                });


        loadDataPembayaran();
        loadProdukDetail();
        loadPenerimaDetail();
    }

    private void tampilDialog() {

        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Terima Pesanan?")
                .setMessage("Apakah anda yakin ingin Menerima Pesanan Anda?")
                .setCancelable(false)
                .setPositiveButton("Iya", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        pesananDiterima();
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

    private void pesananDiterima() {

        ProgressDialog progressDialog = new ProgressDialog(DetailTransaksiActivity.this);
        progressDialog.setMessage("Menerima Pesanan");
        progressDialog.show();

        ConfigRetrofit.service.updatePengiriman(id_pengiriman).enqueue(new Callback<ResponseUpdatePengiriman>() {
            @Override
            public void onResponse(Call<ResponseUpdatePengiriman> call, Response<ResponseUpdatePengiriman> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(DetailTransaksiActivity.this, "berhasil update pengiriman", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal menerima pesanan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdatePengiriman> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailTransaksiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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