package id.luvie.luvieapps;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.mesibo.api.Mesibo;
import com.mesibo.api.MesiboProfile;
import com.mesibo.calls.api.MesiboCall;
import com.mesibo.messaging.MesiboUI;
import com.thekhaeng.pushdownanim.PushDownAnim;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityAlergiObatBinding;
import id.luvie.luvieapps.databinding.ActivitySummaryBinding;
import id.luvie.luvieapps.model.summary.ResponseSummary;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummaryActivity extends AppCompatActivity implements Mesibo.ConnectionListener, Mesibo.MessageListener {

    private ActivitySummaryBinding binding;

    String id_transaksi, token, nama, jadwal;

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
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySummaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(SummaryActivity.this);

        id_transaksi = getIntent().getStringExtra("id_transaksi");
        token = getIntent().getStringExtra("token");
        nama = getIntent().getStringExtra("nama_dokter");
        jadwal = getIntent().getStringExtra("jadwal");

        binding.textTanggalKonsultasiSummary.setText(jadwal);
        binding.textDokterSummary.setText(nama);

        mUser1 = new DemoUser(preferencedConfig.getPreferenceToken(), preferencedConfig.getPreferenceNama());
        mUser2 = new DemoUser(token, nama);

        mesiboInit(mUser1, mUser2);

        PushDownAnim.setPushDownAnimTo(binding.btnLihatHistory)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MesiboUI.launchMessageView(SummaryActivity.this, mProfile.address, 0);
                    }
                });

        loadData();

    }

    private void mesiboInit(DemoUser user, DemoUser remoteUser) {
        Mesibo api = Mesibo.getInstance();
        api.init(getApplicationContext());

        Mesibo.addListener(this);
        Mesibo.setSecureConnection(true);
        Mesibo.setAccessToken(user.token);
        Mesibo.setDatabase("mydb", 0);
//        Mesibo.start();

        mRemoteUser = remoteUser;
        mProfile = Mesibo.getProfile(remoteUser.address);
//        mProfile.name = remoteUser.address;
//        Mesibo.setUserProfile(mProfile, false);

        // enable buttons

        MesiboCall mesiboCall = MesiboCall.getInstance();
        mesiboCall.init(this);


        // Read receipts are enabled only when App is set to be in foreground
        Mesibo.setAppInForeground(this, 0, true);
        mReadSession = new Mesibo.ReadDbSession(mRemoteUser.address, this);
        mReadSession.enableReadReceipt(true);
        mReadSession.read(100);

    }

    private void loadData() {

        ProgressDialog progressDialog = new ProgressDialog(SummaryActivity.this);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        ConfigRetrofit.service.dataSummary(id_transaksi).enqueue(new Callback<ResponseSummary>() {
            @Override
            public void onResponse(Call<ResponseSummary> call, Response<ResponseSummary> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    String tanggal_konsultasi = response.body().getMulaiKonsultasi();
                    String nama_customer = response.body().getNama();
                    String id_transaksi = response.body().getIdTransaksi();
                    String nama_dokter = response.body().getNamaDokter();
                    String spesialis = response.body().getSpesialis();
                    String img = response.body().getImg();
                    String akhir_sesi = response.body().getBatasKonsultasi();
                    String rilis_resep = response.body().getRilisResep();
                    String diagnosa = response.body().getDiagnosis();
                    String catatan = response.body().getCatatan();

                    Glide.with(SummaryActivity.this)
                            .load(img)
                            .into(binding.imgDokterSummary);

                    binding.textTanggalHeaderSummary.setText(tanggal_konsultasi);
                    binding.textNamaCustomerSummary.setText(nama_customer);
                    binding.textIdTransaksiSummary.setText(id_transaksi);
                    binding.textTanggalKonsultasiSummary.setText(tanggal_konsultasi);
                    binding.textDokterSummary.setText(nama_dokter);
                    binding.textSpesialisSummary.setText(spesialis);
                    binding.textAkhirSesiSummary.setText(akhir_sesi);
                    binding.textRilisResepSummary.setText(rilis_resep);
                    binding.textDiagnosisSummary.setText(diagnosa);
                    binding.textCatatanSummary.setText(catatan);

                }
            }

            @Override
            public void onFailure(Call<ResponseSummary> call, Throwable t) {

            }
        });

    }

    @Override
    public void Mesibo_onConnectionStatus(int i) {

    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] bytes) {
        return false;
    }

    @Override
    public void Mesibo_onMessageStatus(Mesibo.MessageParams messageParams) {

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
}