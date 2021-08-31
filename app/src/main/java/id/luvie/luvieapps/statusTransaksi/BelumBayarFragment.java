package id.luvie.luvieapps.statusTransaksi;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.BelumBayarAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.statusTransaksiModel.DataItem;
import id.luvie.luvieapps.model.statusTransaksiModel.ResponseStatusTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BelumBayarFragment extends Fragment {

    public BelumBayarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView rvBelumBayar;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_belum_bayar, container, false);

        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvBelumBayar = view.findViewById(R.id.recycler_status_belum_bayar);

        rvBelumBayar.setHasFixedSize(true);
        rvBelumBayar.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadDataBelumBayar();

        return view;
    }

    private void loadDataBelumBayar() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String status = "1";

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memuat data status transaksi");
        progressDialog.show();

        ConfigRetrofit.service.historyTransaksi(id_customer, status).enqueue(new Callback<ResponseStatusTransaksi>() {
            @Override
            public void onResponse(Call<ResponseStatusTransaksi> call, Response<ResponseStatusTransaksi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataBelumBayar = response.body().getData();
                    BelumBayarAdapter adapter = new BelumBayarAdapter(getActivity(), dataBelumBayar);
                    rvBelumBayar.setAdapter(adapter);

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