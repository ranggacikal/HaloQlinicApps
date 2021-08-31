package id.luvie.luvieapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.adapter.ArtikelAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;

import id.luvie.luvieapps.databinding.ActivityArtikelBinding;
import id.luvie.luvieapps.model.artikel.DataItem;
import id.luvie.luvieapps.model.artikel.ResponseArtikel;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class ArtikelActivity extends AppCompatActivity {

    private ActivityArtikelBinding binding;


    private int page = 1;
    private int page_size = 10;
    private int total_page = 1;
    private List<DataItem> list = new ArrayList<>();
    LinearLayoutManager manager;
    ArtikelAdapter adapter;

    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtikelBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        PushDownAnim.setPushDownAnimTo(binding.imgBackArtikel)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        list.clear();

        manager = new LinearLayoutManager(ArtikelActivity.this);
        binding.rvArtikel.setHasFixedSize(true);
        binding.rvArtikel.setLayoutManager(manager);

        getArtikel();

        adapter = new ArtikelAdapter(ArtikelActivity.this, list);
        binding.rvArtikel.setAdapter(adapter);

        binding.rvArtikel.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                int visibleItem = manager.getChildCount();
                int totalItem = adapter.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if (!isLoading && page < total_page) {

                    if (visibleItem + firstVisibleItemPosition >= totalItem) {

                        page++;
                        Log.d("checkScrolled", "page: " + page);
                        getArtikel();
                    }
                }
            }
        });

    }

    private void getArtikel() {

        binding.progressBarArtikel.setVisibility(View.VISIBLE);
        isLoading = true;

        ConfigRetrofit.service.getArtikel("2", String.valueOf(page)).enqueue(new Callback<ResponseArtikel>() {
            @Override
            public void onResponse(Call<ResponseArtikel> call, Response<ResponseArtikel> response) {

                if (response.isSuccessful()){

                    binding.progressBarArtikel.setVisibility(View.GONE);

                    total_page = response.body().getTotalPage();
                    list = response.body().getData();
                    if (list!=null) {

                        adapter.addList(list);
                    }
                    isLoading = false;

                }else{
                    Toast.makeText(ArtikelActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    binding.progressBarArtikel.setVisibility(View.GONE);
                    isLoading = false;
                }

            }

            @Override
            public void onFailure(Call<ResponseArtikel> call, Throwable t) {
                binding.progressBarArtikel.setVisibility(View.GONE);
                isLoading = false;
                Toast.makeText(ArtikelActivity.this, "Terjadi Kesalahan Di server : "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}