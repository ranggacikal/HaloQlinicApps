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

import com.haloqlinic.haloqlinicapps.adapter.CariUmumAdapter;
import com.haloqlinic.haloqlinicapps.adapter.UmumAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseListDokter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dokter_umum, container, false);

        rvDokterumum = rootView.findViewById(R.id.recycler_umum_fragment);
        searchDokterumum = rootView.findViewById(R.id.search_dokter_umum);

        rvDokterumum.setHasFixedSize(true);
        rvDokterumum.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        loadDokterUmum();

        return rootView;
    }

    private void loadCariDokter(String newText) {

        ConfigRetrofit.service.cariDokter("0", newText).enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> dataCari = response.body().getData();
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

        ConfigRetrofit.service.dataDokter("0").enqueue(new Callback<ResponseListDokter>() {
            @Override
            public void onResponse(Call<ResponseListDokter> call, Response<ResponseListDokter> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataDokter = response.body().getData();
                    UmumAdapter adapter = new UmumAdapter(getActivity(), dataDokter);
                    rvDokterumum.setAdapter(adapter);

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