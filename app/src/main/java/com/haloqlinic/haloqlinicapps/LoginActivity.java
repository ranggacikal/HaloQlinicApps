package com.haloqlinic.haloqlinicapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.loginapi.ResponseItem;
import com.haloqlinic.haloqlinicapps.model.loginapi.ResponseLoginUser;
import com.haloqlinic.haloqlinicapps.model.logingoogle.DataItem;
import com.haloqlinic.haloqlinicapps.model.logingoogle.ResponseLoginGoogle;
import com.haloqlinic.haloqlinicapps.model.loginmesibo.ResponseLoginMesibo;
import com.haloqlinic.haloqlinicapps.model.loginmesibo.UsersItem;
import com.onesignal.OSDeviceState;
import com.onesignal.OSPermissionObserver;
import com.onesignal.OSPermissionStateChanges;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnSignup;
    EditText edtEmail, edtPassword;
    Button btnLogin;

    ImageView showPassBtn, btnLoginGoogle;

    ProgressDialog progressDialog;

    private SharedPreferencedConfig preferencedConfig;

    String token, token_from, user_id, user_id_from;

    private static final String ONESIGNAL_APP_ID = "67314311-5f01-4b4e-b20c-1e0f6fb9958c";

    //google
    GoogleSignInClient googleSignInClient;
    private int RC_SIGN_IN = 0;

    //facebook
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencedConfig = new SharedPreferencedConfig(this);
        progressDialog = new ProgressDialog(LoginActivity.this);

        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_signin);
        edtEmail = findViewById(R.id.edt_login_email);
        edtPassword = findViewById(R.id.login_password);
        showPassBtn = findViewById(R.id.img_hide_password_login);
        btnLoginGoogle = findViewById(R.id.img_login_google);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        OSDeviceState device = OneSignal.getDeviceState();

        token = device.getPushToken();
        user_id = device.getUserId();

        Log.d("checkOneSignal", "token: "+token);
        Log.d("checkOneSignal", "user_id: "+user_id);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        showHidePass(showPassBtn);
    }

    private void showHidePass(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.getId()==R.id.img_hide_password_login){

                    if(edtPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye);
                        //Show Password
                        edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else{
                        ((ImageView)(view)).setImageResource(R.drawable.icon_awesome_eye_slash);

                        //Hide Password
                        edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }

            }
        });

    }

    private void login() {

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (email.isEmpty()){
            edtEmail.setError("email tidak boleh kosong");
            edtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Masukan Alamat email yang valid");
            edtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtPassword.setError("Password tidak boleh kosong");
            edtPassword.requestFocus();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Sign In");
        progressDialog.show();

        ConfigRetrofit.service.login(email, password, user_id).enqueue(new Callback<ResponseLoginUser>() {
            @Override
            public void onResponse(Call<ResponseLoginUser> call, Response<ResponseLoginUser> response) {

                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    Log.d("checkResponse", "onResponse: "+response.message());
                    List<ResponseItem> dataUser = response.body().getResponse();

                    String id_customer = "";
                    String nama = "";
                    String kode = "";
                    String email = "";
                    String no_hp = "";
                    String jk = "";
                    String tgl_lahir = "";
                    String usia = "";
                    String img = "";
                    String provinsi = "";
                    String kota = "";
                    String kecamatan = "";
                    String alamat = "";
                    String idProvinsi = "";
                    String idKota = "";
                    String idKecamatan = "";
                    String token = "";

                    for (int i = 0; i<dataUser.size(); i++){
                        id_customer = dataUser.get(i).getIdCustomer();
                        nama = dataUser.get(i).getNama();
                        kode = dataUser.get(i).getKode();
                        email = dataUser.get(i).getEmail();
                        no_hp = dataUser.get(i).getNoHp();
                        jk = dataUser.get(i).getJk();
                        tgl_lahir = dataUser.get(i).getTglLahir();
                        usia = dataUser.get(i).getUsia();
                        img = (String) dataUser.get(i).getImg();
                        provinsi = (String) dataUser.get(i).getNamaProvinsi();
                        kota = (String) dataUser.get(i).getNamaKota();
                        kecamatan = dataUser.get(i).getNamaKecamatan();
                        alamat = dataUser.get(i).getAlamat();
                        idProvinsi = dataUser.get(i).getProvinsi();
                        idKota = dataUser.get(i).getKota();
                        idKecamatan = dataUser.get(i).getKecamatan();
                        token = dataUser.get(i).getToken();
                    }

                    if (dataUser.size()>0) {
                        progressDialog.dismiss();
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_CUSTOMER, id_customer);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE, kode);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA, nama);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_EMAIL, email);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NO_HP, no_hp);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_JK, jk);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_TGL_LAHIR, tgl_lahir);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_USIA, usia);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMG, img);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_PROVINSI, provinsi);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KOTA, kota);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KECAMATAN, kecamatan);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ALAMAT, alamat);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_PROVINSI, idProvinsi);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_KOTA, idKota);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_KECAMATAN, idKecamatan);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_TOKEN, token);
                        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);

                        Log.d("testLoginUserId", "onResponse: "+user_id);

                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this, "Email Atau Password Salah", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }else{

                    Log.d("checkResponse", "onResponse: "+response.errorBody());
                    Log.d("checkResponse", "message: "+response.message());
                    Log.d("checkResponse", "code: "+response.code());
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLoginUser> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        progressDialog.setTitle("Login Google");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
            // Signed in successfully, show authenticated UI.
            googleUserProfile(acct);


//            Toast.makeText(this, "Login Sukses", Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("failedLoginGoogle", "signInResult:failed code=" + e.getStatusCode()+" Message: "+e.getMessage());
            Log.d("failedLoginGoogle", "signInResult:failed code=" + e.getStatusCode()+" Message: "+e.getMessage());
            progressDialog.dismiss();
            // updateUI(null);
        }
    }

    void googleUserProfile(GoogleSignInAccount acct) {
        if (acct != null) {

            Uri profile = acct.getPhotoUrl();

            loginGoogleFb("google", acct.getId(), acct.getGivenName(), acct.getFamilyName(), acct.getEmail());
            Log.d("dataGoogle", "id: "+acct.getId());
            Log.d("dataGoogle", "namaDepan: "+acct.getGivenName());
            Log.d("dataGoogle", "namaBelakang: "+acct.getFamilyName());
            Log.d("dataGoogle", "email: "+acct.getEmail());
            //  loginGoogleFacebook(acct.getEmail(),"",acct.getDisplayName(),"");
        } else {
            progressDialog.dismiss();
            //   StyleableToast.makeText(getApplicationContext(), "Gagal Login melalui Google", Toast.LENGTH_LONG, R.style.error_toast).show();
        }
    }

    private void loginGoogleFb(final String oauthpro, final String oauthid, String first_name, String last_name, String email) {

        ConfigRetrofit.service.loginGoogle(oauthpro, oauthid, first_name, last_name, email, user_id).enqueue(new Callback<ResponseLoginGoogle>() {
            @Override
            public void onResponse(Call<ResponseLoginGoogle> call, Response<ResponseLoginGoogle> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){


                    List<DataItem> dataItem = response.body().getResponse().getData();

                    String id_customer = "";
                    String nama = "";
                    String kode = "";
                    String email = "";
                    String no_hp = "";
                    String jk = "";
                    String tgl_lahir = "";
                    String usia = "";
                    String img = "";
                    String provinsi = "";
                    String kota = "";
                    String kecamatan = "";
                    String alamat = "";
                    String idProvinsi = "";
                    String idKota = "";
                    String idKecamatan = "";
                    String token = "";

                    for (int i=0; i<dataItem.size(); i++){
                        id_customer = dataItem.get(i).getIdCustomer();
                        nama = dataItem.get(i).getNama();
                        kode = dataItem.get(i).getKode();
                        email = dataItem.get(i).getEmail();
                        no_hp = (String) dataItem.get(i).getNoHp();
                        jk = (String) dataItem.get(i).getJk();
                        tgl_lahir = (String) dataItem.get(i).getTglLahir();
                        usia = (String) dataItem.get(i).getUsia();
                        img = (String) dataItem.get(i).getImg();
                        provinsi = (String) dataItem.get(i).getProvinsi();
                        kota = (String) dataItem.get(i).getKota();
                        kecamatan = (String) dataItem.get(i).getKecamatan();
                        alamat = (String) dataItem.get(i).getAlamat();
                        idProvinsi = (String) dataItem.get(i).getProvinsi();
                        idKota = (String) dataItem.get(i).getKota();
                        idKecamatan = (String) dataItem.get(i).getKecamatan();
                        token = dataItem.get(i).getToken();

                    }

                    if (dataItem.size()>0){
                        progressDialog.dismiss();
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_CUSTOMER, id_customer);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE, kode);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA, nama);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_EMAIL, email);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NO_HP, no_hp);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_JK, jk);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_TGL_LAHIR, tgl_lahir);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_USIA, usia);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMG, img);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_PROVINSI, provinsi);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KOTA, kota);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KECAMATAN, kecamatan);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ALAMAT, alamat);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_PROVINSI, idProvinsi);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_KOTA, idKota);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_KECAMATAN, idKecamatan);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_TOKEN, token);
                        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);

                        Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLoginGoogle> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}