package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.databinding.ActivityHistoryKonsultasiBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityProfileMitraBinding;
import com.haloqlinic.haloqlinicapps.model.profileMitra.DataItem;
import com.haloqlinic.haloqlinicapps.model.profileMitra.ResponseProfileMitra;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileMitraActivity extends AppCompatActivity {

    private ActivityProfileMitraBinding binding;

    String id_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileMitraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id_member = getIntent().getStringExtra("id_member");

        binding.imgBackProfileMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getDataProfile();

    }

    private void getDataProfile() {

        ProgressDialog progressDialog = new ProgressDialog(ProfileMitraActivity.this);
        progressDialog.setMessage("Memuat Data Profile Mitra");
        progressDialog.show();

        ConfigRetrofit.service.profileMitra(id_member).enqueue(new Callback<ResponseProfileMitra>() {
            @Override
            public void onResponse(Call<ResponseProfileMitra> call, Response<ResponseProfileMitra> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();

                    List<DataItem> profileMitra = response.body().getData();

                    String nama_toko = "";
                    String alamat_lengkap = "";
                    String kecamatan = "";
                    String kota = "";
                    String provinsi = "";
                    String alamat = "";
                    String no_hp = "";

                    for (int a = 0; a<profileMitra.size(); a++){
                        nama_toko = profileMitra.get(a).getNamaToko();
                        alamat = profileMitra.get(a).getAlamat();
                        kecamatan = profileMitra.get(a).getNmKecamatan();
                        kota = profileMitra.get(a).getNmKota();
                        provinsi = profileMitra.get(a).getNmProvinsi();
                        no_hp = profileMitra.get(a).getNoHp();
                    }

                    alamat_lengkap = alamat+" ,"+kecamatan+" ,"+kota+" ,"+provinsi;

                    binding.textNamaProfileMitra.setText(nama_toko);
                    binding.textAlamatProfileMitra.setText(alamat_lengkap);
                    binding.textNoHpProfileMitra.setText(no_hp);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(ProfileMitraActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProfileMitra> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ProfileMitraActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}