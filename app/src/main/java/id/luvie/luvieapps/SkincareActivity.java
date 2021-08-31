package id.luvie.luvieapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import id.luvie.luvieapps.adapter.CariProdukKategoriAdapter;
import id.luvie.luvieapps.adapter.ProdukKategoriAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivitySkincareBinding;
import id.luvie.luvieapps.model.cariProdukKategori.ResponseCariProdukKategori;
import id.luvie.luvieapps.model.produkKategori.DataItem;
import id.luvie.luvieapps.model.produkKategori.ResponseProdukKategori;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkincareActivity extends AppCompatActivity {

    private ActivitySkincareBinding binding;

    private int page;
    private  int page_size = 5;
    private List<DataItem> list = new ArrayList<>();
    GridLayoutManager manager;
    GridLayoutManager managerSearch;
    ProdukKategoriAdapter adapter;

    private boolean isLastPage;
    private boolean isLoading;

    String id_kategori, nama_kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySkincareBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id_kategori = getIntent().getStringExtra("id_kategori");
        nama_kategori = getIntent().getStringExtra("nama_kategori");

        binding.textNamaProdukKategori.setText(nama_kategori);

        binding.imgBackSkincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.searchSkincare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchSkincare.setQueryHint("Cari Skincare");
                binding.searchSkincare.setIconified(false);
            }
        });

        manager = new GridLayoutManager(SkincareActivity.this, 2, GridLayoutManager.VERTICAL, false);
        binding.recyclerSkincare.setHasFixedSize(true);
        binding.recyclerSkincare.setLayoutManager(manager);

        managerSearch = new GridLayoutManager(SkincareActivity.this, 2,
                GridLayoutManager.VERTICAL, false);
        binding.recyclerCariSkincare.setHasFixedSize(true);
        binding.recyclerCariSkincare.setLayoutManager(managerSearch);

        page = 1;

        getSkincarePagination();

        adapter = new ProdukKategoriAdapter(SkincareActivity.this, list);
        binding.recyclerSkincare.setAdapter(adapter);

        binding.recyclerSkincare.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = manager.getChildCount();
                int totalItem = manager.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage){

                    if ((visibleItem + firstVisibleItemPosition) >= totalItem &&
                            firstVisibleItemPosition >= 0 && totalItem >= page_size){

                        page++;
                        getSkincarePagination();
                    }
                }
            }
        });

        binding.searchSkincare.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                loadSearchSkincare(newText);
                return true;
            }
        });

    }

    private void loadSearchSkincare(String newText) {

        if (newText.equals("")){

            binding.recyclerSkincare.setVisibility(View.VISIBLE);
            binding.recyclerCariSkincare.setVisibility(View.GONE);

        }else {

            ConfigRetrofit.service.cariProdukKategori(newText, id_kategori).enqueue(new Callback<ResponseCariProdukKategori>() {
                @Override
                public void onResponse(Call<ResponseCariProdukKategori> call, Response<ResponseCariProdukKategori> response) {
                    if (response.isSuccessful()) {

                        binding.recyclerSkincare.setVisibility(View.GONE);
                        binding.recyclerCariSkincare.setVisibility(View.VISIBLE);

                        if (response.body() != null) {

                            List<id.luvie.luvieapps.model.cariProdukKategori.DataItem> dataCari = response
                                    .body().getData();

                            CariProdukKategoriAdapter adapterCari = new CariProdukKategoriAdapter(SkincareActivity.this,
                                    dataCari);

                            binding.recyclerCariSkincare.setAdapter(adapterCari);

                        } else {
                            Toast.makeText(SkincareActivity.this, "Data Kosong",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(SkincareActivity.this, "Response Gagal",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseCariProdukKategori> call, Throwable t) {
                    Toast.makeText(SkincareActivity.this, "Error : " + t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void getSkincarePagination() {

        try {

            binding.progressBarSkincare.setVisibility(View.VISIBLE);
            isLoading = true;
            Log.d("checkPage", "getProdukPagination: "+page);

            ConfigRetrofit.service.produkKategori(String.valueOf(page), id_kategori).enqueue(new Callback<ResponseProdukKategori>() {
                @Override
                public void onResponse(Call<ResponseProdukKategori> call, Response<ResponseProdukKategori> response) {
                    if (response.isSuccessful()){
                        binding.progressBarSkincare.setVisibility(View.GONE);
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
                public void onFailure(Call<ResponseProdukKategori> call, Throwable t) {
                    binding.progressBarSkincare.setVisibility(View.GONE);
                    isLoading = false;
                    Toast.makeText(SkincareActivity.this, "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Log.d("tag", "getProdukPagination: "+e.getMessage());
        }

    }
}