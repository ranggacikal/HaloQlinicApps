package com.haloqlinic.haloqlinicapps;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.HistoryTransaksiAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.historyTransaksi.DataItem;
import com.haloqlinic.haloqlinicapps.model.historyTransaksi.ResponseHistoryTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryTransaksiFragment extends Fragment {


    public HistoryTransaksiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvHistoryTransaksi;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_history_transaksi, container, false);

        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvHistoryTransaksi = rootView.findViewById(R.id.recycler_history_transaksi);

        rvHistoryTransaksi.setHasFixedSize(true);
        rvHistoryTransaksi.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadRecyclerTransaksi();

        return rootView;
    }

    private void loadRecyclerTransaksi() {

//        String id_customer = preferencedConfig.getPreferenceIdCustomer();
//
//        ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Memuat history transaksi");
//        progressDialog.show();
//
//        ConfigRetrofit.service.historyTransaksi(id_customer).enqueue(new Callback<ResponseHistoryTransaksi>() {
//            @Override
//            public void onResponse(Call<ResponseHistoryTransaksi> call, Response<ResponseHistoryTransaksi> response) {
//                if (response.isSuccessful()){
//
//                    progressDialog.dismiss();
//
//                    List<DataItem> dataHistory = response.body().getData();
//
//                    HistoryTransaksiAdapter adapter = new HistoryTransaksiAdapter(getActivity(), dataHistory);
//
//                    rvHistoryTransaksi.setAdapter(adapter);
//
//                }else{
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Load data gagal", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseHistoryTransaksi> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}