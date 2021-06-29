package com.haloqlinic.haloqlinicapps.fragmentDokterTersedia;

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

import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.adapter.BuatJadwalTersediaAdapter;
import com.haloqlinic.haloqlinicapps.adapter.CariJadwalTersediaAdapter;
import com.haloqlinic.haloqlinicapps.adapter.OnlineDrTersediaAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokterTersedia.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokterTersedia.ResponseDokterTersedia;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuatJadwalDrTersediaFragment extends Fragment {

    public BuatJadwalDrTersediaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvDokterBuatJadwal;
    ProgressBar progressBar;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<DataItem> list = new ArrayList<>();
    LinearLayoutManager manager;
    BuatJadwalTersediaAdapter adapter;
    SearchView searchDokter;

    private boolean isLastPage;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_buat_jadwal_dr_tersedia, container, false);

        rvDokterBuatJadwal = rootView.findViewById(R.id.rv_dokter_tersedia_buat_jadwal);
        progressBar = rootView.findViewById(R.id.progressBarBuatJadwalTersedia);
        searchDokter = rootView.findViewById(R.id.cari_dokter_tersedia_buat_jadwal);

        list.clear();

        manager = new LinearLayoutManager(getActivity());
        rvDokterBuatJadwal.setHasFixedSize(true);
        rvDokterBuatJadwal.setLayoutManager(manager);

        getDokterBuatJadwal();

        adapter = new BuatJadwalTersediaAdapter(getActivity(), list);
        rvDokterBuatJadwal.setAdapter(adapter);

        rvDokterBuatJadwal.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = manager.getChildCount();
                int totalItem = adapter.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && page < total_page) {

                    if (visibleItem + firstVisibleItemPosition >= totalItem) {

                        page++;
                        Log.d("checkScrolled", "page: " + page);
                        getDokterBuatJadwal();
                    }
                }
            }
        });

        searchDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDokter.setQueryHint("Cari Dokter");
                searchDokter.setIconified(false);
            }
        });

        searchDokter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String textCari) {

                loadSearchDokter(textCari);
                return true;
            }
        });

        return rootView;
    }

    private void loadSearchDokter(String textCari) {

        ConfigRetrofit.service.cariDokter(textCari, "2", "").enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariJadwalTersediaAdapter adapterCari = new CariJadwalTersediaAdapter(getActivity(), dataCari);
                    rvDokterBuatJadwal.setAdapter(adapter);

                }else{
                    Toast.makeText(getActivity(), "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDokterBuatJadwal() {

        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.dokterTersedia("list", "2", String.valueOf(page))
                .enqueue(new Callback<ResponseDokterTersedia>() {
                    @Override
                    public void onResponse(Call<ResponseDokterTersedia> call, Response<ResponseDokterTersedia> response) {
                        if (response.isSuccessful()){
                            progressBar.setVisibility(View.GONE);

                            total_page = response.body().getTotalPage();
                            list = response.body().getData();
                            if (list!=null) {

                                adapter.addList(list);
                            }
                            isLoading = false;
                        }else{
                            Toast.makeText(getActivity(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            isLoading = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDokterTersedia> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        isLoading = false;
                        Toast.makeText(getActivity(), "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}