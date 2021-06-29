package com.haloqlinic.haloqlinicapps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.CariSpesialisAdapter;
import com.haloqlinic.haloqlinicapps.adapter.DokterAdapter;
import com.haloqlinic.haloqlinicapps.adapter.SpesialisAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseListDokter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterSpesialisFragment extends Fragment {

    public DokterSpesialisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView rvSpesialisFragment, rvCari;
    SearchView searchView;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<DataItem> dataDokter = new ArrayList<>();
    LinearLayoutManager manager;
    DokterAdapter adapter;
    private boolean isLoading = false;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dokter_spesialis, container, false);

        if (dataDokter!=null || dataDokter.size()>0){
            dataDokter.clear();
        }

        rvSpesialisFragment = rootView.findViewById(R.id.recycler_spesialis_fragment);
        rvCari = rootView.findViewById(R.id.recycler_cari_spesialis_fragment);
        searchView = rootView.findViewById(R.id.search_dokter_spesialis);
        progressBar = rootView.findViewById(R.id.progressbar_dokter);

        rvCari.setHasFixedSize(true);
        rvCari.setLayoutManager(new LinearLayoutManager(getContext()));

        manager = new LinearLayoutManager(getContext());
        rvSpesialisFragment.setHasFixedSize(true);
        rvSpesialisFragment.setLayoutManager(manager);

        loadDokterSpesialis();

        adapter = new DokterAdapter(getContext(), dataDokter);
        rvSpesialisFragment.setAdapter(adapter);

        rvSpesialisFragment.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        loadDokterSpesialis();
                    }
                }
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQueryHint("Cari dokter spesialis");
                searchView.setIconified(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.equals("") || newText.isEmpty()){
                    rvCari.setVisibility(View.GONE);
                    rvSpesialisFragment.setVisibility(View.VISIBLE);
                }else {

                    rvSpesialisFragment.setVisibility(View.GONE);
                    rvCari.setVisibility(View.VISIBLE);
                    loadCariDokter(newText);
                }
                return true;
            }
        });

        return rootView;
    }

    private void loadCariDokter(String newText) {

        ConfigRetrofit.service.cariDokter(newText, "1", "spesialis").enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()) {

                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariSpesialisAdapter adapterCari = new CariSpesialisAdapter(getContext(), dataCari);
                    rvCari.setAdapter(adapterCari);

                } else {
                    Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadDokterSpesialis() {

        String status = "1";
        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.dataDokter(status, String.valueOf(page)).enqueue(new Callback<ResponseListDokter>() {
            @Override
            public void onResponse(Call<ResponseListDokter> call, Response<ResponseListDokter> response) {
                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);

                    total_page = response.body().getTotalPage();
                    dataDokter = response.body().getData();
                    if (dataDokter != null) {

                        adapter.addList(dataDokter);
                    }
                    isLoading = false;

                } else {
                    Toast.makeText(getContext(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseListDokter> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                isLoading = false;
                Toast.makeText(getContext(), "Terjadi Kesalahan Di server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}