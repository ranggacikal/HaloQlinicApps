package id.luvie.luvieapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.cekKonsultasi.ResponseCekKonsultasi;
import id.luvie.luvieapps.model.chatOnline.DataItem;
import id.luvie.luvieapps.model.chatOnline.ResponseChatOnline;
import id.luvie.luvieapps.model.notifChat.ResponseNotif;

import com.mesibo.api.Mesibo;
import com.mesibo.api.MesiboProfile;
import com.mesibo.calls.api.MesiboCall;
import com.mesibo.messaging.MesiboUI;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity implements Mesibo.ConnectionListener, Mesibo.MessageListener {

    @Override
    public void Mesibo_onConnectionStatus(int i) {

        if (i == 1) {
            imgOnline.setVisibility(View.VISIBLE);
            imgOffline.setVisibility(View.GONE);
        } else {
            imgOffline.setVisibility(View.VISIBLE);
            imgOnline.setVisibility(View.GONE);
        }

    }

    private void pushNotification() {

        ConfigRetrofit.service.notifChat(player_id).enqueue(new Callback<ResponseNotif>() {
            @Override
            public void onResponse(Call<ResponseNotif> call, Response<ResponseNotif> response) {
                if (response.isSuccessful()) {

                    Log.d("statusNotifChat", "onResponse: " + "Berhasil Push Notification");
                    Log.d("checkPlayerId", "onResponse: " + player_id);


                } else {
                    Log.d("statusNotifChat", "onResponse: " + "Gagal Push Notification");
                }
            }

            @Override
            public void onFailure(Call<ResponseNotif> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void Mesibo_onActivity(Mesibo.MessageParams messageParams, int i) {

    }

    @Override
    public void Mesibo_onLocation(Mesibo.MessageParams messageParams, Mesibo.Location location) {

    }

    @Override
    public void Mesibo_onFile(Mesibo.MessageParams messageParams, Mesibo.FileInfo fileInfo) {

    }


    class DemoUser {
        public String token;
        public String address;

        DemoUser(String token, String address) {
            this.token = token;
            this.address = address;
        }
    }

    DemoUser mUser1;
    DemoUser mUser2;

    DemoUser mRemoteUser;
    MesiboProfile mProfile;
    Mesibo.ReadDbSession mReadSession;

    View mLoginButton1, mLoginButton2, mSendButton, mUiButton, mAudioCallButton, mVideoCallButton;
    TextView mMessageStatus, mConnStatus, txtNamaDokter, txtSpesialis;
    CircleImageView imgDokter;
    ImageView imgOnline, imgOffline, imgBack;
    EditText mMessage;
    String from_activity, id_transaksi;

    String player_id = "";
    String status_konsultasi;
    String id_dokter, status_cek_konsultasi, loop_status;
    int detik;

    private SharedPreferencedConfig preferencedConfig;

    private Handler handler = new Handler();

    // Define the code block to be executed
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getChatOnline();
            cekStatusKonsultasi();

            // Repeat every 2 seconds
            handler.postDelayed(runnable, 2000);
        }
    };

    private Handler handler2 = new Handler();

    // Define the code block to be executed
    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            cekStatus();

            // Repeat every 2 seconds
            handler2.postDelayed(runnable2, 2000);
        }
    };

    Toast toast, toast1;
    View layout1;
    CountDownTimer cdTimer;
    boolean isChat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        preferencedConfig = new SharedPreferencedConfig(this);

        mUiButton = findViewById(R.id.launchUI);
        mVideoCallButton = findViewById(R.id.videoCall);
        mMessage = findViewById(R.id.message);
        imgDokter = findViewById(R.id.img_dokter_konsultasi);
        txtNamaDokter = findViewById(R.id.text_nama_dokter_konsultasi);
        txtSpesialis = findViewById(R.id.text_spesialis_dokter_konsultasi);
        imgOnline = findViewById(R.id.img_online);
        imgOffline = findViewById(R.id.img_offline);
        imgBack = findViewById(R.id.img_back_chat);

        String token = getIntent().getStringExtra("token");
        String nama_dokter = getIntent().getStringExtra("nama_dokter");
        String img = getIntent().getStringExtra("image");
        final String url_image = "https://luvie.co.id/file/dokter/profile/" + img;
        String spesialis = getIntent().getStringExtra("spesialis");
        player_id = getIntent().getStringExtra("player_id");
//        status_konsultasi = getIntent().getStringExtra("status_konsultasi");
        from_activity = getIntent().getStringExtra("from_activity");
        id_dokter = getIntent().getStringExtra("id_dokter");
        id_transaksi = getIntent().getStringExtra("id_transaksi");

        Log.d("id_transaksi_chat", "onCreate: " + id_transaksi);

        txtNamaDokter.setText("Dr. " + nama_dokter);
        txtSpesialis.setText("Spesialis " + spesialis);

        Glide.with(ChatActivity.this)
                .load(url_image)
                .into(imgDokter);

        mUiButton.setEnabled(false);
        mVideoCallButton.setEnabled(false);

        handler.post(runnable);
        handler2.post(runnable2);

        mUser1 = new DemoUser(preferencedConfig.getPreferenceToken(), preferencedConfig.getPreferenceNama());
        mUser2 = new DemoUser(token, nama_dokter);

        Log.d("demoUser", "tokenDokter: " + token);
        Log.d("demoUser", "tokenPasien: " + preferencedConfig.getPreferenceToken());

        mesiboInit(mUser1, mUser2);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (from_activity != null) {

                    if (from_activity.equals("invoice")) {
                        startActivity(new Intent(ChatActivity.this, MainActivity.class));
                        finish();
                    }

                } else {

                    finish();
                }
            }
        });

        if (status_cek_konsultasi != null) {

        }

