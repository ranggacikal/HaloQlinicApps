package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.notifChat.ResponseNotif;
import com.mesibo.api.Mesibo;
import com.mesibo.calls.api.MesiboCall;
import com.mesibo.messaging.MesiboUI;
import com.onesignal.OneSignal;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity implements Mesibo.ConnectionListener, Mesibo.MessageListener{

    @Override
    public void Mesibo_onConnectionStatus(int i) {

        if (i == 1){
            imgOnline.setVisibility(View.VISIBLE);
            imgOffline.setVisibility(View.GONE);
        }else{
            imgOffline.setVisibility(View.VISIBLE);
            imgOnline.setVisibility(View.GONE);
        }

    }

    private void pushNotification() {

        ConfigRetrofit.service.notifChat(player_id).enqueue(new Callback<ResponseNotif>() {
            @Override
            public void onResponse(Call<ResponseNotif> call, Response<ResponseNotif> response) {
                if (response.isSuccessful()){

                    Log.d("statusNotifChat", "onResponse: "+"Berhasil Push Notification");


                }else{
                    Log.d("statusNotifChat", "onResponse: "+"Gagal Push Notification");
                }
            }

            @Override
            public void onFailure(Call<ResponseNotif> call, Throwable t) {
                Toast.makeText(ChatActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void Mesibo_onMessageStatus(Mesibo.MessageParams messageParams) {

        Log.d("checkMessageStatus", "Mesibo_onMessageStatus: "+messageParams.flag);

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
    Mesibo.UserProfile mProfile;
    Mesibo.ReadDbSession mReadSession;

    View mLoginButton1, mLoginButton2, mSendButton, mUiButton, mAudioCallButton, mVideoCallButton;
    TextView mMessageStatus, mConnStatus, txtNamaDokter, txtSpesialis;
    CircleImageView imgDokter;
    ImageView imgOnline, imgOffline, imgBack;
    EditText mMessage;

    String player_id = "";

    private SharedPreferencedConfig preferencedConfig;

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
        final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/"+img;
        String spesialis = getIntent().getStringExtra("spesialis");
        player_id = getIntent().getStringExtra("player_id");

        txtNamaDokter.setText("Dr. "+nama_dokter);
        txtSpesialis.setText("Spesialis "+spesialis);

        Glide.with(ChatActivity.this)
                .load(url_image)
                .into(imgDokter);

        mUiButton.setEnabled(false);
        mVideoCallButton.setEnabled(false);

        mUser1 = new DemoUser(preferencedConfig.getPreferenceToken(), preferencedConfig.getPreferenceNama());
        mUser2 = new DemoUser(token, nama_dokter);

        Log.d("demoUser", "tokenDokter: "+token);
        Log.d("demoUser", "tokenPasien: "+preferencedConfig.getPreferenceToken());

        mesiboInit(mUser1, mUser2);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
        mProfile = new Mesibo.UserProfile();
        mProfile.address = remoteUser.address;
        mProfile.name = remoteUser.address;
        Mesibo.setUserProfile(mProfile, false);

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
        MesiboUI.launchMessageView(this, mRemoteUser.address, 0);
        pushNotification();
    }

    public void onAudioCall(View view) {
        MesiboCall.getInstance().callUi(this, mProfile.address, false);
    }

    public void onSendMessage(View view) {
        Mesibo.MessageParams p = new Mesibo.MessageParams();
        p.peer = mRemoteUser.address;
        p.flag = Mesibo.FLAG_READRECEIPT | Mesibo.FLAG_DELIVERYRECEIPT;

        Mesibo.sendMessage(p, Mesibo.random(), mMessage.getText().toString().trim());
        mMessage.setText("");
    }

    public void onVideoCall(View view) {

        MesiboCall.getInstance().callUi(this, mProfile.address, true);
    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] bytes) {
        try {
            String message = new String(bytes, "UTF-8");

            pushNotification();


        } catch (Exception e) {
        }

        return true;
    }




}