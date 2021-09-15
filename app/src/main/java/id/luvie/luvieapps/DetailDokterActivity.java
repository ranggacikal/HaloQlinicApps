package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.api.ConfigRetrofit;

import id.luvie.luvieapps.databinding.ActivityDetailDokterBinding;
import id.luvie.luvieapps.model.detailDokter.ResponseDetailDokter;
import id.luvie.luvieapps.model.detailDokter.ResultItem;
import id.luvie.luvieapps.model.konsultasi.ResponseKonsultasi;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDokterActivity extends AppCompatActivity {

    String id_dokter, status;
    ProgressDialog progressDialog;

    private SharedPreferencedConfig preferencedConfig;

    private ActivityDetailDokterBinding binding;

    String id_kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailDokterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(DetailDokterActivity.this);
        progressDialog = new ProgressDialog(DetailDokterActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        id_dokter = getIntent().getStringExtra("id_dokter");
        status = getIntent().getStringExtra("status");
        id_kategori = getIntent().getStringExtra("id_kategori");

        if (status!=null) {

            if (status.equals("offline")) {
                binding.btnChatDetailDokter.setVisibility(View.GONE);
                binding.btnBuatJadwalDetailDokter.setVisibility(View.VISIBLE);
            } else {
                binding.btnBuatJadwalDetailDokter.setVisibility(View.GONE);
                binding.btnChatDetailDokter.setVisibility(View.VISIBLE);
            }

        }

        loadDataDetailDokter(id_dokter);

        binding.imgBackDetailDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnChatDetailDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konsultasiSekarang();
            }
        });

        binding.btnBuatJadwalDetailDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailDokterActivity.this, AturJadwalActivity.class);
                intent.putExtra("id_dokter", id_dokter);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadDataDetailDokter(String id_dokter) {

        ConfigRetrofit.service.detailDokter(id_dokter).enqueue(new Callback<ResponseDetailDokter>() {
            @Override
            public void onResponse(Call<ResponseDetailDokter> call, Response<ResponseDetailDokter> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();

                    List<ResultItem> dataDokter = response.body().getResult();

                    String nama_dokter = "", spesialis_dokter = "", harga_dokter = "",
                            pengalaman = "", nama_kampus = "",
                            tempat_praktik = "", nomor_str = "", img="";

                    final String url_image = "https://luvie.co.id/file/dokter/profile/";

                    for (int i = 0; i < dataDokter.size(); i++){

                        nama_dokter = dataDokter.get(i).getNama();
                        spesialis_dokter = dataDokter.get(i).getSpesialis();
                        harga_dokter = dataDokter.get(i).getBiaya();
                        pengalaman = (String) dataDokter.get(i).getPengalaman();
                        nama_kampus = (String) dataDokter.get(i).getAlumni();
                        tempat_praktik = (String) dataDokter.get(i).getTempatPraktik();
                        nomor_str = (String) dataDokter.get(i).getStr();
                        img = dataDokter.get(i).getImg();

                    }

                    Log.d("checkDataDetail", "alumni: "+nama_kampus);
                    Log.d("checkDataDetail", "tempat_praktik: "+tempat_praktik);
                    Log.d("checkDataDetail", "nomor_str: "+nomor_str);

                    Log.d("checkImageDetail", "onResponse: "+url_image+img);

                    Glide.with(DetailDokterActivity.this)
                            .load(url_image+img)
                            .error(R.mipmap.ic_launcher)
                            .into(binding.imgDetailDokter);
                    if(id_kategori!=null){
                        if(id_kategori.equals("4")){
                            binding.textNamaDetailDokter.setText(nama_dokter);
                        }else{

                            binding.textNamaDetailDokter.setText("Dr. "+nama_dokter);

                        }
                    }
                    binding.textSpesialisDetailDokter.setText("Spesialis "+spesialis_dokter);
                    binding.textHargaDetailDokter.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(harga_dokter)));

                    if (pengalaman==null){
                        binding.textTahunDetailDokter.setText("-");
                    }else{
                        binding.textTahunDetailDokter.setText(pengalaman+" tahun");
                    }

                    if (nama_kampus==null){
                        binding.textNamaKampusDetailDokter.setText("-");
                    }else{
                        binding.textNamaKampusDetailDokter.setText(nama_kampus);
                    }

                    if (tempat_praktik==null){
                        binding.textTempatPraktisDetailDokter.setText("-");
                    }else{
                        binding.textTempatPraktisDetailDokter.setText(tempat_praktik);
                    }

                    if (nomor_str==null){
                        binding.textNoStrDetailDokter.setText("-");
                    }else{
                        binding.textNoStrDetailDokter.setText(nomor_str);
                    }


                }else {
                    progressDialog.dismiss();
                    Toast.makeText(DetailDokterActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailDokter> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailDokterActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
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

        RelativeLayout relativeBuatJadwal = alertDialog.findViewById(R.id.relative_buat_jadwal);
        RelativeLayout relativeKonsultasiSekarang = alertDialog.findViewById(R.id.relative_konsultasi_sekarang);

        alertDialog.show();

        relativeBuatJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailDokterActivity.this, AturJadwalActivity.class);
                intent.putExtra("id_dokter", id_dokter);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });

        relativeKonsultasiSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konsultasiSekarang();
                alertDialog.dismiss();
            }
        });

    }

    private void konsultasiSekarang() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ProgressDialog progressKonsultasi = new ProgressDialog(DetailDokterActivity.this);
        progressKonsultasi.setMessage("Membuat Permintaan Konsultasi");
        progressKonsultasi.show();

        ConfigRetrofit.service.postKonsultasi(id_customer, "", id_dokter, "", "1")
                .enqueue(new Callback<ResponseKonsultasi>() {
                    @Override
                    public void onResponse(Call<ResponseKonsultasi> call, Response<ResponseKonsultasi> response) {
                        if (response.isSuccessful()){

                            progressKonsultasi.dismiss();
                            String id_transaksi = response.body().getIdTransaksi();
                            String external_id = response.body().getExternalId();

                            Log.d("getIdTransaksi", "onResponse: "+id_transaksi);

                            Toast.makeText(DetailDokterActivity.this, "Berhasil membuat permintaan konsultasi", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DetailDokterActivity.this, TungguAccActivity.class);
                            intent.putExtra("id_transaksi", id_transaksi);
                            intent.putExtra("id_dokter", id_dokter);
                            intent.putExtra("external_id", external_id);
                            startActivity(intent);
                            finish();

                        }else{
                            progressKonsultasi.dismiss();
                            Toast.makeText(DetailDokterActivity.this, "Gagal membuat permintaan konsultasi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKonsultasi> call, Throwable t) {
                        progressKonsultasi.dismiss();
                        Toast.makeText(DetailDokterActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}