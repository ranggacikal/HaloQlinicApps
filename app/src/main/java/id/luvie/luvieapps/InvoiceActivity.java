package id.luvie.luvieapps;

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

import id.luvie.luvieapps.adapter.InvoiceAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.invoice.DataItem;
import id.luvie.luvieapps.model.invoice.ResponseInvoice;

import net.glxn.qrgen.android.QRCode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceActivity extends AppCompatActivity {

    ImageView imgShopee, imgDana, imgOvo, imgLinkAja, imgQris, imgQrCode;
    RecyclerView rvInvoice;
    Button btnSelesaikanPembayaran, btnPembayaranSelesai;

    String opsi_bayar = "";

    String id_transaksi, mobile_web, mobile_deeplink, qr_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        imgDana = findViewById(R.id.img_dana_invoice);
        imgShopee = findViewById(R.id.img_shopeepay_invoice);
        imgOvo = findViewById(R.id.img_ovo_invoice);
        imgLinkAja = findViewById(R.id.img_linkaja_invoice);
        rvInvoice = findViewById(R.id.recycler_invoice);
        btnSelesaikanPembayaran = findViewById(R.id.btn_selesaikan_pembayaran_invoice);
        imgQris = findViewById(R.id.img_qris_invoice);
        btnPembayaranSelesai = findViewById(R.id.btn_pembayaran_selesai_invoice);
        imgQrCode = findViewById(R.id.img_qrcode_invoice);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(InvoiceActivity.this, KeranjangActivity.class));
        finish();
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

    private void loadDataInvoice() {

        ConfigRetrofit.service.invoice(id_transaksi).enqueue(new Callback<ResponseInvoice>() {
            @Override
            public void onResponse(Call<ResponseInvoice> call, Response<ResponseInvoice> response) {
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

                    InvoiceAdapter adapter = new InvoiceAdapter(InvoiceActivity.this, dataInvoice);
                    rvInvoice.setHasFixedSize(true);
                    rvInvoice.setLayoutManager(new LinearLayoutManager(InvoiceActivity.this));
                    rvInvoice.setAdapter(adapter);

                }else{
                    Toast.makeText(InvoiceActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInvoice> call, Throwable t) {
                Toast.makeText(InvoiceActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}