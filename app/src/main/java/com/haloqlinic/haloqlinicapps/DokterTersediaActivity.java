package com.haloqlinic.haloqlinicapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.CariDokterTersediaAdapter;
import com.haloqlinic.haloqlinicapps.adapter.DokterAdapter;
import com.haloqlinic.haloqlinicapps.adapter.DokterTersediaAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterTersediaActivity extends AppCompatActivity {

    RecyclerView rvDokterTersedia;
    ImageView imgBack;
    SearchView cariDokter;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<DataItem> dataDokter = new ArrayList<>();
    LinearLayoutManager manager;
    DokterTersediaAdapter adapter;
    private boolean isLoading = false;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_tersedia);

        rvDokterTersedia = findViewById(R.id.recycler_dokter_tersedia);
        imgBack = findViewById(R.id.img_back_dokter_tersedia);
        cariDokter = findViewById(R.id.cari_dokter_tersedia);
        progressBar = findViewById(R.id.progressbar_dokter_online);

        manager = new LinearLayoutManager(DokterTersediaActivity.this);
        rvDokterTersedia.setHasFixedSize(true);
        rvDokterTersedia.setLayoutManager(manager);

        loadDokterTersedia();

        adapter = new DokterTersediaAdapter(DokterTersediaActivity.this, dataDokter);
        rvDokterTersedia.setAdapter(adapter);

        rvDokterTersedia.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = manager.getChildCount();
                int totalItem = adapter.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && page < total_page) {

                    if (visibleItem + firstVisibleItemPosition >= totalItem) {

                        page++;
                        Log.d("checkScrolled", "page: " + page);
                        loadDokterTersedia();
                    }
                }
            }
        });

        cariDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cariDokter.setQueryHint("cari dokter umum");
                cariDokter.setIconified(false);
            }
        });

        cariDokter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadCariDataDokter(newText);
                return true;
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadCariDataDokter(String newText) {

        ConfigRetrofit.service.cariDokter("0", newText).enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariDokterTersediaAdapter adapterCari = new CariDokterTersediaAdapter(DokterTersediaActivity.this, dataCari);
                    rvDokterTersedia.setAdapter(adapterCari);

                }else{
                    Toast.makeText(DokterTersediaActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(DokterTersediaActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokterTersedia() {

        String status = "1";
        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.dataDokterAktif(status, String.valueOf(page)).enqueue(new Callback<ResponseDataDokterAktif>() {
            @Override
            public void onResponse(Call<ResponseDataDokterAktif> call, Response<ResponseDataDokterAktif> response) {
                if (response.isSuccessful()){

                    progressBar.setVisibility(View.GONE);

                    total_page = response.body().getTotalPage();
                    dataDokter = response.body().getData();
                    if (dataDokter!=null) {

                        adapter.addList(dataDokter);
                    }
                    isLoading = false;

                }else{
                    Toast.makeText(DokterTersediaActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseDataDokterAktif> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                isLoading = false;
                Toast.makeText(DokterTersediaActivity.this, "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}