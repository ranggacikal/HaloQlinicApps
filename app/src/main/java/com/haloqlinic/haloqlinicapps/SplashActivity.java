package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferencedConfig preferencedConfig;

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferencedConfig = new SharedPreferencedConfig(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (preferencedConfig.getPreferenceIsLogin()){

                    Intent intentIsLogin = new Intent(getApplicationContext(), MainActivity.class);
                    intentIsLogin.putExtra("tab", 0);
                    startActivity(intentIsLogin);
                    finish();

                }else{

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("tab", 0);
                    startActivity(intent);
                    finish();

                }
            }
        }, SPLASH_TIME_OUT);
    }
}