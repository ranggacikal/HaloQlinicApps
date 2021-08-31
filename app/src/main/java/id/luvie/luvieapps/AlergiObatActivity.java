package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.AlergiObatAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;

import id.luvie.luvieapps.databinding.ActivityAlergiObatBinding;
import id.luvie.luvieapps.model.listAlergiObat.DataItem;
import id.luvie.luvieapps.model.listAlergiObat.ResponseListAlergiObat;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class AlergiObatActivity extends AppCompatActivity {

    ActivityAlergiObatBinding binding;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlergiObatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(this);

        binding.rvAlergiObat.setHasFixedSize(true);
        binding.rvAlergiObat.setLayoutManager(new LinearLayoutManager(AlergiObatActivity.this));

        PushDownAnim.setPushDownAnimTo(binding.imgBackAlergiObat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.addAlergiObat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(AlergiObatActivity.this, TambahAlergiObatActivity.class));
                    }
                });

        loadDataAlergi();

    }

    private void loadDataAlergi() {

        ProgressDialog progressDialog = new ProgressDialog(AlergiObatActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.dismiss();

        ConfigRetrofit.service.dataAlergiObat(preferencedConfig.getPreferenceIdCustomer())
                .enqueue(new Callback<ResponseListAlergiObat>() {
                    @Override
                    public void onResponse(Call<ResponseListAlergiObat> call, Response<ResponseListAlergiObat> response) {
                        if (response.isSuccessful()){

                            progressDialog.dismiss();
                            List<DataItem> dataObat = response.body().getData();

                            if (dataObat!=null){
                                AlergiObatAdapter adapter = new AlergiObatAdapter(AlergiObatActivity.this, dataObat);
                                binding.rvAlergiObat.setAdapter(adapter);
                            }else{
                                Toast.makeText(AlergiObatActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(AlergiObatActivity.this, "Response Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseListAlergiObat> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(AlergiObatActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataAlergi();
    }
}