package id.luvie.luvieapps.dokterSpesialisFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

import id.luvie.luvieapps.R;
import id.luvie.luvieapps.adapter.CariDokterSpesialisAdapter;
import id.luvie.luvieapps.adapter.OnDokterSpesialisAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.cariDokter.ResponseCariDokter;
import id.luvie.luvieapps.model.dokterSpesialis.DataItem;
import id.luvie.luvieapps.model.dokterSpesialis.ResponseDokterSpesialis;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineDokterSpesialisFragment extends Fragment {

    public OnlineDokterSpesialisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvDokterSpesialis;
    ProgressBar progressBar;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    List<DataItem> list = new ArrayList<>();
    LinearLayoutManager manager;
    OnDokterSpesialisAdapter adapter;
    SearchView searchDokter;

    private boolean isLastPage;
    private boolean isLoading = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online_dokter_spesialis, container, false);

        rvDokterSpesialis = view.findViewById(R.id.rv_dokter_spesialis_online);
        progressBar = view.findViewById(R.id.progresBar_on_dokter_spesialis);
        searchDokter = view.findViewById(R.id.cari_dokter_spesialis_online);

        list.clear();

        manager = new LinearLayoutManager(getActivity());
        rvDokterSpesialis.setHasFixedSize(true);
        rvDokterSpesialis.setLayoutManager(manager);

        getDokterSpesialis();

        adapter = new OnDokterSpesialisAdapter(getActivity(), list);
        rvDokterSpesialis.setAdapter(adapter);

        rvDokterSpesialis.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        getDokterSpesialis();
                    }
                }
            }
        });

        searchDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDokter.setQueryHint("cari Dokter");
                searchDokter.setIconified(false);
            }
        });

        searchDokter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                loadSearchDokter(newText);
                return true;
            }
        });

        return view;
    }

    private void loadSearchDokter(String newText) {

        ConfigRetrofit.service.cariDokter(newText, "1", "spesialis").enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariDokterSpesialisAdapter adapterCari = new CariDokterSpesialisAdapter(getActivity(), dataCari);
                    rvDokterSpesialis.setAdapter(adapterCari);

                }else{
                    Toast.makeText(getActivity(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDokterSpesialis() {

        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.dokterSpesialis("1", String.valueOf(page)).enqueue(new Callback<ResponseDokterSpesialis>() {
            @Override
            public void onResponse(Call<ResponseDokterSpesialis> call, Response<ResponseDokterSpesialis> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    total_page = response.body().getTotalPage();
                    list = response.body().getData();
                    if (list!=null) {

                        adapter.addList(list);
                    }
                    isLoading = false;
                    Log.d("dokterOnlineSpesialis", "onResponse: Success");
                }else{
                    Toast.makeText(getActivity(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                    Log.d("dokterOnlineSpesialis", "onResponse: Failed Response");
                }
            }

            @Override
            public void onFailure(Call<ResponseDokterSpesialis> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                isLoading = false;
                Log.d("dokterOnlineSpesialis", "failure: "+t.getMessage());
                Toast.makeText(getActivity(), "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}