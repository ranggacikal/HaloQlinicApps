package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.DokterAturJadwalAdapter;
import com.haloqlinic.haloqlinicapps.adapter.KategoriBayarAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.batalkanKonsultasi.ResponseBatalkanKonsultasi;
import com.haloqlinic.haloqlinicapps.model.biayaAdmin.ResponseBiayaAdmin;
import com.haloqlinic.haloqlinicapps.model.ewalletKonsultasi.Actions;
import com.haloqlinic.haloqlinicapps.model.ewalletKonsultasi.ResponseEwalletKonsultasi;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ResponseJadwalDokter;
import com.haloqlinic.haloqlinicapps.model.kategoriXendit.ResponseKategoriXendit;
import com.haloqlinic.haloqlinicapps.model.qriskonsultasi.ResponseQrisKonsultasi;

import java.text.NumberFormat;
import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutKonsultasiActivity extends AppCompatActivity {

    String id_dokter, jadwal_dokter, nomerTelepon, id_transaksi, status, token_dokter, nama_dokter, player_id_dokter;
    int biaya = 0, biaya_admin = 0, total_bayar = 0;
    RelativeLayout relative_biaya_admin;
    RecyclerView rvDokterCheckout, rvMetodeBayar;
    TextView txtHargaKonsultasi, txtBiayaAdmin, txtTotalBayar, txtOpsiBayarPilih;
    ImageView imgBack, imgBayarPilih;
    Button btnCheckout, btnBatal;
    LinearLayout linearBayarPilih;

    String external_id, buat_jadwal;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_konsultasi);

        preferencedConfig = new SharedPreferencedConfig(this);

        id_dokter = getIntent().getStringExtra("id_dokter");
        jadwal_dokter = getIntent().getStringExtra("jadwal_dokter");
        biaya = Integer.parseInt(getIntent().getStringExtra("biaya"));
        id_transaksi = getIntent().getStringExtra("id_transaksi");
        status = getIntent().getStringExtra("status");
        external_id = getIntent().getStringExtra("external_id");
        buat_jadwal = getIntent().getStringExtra("buatJadwal");

        rvDokterCheckout = findViewById(R.id.recycler_dokter_pembayaran_konsultasi);
        rvMetodeBayar = findViewById(R.id.recycler_metode_bayar_konsultasi);
        txtHargaKonsultasi = findViewById(R.id.text_harga_konsultasi);
        txtBiayaAdmin = findViewById(R.id.text_biaya_admin_konsultasi);
        relative_biaya_admin = findViewById(R.id.relative_biaya_admin_konsultasi);
        txtTotalBayar = findViewById(R.id.text_total_bayar_konsultasi);
        imgBack = findViewById(R.id.img_back_pembayaran_konsultasi);
        btnCheckout = findViewById(R.id.btn_checkout_konsultasi);
        imgBayarPilih = findViewById(R.id.img_byar_pilih_konsultasi);
        txtOpsiBayarPilih = findViewById(R.id.text_nama_bayar_pilih_konsultasu);
        linearBayarPilih = findViewById(R.id.metode_bayar_pilih_konsultasi);
        btnBatal = findViewById(R.id.btn_batalkan_konsultasi);

        if (status.equals("1")){
            btnBatal.setVisibility(View.VISIBLE);
        }

        initMetodeBayarPilih();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
                finish();
            }
        });

        txtHargaKonsultasi.setText("Rp" + NumberFormat.getInstance().format(biaya));

        rvMetodeBayar.setHasFixedSize(true);
        rvMetodeBayar.setLayoutManager(new LinearLayoutManager(CheckoutKonsultasiActivity.this));

        rvDokterCheckout.setHasFixedSize(true);
        rvDokterCheckout.setLayoutManager(new LinearLayoutManager(CheckoutKonsultasiActivity.this));

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOut();
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tampilDialog();
            }
        });

        loadDokter();
        loadMetodeBayar();
        loadBiayaAdmin();
    }

    private void batal() {

        ProgressDialog progressDialog = new ProgressDialog(CheckoutKonsultasiActivity.this);
        progressDialog.setMessage("membatalkan konsultasi");
        progressDialog.show();

        ConfigRetrofit.service.batalkanKonsultasi(id_transaksi).enqueue(new Callback<ResponseBatalkanKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseBatalkanKonsultasi> call, Response<ResponseBatalkanKonsultasi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Berhasil membatalkan konsultasi", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CheckoutKonsultasiActivity.this, MainActivity.class));
                    finish();

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Gagal membatalkan pesanan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBatalkanKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CheckoutKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tampilDialog() {

        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Batalkan Konsultasi?")
                .setMessage("Anda yakin ingin membatalkan konsultasi?")
                .setCancelable(false)
                .setPositiveButton("Batalkan", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        batal();
                    }
                })
                .setNegativeButton("Tidak", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();

    }

    private void initMetodeBayarPilih() {

        String urlImage = preferencedConfig.getPreferenceImageOpsiBayar();
        String namaOpsiBayar = preferencedConfig.getPreferenceNamaOpsiBayar();

        if (!namaOpsiBayar.equals("")){
            linearBayarPilih.setVisibility(View.VISIBLE);
            txtOpsiBayarPilih.setText(namaOpsiBayar);

            String url_image = "https://aplikasicerdas.net/haloqlinic/android/xendit/img/"+urlImage;
            Glide.with(CheckoutKonsultasiActivity.this)
                    .load(urlImage)
                    .error(R.mipmap.ic_launcher)
                    .into(imgBayarPilih);
        }else{
            linearBayarPilih.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBiayaAdmin();
        initMetodeBayarPilih();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
        finish();
    }

    private void checkOut() {

        String kategori_bayar = preferencedConfig.getPreferenceKategoriBayar();

        if (kategori_bayar.equals("")){
            Toast.makeText(this, "Anda belum memilih kategori pembayaran", Toast.LENGTH_SHORT).show();
            return;
        }

        if (kategori_bayar.equals("E-Wallets")){
            checkoutEwallet();
        }else if(kategori_bayar.equals("Kartu Kredit/Debit")){

            tampilDialogKartuKredit();

        }else if (kategori_bayar.equals("QR Code")){

            checkoutQris();

        }

    }

    private void checkoutQris() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String total = String.valueOf(total_bayar);
        String biayaAdminPost = String.valueOf(biaya_admin);
        String kategori = preferencedConfig.getPreferenceIdKategoriBayar();


        Log.d("checkParamQris", "id_transaksi: "+id_transaksi);
        Log.d("checkParamQris", "id_customer: "+id_customer);
        Log.d("checkParamQris", "total: "+total);
        Log.d("checkParamQris", "biaya_admin: "+biayaAdminPost);
        Log.d("checkParamQris", "id_dokter: "+id_dokter);
        Log.d("checkParamQris", "kategori: "+kategori);
        Log.d("checkParamQris", "jadwal_dokter: "+jadwal_dokter);
        Log.d("checkParamQris", "status: "+status);
        Log.d("checkParamQris", "external: "+external_id);

        ProgressDialog progressDialog = new ProgressDialog(CheckoutKonsultasiActivity.this);
        progressDialog.setMessage("Memproses Checkout");
        progressDialog.show();

        ConfigRetrofit.service.qrisKonsultasi(id_transaksi, id_customer, total, biayaAdminPost, id_dokter, kategori,
                jadwal_dokter, status, external_id).enqueue(new Callback<ResponseQrisKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseQrisKonsultasi> call, Response<ResponseQrisKonsultasi> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    String id_transaksi = response.body().getExternalId();
                    String qr_string = response.body().getQrString();

                    Toast.makeText(CheckoutKonsultasiActivity.this, "Berhasil checkout Qris", Toast.LENGTH_SHORT).show();
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");

                    Log.d("checkQrString", "onResponse: "+qr_string);

                    Intent intent = new Intent(CheckoutKonsultasiActivity.this, InvoiceKonsultasiActivity.class);
                    intent.putExtra("qr_string", qr_string);
                    intent.putExtra("id_transaksi", id_transaksi);
                    intent.putExtra("status", status);
                    intent.putExtra("konsultasi", "konsultasi");
                    intent.putExtra("token_dokter", token_dokter);
                    intent.putExtra("nama_dokter", nama_dokter);
                    intent.putExtra("player_id", player_id_dokter);
                    intent.putExtra("id_dokter", id_dokter);
                    intent.putExtra("QRIS", "qris");
                    intent.putExtra("buat_jadwal", buat_jadwal);
                    startActivity(intent);
                    finish();
                }else{
                    progressDialog.dismiss();
                    Log.d("checkParamQris", "id_transaksi: "+id_transaksi);
                    Log.d("checkParamQris", "id_customer: "+id_customer);
                    Log.d("checkParamQris", "total: "+total);
                    Log.d("checkParamQris", "biaya_admin: "+biayaAdminPost);
                    Log.d("checkParamQris", "id_dokter: "+id_dokter);
                    Log.d("checkParamQris", "kategori: "+kategori);
                    Log.d("checkParamQris", "jadwal_dokter: "+jadwal_dokter);
                    Log.d("checkParamQris", "status: "+status);
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Gagal checkout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseQrisKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CheckoutKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void tampilDialogKartuKredit() {
    }

    private void checkoutEwallet() {

        if (preferencedConfig.getPreferenceKodeOpsiBayar().equals("ID_OVO")){

            tampilDialogNomor();

        }else{

            checkoutEwalletLainnya();

        }

    }

    private void checkoutEwalletLainnya() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String total = String.valueOf(total_bayar);
        String kode = preferencedConfig.getPreferenceKodeOpsiBayar();
        String biayaAdminPost = String.valueOf(biaya_admin);
        String kategori = preferencedConfig.getPreferenceIdKategoriBayar();

        ProgressDialog progressDialog = new ProgressDialog(CheckoutKonsultasiActivity.this);
        progressDialog.setMessage("Memproses checkout konsultasi");
        progressDialog.show();

        Log.d("dataKirimEWallet", "idCustomer: "+id_customer);
        Log.d("dataKirimEWallet", "total: "+total);
        Log.d("dataKirimEWallet", "kode: "+kode);
        Log.d("dataKirimEWallet", "biayaAdmin: "+biayaAdminPost);
        Log.d("dataKirimEWallet", "kategori: "+kategori);
        Log.d("dataKirimEWallet", "idDokter: "+id_dokter);
        Log.d("dataKirimEWallet", "jadwalDokter: "+jadwal_dokter);
        Log.d("dataKirimEWallet", "status: "+status);
        Log.d("dataKirimEWallet", "id_transaksi: "+id_transaksi);

        ConfigRetrofit.service.ewalletKonsultasi(id_transaksi, id_customer, total, kode, "0", biayaAdminPost,
                id_dokter, kategori, jadwal_dokter, status, external_id).enqueue(new Callback<ResponseEwalletKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseEwalletKonsultasi> call, Response<ResponseEwalletKonsultasi> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    String id_transaksi = response.body().getReferenceId();
                    Log.d("id_transaksi_konsultasi", "onResponse: "+id_transaksi);
                    Actions actions = response.body().getActions();
                    String deeplink = (String) actions.getMobileDeeplinkCheckoutUrl();
                    String mobileUrl = actions.getMobileWebCheckoutUrl();

                    Log.d("checkUrlCheckout", "deeplink: "+deeplink);
                    Log.d("checkUrlCheckout", "mobileWeb: "+mobileUrl);

                    Toast.makeText(CheckoutKonsultasiActivity.this, "Berhasil Checkout", Toast.LENGTH_SHORT).show();
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
                    Intent intent = new Intent(CheckoutKonsultasiActivity.this, InvoiceKonsultasiActivity.class);
                    intent.putExtra("id_transaksi", id_transaksi);
                    intent.putExtra("mobile_web", mobileUrl);
                    intent.putExtra("mobile_deeplink", deeplink);
                    intent.putExtra("konsultasi", "konsultasi");
                    intent.putExtra("status", status);
                    intent.putExtra("token_dokter", token_dokter);
                    intent.putExtra("nama_dokter", nama_dokter);
                    intent.putExtra("player_id", player_id_dokter);
                    intent.putExtra("id_dokter", id_dokter);
                    startActivity(intent);
                    finish();

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Gagal Checkout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEwalletKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CheckoutKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tampilDialogNomor() {
        Dialog dialog = new Dialog(CheckoutKonsultasiActivity.this);
        dialog.setContentView(R.layout.dialog_nomor_telepon);

        EditText edtNomor = dialog.findViewById(R.id.edt_nomer_ovo);
        TextView txtKonfirmasi = dialog.findViewById(R.id.text_konfirmasi_dialog_selesai);

        dialog.show();

        txtKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomor = edtNomor.getText().toString();
                nomerTelepon = "+62"+edtNomor.getText().toString();

                if (nomor.isEmpty()){
                    edtNomor.setError("nomor tidak boleh kosong");
                    edtNomor.requestFocus();
                    return;
                }

                checkoutEwalletOvo();
            }
        });
    }

    private void checkoutEwalletOvo() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();
        String total = String.valueOf(total_bayar);
        String kode = preferencedConfig.getPreferenceKodeOpsiBayar();
        String biayaAdminPost = String.valueOf(biaya_admin);
        String kategori = preferencedConfig.getPreferenceIdKategoriBayar();

        android.app.ProgressDialog progressDialogOvo = new ProgressDialog(CheckoutKonsultasiActivity.this);
        progressDialogOvo.setMessage("Memproses checkout");
        progressDialogOvo.show();


        ConfigRetrofit.service.ewalletKonsultasi(id_transaksi, id_customer, total, kode, nomerTelepon, biayaAdminPost, id_dokter,
                kategori, jadwal_dokter, status, external_id).enqueue(new Callback<ResponseEwalletKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseEwalletKonsultasi> call, Response<ResponseEwalletKonsultasi> response) {
                if (response.isSuccessful()){

                    progressDialogOvo.dismiss();
                    String id_transaksi = response.body().getReferenceId();
                    Log.d("id_transaksi_konsultasi", "onResponse: "+id_transaksi);
                    Actions actions = response.body().getActions();
                    String deeplink = (String) actions.getMobileDeeplinkCheckoutUrl();
                    String mobileUrl = actions.getMobileWebCheckoutUrl();

                    Log.d("checkUrlCheckout", "deeplink: "+deeplink);
                    Log.d("checkUrlCheckout", "mobileWeb: "+mobileUrl);

                    Toast.makeText(CheckoutKonsultasiActivity.this, "Berhasil Checkout", Toast.LENGTH_SHORT).show();
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
                    Intent intent = new Intent(CheckoutKonsultasiActivity.this, InvoiceKonsultasiActivity.class);
                    intent.putExtra("id_transaksi", id_transaksi);
                    intent.putExtra("konsultasi", "konsultasi");
                    intent.putExtra("token_dokter", token_dokter);
                    intent.putExtra("nama_dokter", nama_dokter);
                    intent.putExtra("player_id", player_id_dokter);
                    intent.putExtra("id_dokter", id_dokter);
                    startActivity(intent);
                    finish();
                }else{
                    progressDialogOvo.dismiss();
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Gagal checkout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEwalletKonsultasi> call, Throwable t) {
                progressDialogOvo.dismiss();
                Toast.makeText(CheckoutKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadMetodeBayar() {

        ConfigRetrofit.service.kategoriXendit().enqueue(new Callback<ResponseKategoriXendit>() {
            @Override
            public void onResponse(Call<ResponseKategoriXendit> call, Response<ResponseKategoriXendit> response) {
                if (response.isSuccessful()){

                    List<com.haloqlinic.haloqlinicapps.model.kategoriXendit.DataItem> dataKategori = response.body().getData();
                    KategoriBayarAdapter kategoriBayarAdapter = new KategoriBayarAdapter(CheckoutKonsultasiActivity.this, dataKategori);
                    rvMetodeBayar.setAdapter(kategoriBayarAdapter);

                }else{
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Gagal memuat data metode pembayaran", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKategoriXendit> call, Throwable t) {
                Toast.makeText(CheckoutKonsultasiActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokter() {

        ConfigRetrofit.service.jadwalDokter(id_dokter).enqueue(new Callback<ResponseJadwalDokter>() {
            @Override
            public void onResponse(Call<ResponseJadwalDokter> call, Response<ResponseJadwalDokter> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataDokter = response.body().getData();

                    for (int i = 0; i<dataDokter.size(); i++){
                        token_dokter = dataDokter.get(i).getToken();
                        nama_dokter = dataDokter.get(i).getNama();
                        player_id_dokter = dataDokter.get(i).getPlayerId();
                    }

                    DokterAturJadwalAdapter adapterDokter = new DokterAturJadwalAdapter(CheckoutKonsultasiActivity.this, dataDokter);
                    rvDokterCheckout.setAdapter(adapterDokter);

                }else{
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwalDokter> call, Throwable t) {
                Toast.makeText(CheckoutKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadBiayaAdmin() {

        String totalPost = String.valueOf(biaya);

        ConfigRetrofit.service.biayaAdmin(preferencedConfig.getPreferenceIdOpsiBayar(), totalPost).enqueue(new Callback<ResponseBiayaAdmin>() {
            @Override
            public void onResponse(Call<ResponseBiayaAdmin> call, Response<ResponseBiayaAdmin> response) {
                if (response.isSuccessful()){

                    biaya_admin = response.body().getBiayaAdmin();

                    if (biaya_admin!=0){
                        relative_biaya_admin.setVisibility(View.VISIBLE);
                        txtBiayaAdmin.setText("Rp" + NumberFormat.getInstance().format(biaya_admin));
                    }else if (biaya_admin==0){
                        relative_biaya_admin.setVisibility(View.GONE);
                    }
                }else{
                    Toast.makeText(CheckoutKonsultasiActivity.this, "Gagal mengambil biaya admin", Toast.LENGTH_SHORT).show();
                }

                total_bayar = biaya + biaya_admin;

                txtTotalBayar.setText("Rp" + NumberFormat.getInstance().format(total_bayar));

            }

            @Override
            public void onFailure(Call<ResponseBiayaAdmin> call, Throwable t) {
                Toast.makeText(CheckoutKonsultasiActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}