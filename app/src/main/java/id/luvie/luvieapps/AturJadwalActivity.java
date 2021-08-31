package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.adapter.DokterAturJadwalAdapter;
import id.luvie.luvieapps.adapter.JamJadwalAdapter;
import id.luvie.luvieapps.adapter.WaktuAturJadwalAdapter;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.jadwalDokter.ListItem;
import id.luvie.luvieapps.model.jadwalDokter.ResponseJadwalDokter;
import id.luvie.luvieapps.model.jamDokter.ResponseJamDokter;
import id.luvie.luvieapps.model.konsultasi.ResponseKonsultasi;

import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class AturJadwalActivity extends AppCompatActivity {

    RecyclerView rvDokter, rvJadwal, rvJamJadwal;
    TextView txtKonsultasiDengan;
    ImageView imgBack, imgDown, imgUp;
    Button btnBuatJadwal, btnKonsultasiSekarang;

    CardView cardPilihJam, cardJamJadwal;

    String id_dokter, nama_dokter, biaya;
    public String tanggal, formatTanggal;
    private SharedPreferencedConfig preferencedConfig;

    String token, token_from, user_id, user_id_from;


    public AturJadwalActivity(){

    }

    public String jadwal_dokter = "";
    public String id_jadwal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_jadwal);

        id_dokter = getIntent().getStringExtra("id_dokter");
        preferencedConfig = new SharedPreferencedConfig(AturJadwalActivity.this);

        rvDokter = findViewById(R.id.recycler_dokter_atur_jadwal);
        rvJadwal = findViewById(R.id.recycler_jadwal_konsultasi_atur_jadwal);
        imgBack = findViewById(R.id.img_back_atur_jadwal);
        btnBuatJadwal = findViewById(R.id.btn_buat_jadwal);
        rvJamJadwal = findViewById(R.id.rv_jam_jadwal);
        imgDown = findViewById(R.id.img_ic_down);
        imgUp = findViewById(R.id.img_ic_up);
        cardPilihJam = findViewById(R.id.card_pilih_jam);
        cardJamJadwal = findViewById(R.id.card_jam_jadwal);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rvDokter.setHasFixedSize(true);
        rvDokter.setLayoutManager(new LinearLayoutManager(AturJadwalActivity.this));

        rvJadwal.setHasFixedSize(true);
        rvJadwal.setLayoutManager(new LinearLayoutManager(AturJadwalActivity.this, LinearLayoutManager.HORIZONTAL, false));

        rvJamJadwal.setHasFixedSize(true);
        rvJamJadwal.setLayoutManager(new LinearLayoutManager(AturJadwalActivity.this, LinearLayoutManager.HORIZONTAL, false));

        PushDownAnim.setPushDownAnimTo(imgDown)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardJamJadwal.setVisibility(View.VISIBLE);
                        imgUp.setVisibility(View.VISIBLE);
                        imgDown.setVisibility(View.GONE);
                    }
                });

        PushDownAnim.setPushDownAnimTo(imgUp)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardJamJadwal.setVisibility(View.GONE);
                        imgUp.setVisibility(View.GONE);
                        imgDown.setVisibility(View.VISIBLE);
                    }
                });

        btnBuatJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("checkPostJadwal", "onClick: "+id_jadwal);
                Log.d("checkPostJadwal", "format_tanggal: "+formatTanggal);

                if (id_jadwal.equals("")){
                    Toast.makeText(AturJadwalActivity.this, "Anda belum memilih jadwal dokter", Toast.LENGTH_SHORT).show();
                }else {

                    postKonsultasi();

                }
            }
        });

        loadDokter();
        loadJadwal();
        loadJamDokter();
    }

    public void loadJamDokter() {

        ProgressDialog progressDialog = new ProgressDialog(AturJadwalActivity.this);
        progressDialog.setMessage("Memuat Data Jam Jadwal");
        progressDialog.show();

        ConfigRetrofit.service.jamDokter(id_dokter, tanggal).enqueue(new Callback<ResponseJamDokter>() {
            @Override
            public void onResponse(Call<ResponseJamDokter> call, Response<ResponseJamDokter> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<id.luvie.luvieapps.model.jamDokter.DataItem> dataJam = response.body().getData();

                    JamJadwalAdapter jamAdapter = new JamJadwalAdapter(AturJadwalActivity.this, dataJam, AturJadwalActivity.this);
                    rvJamJadwal.setAdapter(jamAdapter);

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(AturJadwalActivity.this, "Gagal Memuat Data Jam", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJamDokter> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AturJadwalActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void postKonsultasi() {

        String id_customer = preferencedConfig.getPreferenceIdCustomer();

        ProgressDialog progressDialog = new ProgressDialog(AturJadwalActivity.this);
        progressDialog.setMessage("Mengajukan Permintaan Konsultasi");
        progressDialog.show();

        Log.d("checkParamBuatJadwal", "id_customer: "+id_customer);
        Log.d("checkParamBuatJadwal", "id_jadwal: "+id_jadwal);
        Log.d("checkParamBuatJadwal", "id_dokter: "+id_dokter);
        Log.d("checkParamBuatJadwal", "format_tanggal: "+formatTanggal);

        ConfigRetrofit.service.postKonsultasi(id_customer, id_jadwal, id_dokter, formatTanggal, "2").enqueue(new Callback<ResponseKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseKonsultasi> call, Response<ResponseKonsultasi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(AturJadwalActivity.this, "Berhasil mengajukan konsultasi", Toast.LENGTH_SHORT).show();

                    String id_transaksi = response.body().getIdTransaksi();
                    String external_id = response.body().getExternalId();

                    Intent intent = new Intent(AturJadwalActivity.this, CheckoutKonsultasiActivity.class);
                    intent.putExtra("id_dokter", id_dokter);
                    intent.putExtra("id_transaksi", id_transaksi);
                    intent.putExtra("jadwal_dokter", formatTanggal);
                    intent.putExtra("id_jadwal", id_jadwal);
                    intent.putExtra("biaya", biaya);
                    intent.putExtra("status", "2");
                    intent.putExtra("external_id", external_id);
                    intent.putExtra("buatJadwal", "buatJadwal");
                    startActivity(intent);
//                    tampilDialog();
                }else{
                    progressDialog.dismiss();
                    Log.d("checkParamKonsultasi", "id_customer: "+id_customer);
                    Log.d("checkParamKonsultasi", "id_jadwal: "+id_jadwal);
                    Log.d("checkParamKonsultasi", "id_dokter: "+id_dokter);
                    Log.d("checkParamKonsultasi", "jadwal_dokter: "+formatTanggal);
                    Toast.makeText(AturJadwalActivity.this, "Gagal mengajulan konsultasi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKonsultasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AturJadwalActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tampilDialog() {

        MaterialDialog mDialog = new MaterialDialog.Builder(this)
                .setTitle("Berhasil Mengajukan Konsultasi")
                .setMessage("Silahkan buka menu history konsultasi untuk melihat status konsultasi anda")
                .setCancelable(false)
                .setPositiveButton("Lihat menu konsultasi", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        startActivity(new Intent(AturJadwalActivity.this, HistoryKonsultasiActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Kembali ke menu utama", new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        startActivity(new Intent(AturJadwalActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .build();

        // Show Dialog
        mDialog.show();

    }

    private void loadJadwal() {

        ConfigRetrofit.service.jadwalDokter(id_dokter).enqueue(new Callback<ResponseJadwalDokter>() {
            @Override
            public void onResponse(Call<ResponseJadwalDokter> call, Response<ResponseJadwalDokter> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.jadwalDokter.DataItem> dataItems = response.body().getData();
                    List<ListItem> dataJadwal = null;

                    for (int a = 0; a<dataItems.size(); a++){

                        dataJadwal = dataItems.get(a).getList();

                    }

                    WaktuAturJadwalAdapter adapteJadwal = new WaktuAturJadwalAdapter(AturJadwalActivity.this, dataJadwal,
                            AturJadwalActivity.this);
                    rvJadwal.setAdapter(adapteJadwal);

                }else{
                    Toast.makeText(AturJadwalActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwalDokter> call, Throwable t) {
                Toast.makeText(AturJadwalActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadDokter() {

        ConfigRetrofit.service.jadwalDokter(id_dokter).enqueue(new Callback<ResponseJadwalDokter>() {
            @Override
            public void onResponse(Call<ResponseJadwalDokter> call, Response<ResponseJadwalDokter> response) {
                if (response.isSuccessful()){

                    List<id.luvie.luvieapps.model.jadwalDokter.DataItem> dataDokter = response.body().getData();

                    for (int i =0; i<dataDokter.size(); i++){

                        nama_dokter = dataDokter.get(i).getNama();
                        biaya = dataDokter.get(i).getBiaya();

                    }

                    DokterAturJadwalAdapter adapterDokter = new DokterAturJadwalAdapter(AturJadwalActivity.this, dataDokter);
                    rvDokter.setAdapter(adapterDokter);

                }else{
                    Toast.makeText(AturJadwalActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwalDokter> call, Throwable t) {
                Toast.makeText(AturJadwalActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}