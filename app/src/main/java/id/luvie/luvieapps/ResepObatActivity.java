package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.ResepObatAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityResepObatBinding;
import id.luvie.luvieapps.model.listRecipe.DataItem;
import id.luvie.luvieapps.model.listRecipe.ResponseListRecipe;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class ResepObatActivity extends AppCompatActivity {

    private ActivityResepObatBinding binding;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResepObatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(this);

        binding.recyclerResepObat.setHasFixedSize(true);
        binding.recyclerResepObat.setLayoutManager(new LinearLayoutManager(ResepObatActivity.this));

        PushDownAnim.setPushDownAnimTo(binding.imgBackResepObat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        loadResepObat();
    }

    private void loadResepObat() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ConfigRetrofit.service.listRecipe(id_customer).enqueue(new Callback<ResponseListRecipe>() {
            @Override
            public void onResponse(Call<ResponseListRecipe> call, Response<ResponseListRecipe> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataResep = response.body().getData();
                    Log.d("checkDataResep", "onResponse: "+dataResep);
                    Log.d("checkDataResep", "id_customer: "+id_customer);
                    ResepObatAdapter adapter = new ResepObatAdapter(ResepObatActivity.this, dataResep);
                    binding.recyclerResepObat.setAdapter(adapter);

                }else{
                    Toast.makeText(ResepObatActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListRecipe> call, Throwable t) {
                Toast.makeText(ResepObatActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadResepObat();
    }
}