//        Mesibo.setAppInForeground(this, 0, true);
//        Mesibo.ReadDbSession mReadSession = new Mesibo.ReadDbSession("Putri Fidya", 0, null, this);
//        mReadSession.enableReadReceipt(true);
////        mReadSession.enableMissedCalls(mShowMissedCalls);
//        mReadSession.read(100);
//        Log.d("mReadSessionData", "toString: "+mReadSession.toString());

    }

    private void cekStatus(){

        ConfigRetrofit.service.cekKonsultasi(id_transaksi).enqueue(new Callback<ResponseCekKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseCekKonsultasi> call, Response<ResponseCekKonsultasi> response) {
                if(response.isSuccessful()){

                    loop_status = String.valueOf(response.body().getStatus());
                    Log.d("cekLoopStatus", "onResponse: "+loop_status);

                    if (loop_status.equals("2")){
                        int toastDurationInMilliSeconds = 10000;
                        Toast mToastToShow = Toast.makeText(ChatActivity.this,
                                "Konsultasi  dengan Dokter telah berakhir silahkan Cek resep dan resume Dokter di menu history anda Terimakasih",
                                Toast.LENGTH_LONG);

                        if (toast!=null) {
                            toast.cancel();
                        }

                        if(cdTimer!=null){
                            cdTimer.cancel();
                        }

                        if (toast1!=null){
                            toast1.cancel();
                        }

                        // Set the countdown to display the toast
                        CountDownTimer toastCountDown;
                        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 5000 /*Tick duration*/) {
                            public void onTick(long millisUntilFinished) {
                                mToastToShow.show();
                            }
                            public void onFinish() {
                                mToastToShow.cancel();
                            }
                        };

                        // Show the toast and starts the countdown
                        mToastToShow.show();
                        toastCountDown.start();
                        startActivity(new Intent(ChatActivity.this, MainActivity.class));
                        finish();
                        handler.removeCallbacks(runnable);
                        handler2.removeCallbacks(runnable2);
                    }

                }else{
                    Log.e("ErrorCekKonsultasi", "onResponse: Response Error" );
                }
            }

            @Override
            public void onFailure(Call<ResponseCekKonsultasi> call, Throwable t) {
                Log.e("ErrorCekKonsultasi", "onFailure: "+t.getMessage());
            }
        });

    }

    private void cekStatusKonsultasi() {

        ConfigRetrofit.service.cekKonsultasi(id_transaksi).enqueue(new Callback<ResponseCekKonsultasi>() {
            @Override
            public void onResponse(Call<ResponseCekKonsultasi> call, Response<ResponseCekKonsultasi> response) {
                if (response.isSuccessful()) {

                    status_cek_konsultasi = String.valueOf(response.body().getStatus());
//                    loop_status = String.valueOf(response.body().getStatus());
                    Log.d("statusCekKonsultasi", "onResponse: " + status_cek_konsultasi);


                    if (status_cek_konsultasi.equals("1")) {

                        handler.removeCallbacks(runnable);

                        long duration = TimeUnit.MINUTES.toMillis(3);

                        cdTimer = new CountDownTimer(duration, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d",
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                                Log.d("cekSduration", "onTick: " + TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                                detik = (int) TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);


                                LayoutInflater inflater = getLayoutInflater();
                                toast1 = new Toast(getApplicationContext());
                                layout1 = inflater.inflate(R.layout.custom_toast, findViewById(R.id.linear_custom_toast));
                                TextView txtToast = layout1.findViewById(R.id.text_toast_waktu);
                                txtToast.setText(sDuration);
                                toast1.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 0);
                                toast1.setDuration(Toast.LENGTH_SHORT);
                                toast1.setView(layout1);
                                toast1.show();
//                                if (detik == 180) {
//                                    LayoutInflater inflater = getLayoutInflater();
//                                    toast1 = new Toast(getApplicationContext());
//                                    layout1 = inflater.inflate(R.layout.custom_toast, findViewById(R.id.linear_custom_toast));
//                                    TextView txtToast = layout1.findViewById(R.id.text_toast_waktu);
//                                    txtToast.setText(sDuration);
//                                    toast1.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 0);
//                                    toast1.setDuration(Toast.LENGTH_LONG);
//                                    toast1.setView(layout1);
//                                    toast1.show();
//                                } else if (detik < 61 && detik >= 0) {
//                                    LayoutInflater inflater = getLayoutInflater();
//                                    View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.linear_custom_toast));
//                                    TextView txtToast = layout.findViewById(R.id.text_toast_waktu);
//                                    txtToast.setText(sDuration);
//                                    toast.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 0);
//                                    toast.setDuration(Toast.LENGTH_SHORT);
//                                    toast.setView(layout);
//                                    toast.show();
//                                }


                            }

                            @Override
                            public void onFinish() {

                                if(isChat==false) {

                                    MesiboCall.Call mCall2 = MesiboCall.getInstance().getActiveCall();
                                    if (mCall2 == null) {
                                        //There is no active call
                                        //We can make an outgoing call

                                        //Create a CallProperties object
                                        MesiboCall.CallProperties cp = MesiboCall.getInstance().createCallProperties(true);

                                        // Call Factory method to create a call object

                                        mCall2 = MesiboCall.getInstance().call(cp);

                                        mCall2.hangup();

                                        if (mCall2 == null) {
                                            //Error
                                        }
                                    }
                                    Toast.makeText(ChatActivity.this, "Konsultasi Sudah Berakhir",
                                            Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(ChatActivity.this, MainActivity.class));
                                    finish();
                                    handler.removeCallbacks(runnable);
                                    handler2.removeCallbacks(runnable2);

                                }else{
//                            int tutup = MesiboCall.MESIBOCALL_HANGUP_REASON_USER;
                                    Toast.makeText(ChatActivity.this, "Konsultasi Sudah Berakhir",
                                            Toast.LENGTH_LONG).show();
                                    handler.removeCallbacks(runnable);
                                    handler2.removeCallbacks(runnable2);
                                }
                            }
                        }.start();

                    }
//                    else if (status_cek_konsultasi.equals("2")) {
//                        Toast.makeText(ChatActivity.this, "Konsultasi Selesai", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }

                } else {
                    Toast.makeText(ChatActivity.this, "Gagal Ambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCekKonsultasi> call, Throwable t) {
                Log.e("ErrorCekKonsultasi", "onFailure: " + t.getMessage());
            }
        });

    }

    private void getChatOnline() {

        ConfigRetrofit.service.chatOnline(id_dokter).enqueue(new Callback<ResponseChatOnline>() {
            @Override
            public void onResponse(Call<ResponseChatOnline> call, Response<ResponseChatOnline> response) {
                if (response.isSuccessful()) {

                    List<DataItem> dataChat = response.body().getData();

                    for (int i = 0; i < dataChat.size(); i++) {

                        status_konsultasi = dataChat.get(i).getStatusKonsultasi();

                    }

                    Log.d("checkStatusKonsultasi", "onResponse: " + status_konsultasi);

                    if (status_konsultasi != null) {

                        if (status_konsultasi.equals("1")) {
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_DOKTER, "");
                        }
                    }

                } else {
                    Toast.makeText(ChatActivity.this, "Gagal memuat data chat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChatOnline> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (from_activity != null) {

            if (from_activity.equals("invoice")) {
                startActivity(new Intent(ChatActivity.this, MainActivity.class));
                finish();
            }

        }
    }

    private void mesiboInit(DemoUser user, DemoUser remoteUser) {
        Mesibo api = Mesibo.getInstance();
        api.init(getApplicationContext());

        Mesibo.addListener(this);
        Mesibo.setSecureConnection(true);
        Mesibo.setAccessToken(user.token);
        Mesibo.setDatabase("mydb", 0);
        Mesibo.start();

        mRemoteUser = remoteUser;
        mProfile = Mesibo.getProfile(remoteUser.address);

        // enable buttons
        mUiButton.setEnabled(true);
        mVideoCallButton.setEnabled(true);

        MesiboCall mesiboCall = MesiboCall.getInstance();
        mesiboCall.init(this);


        // Read receipts are enabled only when App is set to be in foreground
        Mesibo.setAppInForeground(this, 0, true);
        mReadSession = new Mesibo.ReadDbSession(mRemoteUser.address, this);
        mReadSession.enableReadReceipt(true);
        mReadSession.read(100);

    }

    public void onLaunchMessagingUi(View view) {

//        if (from_activity != null) {
//            if (from_activity.equals("jadwal_konsultasi")) {
//                handler.removeCallbacks(runnable);
//                status_konsultasi = getIntent().getStringExtra("status_konsultasi");
//            }
//        }

        isChat = true;

        if (status_cek_konsultasi.equals("2") || loop_status.equals("2")) {
            Toast.makeText(ChatActivity.this, "Konsultasi sudah diakhiri oleh dokter",
                    Toast.LENGTH_SHORT).show();
        } else if (status_cek_konsultasi.equals("0")) {
            Toast.makeText(ChatActivity.this, "Konsultasi belum dibuka oleh dokter",
                    Toast.LENGTH_SHORT).show();
        } else {
            MesiboUI.launchMessageView(this, mProfile.address, 0);
        }
    }

    public void onVideoCall(View view) {

        MesiboCall.getInstance().callUi(this, mProfile.address, true);
    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] bytes) {
        try {
            String data = messageParams.toString();
            Log.d("dataMessage", "Mesibo_onMessage: "+bytes.toString());

        } catch (Exception e) {
        }

        return true;
    }


    @Override
    public void Mesibo_onMessageStatus(Mesibo.MessageParams messageParams) {

        int status = messageParams.getStatus();
        long id = messageParams.mid;
        Log.d("cekStatus", "id: " + id);
        Log.d("cekStatus", "Mesibo_onMessageStatus: " + status);
        Log.d("cekStatus", "messageParam: " + messageParams.isSavedMessage());

        if (status == 1 && id != 0) {
            pushNotification();
        }

    }


}