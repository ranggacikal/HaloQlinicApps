package com.haloqlinic.haloqlinicapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.CariSpesialisAdapter;
import com.haloqlinic.haloqlinicapps.adapter.SpesialisAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseListDokter;

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

    RecyclerView rvSpesialisFragment;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dokter_spesialis, container, false);

        rvSpesialisFragment = rootView.findViewById(R.id.recycler_spesialis_fragment);
        searchView = rootView.findViewById(R.id.search_dokter_spesialis);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQueryHint("Cari dokter spesialis");
                searchView.setIconified(false);
            }
        });

        rvSpesialisFragment.setHasFixedSize(true);
        rvSpesialisFragment.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        loadDokterSpesialis();

        return rootView;
    }

    private void loadCariDokter(String newText) {

        ConfigRetrofit.service.cariDokter("1", newText).enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> dataCari = response.body().getData();
                    CariSpesialisAdapter adapterCari = new CariSpesialisAdapter(getActivity(), dataCari);
                    rvSpesialisFragment.setAdapter(adapterCari);

                }else{
                    Toast.makeText(getActivity(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokterSpesialis() {

        String status = "1";

        ConfigRetrofit.service.dataDokter(status).enqueue(new Callback<ResponseListDokter>() {
            @Override
            public void onResponse(Call<ResponseListDokter> call, Response<ResponseListDokter> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataDokter = response.body().getData();
                    SpesialisAdapter adapter = new SpesialisAdapter(getActivity(), dataDokter);
                    rvSpesialisFragment.setAdapter(adapter);

                }else{
                    Toast.makeText(getActivity(), "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListDokter> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}