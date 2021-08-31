package id.luvie.luvieapps.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.chatOnline.DataItem;
import id.luvie.luvieapps.model.chatOnline.ResponseChatOnline;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyService extends Service {

    private SharedPreferencedConfig preferencedConfig;

    public String status_konsultasi = "";

    private Handler handler = new Handler();

    // Define the code block to be executed
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getChatOnline();

            // Repeat every 2 seconds
            handler.postDelayed(runnable, 2000);
        }
    };

    private void getChatOnline() {

        preferencedConfig = new SharedPreferencedConfig(this);

        ConfigRetrofit.service.chatOnline(preferencedConfig.getPreferenceIdDokter()).enqueue(new Callback<ResponseChatOnline>() {
            @Override
            public void onResponse(Call<ResponseChatOnline> call, Response<ResponseChatOnline> response) {
                if (response.isSuccessful()) {

                    List<DataItem> dataChat = response.body().getData();

                    String nama_dokter, token_dokter, player_id;

                    for (int i = 0; i < dataChat.size(); i++) {

                        status_konsultasi = dataChat.get(i).getStatusKonsultasi();
                        nama_dokter = dataChat.get(i).getNama();
                        token_dokter = dataChat.get(i).getToken();
                        player_id = dataChat.get(i).getPlayerId();

                    }
                    Log.d("statusKonsultasiService", "onResponse: " + status_konsultasi);

                    if (status_konsultasi != null) {

                        if (status_konsultasi.equals("1")) {
                        } else if (status_konsultasi.equals("0")) {
                        }
                    }

                } else {
                    Toast.makeText(MyService.this, "Gagal memuat data chat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChatOnline> call, Throwable t) {
                Toast.makeText(MyService.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Mulai", Toast.LENGTH_SHORT).show();
        handler.post(runnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Log.d("myService", "onStartCommand: start berhenti");
    }


}
