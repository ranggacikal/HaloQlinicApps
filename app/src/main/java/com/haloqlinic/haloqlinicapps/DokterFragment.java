package com.haloqlinic.haloqlinicapps;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.DokterAdapter;
import com.haloqlinic.haloqlinicapps.adapter.SearchDokterAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseDataDokter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterFragment extends Fragment {

    public DokterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    SearchView searchDokter;
    RecyclerView rvDokter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dokter, container, false);

        searchDokter = rootView.findViewById(R.id.search_dokter);
        rvDokter = rootView.findViewById(R.id.recycler_dokter);

        searchDokter.setQueryHint("Cari Dokter");
        searchDokter.setIconified(false);

        rvDokter.setHasFixedSize(true);
        rvDokter.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        loadDataDokter();

        return rootView;
    }

    private void loadSearchDokter(String query) {

        ConfigRetrofit.service.cariDokter(query).enqueue(new Callback<ResponseCariDokter>() {
            @Override
            public void onResponse(Call<ResponseCariDokter> call, Response<ResponseCariDokter> response) {
                if (response.isSuccessful()){
                    List<com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem> cariDokterItems = response.body().getData();
                    SearchDokterAdapter cariAdapter = new SearchDokterAdapter(getActivity(), cariDokterItems);
                    rvDokter.setAdapter(cariAdapter);
                }else{
                    Toast.makeText(getActivity(), "Gagal Ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCariDokter> call, Throwable t) {
                Toast.makeText(getActivity(), "Terjadi kesalahan di server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDataDokter() {

        ConfigRetrofit.service.dataDokter().enqueue(new Callback<ResponseDataDokter>() {
            @Override
            public void onResponse(Call<ResponseDataDokter> call, Response<ResponseDataDokter> response) {
                if (response.isSuccessful()){

                    List<DataItem> dokterItems = response.body().getData();
                    DokterAdapter dokterAdapter = new DokterAdapter(getActivity(), dokterItems);
                    rvDokter.setAdapter(dokterAdapter);

                }else{
                    Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataDokter> call, Throwable t) {
                Toast.makeText(getActivity(), "Terjadi kesalahan diserver", Toast.LENGTH_SHORT).show();
            }
        });

    }
}