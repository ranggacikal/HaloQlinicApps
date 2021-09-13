package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.adapter.DokterMitraAdapter;
import id.luvie.luvieapps.adapter.DokterMitraTersediaAdapter;
import id.luvie.luvieapps.adapter.NonDokterMitraAdapter;
import id.luvie.luvieapps.adapter.PromoMitraAdapter;
import id.luvie.luvieapps.adapter.TreatmentAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;


import id.luvie.luvieapps.databinding.ActivityProfileMitraBinding;
import id.luvie.luvieapps.model.dokterMitra.DataItem;
import id.luvie.luvieapps.model.dokterMitra.ResponseDokterMitra;
import id.luvie.luvieapps.model.nonDokter.ResponseNonDokter;
import id.luvie.luvieapps.model.produkMitra.ResponseProdukMitra;
import id.luvie.luvieapps.model.profileMitra.ListItem;
import id.luvie.luvieapps.model.profileMitra.ResponseProfileMitra;
import id.luvie.luvieapps.model.promoMitra.ResponsePromoMitra;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
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
        Log.d("idMemberMitra", "onCreate: "+id_member);

        binding.rvListDokterMitra.setHasFixedSize(true);
        GridLayoutManager managerGrid = new GridLayoutManager(ProfileMitraActivity.this,
                2, GridLayoutManager.VERTICAL, false);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(ProfileMitraActivity.this,
//                LinearLayoutManager.HORIZONTAL, false);
        binding.rvListDokterMitra.setLayoutManager(managerGrid);

        loadDokterMitra();

        binding.rvListNonDokterMitra.setHasFixedSize(true);
        GridLayoutManager managerGrid2 = new GridLayoutManager(ProfileMitraActivity.this,
                2, GridLayoutManager.VERTICAL, false);
        binding.rvListNonDokterMitra.setLayoutManager(managerGrid2);

        loadNonDokter();

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

        PushDownAnim.setPushDownAnimTo(binding.textLihatSemuaDokterMitra)
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

    private void loadNonDokter() {

        ProgressDialog progressDialog = new ProgressDialog(ProfileMitraActivity.this);
        progressDialog.setMessage("load data non dokter");
        progressDialog.show();

        ConfigRetrofit.service.dataNonDokter(kode).enqueue(new Callback<ResponseNonDokter>() {
            @Override
            public void onResponse(Call<ResponseNonDokter> call, Response<ResponseNonDokter> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();

                    List<id.luvie.luvieapps.model.nonDokter.DataItem> dataNonDokter =
                            response.body().getData();
                    NonDokterMitraAdapter adapter = new NonDokterMitraAdapter
                            (ProfileMitraActivity.this, dataNonDokter);
                    binding.rvListNonDokterMitra.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(ProfileMitraActivity.this,
                            "Gagal Load Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseNonDokter> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ProfileMitraActivity.this,
                        "Koneksi Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokterMitra() {

        ProgressDialog progressDialog = new ProgressDialog(ProfileMitraActivity.this);
        progressDialog.setMessage("load data dokter mitra");
        progressDialog.show();


        Log.d("kodeDokterMitra", "onCreate: "+kode);

        ConfigRetrofit.service.dataDokterMitra(kode).enqueue(new Callback<ResponseDokterMitra>() {
            @Override
            public void onResponse(Call<ResponseDokterMitra> call, Response<ResponseDokterMitra> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    if (response.body()!=null){

                        List<DataItem> dataDokter = response.body().getData();
                        DokterMitraTersediaAdapter adapter = new DokterMitraTersediaAdapter(ProfileMitraActivity.this,
                                dataDokter);
                        binding.rvListDokterMitra.setAdapter(adapter);

                    }else{
                        Toast.makeText(ProfileMitraActivity.this, "Data Dokter Kosong",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(ProfileMitraActivity.this, "Response Error",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDokterMitra> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ProfileMitraActivity.this, "Error: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getPromoMitra() {

        ConfigRetrofit.service.dataProdukMitra(id_member, "1").enqueue(new Callback<ResponseProdukMitra>() {
            @Override
            public void onResponse(Call<ResponseProdukMitra> call, Response<ResponseProdukMitra> response) {
                if (response.isSuccessful()){

                    if (response.body() != null){

                        List<id.luvie.luvieapps.model.produkMitra.DataItem> dataPromo = response.body()
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
            public void onFailure(Call<ResponseProdukMitra> call, Throwable t) {
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

                        List<id.luvie.luvieapps.model.profileMitra.DataItem> profileMitra = response.body().getData();
                        List<ListItem> listTreatment = null;

                        String nama_toko = "";

                        for (int a = 0; a < profileMitra.size(); a++) {

                            nama_toko = profileMitra.get(a).getNamaToko();
                            listTreatment = profileMitra.get(a).getList();


                        }

                        List<ListItem> listTest = new ArrayList<>();

                        Log.d("cekListTreatment", "onResponse: "+listTreatment.toString());

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