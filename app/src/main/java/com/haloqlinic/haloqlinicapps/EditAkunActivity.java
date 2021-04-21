package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseDataKecamatan;
import com.haloqlinic.haloqlinicapps.model.kota.ResponseDataKota;
import com.haloqlinic.haloqlinicapps.model.kota.ResponseItem;
import com.haloqlinic.haloqlinicapps.model.provinsi.DataItem;
import com.haloqlinic.haloqlinicapps.model.provinsi.ResponseDataProvinsi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunActivity extends AppCompatActivity {

    CircleImageView imgEditAkun;
    EditText edtNamaLengkap, edtNotelepon, edtAlamat;
    TextView txtJenisKelamin, txtTanggalLahir, txtProvinsi, txtKota, txtKecamatan;
    LinearLayout linearJenisKelamin, linearTglLahir;
    RelativeLayout relativeProvinsi, relativeKota, relativeKecamatan;
    Spinner spinnerJK, spinnerProvinsi, spinnerKota, spinnerKecamatan;
    Button btnEditAkun;

    List<DataItem> dataProvinsi;
    List<ResponseItem> dataKota;
    List<com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseItem> dataKecamatan;

    String id_provinsi = "";
    String id_kota = "";
    String id_kecamatan = "";

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private SharedPreferencedConfig preferencedConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        preferencedConfig = new SharedPreferencedConfig(this);

        imgEditAkun = findViewById(R.id.img_edit_akun);
        edtNamaLengkap = findViewById(R.id.edt_nama_edit_akun);
        edtNotelepon = findViewById(R.id.edt_no_telepon_edit_akun);
        edtAlamat = findViewById(R.id.edt_alamat_edit_akun);
        txtJenisKelamin = findViewById(R.id.text_jenis_kelamin_edit_akun);
        txtTanggalLahir = findViewById(R.id.text_tgl_lahir_edit_akun);
        txtProvinsi = findViewById(R.id.text_provinsi_edit_akun);
        txtKota = findViewById(R.id.text_kota_edit_akun);
        txtKecamatan = findViewById(R.id.text_kecamatan_edit_akun);
        linearJenisKelamin = findViewById(R.id.linear_jenis_kelamin);
        linearTglLahir = findViewById(R.id.linear_tgl_lahir_edit_akun);
        relativeProvinsi = findViewById(R.id.relative_provinsi_edit_akun);
        relativeKota = findViewById(R.id.relative_kota_edit_akun);
        relativeKecamatan = findViewById(R.id.relative_kecamatan_edit_akun);
        spinnerJK = findViewById(R.id.spinner_jk_edit_akun);
        spinnerKecamatan = findViewById(R.id.spinner_kecamatan_edit_akun);
        spinnerProvinsi = findViewById(R.id.spinner_provinsi_edit_akun);
        spinnerKota = findViewById(R.id.spinner_kota_edit_akun);
        btnEditAkun = findViewById(R.id.btn_edit_akun);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        linearTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        relativeProvinsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeProvinsi.setVisibility(View.GONE);
                spinnerProvinsi.setVisibility(View.VISIBLE);
            }
        });

        relativeKota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeKota.setVisibility(View.GONE);
                spinnerKota.setVisibility(View.VISIBLE);
            }
        });

        relativeKecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeKecamatan.setVisibility(View.GONE);
                spinnerKecamatan.setVisibility(View.VISIBLE);
            }
        });

        linearJenisKelamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearJenisKelamin.setVisibility(View.GONE);
                spinnerJK.setVisibility(View.VISIBLE);
            }
        });

        initSpinnerProvinsi();

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_provinsi = dataProvinsi.get(position).getProvinceId();
                initSpinnerKota(id_provinsi);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_kota = dataKota.get(position).getCityId();
                initSpinnerKecamatan(id_kota);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initData();
        Log.d("testPreference", "onCreate: "+preferencedConfig.getPreferenceJk());

    }

    private void initData() {

        if (preferencedConfig.getPreferenceNama().equals("null")){
            edtNamaLengkap.setText("");
        }else{
            edtNamaLengkap.setText(preferencedConfig.getPreferenceNama());
        }

        if (preferencedConfig.getPreferenceNoHp().equals("null") || preferencedConfig.getPreferenceNoHp() == null){
            edtNotelepon.setText("");
        }else{
            edtNotelepon.setText(preferencedConfig.getPreferenceNoHp());
        }

        if (preferencedConfig.getPreferenceJk().equals("")){
            txtJenisKelamin.setText("Jenis Kelamin");
        }else{
            txtJenisKelamin.setText(preferencedConfig.getPreferenceJk());
        }

        if (preferencedConfig.getPreferenceTglLahir().equals("")){
            txtTanggalLahir.setText("Tanggal Lahir");
        }else{
            txtTanggalLahir.setText(preferencedConfig.getPreferenceTglLahir());
        }

        if (preferencedConfig.getPreferenceProvinsi().equals("")){
            txtProvinsi.setText("Provinsi");
        }else{
            txtProvinsi.setText(preferencedConfig.getPreferenceProvinsi());
        }

        if (preferencedConfig.getPreferenceKota().equals("")){
            txtKota.setText("Kota");
        }else{
            txtKota.setText(preferencedConfig.getPreferenceKota());
        }

        if (preferencedConfig.getPreferenceKecamatan().equals("")){
            txtKecamatan.setText("Kecamatan");
        }else{
            txtKecamatan.setText(preferencedConfig.getPreferenceKecamatan());
        }

        if (preferencedConfig.getPreferenceAlamat().equals("")){
            edtAlamat.setText("");
        }else{
            edtAlamat.setText(preferencedConfig.getPreferenceAlamat());
        }

    }

    private void initSpinnerKecamatan(String id_kota) {

        ConfigRetrofit.service.dataKecamatan(id_kota).enqueue(new Callback<ResponseDataKecamatan>() {
            @Override
            public void onResponse(Call<ResponseDataKecamatan> call, Response<ResponseDataKecamatan> response) {
                if (response.isSuccessful()){

                    dataKecamatan = response.body().getResponse();
                    List<String> listSpinnerKecamatan = new ArrayList<String>();

                    for (int i = 0; i<dataKecamatan.size(); i++){
                        listSpinnerKecamatan.add(dataKecamatan.get(i).getSubdistrictName());
                    }

                    ArrayAdapter<String> adapterKecamatan = new ArrayAdapter<String>(EditAkunActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerKecamatan);

                    adapterKecamatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerKecamatan.setAdapter(adapterKecamatan);

                }else{
                    Toast.makeText(EditAkunActivity.this, "Gagal Memuat data Kecamatan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKecamatan> call, Throwable t) {
                Toast.makeText(EditAkunActivity.this, "Terjadi Kesalahan di server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerKota(String id_provinsi) {



        ConfigRetrofit.service.dataKota(id_provinsi).enqueue(new Callback<ResponseDataKota>() {
            @Override
            public void onResponse(Call<ResponseDataKota> call, Response<ResponseDataKota> response) {
                if (response.isSuccessful()){

                    dataKota = response.body().getResponse();
                    List<String> listSpinnerKota = new ArrayList<String>();
                    for (int i = 0; i<dataKota.size(); i++){
                        listSpinnerKota.add(dataKota.get(i).getCityName());
                    }

                    ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(EditAkunActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerKota);

                    adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerKota.setAdapter(adapterKota);

                }else{
                    Toast.makeText(EditAkunActivity.this, "Gagal Memuat data Kota", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKota> call, Throwable t) {
                Toast.makeText(EditAkunActivity.this, "Terjadi Kesalahan Di server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerProvinsi() {

        ConfigRetrofit.service.dataProvinsi().enqueue(new Callback<ResponseDataProvinsi>() {
            @Override
            public void onResponse(Call<ResponseDataProvinsi> call, Response<ResponseDataProvinsi> response) {
                if (response.isSuccessful()){
                    dataProvinsi = response.body().getData();
                    List<String> listSpinnerProvinsi = new ArrayList<String>();
                    for (int i = 0; i<dataProvinsi.size(); i++){
                        listSpinnerProvinsi.add(dataProvinsi.get(i).getProvince());
                    }

                    ArrayAdapter<String> adapterProvinsi = new ArrayAdapter<String>(EditAkunActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerProvinsi);
                    adapterProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvinsi.setAdapter(adapterProvinsi);
                }else{
                    Toast.makeText(EditAkunActivity.this, "Gagal Mengambil data provinsi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataProvinsi> call, Throwable t) {
                Toast.makeText(EditAkunActivity.this, "Terjadi Kesalahan : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                txtTanggalLahir.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }
}