package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.adapter.KategoriAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;

import id.luvie.luvieapps.databinding.ActivityKategoriBinding;
import id.luvie.luvieapps.model.kategoriProduk.DataItem;
import id.luvie.luvieapps.model.kategoriProduk.ResponseKategoriProduk;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriActivity extends AppCompatActivity {

    private ActivityKategoriBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKategoriBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.imgBackKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        GridLayoutManager manager = new GridLayoutManager(KategoriActivity.this, 2, GridLayoutManager.VERTICAL, false);
        binding.rvKategori.setHasFixedSize(true);
        binding.rvKategori.setLayoutManager(manager);

        loadKategori();

    }

    private void loadKategori() {

        android.app.ProgressDialog progressDialog = new ProgressDialog(KategoriActivity.this);
        progressDialog.setMessage("Memuat Kategori");
        progressDialog.show();

        ConfigRetrofit.service.kategoriProduk().enqueue(new Callback<ResponseKategoriProduk>() {
            @Override
            public void onResponse(Call<ResponseKategoriProduk> call, Response<ResponseKategoriProduk> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataKategori = response.body().getData();
                    KategoriAdapter adapter = new KategoriAdapter(KategoriActivity.this, dataKategori);
                    binding.rvKategori.setAdapter(adapter);

                }else{
                    Toast.makeText(KategoriActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseKategoriProduk> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KategoriActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}