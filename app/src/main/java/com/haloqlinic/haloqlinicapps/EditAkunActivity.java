package com.haloqlinic.haloqlinicapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.github.dhaval2404.imagepicker.ImagePicker;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.editAkun.ResponseEditAkun;
import com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseDataKecamatan;
import com.haloqlinic.haloqlinicapps.model.kota.ResponseDataKota;
import com.haloqlinic.haloqlinicapps.model.kota.ResponseItem;
import com.haloqlinic.haloqlinicapps.model.provinsi.DataItem;
import com.haloqlinic.haloqlinicapps.model.provinsi.ResponseDataProvinsi;
import com.haloqlinic.haloqlinicapps.model.updatePhoto.ResponseUpdatePhoto;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunActivity extends AppCompatActivity {

    CircleImageView imgEditAkun, imgUpdatePhoto;
    EditText edtNamaLengkap, edtNotelepon, edtAlamat;
    TextView txtJenisKelamin, txtTanggalLahir, txtProvinsi, txtKota, txtKecamatan;
    LinearLayout linearJenisKelamin, linearTglLahir;
    RelativeLayout relativeProvinsi, relativeKota, relativeKecamatan;
    Spinner spinnerJK, spinnerProvinsi, spinnerKota, spinnerKecamatan;
    Button btnEditAkun;
    ImageView imgBack;

    List<DataItem> dataProvinsi;
    List<ResponseItem> dataKota;
    List<com.haloqlinic.haloqlinicapps.model.kecamatan.ResponseItem> dataKecamatan;

    String id_provinsi = "";
    String id_kota = "";
    String id_kecamatan = "";

    String provinsi, kota, kecamatan, tanggal_lahir, jenis_kelamin;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private SharedPreferencedConfig preferencedConfig;

    Typeface face;

    Uri uri1;

    String nama_file1 = "";
    private String PicturePath1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        preferencedConfig = new SharedPreferencedConfig(this);

        AndroidNetworking.initialize(getApplicationContext());

        imgEditAkun = findViewById(R.id.img_edit_akun);
        imgUpdatePhoto = findViewById(R.id.img_update_photo);
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
        imgBack = findViewById(R.id.img_back_edit_akun);

        face = ResourcesCompat.getFont(EditAkunActivity.this, R.font.robotobold);

        ArrayList<String> jenisKelaminList = new ArrayList<>();
        jenisKelaminList.add("Laki - Laki");
        jenisKelaminList.add("Perempuan");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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



                ArrayAdapter<String> adapterJenisKelamin = new ArrayAdapter<String>(EditAkunActivity.this, R.layout.spinner_item, jenisKelaminList);

                spinnerJK.setAdapter(adapterJenisKelamin);

                spinnerJK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        jenis_kelamin = jenisKelaminList.get(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        });

        initSpinnerProvinsi();

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_provinsi = dataProvinsi.get(position).getProvinceId();
                provinsi = dataProvinsi.get(position).getProvince();
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
                kota = dataKota.get(position).getCityName();
                initSpinnerKecamatan(id_kota);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                id_kecamatan = dataKecamatan.get(position).getSubdistrictId();
                kecamatan = dataKecamatan.get(position).getSubdistrictName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("checkIdCustomer", "idCustomer: "+preferencedConfig.getPreferenceIdCustomer());

        initData();
        Log.d("testPreference", "onCreate: "+preferencedConfig.getPreferenceJk());

        btnEditAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAkun();
            }
        });

        imgEditAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(EditAkunActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {


                uri1 = data.getData();
                nama_file1 = uri1.getLastPathSegment();
                imgEditAkun.setImageURI(uri1);
                PicturePath1 = uri1.getPath();

                Log.d("checkPath", "onActivityResult: "+PicturePath1);

                updatePhoto();

            }else if (resultCode == ImagePicker.RESULT_ERROR){
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }
    }

    private void updatePhoto() {

        ProgressDialog progressDialog = new ProgressDialog(EditAkunActivity.this);
        progressDialog.setMessage("Mengajukan Penukaran Produk");
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.show();

        File file = new File(PicturePath1);

        RequestBody requestFile =
                RequestBody.create(file, MediaType.parse("multipart/form-data"));

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        RequestBody id_customer = RequestBody.create(preferencedConfig.getPreferenceIdCustomer(), MediaType.parse("text/plain"));

        ConfigRetrofit.service.updatePhoto(id_customer, body).enqueue(new Callback<ResponseUpdatePhoto>() {
            @Override
            public void onResponse(Call<ResponseUpdatePhoto> call, Response<ResponseUpdatePhoto> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    String nama_file = response.body().getFile();
                    Log.d("namaUpdatePhoto", "onResponse: "+nama_file);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMG, nama_file);
                    Toast.makeText(EditAkunActivity.this, "Berhasil Update photo", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(EditAkunActivity.this, "Gagal update photo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdatePhoto> call, Throwable t) {
                Toast.makeText(EditAkunActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        HashMap<String, String> params = new HashMap<>();
//        params.put("id_customer", preferencedConfig.getPreferenceIdCustomer());
//        //params.put("password2",picturePath);
//
//        try {
//            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                    .connectTimeout(10, TimeUnit.SECONDS)
//                    .readTimeout(10, TimeUnit.SECONDS)
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .build();
//
//
//            AndroidNetworking.upload("https://aplikasicerdas.net/haloqlinic/android/customer/update_photo.php")
//                    .addMultipartParameter(params)
//                    .addMultipartFile("file", new File(PicturePath1))
//                    .setTag(EditAkunActivity.this)
//                    .setPriority(Priority.HIGH)
//                    .setOkHttpClient(okHttpClient)
//                    .build()
//                    .setUploadProgressListener(new UploadProgressListener() {
//                        @Override
//                        public void onProgress(long bytesUploaded, long totalBytes) {
//
//                        }
//                    })
//                    .getAsString(new StringRequestListener() {
//                        @Override
//                        public void onResponse(String response) {
//                            if (response.contains("Berhasil update foto")) {
//                                Toast.makeText(EditAkunActivity.this, "Profile Picture has succesfully changed.", Toast.LENGTH_LONG).show();
//                                Log.d("getFoto", "onResponse: "+new File(PicturePath1));
//                                Log.d("getNamaFoto", "onResponse: "+nama_file1);
//                            } else {
//                                Toast.makeText(EditAkunActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
//                                Log.d("getFoto", "onResponse: "+new File(PicturePath1));
//                            }
//                        }
//
//                        @Override
//                        public void onError(ANError anError) {
////                        progressDialog.dismiss();
//                            Toast.makeText(EditAkunActivity.this, anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
//                            Log.d("checkErrorUpdatePhoto", "onError: "+anError.getErrorDetail());
//                            Log.d("checkErrorUpdatePhoto", "onError: "+anError.getErrorCode());
//                            Log.d("getParam", "onError: "+params);
//                            Log.d("getFoto", "onResponse: "+new File(PicturePath1));
//                        }
//                    });
//        }catch (NullPointerException e){
//            //kalau kosong
//            e.printStackTrace();
//            Toast.makeText(EditAkunActivity.this, "Upload dulu Imagenya, tekan di bagian gambar.", Toast.LENGTH_SHORT).show();
//        }

    }

    private void editAkun() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String nama = edtNamaLengkap.getText().toString();
        String no_hp = edtNotelepon.getText().toString();
        String alamat = edtAlamat.getText().toString();
        String provinsiPost = "";
        String kotaPost = "";
        String kecamatanPost = "";
        String jenisKelaminPost = "";
        String tglLahirPost = "";

        if (nama.isEmpty()){
            edtNamaLengkap.setError("Nama Lengkap tidak boleh kosong");
            edtNamaLengkap.requestFocus();
            return;
        }

        if (no_hp.isEmpty()){
            edtNotelepon.setError("No Telepon tidak boleh kosong");
            edtNotelepon.requestFocus();
            return;
        }

        if (alamat.isEmpty()){
            edtAlamat.setError("alamat tidak boleh kosong");
            edtAlamat.requestFocus();
            return;
        }

        if (preferencedConfig.getPreferenceJk().equals("") && jenis_kelamin.isEmpty()){

            Toast.makeText(this, "Anda belum memilih jenis kelamin", Toast.LENGTH_SHORT).show();
            return;

        }

        if (preferencedConfig.getPreferenceTglLahir().equals("") && tanggal_lahir.isEmpty()){
            Toast.makeText(this, "Anda belum memilih tanggal lahir", Toast.LENGTH_SHORT).show();
            return;
        }

        if (preferencedConfig.getPreferenceProvinsi().equals("") && provinsi.isEmpty()){
            Toast.makeText(this, "Anda belum memilih Provinsi", Toast.LENGTH_SHORT).show();
            return;
        }

        if (preferencedConfig.getPreferenceKota().equals("") && kota.isEmpty()){
            Toast.makeText(this, "Anda belum memilih Kota", Toast.LENGTH_SHORT).show();
            return;
        }

        if (preferencedConfig.getPreferenceKecamatan().equals("") && kecamatan.isEmpty()){
            Toast.makeText(this, "Anda Belum memilih kecamatan", Toast.LENGTH_SHORT).show();
        }

        if (jenis_kelamin != null){
            jenisKelaminPost = jenis_kelamin;
        }else{
            jenisKelaminPost = preferencedConfig.getPreferenceJk();
        }

        if (tanggal_lahir != null){
            tglLahirPost = tanggal_lahir;
        }else{
            tglLahirPost = preferencedConfig.getPreferenceTglLahir();
        }

        if (!id_provinsi.equals("")){
            provinsiPost = id_provinsi;
        }else if (id_provinsi.equals("")){
            provinsiPost = preferencedConfig.getPreferenceIdProvinsi();
        }

        if (!id_kota.equals("")){
            kotaPost = id_kota;
        }else if (id_kota.equals("")){
            kotaPost = preferencedConfig.getPreferenceIdKota();
        }

        if (!id_kecamatan.equals("")){
            kecamatanPost = id_kecamatan;
        }else if (id_kecamatan.equals("")){
            kecamatanPost = preferencedConfig.getPreferenceIdKecamatan();
        }

        Log.d("checkDataAlamat", "onResponse: "+provinsiPost+", "+kotaPost+", "+kecamatanPost+", "+jenisKelaminPost+", "+tglLahirPost);
        Log.d("checkDataAlamat", "preference: "+preferencedConfig.getPreferenceIdProvinsi()+
                ", "+preferencedConfig.getPreferenceIdKota()+", "+preferencedConfig.getPreferenceIdKecamatan());
        Log.d("checkDataAlamat", "fromSpinner: "+id_provinsi+", "+id_kota+", "+id_kecamatan);
        
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Update data akun");
        progressDialog.show();

        ConfigRetrofit.service.editAkun(id_customer, nama, alamat, no_hp, jenisKelaminPost, provinsiPost, kotaPost, kecamatanPost,
                tglLahirPost).enqueue(new Callback<ResponseEditAkun>() {
            @Override
            public void onResponse(Call<ResponseEditAkun> call, Response<ResponseEditAkun> response) {
                if (response.isSuccessful()){
                    
                    progressDialog.dismiss();
                    Toast.makeText(EditAkunActivity.this, "berhasil edit data akun", Toast.LENGTH_SHORT).show();
                    
                    List<com.haloqlinic.haloqlinicapps.model.editAkun.ResponseItem> dataEditAkun = response.body().getResponse();

                    String id_customer = "";
                    String nama = "";
                    String kode = "";
                    String email = "";
                    String no_hp = "";
                    String jk = "";
                    String tgl_lahir = "";
                    String usia = "";
                    String img = "";
                    String provinsi = "";
                    String kota = "";
                    String kecamatan = "";
                    String alamat = "";
                    String idProvinsi = "";
                    String idKota = "";
                    String idKecamatan = "";
                    
                    for (int i = 0; i<dataEditAkun.size(); i++){
                        id_customer = dataEditAkun.get(i).getIdCustomer();
                        nama = dataEditAkun.get(i).getNama();
                        kode = dataEditAkun.get(i).getKode();
                        email = dataEditAkun.get(i).getEmail();
                        no_hp = dataEditAkun.get(i).getNoHp();
                        jk = dataEditAkun.get(i).getJk();
                        tgl_lahir = dataEditAkun.get(i).getTglLahir();
                        usia = dataEditAkun.get(i).getUsia();
                        img = (String) dataEditAkun.get(i).getImg();
                        provinsi = dataEditAkun.get(i).getNamaProvinsi();
                        kota = dataEditAkun.get(i).getNamaKota();
                        kecamatan = dataEditAkun.get(i).getNamaKecamatan();
                        alamat = dataEditAkun.get(i).getAlamat();
                        idProvinsi = dataEditAkun.get(i).getProvinsi();
                        idKota = dataEditAkun.get(i).getKota();
                        idKecamatan = dataEditAkun.get(i).getKecamatan();
                    }

                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_CUSTOMER, id_customer);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE, kode);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA, nama);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_EMAIL, email);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NO_HP, no_hp);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_JK, jk);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_TGL_LAHIR, tgl_lahir);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_USIA, usia);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMG, img);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_PROVINSI, provinsi);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KOTA, kota);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KECAMATAN, kecamatan);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ALAMAT, alamat);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_PROVINSI, idProvinsi);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_KOTA, idKota);
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_KECAMATAN, idKecamatan);
                    preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);
                    finish();
                    
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(EditAkunActivity.this, "Gagal edit data akun", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEditAkun> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditAkunActivity.this, "Terjadi kesalahan di server", Toast.LENGTH_SHORT).show();
            }
        });

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
            txtJenisKelamin.setTextColor(Color.parseColor("#000000"));
            txtJenisKelamin.setTypeface(face);

        }

        if (preferencedConfig.getPreferenceTglLahir().equals("")){
            txtTanggalLahir.setText("Tanggal Lahir");
        }else{
            txtTanggalLahir.setText(preferencedConfig.getPreferenceTglLahir());
            txtTanggalLahir.setTextColor(Color.parseColor("#000000"));
            txtTanggalLahir.setTypeface(face);
        }

        if (preferencedConfig.getPreferenceProvinsi().equals("")){
            txtProvinsi.setText("Provinsi");
        }else{
            txtProvinsi.setText(preferencedConfig.getPreferenceProvinsi());
            txtProvinsi.setTextColor(Color.parseColor("#000000"));
            txtProvinsi.setTypeface(face);
        }

        if (preferencedConfig.getPreferenceKota().equals("")){
            txtKota.setText("Kota");
        }else{
            txtKota.setText(preferencedConfig.getPreferenceKota());
            txtKota.setTextColor(Color.parseColor("#000000"));
            txtKota.setTypeface(face);
        }

        if (preferencedConfig.getPreferenceKecamatan().equals("")){
            txtKecamatan.setText("Kecamatan");
        }else{
            txtKecamatan.setText(preferencedConfig.getPreferenceKecamatan());
            txtKecamatan.setTextColor(Color.parseColor("#000000"));
            txtKecamatan.setTypeface(face);
        }

        if (preferencedConfig.getPreferenceAlamat().equals("")){
            edtAlamat.setText("");
        }else{
            edtAlamat.setText(preferencedConfig.getPreferenceAlamat());
        }

        String url = "https://aplikasicerdas.net/haloqlinic/file/customer/profile/"+preferencedConfig.getPreferenceImg();
        Glide.with(EditAkunActivity.this)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(imgEditAkun);

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
                            R.layout.spinner_item, listSpinnerKecamatan);

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
                            R.layout.spinner_item, listSpinnerKota);

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
                            R.layout.spinner_item, listSpinnerProvinsi);
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
                tanggal_lahir = dateFormatter.format(newDate.getTime());
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }
}