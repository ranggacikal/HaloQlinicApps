package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import id.luvie.luvieapps.api.ConfigRetrofit;

import id.luvie.luvieapps.databinding.ActivityKebijakanPrivasiBinding;
import id.luvie.luvieapps.model.kebijakanPrivasi.DataItem;
import id.luvie.luvieapps.model.kebijakanPrivasi.ResponseKebijakanPrivasi;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class KebijakanPrivasiActivity extends AppCompatActivity {

    ActivityKebijakanPrivasiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKebijakanPrivasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getKebijakanPrivasi();

        PushDownAnim.setPushDownAnimTo(binding.imgBackKebijakanPrivasi)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

    }

    private void getKebijakanPrivasi() {

        ProgressDialog progressDialog = new ProgressDialog(KebijakanPrivasiActivity.this);
        progressDialog.setMessage("Memuat Kebijakan Privasi");
        progressDialog.show();

        ConfigRetrofit.service.getKebijakanPrivasi().enqueue(new Callback<ResponseKebijakanPrivasi>() {
            @Override
            public void onResponse(Call<ResponseKebijakanPrivasi> call, Response<ResponseKebijakanPrivasi> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    List<DataItem> dataKebijakan = response.body().getData();

                    if (dataKebijakan != null || dataKebijakan.size()>0){

                        String kebijakanPrivasi = "";

                        for (int a = 0; a<dataKebijakan.size(); a++){
                            kebijakanPrivasi = dataKebijakan.get(a).getTeks();
                        }

                        binding.txtKebijakanPrivasiDetail.setText(Html.fromHtml(kebijakanPrivasi));
                    }else{
                        Toast.makeText(KebijakanPrivasiActivity.this, "Data Kosong",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(KebijakanPrivasiActivity.this, "Gagal Memuat Data",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKebijakanPrivasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(KebijakanPrivasiActivity.this, "Error: "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}