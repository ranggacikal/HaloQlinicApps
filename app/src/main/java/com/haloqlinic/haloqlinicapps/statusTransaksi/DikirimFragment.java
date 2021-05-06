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
import com.haloqlinic.haloqlinicapps.adapter.DikirimAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.DataItem;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.ResponseStatusTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DikirimFragment extends Fragment {


    public DikirimFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView rvDikirim;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dikirim, container, false);

        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvDikirim = rootView.findViewById(R.id.recycler_status_dikirim);
        rvDikirim.setHasFixedSize(true);
        rvDikirim.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadDataDikirim();

        return  rootView;
    }

    private void loadDataDikirim() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String status = "3";

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memuat data status transaksi");
        progressDialog.show();

        ConfigRetrofit.service.historyTransaksi(id_customer, status).enqueue(new Callback<ResponseStatusTransaksi>() {
            @Override
            public void onResponse(Call<ResponseStatusTransaksi> call, Response<ResponseStatusTransaksi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataDikirim = response.body().getData();
                    DikirimAdapter adapter = new DikirimAdapter(getActivity(), dataDikirim);
                    rvDikirim.setAdapter(adapter);

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