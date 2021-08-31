package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityLupaPasswordBinding;
import id.luvie.luvieapps.model.lupaPassword.ResponseLupaPassword;
import com.thekhaeng.pushdownanim.PushDownAnim;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class LupaPasswordActivity extends AppCompatActivity {

    private ActivityLupaPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLupaPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        PushDownAnim.setPushDownAnimTo(binding.imgBackLupaPassword)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.btnLupaPassword)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lupaPassword();
                    }
                });

    }

    private void lupaPassword() {

        String email = binding.edtEmailLupaPassword.getText().toString();

        if (email.isEmpty()){

            binding.edtEmailLupaPassword.setError("Email tidak boleh kosong");
            binding.edtEmailLupaPassword.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.edtEmailLupaPassword.setError("Masukan alamat email yang valid");
            binding.edtEmailLupaPassword.requestFocus();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(LupaPasswordActivity.this);
        progressDialog.setMessage("proses kirim email");
        progressDialog.show();

        ConfigRetrofit.service.lupaPassword(email).enqueue(new Callback<ResponseLupaPassword>() {
            @Override
            public void onResponse(Call<ResponseLupaPassword> call, Response<ResponseLupaPassword> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    if (response.body()!=null){

                        String responseEmail = response.body().getResponse();

                        if (responseEmail.equals("Email terkirim")){

                            tampilDialog();

                        }else{
                            Toast.makeText(LupaPasswordActivity.this, "Gagal mengirim email",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(LupaPasswordActivity.this, "Response Null", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(LupaPasswordActivity.this, "Gagal Mengambil response dari server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLupaPassword> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LupaPasswordActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void tampilDialog() {

        Dialog dialog = new Dialog(LupaPasswordActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_lupa_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button btnSelesai = dialog.findViewById(R.id.btn_selesai_lupa_password);

        PushDownAnim.setPushDownAnimTo(btnSelesai)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });

    }
}