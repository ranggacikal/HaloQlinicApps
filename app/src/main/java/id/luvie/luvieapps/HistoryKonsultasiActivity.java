package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.ListKonsultasiAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityHistoryKonsultasiBinding;
import id.luvie.luvieapps.fragmentHistoryKonsultasi.KonsultasiAccFragment;
import id.luvie.luvieapps.fragmentHistoryKonsultasi.KonsultasiPendingFragment;
import id.luvie.luvieapps.fragmentHistoryKonsultasi.KonsultasiSelesaiFragment;
import id.luvie.luvieapps.model.listKonsultasi.DataItem;
import id.luvie.luvieapps.model.listKonsultasi.ResponseListKonsultasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryKonsultasiActivity extends AppCompatActivity {

    private ActivityHistoryKonsultasiBinding binding;

    private KonsultasiPendingFragment konsultasiPendingFragment;
    private KonsultasiAccFragment konsultasiAccFragment;
    private KonsultasiSelesaiFragment konsultasiSelesaiFragment;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryKonsultasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.recyclerHistoryKonsultasi.setHasFixedSize(true);
        binding.recyclerHistoryKonsultasi.setLayoutManager(new LinearLayoutManager(HistoryKonsultasiActivity.this));

        preferencedConfig = new SharedPreferencedConfig(HistoryKonsultasiActivity.this);

        binding.imgBackHistoryKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadKonsultasiSelesai();
    }

    private void loadKonsultasiSelesai() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ConfigRetrofit.service.listKonsultasi(id_customer, "2").enqueue(new Callback<ResponseListKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseListKonsultasi> call, Response<ResponseListKonsultasi> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataKonsultasi = response.body().getData();
                    ListKonsultasiAdapter adapter = new ListKonsultasiAdapter(HistoryKonsultasiActivity.this,
                            dataKonsultasi);
                    binding.recyclerHistoryKonsultasi.setAdapter(adapter);

                }else{
                    Toast.makeText(HistoryKonsultasiActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListKonsultasi> call, Throwable t) {
                Toast.makeText(HistoryKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}