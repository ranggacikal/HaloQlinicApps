package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityDetailHistoryBinding;
import id.luvie.luvieapps.model.detailHistory.ResponseDetailHistory;

import net.glxn.qrgen.android.QRCode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryActivity extends AppCompatActivity {

    private ActivityDetailHistoryBinding binding;

    String id_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id_transaksi = getIntent().getStringExtra("id_transaksi");

        loadDataDetailHistory();

    }

    private void loadDataDetailHistory() {

        android.app.ProgressDialog progressDialog = new ProgressDialog(DetailHistoryActivity.this);

        progressDialog.setMessage("Memuat info pembayaran");
        progressDialog.show();

        ConfigRetrofit.service.detailHistory(id_transaksi, "1").enqueue(new Callback<ResponseDetailHistory>() {
            @Override
            public void onResponse(Call<ResponseDetailHistory> call, Response<ResponseDetailHistory> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    String tipe = response.body().getTipe();
                    String kode_bayar = response.body().getKodeBayar();

                    if (tipe.equals("qr_string")){
                        binding.textQrCode.setVisibility(View.VISIBLE);
                        binding.textLink.setVisibility(View.GONE);
                        binding.buttonBayarSekarang.setVisibility(View.GONE);
                        binding.imgQrcodeHistoryKonsultasi.setVisibility(View.VISIBLE);
                        Bitmap bitmap = QRCode.from(kode_bayar).bitmap();
                        binding.imgQrcodeHistoryKonsultasi.setImageBitmap(bitmap);
                    }else if(tipe.equals("mobile_web_url")){
                        binding.textQrCode.setVisibility(View.GONE);
                        binding.textLink.setVisibility(View.VISIBLE);
                        binding.buttonBayarSekarang.setVisibility(View.VISIBLE);
                        binding.imgQrcodeHistoryKonsultasi.setVisibility(View.GONE);
                        binding.buttonBayarSekarang.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(kode_bayar));
                                startActivity(i);
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseDetailHistory> call, Throwable t) {

            }
        });

    }
}