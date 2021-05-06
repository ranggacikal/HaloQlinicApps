package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.LayananKurirAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.api.apiRajaOngkir.ConfigRajaOngkir;
import com.haloqlinic.haloqlinicapps.model.dataEkspedisi.DataItem;
import com.haloqlinic.haloqlinicapps.model.dataEkspedisi.ResponseDataEkspedisi;
import com.haloqlinic.haloqlinicapps.model.layananEkspedisi.CostItem;
import com.haloqlinic.haloqlinicapps.model.layananEkspedisi.CostsItem;
import com.haloqlinic.haloqlinicapps.model.layananEkspedisi.ResponseLayananEkspedisi;
import com.haloqlinic.haloqlinicapps.model.layananEkspedisi.ResultsItem;
import com.haloqlinic.haloqlinicapps.model.updateKurir.ResponseUpdateKurir;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihKurirActivity extends AppCompatActivity {

    Spinner spinnerKurir, spinnerLayanan;
    List<DataItem> dataEkspedisi;
    List<ResultsItem> dataLayanan;
    List<CostsItem> dataCosts;
    List<CostItem> dataharga;
    String key = "9bc530edfde2ffdba603d779832349cf";
    Button btnPilihKurir;
    String id_kurir, nama_layanan, harga_layanan, id_transaksi, id_member;
    RelativeLayout relativePilihLayanan;
    String kurir, ekspedisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kurir);
        spinnerKurir = findViewById(R.id.spinner_ekspedisi_kurir);
        spinnerLayanan = findViewById(R.id.spinner_layanan_kurir);
        btnPilihKurir = findViewById(R.id.btn_pilih_kurir);
        relativePilihLayanan = findViewById(R.id.relative_pilih_layanan);

        id_transaksi = getIntent().getStringExtra("id_transaksi");
        id_member = getIntent().getStringExtra("id_member");

        initSpinnerKurir();

        btnPilihKurir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKurir();
            }
        });


        spinnerKurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kurir = dataEkspedisi.get(position).getValue();
                ekspedisi = dataEkspedisi.get(position).getEkspedisi();
                id_kurir = dataEkspedisi.get(position).getId();
                initSpinnerLayanan(kurir);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                nama_layanan = dataCosts.get(position).getService();

                List<String> dataHarga2 = new ArrayList<>();
                List<CostItem> dataHargaTest = null;

                for (int a = 0; a<dataCosts.size(); a++){

                    dataharga = dataCosts.get(a).getCost();
                    dataHargaTest = dataCosts.get(a).getCost();
                    dataHarga2.add(String.valueOf(dataCosts.get(a).getCost()));

                }

                Log.d("checkCost", "onItemSelected: "+dataHargaTest);

                for (int i = 0; i<dataHargaTest.size(); i++){
                    harga_layanan = String.valueOf(dataHargaTest.get(i).getValue());
                    Toast.makeText(PilihKurirActivity.this, "index: "+i, Toast.LENGTH_SHORT).show();

                }

                //Toast.makeText(PilihKurirActivity.this, "harga: "+harga_layanan+" nama: "+nama, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        relativePilihLayanan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tampilDialog();
