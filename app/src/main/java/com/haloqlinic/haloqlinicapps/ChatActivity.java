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

import com.mesibo.api.Mesibo;
import com.mesibo.calls.api.MesiboCall;
import com.mesibo.messaging.MesiboUI;

public class ChatActivity extends AppCompatActivity implements Mesibo.ConnectionListener, Mesibo.MessageListener{

    @Override
    public void Mesibo_onConnectionStatus(int i) {

        mConnStatus.setText("Connection Status: " + i);

    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] bytes) {
        try {
            String message = new String(bytes, "UTF-8");

            Toast toast = Toast.makeText(getApplicationContext(),
                    message,
                    Toast.LENGTH_SHORT);

            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

            toast.show();

        } catch (Exception e) {
        }

        return true;
    }

    @Override
    public void Mesibo_onMessageStatus(Mesibo.MessageParams messageParams) {
        mMessageStatus.setText("Message Status: " + messageParams.getStatus());
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

    DemoUser mUser1 = new DemoUser("13f8a47da0bb371dc8926fac4aec38505c78ca936a8dfa95e4431ea98",  "123");
    DemoUser mUser2 = new DemoUser("c642455a89f2878b4689d69acfd51b16fa6976225b5ce6631eaa9", "456");

    DemoUser mRemoteUser;
    Mesibo.UserProfile mProfile;
    Mesibo.ReadDbSession mReadSession;

    View mLoginButton1, mLoginButton2, mSendButton, mUiButton, mAudioCallButton, mVideoCallButton;
    TextView mMessageStatus, mConnStatus;
    EditText mMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        mLoginButton1 = findViewById(R.id.login1);
//        mLoginButton2 = findViewById(R.id.login2);
//        mSendButton = findViewById(R.id.send);
//        mUiButton = findViewById(R.id.launchUI);
//        mAudioCallButton = findViewById(R.id.audioCall);
//        mVideoCallButton = findViewById(R.id.videoCall);
//        mMessageStatus = findViewById(R.id.msgStatus);
//        mConnStatus = findViewById(R.id.connStatus);
//        mMessage = findViewById(R.id.message);
//
//        mSendButton.setEnabled(false);
//        mUiButton.setEnabled(false);
//        mAudioCallButton.setEnabled(false);
//        mVideoCallButton.setEnabled(false);




    }

    private void mesiboInit(DemoUser user, DemoUser remoteUser) {
//        Mesibo api = Mesibo.getInstance();
//        api.init(getApplicationContext());
//
//        Mesibo.addListener(this);
//        Mesibo.setSecureConnection(true);
//        Mesibo.setAccessToken(user.token);
//        Mesibo.setDatabase("mydb", 0);
//        Mesibo.start();
//
//        mRemoteUser = remoteUser;
//        mProfile = new Mesibo.UserProfile();
//        mProfile.address = remoteUser.address;
//        mProfile.name = remoteUser.address;
//        Mesibo.setUserProfile(mProfile, false);
//
//        // disable login buttons
//        mLoginButton1.setEnabled(false);
//        mLoginButton2.setEnabled(false);
//
//        // enable buttons
//        mSendButton.setEnabled(true);
//        mUiButton.setEnabled(true);
//        mAudioCallButton.setEnabled(true);
//        mVideoCallButton.setEnabled(true);
//
//        MesiboCall mesiboCall = MesiboCall.getInstance();
//        mesiboCall.init(this);
//
//
//        // Read receipts are enabled only when App is set to be in foreground
//        Mesibo.setAppInForeground(this, 0, true);
//        mReadSession = new Mesibo.ReadDbSession(mRemoteUser.address, this);
//        mReadSession.enableReadReceipt(true);
//        mReadSession.read(100);

    }

    public void onLaunchMessagingUi(View view) {
//        MesiboUI.launchMessageView(this, mRemoteUser.address, 0);
    }

    public void onAudioCall(View view) {
//        MesiboCall.getInstance().callUi(this, mProfile.address, false);
    }

    public void onLoginUser1(View view) {
        mesiboInit(mUser1, mUser2);
    }

    public void onLoginUser2(View view) {
        mesiboInit(mUser2, mUser1);
    }

    public void onSendMessage(View view) {
//        Mesibo.MessageParams p = new Mesibo.MessageParams();
//        p.peer = mRemoteUser.address;
//        p.flag = Mesibo.FLAG_READRECEIPT | Mesibo.FLAG_DELIVERYRECEIPT;
//
//        Mesibo.sendMessage(p, Mesibo.random(), mMessage.getText().toString().trim());
//        mMessage.setText("");
    }

    public void onVideoCall(View view) {

//        MesiboCall.getInstance().callUi(this, mProfile.address, true);
    }


}