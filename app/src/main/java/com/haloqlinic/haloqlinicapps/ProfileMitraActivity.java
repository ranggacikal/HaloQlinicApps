package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.PromoMitraAdapter;
import com.haloqlinic.haloqlinicapps.adapter.TreatmentAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.databinding.ActivityHistoryKonsultasiBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityProfileMitraBinding;
import com.haloqlinic.haloqlinicapps.model.profileMitra.DataItem;
import com.haloqlinic.haloqlinicapps.model.profileMitra.ListItem;
import com.haloqlinic.haloqlinicapps.model.profileMitra.ResponseProfileMitra;
import com.haloqlinic.haloqlinicapps.model.promoMitra.ResponsePromoMitra;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class ProfileMitraActivity extends AppCompatActivity {

    private ActivityProfileMitraBinding binding;

    public String id_member, kode;

    public ProfileMitraActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileMitraBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id_member = getIntent().getStringExtra("id_member");
        kode = getIntent().getStringExtra("kode");

        binding.recyclerTreatment.setLayoutManager(new LinearLayoutManager(ProfileMitraActivity.this));
        binding.recyclerTreatment.setHasFixedSize(true);

        binding.recyclerPromoMitra.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(ProfileMitraActivity.this, 2, GridLayoutManager.VERTICAL, false);
        binding.recyclerPromoMitra.setLayoutManager(manager);


        binding.imgBackProfileMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        PushDownAnim.setPushDownAnimTo(binding.textLihatListDokter)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileMitraActivity.this, DokterMitraActivity.class);
                        intent.putExtra("kode", kode);
                        startActivity(intent);
                    }
                });

        getDataProfile();
        getPromoMitra();

    }

    private void getPromoMitra() {

        ConfigRetrofit.service.promoMitra(id_member).enqueue(new Callback<ResponsePromoMitra>() {
            @Override
            public void onResponse(Call<ResponsePromoMitra> call, Response<ResponsePromoMitra> response) {
                if (response.isSuccessful()){

                    if (response.body() != null){

                        List<com.haloqlinic.haloqlinicapps.model.promoMitra.DataItem> dataPromo = response.body()
                                .getData();

                        PromoMitraAdapter adapter = new PromoMitraAdapter(ProfileMitraActivity.this, dataPromo);
                        binding.recyclerPromoMitra.setAdapter(adapter);

                    }else{
                        Toast.makeText(ProfileMitraActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(ProfileMitraActivity.this, "Response from server failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePromoMitra> call, Throwable t) {
                Toast.makeText(ProfileMitraActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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

                    if (response.body() != null) {

                        List<DataItem> profileMitra = response.body().getData();
                        List<ListItem> listTreatment = null;

                        String nama_toko = "";

                        for (int a = 0; a < profileMitra.size(); a++) {

                            nama_toko = profileMitra.get(a).getNamaToko();
                            listTreatment = profileMitra.get(a).getList();


                        }

                        if (listTreatment.size()<1){
                            Toast.makeText(ProfileMitraActivity.this, "list treatment kosong",
                                    Toast.LENGTH_SHORT).show();
                        }

                        TreatmentAdapter adapter = new TreatmentAdapter(ProfileMitraActivity.this,
                                listTreatment, ProfileMitraActivity.this);

                        binding.recyclerTreatment.setAdapter(adapter);

                        binding.textNamaProfileMitra.setText(nama_toko);

                    }else{
                        Toast.makeText(ProfileMitraActivity.this, "Data Kosong",
                                Toast.LENGTH_SHORT).show();
                    }



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

    @Override
    protected void onResume() {
        super.onResume();
        getDataProfile();
    }
}