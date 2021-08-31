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
import id.luvie.luvieapps.adapter.SelesaiAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.statusTransaksiModel.DataItem;
import id.luvie.luvieapps.model.statusTransaksiModel.ResponseStatusTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelesaiFragment extends Fragment {

    public SelesaiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView rvSelesai;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_selesai, container, false);
        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvSelesai = rootView.findViewById(R.id.recycler_status_selesai);
        rvSelesai.setHasFixedSize(true);
        rvSelesai.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadDataSelesai();

        return rootView;
    }

    private void loadDataSelesai() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String status = "4";

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memuat data status transaksi");
        progressDialog.show();

        ConfigRetrofit.service.historyTransaksi(id_customer, status).enqueue(new Callback<ResponseStatusTransaksi>() {
            @Override
            public void onResponse(Call<ResponseStatusTransaksi> call, Response<ResponseStatusTransaksi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataSelesai = response.body().getData();
                    SelesaiAdapter adapter = new SelesaiAdapter(getActivity(), dataSelesai);
                    rvSelesai.setAdapter(adapter);
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Gagal Memuat data", Toast.LENGTH_SHORT).show();
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