//            }
//        });


    }

    private void tampilDialog() {

        Dialog alertDialog = new Dialog(this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.dialog_layanan_kurir);

        RecyclerView rvLayananKurir = alertDialog.findViewById(R.id.recycler_layanan_kurir);
        rvLayananKurir.setHasFixedSize(true);
        rvLayananKurir.setLayoutManager(new LinearLayoutManager(PilihKurirActivity.this));
        loadLayanan(rvLayananKurir);

        alertDialog.show();



    }

    private void loadLayanan(RecyclerView rvKurir) {

        String id_kota = getIntent().getStringExtra("id_kota");
        String berat = getIntent().getStringExtra("berat");
        String id_kecamatan = getIntent().getStringExtra("id_kecamatan");

        ConfigRajaOngkir.serviceRajaOngkir.layananEkspedisi(key, id_kota, "city", id_kecamatan, "subdistrict",
                berat, kurir).enqueue(new Callback<ResponseLayananEkspedisi>() {
            @Override
            public void onResponse(Call<ResponseLayananEkspedisi> call, Response<ResponseLayananEkspedisi> response) {
                if (response.isSuccessful()){

                    int status = response.body().getRajaongkir().getStatus().getCode();
                    dataLayanan = response.body().getRajaongkir().getResults();

                    if (status == 200){

                        LayananKurirAdapter adapter = new LayananKurirAdapter(PilihKurirActivity.this, dataLayanan);
                        rvKurir.setAdapter(adapter);

                    }else{
                        Toast.makeText(PilihKurirActivity.this, "Gagal load data", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(PilihKurirActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLayananEkspedisi> call, Throwable t) {
                Toast.makeText(PilihKurirActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateKurir() {

        ProgressDialog progressDialog = new ProgressDialog(PilihKurirActivity.this);
        progressDialog.setMessage("Menyimpan pilihan kurir");
        progressDialog.show();

        ConfigRetrofit.service.updateKurir(id_transaksi, id_member, id_kurir, harga_layanan, nama_layanan, ekspedisi).enqueue(new Callback<ResponseUpdateKurir>() {
            @Override
            public void onResponse(Call<ResponseUpdateKurir> call, Response<ResponseUpdateKurir> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    Toast.makeText(PilihKurirActivity.this, "Berhasil memilih kurir", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(PilihKurirActivity.this, "Gagal memilih kurir", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateKurir> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PilihKurirActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerLayanan(String kurir) {

        String id_kota = getIntent().getStringExtra("id_kota");
        String berat = getIntent().getStringExtra("berat");
        String id_kecamatan = getIntent().getStringExtra("id_kecamatan");

        ConfigRajaOngkir.serviceRajaOngkir.layananEkspedisi(key, id_kota, "city", id_kecamatan, "subdistrict",
                berat, kurir).enqueue(new Callback<ResponseLayananEkspedisi>() {
            @Override
            public void onResponse(Call<ResponseLayananEkspedisi> call, Response<ResponseLayananEkspedisi> response) {
                if (response.isSuccessful()){
                    int status = response.body().getRajaongkir().getStatus().getCode();
                    dataLayanan = response.body().getRajaongkir().getResults();
                    List<String> listSpinnerService = new ArrayList<String>();
                    List<String> listSpinnerLayanan = new ArrayList<String>();

                    if (status == 200){

                        for (int i = 0; i < dataLayanan.size(); i++) {

                            dataCosts = dataLayanan.get(i).getCosts();

                            for (int a = 0; a<dataCosts.size(); a++){

                                String namaLayanan = dataCosts.get(a).getService();
                                String descLayanan = dataCosts.get(a).getDescription();
                                int harga = 0;

                                dataharga = dataCosts.get(a).getCost();

                                for (int b = 0; b<dataharga.size(); b++){

                                    harga = dataharga.get(b).getValue();

                                }

                                listSpinnerLayanan.add(namaLayanan+" ( "+descLayanan+" )"+"\n"+
                                        "Rp" + NumberFormat.getInstance().format(harga));
                            }

                        }

                        ArrayAdapter<String> adapterLayanan = new ArrayAdapter<String>(PilihKurirActivity.this,
                                R.layout.spinner_item, listSpinnerLayanan);

                        adapterLayanan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerLayanan.setAdapter(adapterLayanan);

                    }else{
                        Toast.makeText(PilihKurirActivity.this, "Gagal load data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PilihKurirActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLayananEkspedisi> call, Throwable t) {
                Toast.makeText(PilihKurirActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerKurir() {

        ConfigRetrofit.service.dataEkspedisi().enqueue(new Callback<ResponseDataEkspedisi>() {
            @Override
            public void onResponse(Call<ResponseDataEkspedisi> call, Response<ResponseDataEkspedisi> response) {
                if (response.isSuccessful()) {

                    dataEkspedisi = response.body().getData();
                    List<String> listSpinnerEkspedisi = new ArrayList<String>();
                    for (int i = 0; i < dataEkspedisi.size(); i++) {

                        listSpinnerEkspedisi.add(dataEkspedisi.get(i).getEkspedisi());

                    }

                    ArrayAdapter<String> adapterEkspedisi = new ArrayAdapter<String>(PilihKurirActivity.this,
                            R.layout.spinner_item, listSpinnerEkspedisi);

                    adapterEkspedisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerKurir.setAdapter(adapterEkspedisi);
                } else {
                    Toast.makeText(PilihKurirActivity.this, "Gagal memuat ekspedisi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataEkspedisi> call, Throwable t) {
                Toast.makeText(PilihKurirActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}