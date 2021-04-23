package com.haloqlinic.haloqlinicapps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private int page;
    private  int page_size = 5;
    private List<DataItem> list = new ArrayList<>();
    GridLayoutManager manager;
    ProdukAdapter adapter;

    private boolean isLastPage;
    private boolean isLoading;

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

        searchProduk.setQueryHint("Cari Produk");
        searchProduk.setIconified(false);

        manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        rvProduk.setHasFixedSize(true);
        rvProduk.setLayoutManager(manager);

        page = 1;

        getProdukPagination();

        adapter = new ProdukAdapter(getActivity(), list);
        rvProduk.setAdapter(adapter);

        rvProduk.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = manager.getChildCount();
                int totalItem = manager.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage){

                    if ((visibleItem + firstVisibleItemPosition) >= totalItem &&
                            firstVisibleItemPosition >= 0 && totalItem >= page_size){

                        page++;
                        getProdukPagination();
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
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.cariProduk.DataItem> dataItems = response.body().getData();
                    CariProdukAdapter produkAdapter = new CariProdukAdapter(getActivity(), dataItems);
                    rvProduk.setAdapter(produkAdapter);

                }else{
                    Toast.makeText(getActivity(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariProduk> call, Throwable t) {
                Toast.makeText(getActivity(), "Terjadi kesalahan di server "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getProdukPagination() {

        try {

            progressBar.setVisibility(View.VISIBLE);
            isLoading = true;
            Log.d("checkPage", "getProdukPagination: "+page);

            ConfigRetrofit.service.dataProduk(String.valueOf(page)).enqueue(new Callback<ResponseDataProduk>() {
                @Override
                public void onResponse(Call<ResponseDataProduk> call, Response<ResponseDataProduk> response) {
                    if (response.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        isLoading = false;

                        try{

                            list = response.body().getData();

                            if (list.size()>0){
                                adapter.addList(list);
                                isLastPage = list.size() < page_size;
                            }else{
                                isLastPage = true;
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                            Log.d("tag", "onResponse: "+e.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseDataProduk> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                    Toast.makeText(getActivity(), "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Log.d("tag", "getProdukPagination: "+e.getMessage());
        }

    }
}