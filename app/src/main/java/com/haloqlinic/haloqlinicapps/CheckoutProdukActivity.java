package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.api.apiRajaOngkir.ConfigRajaOngkir;
import com.haloqlinic.haloqlinicapps.model.kecamatanRajaOngkir.ResponseKecamatanRajaOngkir;
import com.haloqlinic.haloqlinicapps.model.kotaRajaOngkir.ResponseKotaRajaOngkir;
import com.haloqlinic.haloqlinicapps.model.provinsiRajaOngkir.ResponseProvinsiRajaOngkir;
import com.haloqlinic.haloqlinicapps.model.provinsiRajaOngkir.ResultsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutProdukActivity extends AppCompatActivity {

    Spinner spinnerProvinsi, spinnerKota, spinnerKecamatan;

    List<ResultsItem> dataProvinsi;
    List<com.haloqlinic.haloqlinicapps.model.kotaRajaOngkir.ResultsItem> dataKota;
    List<com.haloqlinic.haloqlinicapps.model.kecamatanRajaOngkir.ResultsItem> dataKecamatan;

    ProgressDialog progressDialog;

    String key = "9bc530edfde2ffdba603d779832349cf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_produk);

        spinnerProvinsi = findViewById(R.id.spinner_provinsi_pembayaran);
        spinnerKota = findViewById(R.id.spinner_kabupaten_pembayaran);
        spinnerKecamatan = findViewById(R.id.spinner_kecamatan_pembayaran);

        initSpinnerProvinsi();

        progressDialog = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialog.setMessage("Memuat Data Provinsi");
        progressDialog.show();

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String id_provinsi = dataProvinsi.get(position).getProvinceId();
                initSpinnerKota(id_provinsi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String id_kota = dataKota.get(position).getCityId();
                initSpinnerKecamatan(id_kota);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerKecamatan(String id_kota) {
        ProgressDialog progressDialogKecamatan = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialogKecamatan.setMessage("Memuat data kecamatan");
        progressDialogKecamatan.show();

        ConfigRajaOngkir.serviceRajaOngkir.getKecamatan(key, id_kota).enqueue(new Callback<ResponseKecamatanRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseKecamatanRajaOngkir> call, Response<ResponseKecamatanRajaOngkir> response) {
                if (response.isSuccessful()){
                    progressDialogKecamatan.dismiss();

                    int status = response.body().getRajaongkir().getStatus().getCode();

                    if (status == 200){

                        progressDialogKecamatan.dismiss();
                        dataKecamatan = response.body().getRajaongkir().getResults();
                        List<String> listSpinnerKecamatan = new ArrayList<String>();

                        for (int i = 0; i<dataKecamatan.size(); i++){
                            listSpinnerKecamatan.add(dataKecamatan.get(i).getSubdistrictName());
                        }

                        ArrayAdapter<String> adapterKecamatan = new ArrayAdapter<String>(CheckoutProdukActivity.this,
                                R.layout.spinner_item, listSpinnerKecamatan);

                        adapterKecamatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerKecamatan.setAdapter(adapterKecamatan);

                    }else{
                        progressDialogKecamatan.dismiss();
                        Toast.makeText(CheckoutProdukActivity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialogKecamatan.dismiss();
                    Toast.makeText(CheckoutProdukActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKecamatanRajaOngkir> call, Throwable t) {
                progressDialogKecamatan.dismiss();
                Toast.makeText(CheckoutProdukActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initSpinnerKota(String id_provinsi) {
        ProgressDialog progressDialogKota = new ProgressDialog(CheckoutProdukActivity.this);
        progressDialogKota.setMessage("Memuat data kota");
        progressDialogKota.show();

        ConfigRajaOngkir.serviceRajaOngkir.getKota(key, id_provinsi).enqueue(new Callback<ResponseKotaRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseKotaRajaOngkir> call, Response<ResponseKotaRajaOngkir> response) {
                if (response.isSuccessful()){
                    progressDialogKota.dismiss();

                    int status = response.body().getRajaongkir().getStatus().getCode();

                    if (status==200){

                        dataKota = response.body().getRajaongkir().getResults();
                        List<String> listSpinnerKota = new ArrayList<String>();

                        for (int i = 0; i<dataKota.size(); i++){
                            listSpinnerKota.add(dataKota.get(i).getCityName());
                        }

                        ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(CheckoutProdukActivity.this,
                                R.layout.spinner_item, listSpinnerKota);

                        adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerKota.setAdapter(adapterKota);

                    }else{
                        Toast.makeText(CheckoutProdukActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialogKota.dismiss();
                    Toast.makeText(CheckoutProdukActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKotaRajaOngkir> call, Throwable t) {
                progressDialogKota.dismiss();
                Toast.makeText(CheckoutProdukActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerProvinsi() {



        ConfigRajaOngkir.serviceRajaOngkir.getProvinsi(key).enqueue(new Callback<ResponseProvinsiRajaOngkir>() {
            @Override
            public void onResponse(Call<ResponseProvinsiRajaOngkir> call, Response<ResponseProvinsiRajaOngkir> response) {
                if (response.isSuccessful()){

                    int status = response.body().getRajaongkir().getStatus().getCode();

                    if (status==200){
                        progressDialog.dismiss();

                        dataProvinsi = response.body().getRajaongkir().getResults();
                        List<String> listSpinnerProvinsi = new ArrayList<String>();
                        for (int i = 0; i<dataProvinsi.size(); i++){
                            listSpinnerProvinsi.add(dataProvinsi.get(i).getProvince());
                        }

                        ArrayAdapter<String> adapterProvinsi = new ArrayAdapter<String>(CheckoutProdukActivity.this,
                                R.layout.spinner_item, listSpinnerProvinsi);
                        adapterProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerProvinsi.setAdapter(adapterProvinsi);

                    }else{
                        Toast.makeText(CheckoutProdukActivity.this, "Data Gagal dimuat", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(CheckoutProdukActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProvinsiRajaOngkir> call, Throwable t) {
                Toast.makeText(CheckoutProdukActivity.this, "Terjadi Kesalahan: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}