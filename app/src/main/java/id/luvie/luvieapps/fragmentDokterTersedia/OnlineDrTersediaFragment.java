package id.luvie.luvieapps.fragmentDokterTersedia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import id.luvie.luvieapps.adapter.CariDokterTersediaAdapter;
import id.luvie.luvieapps.adapter.OnlineDrTersediaAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.cariDokter.ResponseCariDokter;
import id.luvie.luvieapps.model.dokterOnTersedia.DataItem;
import id.luvie.luvieapps.model.dokterOnTersedia.ResponseDokterOnTersedia;
import id.luvie.luvieapps.model.listDokterTersedia.ResponseDokterTersedia;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineDrTersediaFragment extends Fragment {


    public OnlineDrTersediaFragment() {
        // Required empty public constructor
    }

    RecyclerView rvDokterTersedia;
    ProgressBar progressBar;
    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<DataItem> list = new ArrayList<>();
    LinearLayoutManager manager;
    OnlineDrTersediaAdapter adapter;
    SearchView searchDokter;

    private boolean isLastPage;
    private boolean isLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_online_dr_tersedia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvDokterTersedia = view.findViewById(R.id.rv_dokter_spesialis_online);
        progressBar = view.findViewById(R.id.progressBarOnlineTersedia);
        searchDokter = view.findViewById(R.id.cari_dokter_tersedia_online);

//        list.clear();
//        page = 1;

        manager = new LinearLayoutManager(getActivity());
        rvDokterTersedia.setHasFixedSize(true);
        rvDokterTersedia.setLayoutManager(manager);

        getDokterOnline();

        adapter = new OnlineDrTersediaAdapter(getActivity(), list);
        rvDokterTersedia.setAdapter(adapter);

        rvDokterTersedia.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        getDokterOnline();
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
    }

    private void loadSearchDokter(String textCari) {

        ConfigRetrofit.service.cariDokter(textCari, "1", "").enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariDokterTersediaAdapter adapterCari = new CariDokterTersediaAdapter(getActivity(), dataCari);
                    rvDokterTersedia.setAdapter(adapterCari);

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

    private void getDokterOnline() {

        progressBar.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.dokterOnTersedia("list", "1", String.valueOf(page))
                .enqueue(new Callback<ResponseDokterOnTersedia>() {
                    @Override
                    public void onResponse(Call<ResponseDokterOnTersedia> call, Response<ResponseDokterOnTersedia> response) {
                        if (response.isSuccessful()){
                            progressBar.setVisibility(View.GONE);

                            total_page = response.body().getTotalPage();
                            list = response.body().getData();
                            Log.d("checkListDokterOnline", "onResponse: "+list.toString());
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
                    public void onFailure(Call<ResponseDokterOnTersedia> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        isLoading = false;
                        Toast.makeText(getActivity(), "Terjadi Kesalahan Di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}