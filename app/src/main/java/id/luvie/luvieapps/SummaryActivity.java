package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityAlergiObatBinding;
import id.luvie.luvieapps.databinding.ActivitySummaryBinding;
import id.luvie.luvieapps.model.summary.ResponseSummary;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummaryActivity extends AppCompatActivity {

    private ActivitySummaryBinding binding;

    String id_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySummaryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id_transaksi = getIntent().getStringExtra("id_transaksi");

        loadData();

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
}