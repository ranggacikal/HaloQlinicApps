package id.luvie.luvieapps.fragmentHistoryKonsultasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.luvie.luvieapps.HistoryKonsultasiActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.ListKonsultasiAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.listKonsultasi.DataItem;
import id.luvie.luvieapps.model.listKonsultasi.ResponseListKonsultasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonsultasiPendingFragment extends Fragment {

    public KonsultasiPendingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvKonsultasiPending;
    private SharedPreferencedConfig preferencedConfig;
    HistoryKonsultasiActivity historyKonsultasiActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_konsultasi_pending, container, false);

        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvKonsultasiPending = rootView.findViewById(R.id.recycler_konsultasi_pending);
        rvKonsultasiPending.setHasFixedSize(true);
        rvKonsultasiPending.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadDataKonsultasi();

        return rootView;
    }

    private void loadDataKonsultasi() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ConfigRetrofit.service.listKonsultasi(id_customer, "0").enqueue(new Callback<ResponseListKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseListKonsultasi> call, Response<ResponseListKonsultasi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataKonsultasi = response.body().getData();
                    ListKonsultasiAdapter adapter = new ListKonsultasiAdapter(getActivity(),
                            dataKonsultasi);
                    rvKonsultasiPending.setAdapter(adapter);

                }else{
                    Toast.makeText(getActivity(), "Data kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListKonsultasi> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}