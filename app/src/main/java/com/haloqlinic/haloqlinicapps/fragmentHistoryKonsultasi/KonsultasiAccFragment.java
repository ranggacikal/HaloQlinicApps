package com.haloqlinic.haloqlinicapps.fragmentHistoryKonsultasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.HistoryKonsultasiActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.ListKonsultasiAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.listKonsultasi.DataItem;
import com.haloqlinic.haloqlinicapps.model.listKonsultasi.ResponseListKonsultasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsultasiAccFragment extends Fragment {

    public KonsultasiAccFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvKonsultasiAcc;
    private SharedPreferencedConfig preferencedConfig;
    HistoryKonsultasiActivity historyKonsultasiActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_konsultasi_acc, container, false);
        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvKonsultasiAcc = rootView.findViewById(R.id.recycler_konsultasi_acc);

        rvKonsultasiAcc.setHasFixedSize(true);
        rvKonsultasiAcc.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadKonsultasiAcc();

        return rootView;
    }

    private void loadKonsultasiAcc() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ConfigRetrofit.service.listKonsultasi(id_customer, "1").enqueue(new Callback<ResponseListKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseListKonsultasi> call, Response<ResponseListKonsultasi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataKonsultasi = response.body().getData();
                    ListKonsultasiAdapter adapter = new ListKonsultasiAdapter(getActivity(),
                            dataKonsultasi);
                    rvKonsultasiAcc.setAdapter(adapter);

                }else{
                    Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListKonsultasi> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}