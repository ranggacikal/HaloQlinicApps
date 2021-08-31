package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.adapter.ResepTindakanAdapter;
import id.luvie.luvieapps.adapter.TebusObatAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityListTebusObatBinding;
import id.luvie.luvieapps.model.listPesanan.DataItem;
import id.luvie.luvieapps.model.listPesanan.ProdukItem;
import id.luvie.luvieapps.model.listPesanan.ResponseListPesanan;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import id.luvie.luvieapps.model.resepTindakan.ResponseResepTindakan;
import id.luvie.luvieapps.model.resepTindakan.TindakanItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class ListTebusObatActivity extends AppCompatActivity {

    private ActivityListTebusObatBinding binding;

    String id_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListTebusObatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        PushDownAnim.setPushDownAnimTo(binding.imgBackListTebusObat)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        id_transaksi = getIntent().getStringExtra("id_transaksi");

        binding.recyclerTebusObat.setHasFixedSize(true);
        binding.recyclerTebusObat.setLayoutManager(new LinearLayoutManager(ListTebusObatActivity.this));

        binding.recyclerTebusTindakan.setHasFixedSize(true);
        binding.recyclerTebusTindakan.setLayoutManager(new LinearLayoutManager(ListTebusObatActivity.this));

        loadTebusObat();
        loadTebusTindakan();

        PushDownAnim.setPushDownAnimTo(binding.btnTebusObat)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(ListTebusObatActivity.this,
                                CheckoutProdukActivity.class);
                        intent.putExtra("id_transaksi", id_transaksi);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void loadTebusTindakan() {

        android.app.ProgressDialog progressDialogTindakan = new
                ProgressDialog(ListTebusObatActivity.this);
        progressDialogTindakan.setMessage("Memuat Data");
        progressDialogTindakan.show();

        ConfigRetrofit.service.resepTindakan(id_transaksi).enqueue(new Callback<ResponseResepTindakan>() {
            @Override
            public void onResponse(Call<ResponseResepTindakan> call, Response<ResponseResepTindakan> response) {
                if (response.isSuccessful()) {

                    progressDialogTindakan.dismiss();
                    List<id.luvie.luvieapps.model.resepTindakan.DataItem> dataTebusTindakan = response.body().getData();
                    List<TindakanItem> dataTindakan = null;
                    ArrayList<String> dataStringTindakan = new ArrayList<>();
                    List<TindakanItem> testArray = new ArrayList<TindakanItem>();

                    Log.d("cekDataList", "dataTebusObatSize: " + dataTebusTindakan.size());

                    for (int a = 0; a < dataTebusTindakan.size(); a++) {
                        dataTindakan = dataTebusTindakan.get(a).getTindakan();
                        for (int b = 0; b < dataTindakan.size(); b++) {

                            String test = dataTindakan.get(b).toString();
                            Log.d("testDataProduk", "onResponse: " + test);
                            testArray.add(dataTindakan.get(b));
                            Log.d("testArrayData", "onResponse: " + testArray);

                        }
                    }


                    Log.d("cekDataList", "dataProduk: " + dataStringTindakan);
                    Log.d("cekDataList", "dataProdukSize: " + dataStringTindakan.size());
                    ResepTindakanAdapter adapter = new ResepTindakanAdapter(ListTebusObatActivity.this, testArray);
                    binding.recyclerTebusTindakan.setAdapter(adapter);

                }else{
                    progressDialogTindakan.dismiss();
                    Toast.makeText(ListTebusObatActivity.this, "Data Tindakan Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseResepTindakan> call, Throwable t) {
                progressDialogTindakan.dismiss();
                Toast.makeText(ListTebusObatActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadTebusObat() {

        android.app.ProgressDialog progressDialog = new ProgressDialog(ListTebusObatActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();


        ConfigRetrofit.service.listPesanan(id_transaksi).enqueue(new Callback<ResponseListPesanan>() {
            @Override
            public void onResponse(Call<ResponseListPesanan> call, Response<ResponseListPesanan> response) {
                if (response.isSuccessful()) {

                    progressDialog.dismiss();
                    List<DataItem> dataTebusObat = response.body().getData();
                    List<ProdukItem> dataProduk = null;
                    ArrayList<String> dataStringProduk = new ArrayList<>();
                    List<ProdukItem> testArray = new ArrayList<ProdukItem>();

                    Log.d("cekDataList", "dataTebusObatSize: " + dataTebusObat.size());

                    for (int a = 0; a < dataTebusObat.size(); a++) {
                        dataProduk = dataTebusObat.get(a).getProduk();
                        for (int b = 0; b<dataProduk.size(); b++){

                            String test = dataProduk.get(b).toString();
                            Log.d("testDataProduk", "onResponse: "+test);
                            testArray.add(dataProduk.get(b));
                            Log.d("testArrayData", "onResponse: "+testArray);

                        }
                    }


                    Log.d("cekDataList", "dataProduk: " + dataStringProduk);
                    Log.d("cekDataList", "dataProdukSize: " + dataStringProduk.size());
                    TebusObatAdapter adapter = new TebusObatAdapter(ListTebusObatActivity.this, testArray);
                    binding.recyclerTebusObat.setAdapter(adapter);

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ListTebusObatActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListPesanan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ListTebusObatActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}