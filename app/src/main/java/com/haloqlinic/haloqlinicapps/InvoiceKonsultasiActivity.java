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

import com.haloqlinic.haloqlinicapps.adapter.InvoiceKonsultasiAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.invoiceKonsultasi.DataItem;
import com.haloqlinic.haloqlinicapps.model.invoiceKonsultasi.ResponseInvoiceKonsultasi;

import net.glxn.qrgen.android.QRCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceKonsultasiActivity extends AppCompatActivity {

    ImageView imgShopee, imgDana, imgOvo, imgLinkAja, imgQris, imgQrCode;
    RecyclerView rvInvoice;
    Button btnSelesaikanPembayaran, btnPembayaranSelesai;

    String opsi_bayar = "";

    String id_transaksi, mobile_web, mobile_deeplink, qr_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_konsultasi);
        imgDana = findViewById(R.id.img_dana_invoice_konsultasi);
        imgShopee = findViewById(R.id.img_shopeepay_invoice_konsultasi);
        imgOvo = findViewById(R.id.img_ovo_invoice_konsultasi);
        imgLinkAja = findViewById(R.id.img_linkaja_invoice_konsultasi);
        rvInvoice = findViewById(R.id.recycler_invoice_konsultasi);
        btnSelesaikanPembayaran = findViewById(R.id.btn_selesaikan_pembayaran_invoice_konsultasi);
        imgQris = findViewById(R.id.img_qris_invoice_konsultasi);
        btnPembayaranSelesai = findViewById(R.id.btn_pembayaran_selesai_invoice_konsultasi);
        imgQrCode = findViewById(R.id.img_qrcode_invoice_konsultasi);

        id_transaksi = getIntent().getStringExtra("id_transaksi");
        mobile_web = getIntent().getStringExtra("mobile_web");
        mobile_deeplink = getIntent().getStringExtra("mobile_deeplink");
        qr_string = getIntent().getStringExtra("qr_string");

        btnSelesaikanPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl(opsi_bayar);
            }
        });

        btnPembayaranSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadDataInvoice();
    }

    private void loadDataInvoice() {

        ConfigRetrofit.service.invoiceKonsultasi(id_transaksi).enqueue(new Callback<ResponseInvoiceKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseInvoiceKonsultasi> call, Response<ResponseInvoiceKonsultasi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataInvoice = response.body().getData();

                    for (int i = 0; i<dataInvoice.size(); i++){

                        opsi_bayar = dataInvoice.get(i).getOpsiBayar();

                    }

                    if (opsi_bayar.equals("DANA")){
                        imgDana.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("LinkAja")){
                        imgLinkAja.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("OVO")){
                        imgOvo.setVisibility(View.VISIBLE);
                        btnSelesaikanPembayaran.setVisibility(View.GONE);
                        btnPembayaranSelesai.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("ShopeePay")){
                        imgShopee.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("QRIS")){
                        imgQris.setVisibility(View.VISIBLE);
                        btnSelesaikanPembayaran.setVisibility(View.GONE);
                        btnPembayaranSelesai.setVisibility(View.VISIBLE);
                        imgQrCode.setVisibility(View.VISIBLE);
                        Bitmap myBitmap = QRCode.from(qr_string).bitmap();
                        imgQrCode.setImageBitmap(myBitmap);
                    }

                    InvoiceKonsultasiAdapter adapter = new InvoiceKonsultasiAdapter(InvoiceKonsultasiActivity.this, dataInvoice);
                    rvInvoice.setHasFixedSize(true);
                    rvInvoice.setLayoutManager(new LinearLayoutManager(InvoiceKonsultasiActivity.this));
                    rvInvoice.setAdapter(adapter);

                }else{
                    Toast.makeText(InvoiceKonsultasiActivity.this, "Gagal Memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInvoiceKonsultasi> call, Throwable t) {
                Toast.makeText(InvoiceKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getUrl(String opsi_bayar) {

        String url = "";

        if (opsi_bayar.equals("DANA") || opsi_bayar.equals("LinkAja")){
            url = mobile_web;
        }else if (opsi_bayar.equals("ShopeePay")){
            url = mobile_deeplink;
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }
}