package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferencedConfig preferencedConfig;

    private static int SPLASH_TIME_OUT = 2000;

    Runnable runnable;
    Toast toast1;
    View layout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferencedConfig = new SharedPreferencedConfig(this);
       // countDown("2021-09-13 22:43:00");

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

    void countDown(String tanggal_selesai){
        Handler handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,1000);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date selesai = simpleDateFormat.parse(tanggal_selesai);
                   // Date mulai = simpleDateFormat.parse(tanggal_mulai);
                    Date current_date = new Date();
                    if (!current_date.after(selesai)){
                        long diff = selesai.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;

                        String sDuration =  String.format("%02d", Minutes)+" : "+String.format("%02d", Seconds);


                        LayoutInflater inflater = getLayoutInflater();
                        toast1 = new Toast(getApplicationContext());
                        layout1 = inflater.inflate(R.layout.custom_toast, findViewById(R.id.linear_custom_toast));
                        TextView txtToast = layout1.findViewById(R.id.text_toast_waktu);
                        txtToast.setText(sDuration);
                        toast1.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 0);
                        toast1.setDuration(Toast.LENGTH_SHORT);
                        toast1.setView(layout1);
                        toast1.show();
                    }else {
                        handler.removeCallbacks(runnable);
                        toast1.cancel();
                        Toast.makeText(getApplicationContext(),"Selesai yoooooo",Toast.LENGTH_SHORT).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };
        handler.postDelayed(runnable,0);
    }
}