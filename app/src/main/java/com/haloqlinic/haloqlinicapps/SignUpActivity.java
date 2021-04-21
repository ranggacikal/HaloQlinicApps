package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.daftar.ResponseDaftar;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText edtNama, edtEmail, edtPassword, edtNoTelepon, edtAlamat;
    Spinner spinnerJK, spinnerProvinsi, spinnerKota, spinnerKecamatan;
    LinearLayout linearTglLahir;
    TextView txtTanggalLahir;
    Button btnSignup;
    ImageView showPassBtn;

    List<DataItem> dataProvinsi;
    List<ResponseItem> dataKota;
    List<com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseItem> dataKecamatan;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    String id_province = "";
    String id_city = "";
    String id_kecamatan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtNama = findViewById(R.id.edt_nama_signup);
        edtEmail = findViewById(R.id.edt_email_signup);
        edtPassword = findViewById(R.id.edt_password_signup);
        edtNoTelepon = findViewById(R.id.edt_no_hp_signup);
        spinnerProvinsi = findViewById(R.id.spinner_provinsi_signup);
        spinnerKota = findViewById(R.id.spinner_kota_signup);
        spinnerKecamatan = findViewById(R.id.spinner_kecamatan_signup);
        spinnerJK = findViewById(R.id.spinner_jenis_kelamin);
        linearTglLahir = findViewById(R.id.linear_tanggal_lahir);
        txtTanggalLahir = findViewById(R.id.text_tanggal_lahir);
        btnSignup = findViewById(R.id.btn_daftar);
        showPassBtn = findViewById(R.id.img_hide_password_signup);
        edtAlamat = findViewById(R.id.edt_alamat_signup);

        initSpinnerProvinsi();

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        linearTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftar();
            }
        });

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_province = dataProvinsi.get(position).getProvinceId();
                initSpinnerKota(id_province);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_city = dataKota.get(position).getCityId();
                initSpinnerKecamatan(id_city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_kecamatan = dataKecamatan.get(position).getSubdistrictId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        showHidePass(showPassBtn);


    }

    private void initSpinnerKecamatan(String id_city) {

        ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Memuat Data Kecamatan");
        progressDialog.show();

        ConfigRetrofit.service.dataKecamatan(id_city).enqueue(new Callback<ResponseDataKecamatan>() {
            @Override
            public void onResponse(Call<ResponseDataKecamatan> call, Response<ResponseDataKecamatan> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    dataKecamatan = response.body().getResponse();
                    List<String> listSpinnerKecamatan = new ArrayList<String>();

                    for (int i = 0; i<dataKecamatan.size(); i++){
                        listSpinnerKecamatan.add(dataKecamatan.get(i).getSubdistrictName());
                    }

                    ArrayAdapter<String> adapterKecamatan = new ArrayAdapter<String>(SignUpActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerKecamatan);

                    adapterKecamatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerKecamatan.setAdapter(adapterKecamatan);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Gagal Memuat data Kecamatan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKecamatan> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, "Terjadi Kesalahan di server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerKota(String id_province) {

        ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Memuat Data Kota");
        progressDialog.show();

        ConfigRetrofit.service.dataKota(id_province).enqueue(new Callback<ResponseDataKota>() {
            @Override
            public void onResponse(Call<ResponseDataKota> call, Response<ResponseDataKota> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    dataKota = response.body().getResponse();
                    List<String> listSpinnerKota = new ArrayList<String>();
                    for (int i = 0; i<dataKota.size(); i++){
                        listSpinnerKota.add(dataKota.get(i).getCityName());
                    }

                    ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(SignUpActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerKota);

                    adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerKota.setAdapter(adapterKota);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Gagal Memuat data Kota", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataKota> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, "Terjadi Kesalahan Di server", Toast.LENGTH_SHORT).show();
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

                    ArrayAdapter<String> adapterProvinsi = new ArrayAdapter<String>(SignUpActivity.this,
                            android.R.layout.simple_spinner_item, listSpinnerProvinsi);
                    adapterProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvinsi.setAdapter(adapterProvinsi);
                }else{
                    Toast.makeText(SignUpActivity.this, "Gagal Mengambil data provinsi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataProvinsi> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Terjadi Kesalahan : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showHidePass(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.getId()==R.id.img_hide_password_signup){

                    if(edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye);
                        //Show Password
                        edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye_slash);

                        //Hide Password
                        edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }

            }
        });

    }

    private void daftar() {

        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String no_telepon = edtNoTelepon.getText().toString();
        String tanggal_lahir = txtTanggalLahir.getText().toString();
        String jenis_kelamin = spinnerJK.getSelectedItem().toString();
        String alamat = edtAlamat.getText().toString();

        if (nama.isEmpty()){
            edtNama.setError("Nama Tidak Boleh Kosong");
            edtNama.requestFocus();
            return;
        }

        if (email.isEmpty()){
            edtEmail.setError("Email Tidak Boleh Kosong");
            edtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Alamat Email Tidak Valid");
            edtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtPassword.setError("Password tidak boleh kosong");
            edtPassword.requestFocus();
            return;
        }

        if (no_telepon.isEmpty()){
            edtNoTelepon.setError("No telepon tidak boleh kosong");
            edtNoTelepon.requestFocus();
            return;
        }

        if (tanggal_lahir.equals("Tanggal Lahir")){
            Toast.makeText(this, "Anda belum memilih tanggal lahir", Toast.LENGTH_SHORT).show();
            return;
        }

        if (alamat.isEmpty()){
            edtAlamat.setError("Alamat tidak boleh kosong");
            edtAlamat.requestFocus();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Signup");
        progressDialog.show();

        ConfigRetrofit.service.daftar(nama, email, no_telepon, jenis_kelamin, tanggal_lahir, id_province, id_city, id_kecamatan, alamat, password).enqueue(new Callback<ResponseDaftar>() {
            @Override
            public void onResponse(Call<ResponseDaftar> call, Response<ResponseDaftar> response) {

                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    String validasi = response.body().getResponse();

                    if (validasi.equals("Data Berhasil Dikirim")){
                        Toast.makeText(SignUpActivity.this, "Pendaftaran berhasil, Silahkan login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(SignUpActivity.this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Terjadi kesalahan di server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseDaftar> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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