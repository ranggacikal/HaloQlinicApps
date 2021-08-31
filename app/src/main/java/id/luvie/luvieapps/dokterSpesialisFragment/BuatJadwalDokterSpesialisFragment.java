package id.luvie.luvieapps.dokterSpesialisFragment;

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
import id.luvie.luvieapps.adapter.BuatJadwalSpesialisAdapter;
import id.luvie.luvieapps.adapter.CariJadwalSpesialisAdapter;
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

public class BuatJadwalDokterSpesialisFragment extends Fragment {

    public BuatJadwalDokterSpesialisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvDokterSpesialisJadwal;
    ProgressBar progressBar;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    List<DataItem> list = new ArrayList<>();
    LinearLayoutManager manager;
    BuatJadwalSpesialisAdapter adapter;

    private boolean isLastPage;
    private boolean isLoading = false;

    SearchView searchDokter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_buat_jadwal_dokter_spesialis, container, false);

        rvDokterSpesialisJadwal = rootview.findViewById(R.id.rv_dokter_spesialis_buat_jadwal);
        progressBar = rootview.findViewById(R.id.progressbar_spesialis_buat_jadwal);
        searchDokter = rootview.findViewById(R.id.cari_dokter_spesialis_buat_jadwal);

        list.clear();

        manager = new LinearLayoutManager(getActivity());
        rvDokterSpesialisJadwal.setHasFixedSize(true);
        rvDokterSpesialisJadwal.setLayoutManager(manager);

        getDokterSpesialisJadwal();

        adapter = new BuatJadwalSpesialisAdapter(getActivity(), list);
        rvDokterSpesialisJadwal.setAdapter(adapter);

        rvDokterSpesialisJadwal.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                int visibleItem = manager.getChildCount();
                int totalItem = adapter.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && page < total_page) {

                    if (visibleItem + firstVisibleItemPosition >= totalItem) {

                        page++;
                        Log.d("checkScrolled", "page: " + page);
                        getDokterSpesialisJadwal();
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
            public boolean onQueryTextChange(String newText) {

                loadSearchDokter(newText);
                return true;
            }
        });

        return rootview;
    }

    private void loadSearchDokter(String newText) {

        ConfigRetrofit.service.cariDokter(newText, "2", "spesialis").enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.cariDokter.DataItem> datCari = response.body().getData();
                    CariJadwalSpesialisAdapter adapterCari = new CariJadwalSpesialisAdapter(getActivity(), datCari);
                    rvDokterSpesialisJadwal.setAdapter(adapterCari);

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

    private void getDokterSpesialisJadwal() {

        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.dokterSpesialis("2", String.valueOf(page)).enqueue(new Callback<ResponseDokterSpesialis>() {
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
                }else{
                    Toast.makeText(getActivity(), "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    isLoading = false;
                }
            }

            @Override
            public void onFailure(Call<ResponseDokterSpesialis> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                isLoading = false;
                Toast.makeText(getActivity(), "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}