package com.haloqlinic.haloqlinicapps;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.CariProdukAdapter;
import com.haloqlinic.haloqlinicapps.adapter.ProdukAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariProduk.ResponseCariProduk;
import com.haloqlinic.haloqlinicapps.model.produk.DataItem;
import com.haloqlinic.haloqlinicapps.model.produk.ResponseDataProduk;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukFragment extends Fragment {

    public ProdukFragment() {
        // Required empty public constructor
    }

    RecyclerView rvProduk;
    ProgressBar progressBar;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<DataItem> list = new ArrayList<>();
    GridLayoutManager manager;
    ProdukAdapter adapter;

    private boolean isLastPage;
    private boolean isLoading = false;

    SearchView searchProduk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_produk, container, false);

        rvProduk = rootView.findViewById(R.id.recycler_produk);
        progressBar = rootView.findViewById(R.id.progress_bar_produk);
        searchProduk = rootView.findViewById(R.id.search_produk);

        if (list!=null) {
            list.clear();
        }

        getProdukPagination(1);

        searchProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchProduk.setQueryHint("Cari Produk");
                searchProduk.setIconified(false);
            }
        });

        manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rvProduk.setHasFixedSize(true);
        rvProduk.setLayoutManager(manager);

        adapter = new ProdukAdapter(getContext(), list);
        rvProduk.setAdapter(adapter);

        rvProduk.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                int visibleItem = manager.getChildCount();
                int totalItem = adapter.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && page < total_page) {

                    if (visibleItem + firstVisibleItemPosition >= totalItem) {

                        page++;
                        Log.d("checkScrolled", "page: " + page);
                        getProdukPagination(page);
                    }
                }
            }
        });

        searchProduk.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                loadSearchProduk(newText);
                return true;
            }
        });

        return rootView;
    }

    private void loadSearchProduk(String newText) {

        ConfigRetrofit.service.cariProduk(newText).enqueue(new Callback<ResponseCariProduk>() {
            @Override
            public void onResponse(Call<ResponseCariProduk> call, Response<ResponseCariProduk> response) {
                if (response.isSuccessful()) {

                    List<com.haloqlinic.haloqlinicapps.model.cariProduk.DataItem> dataItems = response.body().getData();
                    CariProdukAdapter produkAdapter = new CariProdukAdapter(getContext(), dataItems);
                    rvProduk.setAdapter(produkAdapter);

                } else {
                    Toast.makeText(getContext(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariProduk> call, Throwable t) {
                Toast.makeText(getContext(), "Terjadi kesalahan di server " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getProdukPagination(int pageCons) {

        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;
        Log.d("checkPage", "getProdukPagination: " + page);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConfigRetrofit.service.dataProduk(String.valueOf(pageCons)).enqueue(new Callback<ResponseDataProduk>() {
                    @Override
                    public void onResponse(Call<ResponseDataProduk> call, Response<ResponseDataProduk> response) {
                        if (response.isSuccessful()){
                            progressBar.setVisibility(View.GONE);

                            total_page = response.body().getTotalPage();
                            list = response.body().getData();
                            if (list!=null) {

                                adapter.addList(list);
                            }
                            isLoading = false;
                        }else{
                            Toast.makeText(getContext(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            isLoading = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDataProduk> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        isLoading = false;
                        Toast.makeText(getContext(), "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, 2000);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
    }
}