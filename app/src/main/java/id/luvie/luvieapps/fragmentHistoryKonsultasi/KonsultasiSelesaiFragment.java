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

public class KonsultasiSelesaiFragment extends Fragment {

    public KonsultasiSelesaiFragment() {
        // Required empty public constructor
    }

    RecyclerView rvKonsultasiSelesai;
    private SharedPreferencedConfig preferencedConfig;
    HistoryKonsultasiActivity historyKonsultasiActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_konsultasi_selesai, container, false);
        preferencedConfig = new SharedPreferencedConfig(getActivity());

        rvKonsultasiSelesai = rootView.findViewById(R.id.recycler_konsultasi_selesai);

        rvKonsultasiSelesai.setHasFixedSize(true);
        rvKonsultasiSelesai.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadKonsultasiSelesai();

        return rootView;
    }

    private void loadKonsultasiSelesai() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ConfigRetrofit.service.listKonsultasi(id_customer, "2").enqueue(new Callback<ResponseListKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseListKonsultasi> call, Response<ResponseListKonsultasi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataKonsultasi = response.body().getData();
                    ListKonsultasiAdapter adapter = new ListKonsultasiAdapter(getActivity(),
                            dataKonsultasi);
                    rvKonsultasiSelesai.setAdapter(adapter);

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