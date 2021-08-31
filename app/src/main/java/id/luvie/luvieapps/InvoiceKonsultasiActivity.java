package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.InvoiceKonsultasiAdapter;
import id.luvie.luvieapps.adapter.InvoiceKonsultasiQrAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.batalkanKonsultasi.ResponseBatalkanKonsultasi;
import id.luvie.luvieapps.model.invoiceKonsultasi.DataItem;
import id.luvie.luvieapps.model.invoiceKonsultasi.ResponseInvoiceKonsultasi;
import id.luvie.luvieapps.model.invoiceKonsultasiQR.ResponseInvoiceKonsultasiQr;
import id.luvie.luvieapps.model.notifChat.ResponseNotif;
import com.mesibo.api.Mesibo;
import com.mesibo.api.MesiboProfile;
import com.mesibo.calls.api.MesiboCall;
import com.mesibo.messaging.MesiboUI;

import net.glxn.qrgen.android.QRCode;

import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceKonsultasiActivity extends AppCompatActivity implements Mesibo.ConnectionListener, Mesibo.MessageListener {

    ImageView imgShopee, imgDana, imgOvo, imgLinkAja, imgQris, imgQrCode;
    RecyclerView rvInvoice;
    Button btnSelesaikanPembayaran, btnPembayaranSelesai, btnBayarNanti;

    String opsi_bayar = "";

    String id_transaksi, mobile_web, mobile_deeplink, qr_string, status, token_dokter, nama_dokter, player_id_dokter;
    String id_dokter, qris, buat_jadwal, img_dokter, spesialis_dokter, id_transaksi_intent;
    public String cekKonsultasi;
    private SharedPreferencedConfig preferencedConfig;

    class DemoUser {
        public String token;
        public String address;

        DemoUser(String token, String address) {
            this.token = token;
            this.address = address;
        }
    }

    DemoUser mUser1;
    DemoUser mUser2;

    DemoUser mRemoteUser;
    MesiboProfile mProfile;
    Mesibo.ReadDbSession mReadSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_konsultasi);

        preferencedConfig = new SharedPreferencedConfig(this);



        imgDana = findViewById(R.id.img_dana_invoice_konsultasi);
        imgShopee = findViewById(R.id.img_shopeepay_invoice_konsultasi);
        imgOvo = findViewById(R.id.img_ovo_invoice_konsultasi);
        imgLinkAja = findViewById(R.id.img_linkaja_invoice_konsultasi);
        rvInvoice = findViewById(R.id.recycler_invoice_konsultasi);
        btnSelesaikanPembayaran = findViewById(R.id.btn_selesaikan_pembayaran_invoice_konsultasi);
        imgQris = findViewById(R.id.img_qris_invoice_konsultasi);
        btnPembayaranSelesai = findViewById(R.id.btn_pembayaran_selesai_invoice_konsultasi);
        imgQrCode = findViewById(R.id.img_qrcode_invoice_konsultasi);

        id_transaksi = getIntent().getStringExtra("id_transaksi");
        id_transaksi_intent = getIntent().getStringExtra("id_transaksi_intent");
        Log.d("id_transaksi_invoice", "onCreate: "+id_transaksi_intent);
        mobile_web = getIntent().getStringExtra("mobile_web");
        mobile_deeplink = getIntent().getStringExtra("mobile_deeplink");
        qr_string = getIntent().getStringExtra("qr_string");
        status = getIntent().getStringExtra("status");
        cekKonsultasi = getIntent().getStringExtra("konsultasi");
        player_id_dokter = getIntent().getStringExtra("player_id");
        id_dokter = getIntent().getStringExtra("id_dokter");
        qris = getIntent().getStringExtra("QRIS");
        buat_jadwal = getIntent().getStringExtra("buat_jadwal");
        img_dokter = getIntent().getStringExtra("img");
        spesialis_dokter = getIntent().getStringExtra("spesialis");

        Log.d("statusInvoice", "onCreate: "+status);
        Log.d("QrString", "onCreate: "+qr_string);

        token_dokter = getIntent().getStringExtra("token_dokter");
        nama_dokter = getIntent().getStringExtra("nama_dokter");

        mUser1 = new DemoUser(preferencedConfig.getPreferenceToken(), preferencedConfig.getPreferenceNama());
        mUser2 = new DemoUser(token_dokter, nama_dokter);

        mesiboInit(mUser1, mUser2);

        if (status.equals("2")){



        }

        btnSelesaikanPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl(opsi_bayar);
            }
        });

        btnPembayaranSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_DOKTER, id_dokter);
                Toast.makeText(InvoiceKonsultasiActivity.this,
                        "Pembayaran Berhasil, Silahkan mulai chat dengan dokter / Cek jadwal konsultasi", Toast.LENGTH_SHORT).show();

                if (buat_jadwal != null){
                    if (buat_jadwal.equals("buatJadwal")){

                        startActivity(new Intent(InvoiceKonsultasiActivity.this, MainActivity.class));
                        finish();

                    }
                }else{
//                    MesiboUI.launchMessageView(InvoiceKonsultasiActivity.this, mProfile.address, 0);
                    Intent intentChat = new Intent(InvoiceKonsultasiActivity.this, ChatActivity.class);
                    intentChat.putExtra("token", token_dokter);
                    intentChat.putExtra("nama_dokter", nama_dokter);
                    intentChat.putExtra("image", img_dokter);
                    intentChat.putExtra("spesialis", spesialis_dokter);
                    intentChat.putExtra("id_dokter", id_dokter);
                    intentChat.putExtra("status_konsultasi", status);
                    intentChat.putExtra("player_id", player_id_dokter);
                    intentChat.putExtra("id_transaksi", id_transaksi);
                    Log.d("cekIdTransaksi", "onClick: "+id_transaksi);
                    intentChat.putExtra("from_activity", "invoice");
                    startActivity(intentChat);
                    finish();
                }
            }
        });

        if (qris!=null) {
            if (qris.equals("qris")) {
                loadInvoiceQr();
            }
        }

        loadDataInvoice();
    }

    private void loadInvoiceQr() {

        ProgressDialog progressDialog = new ProgressDialog(InvoiceKonsultasiActivity.this);
        progressDialog.setMessage("Load Data invoice");
        progressDialog.show();

        Log.d("checkIdTransaksi", "loadDataInvoice: "+id_transaksi);

        ConfigRetrofit.service.invoiceKonsultasiQr(id_transaksi).enqueue(new Callback<ResponseInvoiceKonsultasiQr>() {
            @Override
            public void onResponse(Call<ResponseInvoiceKonsultasiQr> call, Response<ResponseInvoiceKonsultasiQr> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    List<id.luvie.luvieapps.model.invoiceKonsultasiQR.DataItem> dataInvoiceQr = response.body().getData();

                    for (int i = 0; i<dataInvoiceQr.size(); i++){

                        opsi_bayar = dataInvoiceQr.get(i).getOpsiBayar();

                    }

                    Log.d("checkOpsiBayar", "onResponse: "+opsi_bayar);

                    if (opsi_bayar.equals("QRIS") || qris.equals("qris")){
                        imgQris.setVisibility(View.VISIBLE);
                        btnSelesaikanPembayaran.setVisibility(View.GONE);
                        btnPembayaranSelesai.setVisibility(View.VISIBLE);
                        imgQrCode.setVisibility(View.VISIBLE);
                        Bitmap myBitmap = QRCode.from(qr_string).bitmap();
                        imgQrCode.setImageBitmap(myBitmap);
                    }

                    InvoiceKonsultasiQrAdapter adapter = new InvoiceKonsultasiQrAdapter(InvoiceKonsultasiActivity.this, dataInvoiceQr, InvoiceKonsultasiActivity.this);
                    rvInvoice.setHasFixedSize(true);
                    rvInvoice.setLayoutManager(new LinearLayoutManager(InvoiceKonsultasiActivity.this));
                    rvInvoice.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(InvoiceKonsultasiActivity.this, "Gagal Memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInvoiceKonsultasiQr> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(InvoiceKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void mesiboInit(DemoUser mUser1, DemoUser mUser2) {

        Mesibo api = Mesibo.getInstance();
        api.init(getApplicationContext());

        Mesibo.addListener(this);
        Mesibo.setSecureConnection(true);
        Mesibo.setAccessToken(mUser1.token);
        Mesibo.setDatabase("mydb", 0);
        Mesibo.start();

        mRemoteUser = mUser2;
        mProfile = Mesibo.getProfile(mUser2.address);

        MesiboCall mesiboCall = MesiboCall.getInstance();
        mesiboCall.init(this);


        // Read receipts are enabled only when App is set to be in foreground
        Mesibo.setAppInForeground(this, 0, true);
        mReadSession = new Mesibo.ReadDbSession(mRemoteUser.address, this);
        mReadSession.enableReadReceipt(true);
        mReadSession.read(100);

    }

    private void tampilDialog() {

        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Bayar nanti?")
                .setMessage("Silahkan buka menu history konsultasi di profile untuk melakukan pembayaran")
                .setCancelable(false)
                .setPositiveButton("Buka history konsultasi", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //BATALKAN PESANAN METHO
                        startActivity(new Intent(InvoiceKonsultasiActivity.this, HistoryKonsultasiActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Kembali ke home", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        startActivity(new Intent(InvoiceKonsultasiActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();

    }

    private void loadDataInvoice() {

        ProgressDialog progressDialog = new ProgressDialog(InvoiceKonsultasiActivity.this);
        progressDialog.setMessage("Load Data invoice");
        progressDialog.show();

        Log.d("checkIdTransaksi", "loadDataInvoice: "+id_transaksi);

        ConfigRetrofit.service.invoiceKonsultasi(id_transaksi).enqueue(new Callback<ResponseInvoiceKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseInvoiceKonsultasi> call, Response<ResponseInvoiceKonsultasi> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    List<DataItem> dataInvoice = response.body().getData();

                    for (int i = 0; i<dataInvoice.size(); i++){

                        opsi_bayar = dataInvoice.get(i).getOpsiBayar();

                    }

                    Log.d("checkOpsiBayar", "onResponse: "+opsi_bayar);

                    if (opsi_bayar.equals("DANA")){
                        imgDana.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("LinkAja")){
                        imgLinkAja.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("OVO")){
                        imgOvo.setVisibility(View.VISIBLE);
                        btnSelesaikanPembayaran.setVisibility(View.GONE);
                        btnPembayaranSelesai.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("ShopeePay")){
                        imgShopee.setVisibility(View.VISIBLE);
                    }else if (opsi_bayar.equals("QRIS")){
                        imgQris.setVisibility(View.VISIBLE);
                        btnSelesaikanPembayaran.setVisibility(View.GONE);
                        btnPembayaranSelesai.setVisibility(View.VISIBLE);
                        imgQrCode.setVisibility(View.VISIBLE);
                        Bitmap myBitmap = QRCode.from(qr_string).bitmap();
                        imgQrCode.setImageBitmap(myBitmap);
                    }

                    InvoiceKonsultasiAdapter adapter = new InvoiceKonsultasiAdapter(InvoiceKonsultasiActivity.this, dataInvoice, InvoiceKonsultasiActivity.this);
                    rvInvoice.setHasFixedSize(true);
                    rvInvoice.setLayoutManager(new LinearLayoutManager(InvoiceKonsultasiActivity.this));
                    rvInvoice.setAdapter(adapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(InvoiceKonsultasiActivity.this, "Gagal Memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInvoiceKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(InvoiceKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getUrl(String opsi_bayar) {

        String url = "";

        if (opsi_bayar.equals("DANA") || opsi_bayar.equals("LinkAja")){
            url = mobile_web;
        }else if (opsi_bayar.equals("ShopeePay")){
            url = mobile_deeplink;
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        if (status.equals("1")) {
            MaterialDialog mDialog = new MaterialDialog.Builder(this)
                    .setTitle("Anda Yakin ingin keluar dari halaman pembayaran?")
                    .setMessage("Konsultasi anda akan dibatalkan, jika anda keluar dari halaman pembayaran")
                    .setCancelable(false)
                    .setPositiveButton("Iya", new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            InvoiceKonsultasiActivity.super.onBackPressed();
                            //BATALKAN PESANAN METHO
                            cancelPayment();
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

        }else{
            InvoiceKonsultasiActivity.super.onBackPressed();
            //BATALKAN PESANAN METHO
            startActivity(new Intent(InvoiceKonsultasiActivity.this, MainActivity.class));
            finish();
        }
    }

    private void cancelPayment() {

        ConfigRetrofit.service.batalkanKonsultasi(id_transaksi).enqueue(new Callback<ResponseBatalkanKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseBatalkanKonsultasi> call, Response<ResponseBatalkanKonsultasi> response) {
                if (response.isSuccessful()){

                    Toast.makeText(InvoiceKonsultasiActivity.this, "Berhasil batalkan transaksi", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(InvoiceKonsultasiActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(InvoiceKonsultasiActivity.this, "Gagal batalkan transaksi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBatalkanKonsultasi> call, Throwable t) {
                Toast.makeText(InvoiceKonsultasiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void Mesibo_onConnectionStatus(int i) {

        if (i == 1){

            Log.d("cekKoneksiStatus", "Mesibo_onConnectionStatus: ONLINE");

        }else{
            Log.d("cekKoneksiStatus", "Mesibo_onConnectionStatus: OFFLINE/GAGAL MENGHUBUNGKAN");
        }

    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] bytes) {
        return false;
    }

    @Override
    public void Mesibo_onMessageStatus(Mesibo.MessageParams messageParams) {
        int status = messageParams.getStatus();
        long id = messageParams.mid;
        Log.d("cekStatus", "id: " + id);
        Log.d("cekStatus", "Mesibo_onMessageStatus: " + status);
        Log.d("cekStatus", "messageParam: " + messageParams.isSavedMessage());

        if (status == 1 && id != 0) {
            pushNotification();
        }
    }

    @Override
    public void Mesibo_onActivity(Mesibo.MessageParams messageParams, int i) {

    }

    @Override
    public void Mesibo_onLocation(Mesibo.MessageParams messageParams, Mesibo.Location location) {

    }

    @Override
    public void Mesibo_onFile(Mesibo.MessageParams messageParams, Mesibo.FileInfo fileInfo) {

    }

    private void pushNotification() {

        ConfigRetrofit.service.notifChat(player_id_dokter).enqueue(new Callback<ResponseNotif>() {
            @Override
            public void onResponse(Call<ResponseNotif> call, Response<ResponseNotif> response) {
                if (response.isSuccessful()) {

                    Log.d("statusNotifChat", "onResponse: " + "Berhasil Push Notification");
                    Log.d("checkPlayerId", "onResponse: " + player_id_dokter);


                } else {
                    Log.d("statusNotifChat", "onResponse: " + "Gagal Push Notification");
                }
            }

            @Override
            public void onFailure(Call<ResponseNotif> call, Throwable t) {
                Toast.makeText(InvoiceKonsultasiActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}