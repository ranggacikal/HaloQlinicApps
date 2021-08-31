package id.luvie.luvieapps;

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

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.adapter.CariUmumAdapter;
import id.luvie.luvieapps.adapter.DokterUmumAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.cariDokter.ResponseCariDokter;
import id.luvie.luvieapps.model.listDokterumum.DataItem;
import id.luvie.luvieapps.model.listDokterumum.ResponseDataDokterUmum;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterUmumFragment extends Fragment {

    public DokterUmumFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView rvDokterumum;
    SearchView searchDokterumum;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<DataItem> dataDokter = new ArrayList<>();
    LinearLayoutManager manager;
    DokterUmumAdapter adapter;
    private boolean isLoading = false;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dokter_umum, container, false);

        if (dataDokter!=null || dataDokter.size()>0){
            dataDokter.clear();
        }

        rvDokterumum = rootView.findViewById(R.id.recycler_umum_fragment);
        searchDokterumum = rootView.findViewById(R.id.search_dokter_umum);
        progressBar = rootView.findViewById(R.id.progressbar_dokter_umum);

        manager = new LinearLayoutManager(getActivity());

        rvDokterumum.setHasFixedSize(true);
        rvDokterumum.setLayoutManager(manager);

        loadDokterUmum();

        adapter = new DokterUmumAdapter(getActivity(), dataDokter);
        rvDokterumum.setAdapter(adapter);

        rvDokterumum.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        loadDokterUmum();
                    }
                }
            }
        });

        searchDokterumum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDokterumum.setQueryHint("Cari dokter umum");
                searchDokterumum.setIconified(false);
            }
        });

        searchDokterumum.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                loadCariDokter(newText);
                return true;
            }
        });

        return rootView;
    }

    private void loadCariDokter(String newText) {

        dataDokter.clear();

        ConfigRetrofit.service.cariDokter(newText, "2", "").enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariUmumAdapter adapterCari = new CariUmumAdapter(getActivity(), dataCari);
                    rvDokterumum.setAdapter(adapterCari);

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

    private void loadDokterUmum() {

        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.dataDokterUmum("2", String.valueOf(page)).enqueue(new Callback<ResponseDataDokterUmum>() {
            @Override
            public void onResponse(Call<ResponseDataDokterUmum> call, Response<ResponseDataDokterUmum> response) {
                if (response.isSuccessful()){

                    progressBar.setVisibility(View.GONE);

                    total_page = response.body().getTotalPage();
                    dataDokter = response.body().getData();
                    if (dataDokter!=null) {

                        adapter.addList(dataDokter);
                    }
                    isLoading = false;

                }else{
                    Toast.makeText(getActivity(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseDataDokterUmum> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                isLoading = false;
                Toast.makeText(getActivity(), "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        dataDokter.clear();
    }
}