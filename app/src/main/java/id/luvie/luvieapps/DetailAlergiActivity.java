package id.luvie.luvieapps;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.api.ConfigRetrofit;

import id.luvie.luvieapps.databinding.ActivityDetailAlergiBinding;
import id.luvie.luvieapps.model.detailAlergiObat.DataItem;
import id.luvie.luvieapps.model.detailAlergiObat.ResponseDetailAlergiObat;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlergiActivity extends AppCompatActivity {

    private ActivityDetailAlergiBinding binding;

    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailAlergiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id = getIntent().getStringExtra("id");

        loadDataDetail();

        PushDownAnim.setPushDownAnimTo(binding.imgBackDetailAlergi)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.btnCopyDetailAlergi)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("text",
                                binding.textNamaLabel.getText()+"\n"+binding.textNamaObat.getText()+"\n\n"+
                                binding.textMerekLabel.getText()+"\n"+binding.textMerekObat.getText()+"\n\n"+
                                binding.textGolonganLabel.getText()+"\n"+binding.textGolonganObat.getText()+"\n\n"+
                                binding.textEfekLabel.getText()+"\n"+binding.textEfekObat.getText());
                        manager.setPrimaryClip(clipData);
                        Toast.makeText(DetailAlergiActivity.this, "Berhasil Copy Ke Cliboard", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void loadDataDetail() {

        ProgressDialog progressDialog = new ProgressDialog(DetailAlergiActivity.this);
        progressDialog.setMessage("Memuat Data Detail");
        progressDialog.show();

        ConfigRetrofit.service.detailAlergiObat(id).enqueue(new Callback<ResponseDetailAlergiObat>() {
            @Override
            public void onResponse(Call<ResponseDetailAlergiObat> call, Response<ResponseDetailAlergiObat> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();

                    List<DataItem> detailAlergi = response.body().getData();

                    if (detailAlergi!=null || detailAlergi.size()>0){

                        String nama_obat ="", merek_obat = "", golongan_obat = "", efek_obat = "";

                        for (int a = 0; a<detailAlergi.size(); a++){

                            nama_obat = detailAlergi.get(a).getObat();
                            merek_obat = detailAlergi.get(a).getMerek();
                            golongan_obat = detailAlergi.get(a).getGolongan();
                            efek_obat = detailAlergi.get(a).getEfek();

                        }

                        binding.textNamaObat.setText(nama_obat);
                        binding.textMerekObat.setText(merek_obat);
                        binding.textGolonganObat.setText(golongan_obat);
                        binding.textEfekObat.setText(efek_obat);
                    }else{
                        Toast.makeText(DetailAlergiActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(DetailAlergiActivity.this, "Response Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailAlergiObat> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailAlergiActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}