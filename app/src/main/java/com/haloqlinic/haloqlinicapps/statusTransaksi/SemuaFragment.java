package com.haloqlinic.haloqlinicapps.statusTransaksi;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.StatusSemuaAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.DataItem;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.ResponseStatusTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SemuaFragment extends Fragment {

  public SemuaFragment() {
        // Required empty public constructor
    }
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView rvStatusSemua;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_semua, container, false);

        rvStatusSemua = rootView.findViewById(R.id.recycler_status_semua);
        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvStatusSemua.setHasFixedSize(true);
        rvStatusSemua.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadStatusSemua();

        return rootView;
    }

    private void loadStatusSemua() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String status = "5";

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memuat data status transaksi");
        progressDialog.show();

        ConfigRetrofit.service.historyTransaksi(id_customer, status).enqueue(new Callback<ResponseStatusTransaksi>() {
            @Override
            public void onResponse(Call<ResponseStatusTransaksi> call, Response<ResponseStatusTransaksi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataSemua = response.body().getData();
                    StatusSemuaAdapter adapter = new StatusSemuaAdapter(getActivity(), dataSemua);
                    rvStatusSemua.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusTransaksi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}