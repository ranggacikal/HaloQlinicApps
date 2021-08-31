package id.luvie.luvieapps;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

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
import android.widget.TextView;
import android.widget.Toast;

import com.thekhaeng.pushdownanim.PushDownAnim;

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.OpsiBayarAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.biayaAdmin.ResponseBiayaAdmin;
import id.luvie.luvieapps.model.ewalletKonsultasi.Actions;
import id.luvie.luvieapps.model.ewalletKonsultasi.ResponseEwalletKonsultasi;
import id.luvie.luvieapps.model.opsiBayar.DataItem;
import id.luvie.luvieapps.model.opsiBayar.ResponseOpsiBayar;

import java.text.NumberFormat;
import java.util.List;

import id.luvie.luvieapps.model.qriskonsultasi.ResponseQrisKonsultasi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpsiBayarActivity extends AppCompatActivity {

    TextView txtNamaKategori;
    RecyclerView rvOpsibayar;
    Button btnBayarKonsultasi;

    public int biaya = 0, biaya_admin = 0, total_bayar = 0;

    public String id_transaksi, id_dokter, jadwal_dokter, status, external_id, from_activity, nomerTelepon;
    String token_dokter, nama_dokter, player_id_dokter, buat_jadwal, image_dokter, spesialis_dokter;

    CheckoutProdukActivity checkoutProdukActivity;
    private SharedPreferencedConfig preferencedConfig;

    public OpsiBayarActivity(){

    }

    public String idOpsi="";

    public String nama, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_bayar);

        preferencedConfig = new SharedPreferencedConfig(this);

        txtNamaKategori = findViewById(R.id.text_nama_kategori_opsi_bayar);
        rvOpsibayar = findViewById(R.id.recycler_opsi_bayar);
        btnBayarKonsultasi = findViewById(R.id.btn_bayar_konsultasi_opsi_bayar);

        nama = getIntent().getStringExtra("kategoriBayar");
        id = getIntent().getStringExtra("idKategori");

        from_activity = getIntent().getStringExtra("from_activity");

        if (from_activity!=null){
            if (from_activity.equals("checkout_konsultasi")){
                id_transaksi = getIntent().getStringExtra("id_transaksi");
                biaya = Integer.parseInt(getIntent().getStringExtra("biaya"));
                id_dokter = getIntent().getStringExtra("id_dokter");
                jadwal_dokter = getIntent().getStringExtra("jadwal_dokter");
                status = getIntent().getStringExtra("status");
                external_id = getIntent().getStringExtra("external_id");
                token_dokter = getIntent().getStringExtra("token_dokter");
                nama_dokter = getIntent().getStringExtra("nama_dokter");
                player_id_dokter = getIntent().getStringExtra("player_id_dokter");
                buat_jadwal = getIntent().getStringExtra("buat_jadwal");
                image_dokter = getIntent().getStringExtra("image_dokter");
                spesialis_dokter = getIntent().getStringExtra(spesialis_dokter);
            }else{
                btnBayarKonsultasi.setVisibility(View.GONE);
            }
        }

        PushDownAnim.setPushDownAnimTo(btnBayarKonsultasi)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkOut();
                    }
                });


        Log.d("checkIdopsi", "onCreate: "+idOpsi);

        txtNamaKategori.setText(nama);

        loadBiayaAdmin();

        loadOpsiBayar();
    }

    private void loadOpsiBayar() {

        ConfigRetrofit.service.opsiBayar(id).enqueue(new Callback<ResponseOpsiBayar>() {
            @Override
            public void onResponse(Call<ResponseOpsiBayar> call, Response<ResponseOpsiBayar> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataOpsi = response.body().getData();
                    OpsiBayarAdapter opsiBayarAdapter = new OpsiBayarAdapter(OpsiBayarActivity.this, dataOpsi, OpsiBayarActivity.this);
                    rvOpsibayar.setHasFixedSize(true);
                    rvOpsibayar.setLayoutManager(new LinearLayoutManager(OpsiBayarActivity.this));
                    rvOpsibayar.setAdapter(opsiBayarAdapter);

                }else{
                    Toast.makeText(OpsiBayarActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseOpsiBayar> call, Throwable t) {
                Toast.makeText(OpsiBayarActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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

        ProgressDialog progressDialog = new ProgressDialog(OpsiBayarActivity.this);
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

                    Toast.makeText(OpsiBayarActivity.this, "Berhasil Checkout", Toast.LENGTH_SHORT).show();
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
                    Intent intent = new Intent(OpsiBayarActivity.this, InvoiceKonsultasiActivity.class);
                    intent.putExtra("id_transaksi", id_transaksi);
                    intent.putExtra("mobile_web", mobileUrl);
                    intent.putExtra("mobile_deeplink", deeplink);
                    intent.putExtra("konsultasi", "konsultasi");
                    intent.putExtra("status", status);
                    intent.putExtra("token_dokter", token_dokter);
                    intent.putExtra("nama_dokter", nama_dokter);
                    intent.putExtra("player_id", player_id_dokter);
                    intent.putExtra("id_dokter", id_dokter);
                    intent.putExtra("img", image_dokter);
                    intent.putExtra("spesialis", spesialis_dokter);
                    startActivity(intent);
                    finish();

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(OpsiBayarActivity.this, "Gagal Checkout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEwalletKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(OpsiBayarActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tampilDialogNomor() {
        Dialog dialog = new Dialog(OpsiBayarActivity.this);
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

        android.app.ProgressDialog progressDialogOvo = new ProgressDialog(OpsiBayarActivity.this);
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

                    Toast.makeText(OpsiBayarActivity.this, "Berhasil Checkout", Toast.LENGTH_SHORT).show();
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");
                    Intent intent = new Intent(OpsiBayarActivity.this, InvoiceKonsultasiActivity.class);
                    intent.putExtra("id_transaksi", id_transaksi);
                    intent.putExtra("konsultasi", "konsultasi");
                    intent.putExtra("token_dokter", token_dokter);
                    intent.putExtra("nama_dokter", nama_dokter);
                    intent.putExtra("player_id", player_id_dokter);
                    intent.putExtra("id_dokter", id_dokter);
                    intent.putExtra("img", image_dokter);
                    intent.putExtra("spesialis", spesialis_dokter);
                    startActivity(intent);
                    finish();
                }else{
                    progressDialogOvo.dismiss();
                    Toast.makeText(OpsiBayarActivity.this, "Gagal checkout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEwalletKonsultasi> call, Throwable t) {
                progressDialogOvo.dismiss();
                Toast.makeText(OpsiBayarActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loadBiayaAdmin() {

        String totalPost = String.valueOf(biaya);

        ConfigRetrofit.service.biayaAdmin(preferencedConfig.getPreferenceIdOpsiBayar(), totalPost).enqueue(new Callback<ResponseBiayaAdmin>() {
            @Override
            public void onResponse(Call<ResponseBiayaAdmin> call, Response<ResponseBiayaAdmin> response) {
                if (response.isSuccessful()){

                    biaya_admin = response.body().getBiayaAdmin();
                }else{
                    Toast.makeText(OpsiBayarActivity.this, "Gagal mengambil biaya admin", Toast.LENGTH_SHORT).show();
                }

                total_bayar = biaya + biaya_admin;

            }

            @Override
            public void onFailure(Call<ResponseBiayaAdmin> call, Throwable t) {
                Toast.makeText(OpsiBayarActivity.this, "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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

        ProgressDialog progressDialog = new ProgressDialog(OpsiBayarActivity.this);
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

                    Toast.makeText(OpsiBayarActivity.this, "Berhasil checkout Qris", Toast.LENGTH_SHORT).show();
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, "");
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, "");

                    Log.d("checkQrString", "onResponse: "+qr_string);
                    String id_transaksi_intent = response.body().getMetadata().getIdTransaksi();

                    Intent intent = new Intent(OpsiBayarActivity.this, InvoiceKonsultasiActivity.class);
                    intent.putExtra("qr_string", qr_string);
                    intent.putExtra("id_transaksi", id_transaksi_intent);
                    intent.putExtra("status", status);
                    intent.putExtra("konsultasi", "konsultasi");
                    intent.putExtra("token_dokter", token_dokter);
                    intent.putExtra("nama_dokter", nama_dokter);
                    intent.putExtra("player_id", player_id_dokter);
                    intent.putExtra("id_dokter", id_dokter);
                    intent.putExtra("QRIS", "qris");
                    intent.putExtra("buat_jadwal", buat_jadwal);
                    intent.putExtra("img", image_dokter);
                    intent.putExtra("spesialis", spesialis_dokter);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
                    Toast.makeText(OpsiBayarActivity.this, "Gagal checkout", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseQrisKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(OpsiBayarActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}