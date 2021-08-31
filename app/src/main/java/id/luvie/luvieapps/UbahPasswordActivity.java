package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.databinding.ActivityUbahPasswordBinding;
import id.luvie.luvieapps.model.ubahPassword.ResponseUbahPassword;
import com.thekhaeng.pushdownanim.PushDownAnim;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class UbahPasswordActivity extends AppCompatActivity {

    private ActivityUbahPasswordBinding binding;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUbahPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(this);

        showHidePass1(binding.imgHidePassword1);
        showHidePass2(binding.imgHidePassword2);

        binding.edtPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String passwordCheck1 = binding.edtPassword1.getText().toString();
                String passwordCheck2 = binding.edtPassword2.getText().toString();


                if (passwordCheck2.equals(passwordCheck1)){
                    binding.txtPasswordSesuai.setVisibility(View.VISIBLE);
                    binding.txtPasswordTidakSesuai.setVisibility(View.GONE);
                }else{
                    binding.txtPasswordTidakSesuai.setVisibility(View.VISIBLE);
                    binding.txtPasswordSesuai.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        PushDownAnim.setPushDownAnimTo(binding.btnUbahPassword)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ubahPassword();
                    }
                });

        PushDownAnim.setPushDownAnimTo(binding.imgBackUbahPassword)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

    }

    private void ubahPassword() {

        String password1 = binding.edtPassword1.getText().toString();
        String password2 = binding.edtPassword2.getText().toString();

        if (password1.equals("")){
            binding.edtPassword1.setError("Password Tidak boleh kosong");
            binding.edtPassword1.requestFocus();
            return;
        }

        if (password2.equals("")){
            binding.edtPassword2.setError("Ulangi Password Tidak boleh kosong");
            binding.edtPassword2.requestFocus();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(UbahPasswordActivity.this);
        progressDialog.setMessage("Mengubah Password");
        progressDialog.show();

        ConfigRetrofit.service.ubahPassword(preferencedConfig.getPreferenceIdCustomer(), password1, password2)
                .enqueue(new Callback<ResponseUbahPassword>() {
                    @Override
                    public void onResponse(Call<ResponseUbahPassword> call, Response<ResponseUbahPassword> response) {
                        if (response.isSuccessful()){

                            progressDialog.dismiss();
                            String status = response.body().getResponse();

                            if (status.equals("Berhasil update")) {

                                Toast.makeText(UbahPasswordActivity.this, "Berhasil Update password", Toast.LENGTH_SHORT).show();
                                finish();

                            }else{
                                Toast.makeText(UbahPasswordActivity.this, "Gagal Update Password", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(UbahPasswordActivity.this, "Response Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUbahPassword> call, Throwable t) {

                        progressDialog.dismiss();
                        Toast.makeText(UbahPasswordActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void showHidePass1(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.getId()== R.id.img_hide_password1){

                    if(binding.edtPassword1.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye);
                        //Show Password
                        binding.edtPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye_slash);

                        //Hide Password
                        binding.edtPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }

            }
        });

    }

    private void showHidePass2(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.getId()==R.id.img_hide_password2){

                    if(binding.edtPassword2.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye);
                        //Show Password
                        binding.edtPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye_slash);

                        //Hide Password
                        binding.edtPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }

            }
        });

    }
}