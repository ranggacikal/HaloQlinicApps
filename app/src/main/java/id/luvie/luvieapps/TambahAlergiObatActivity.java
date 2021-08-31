package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityTambahAlergiObatBinding;
import id.luvie.luvieapps.model.tambahAlergiObat.ResponseTambahAlergiObat;
import com.thekhaeng.pushdownanim.PushDownAnim;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class TambahAlergiObatActivity extends AppCompatActivity {

    private ActivityTambahAlergiObatBinding binding;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahAlergiObatBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(this);

        PushDownAnim.setPushDownAnimTo(binding.btnTambahAlergiObat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tambahObat();
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.imgBackTambahAlergiObat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

    }

    private void tambahObat() {

        String nama_obat = binding.edtObatAlergi1.getText().toString();
        String merek_obat = binding.edtMerekObat.getText().toString();
        String golongan_obat = binding.edtGolonganObat.getText().toString();
        String efek_obat = binding.edtEfekObat.getText().toString();

        if (nama_obat.equals(" ")||nama_obat.isEmpty()){
            binding.edtObatAlergi1.setError("Nama Obat Tidak Boleh Kosong");
            binding.edtObatAlergi1.requestFocus();
            return;
        }

        if (merek_obat.equals(" ")||merek_obat.isEmpty()){
            binding.edtMerekObat.setError("Merek Obat Tidak Boleh Kosong");
            binding.edtMerekObat.requestFocus();
            return;
        }

        if (golongan_obat.equals(" ")||golongan_obat.isEmpty()){
            binding.edtGolonganObat.setError("Golongan Obat Tidak Boleh Kosong");
            binding.edtGolonganObat.requestFocus();
            return;
        }

        if (efek_obat.equals(" ")||efek_obat.isEmpty()){
            binding.edtEfekObat.setError("Efek Obat Tidak Boleh Kosong");
            binding.edtEfekObat.requestFocus();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(TambahAlergiObatActivity.this);
        progressDialog.setMessage("Menambahkan Data");
        progressDialog.show();

        ConfigRetrofit.service.tambahAlergiObat(preferencedConfig.getPreferenceIdCustomer(), nama_obat, merek_obat,
                golongan_obat, efek_obat)
                .enqueue(new Callback<ResponseTambahAlergiObat>() {
                    @Override
                    public void onResponse(Call<ResponseTambahAlergiObat> call, Response<ResponseTambahAlergiObat> response) {
                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            String responseData = response.body().getResponse();

                            if (responseData.equals("Data Berhasil Dikirim")){

                                Toast.makeText(TambahAlergiObatActivity.this, "Berhasil menambah data", Toast.LENGTH_SHORT).show();
                                binding.edtObatAlergi1.setText("");
                                binding.edtMerekObat.setText("");
                                binding.edtGolonganObat.setText("");
                                binding.edtEfekObat.setText("");
                                binding.edtObatAlergi1.requestFocus();

                            }else{
                                Toast.makeText(TambahAlergiObatActivity.this, "Gagal Menambah data", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(TambahAlergiObatActivity.this, "Response dari server error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseTambahAlergiObat> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(TambahAlergiObatActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}