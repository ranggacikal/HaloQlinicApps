package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.chatOnline.DataItem;
import com.haloqlinic.haloqlinicapps.model.chatOnline.ResponseChatOnline;
import com.haloqlinic.haloqlinicapps.model.notifChat.ResponseNotif;
import com.mesibo.api.Mesibo;
import com.mesibo.calls.api.MesiboCall;
import com.mesibo.messaging.MesiboUI;
import com.onesignal.OneSignal;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class MainActivity extends AppCompatActivity implements Mesibo.ConnectionListener, Mesibo.MessageListener{

    public TabLayout tabLayout;

    private HomeFragment homeFragment;
    private DokterFragment dokterFragment;
    private ProdukFragment produkFragment;
    private ProfileFragment profileFragment;

    ImageView imgPesan, imgKeranjang, imgKeranjangHome, imgChat;
    TextView txtKataPertama, txtKataKedua;
    LinearLayout linearIconHome;

    public String tokenUser;
    public String addressUser;

    private SharedPreferencedConfig preferencedConfig;

    String token, user_id, id_dokter, status_konsultasi, nama_dokter, token_dokter, player_id;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencedConfig = new SharedPreferencedConfig(this);

        tabLayout = findViewById(R.id.tab_layout_home);
        txtKataPertama = findViewById(R.id.text_header_kata_pertama);
        txtKataKedua = findViewById(R.id.text_header_kata_kedua);
        imgKeranjang = findViewById(R.id.img_keranjang_home);
        linearIconHome = findViewById(R.id.linear_icon_home_fragment);
        imgKeranjangHome = findViewById(R.id.ic_keranjang_home_fragment);
        imgChat = findViewById(R.id.ic_chat_home_fragment);

        tokenUser = getIntent().getStringExtra("tokenUser");
        addressUser = getIntent().getStringExtra("addressUser");

        imgKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KeranjangActivity.class));
            }
        });

        PushDownAnim.setPushDownAnimTo(imgChat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MesiboUI.launchMessageView(MainActivity.this, mRemoteUser.address, 0);
                    }
                });

        PushDownAnim.setPushDownAnimTo(imgKeranjangHome)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, ResepObatActivity.class));
                    }
                });

        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }

    private void getAllWidgets() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout_home);
    }

    private void setupTabLayout() {
        homeFragment = new HomeFragment();
        dokterFragment = new DokterFragment();
        produkFragment = new ProdukFragment();
        profileFragment = new ProfileFragment();

        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.ic_home_red));
        tabLayout.addTab(tabLayout.newTab().setText("Dokter").setIcon(R.drawable.ic_dokter_red));
        tabLayout.addTab(tabLayout.newTab().setText("Produk").setIcon(R.drawable.ic_produk_red));
        tabLayout.addTab(tabLayout.newTab().setText("Profile").setIcon(R.drawable.ic_profile_red));

    }

    private void bindWidgetsWithAnEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(homeFragment);
                txtKataPertama.setText("Halo,");
                txtKataKedua.setText(preferencedConfig.getPreferenceNama());
                txtKataKedua.setVisibility(View.VISIBLE);
                imgKeranjang.setVisibility(View.GONE);
                linearIconHome.setVisibility(View.VISIBLE);

                id_dokter = preferencedConfig.getPreferenceIdDokter();

                Log.d("idDokterHome", "onCreate: "+id_dokter);

                if (!id_dokter.equals("") || id_dokter !=null){

                getChatOnline();

                }

                break;
            case 1 :
                replaceFragment(dokterFragment);
                txtKataPertama.setText("Data");
                txtKataKedua.setText("Dokter");
                txtKataKedua.setVisibility(View.VISIBLE);
                imgKeranjang.setVisibility(View.GONE);
                linearIconHome.setVisibility(View.GONE);
                break;
            case 2 :
                replaceFragment(produkFragment);
                txtKataPertama.setText("Produk");
                txtKataKedua.setText("Kecantikan");
                txtKataKedua.setVisibility(View.VISIBLE);
                imgKeranjang.setVisibility(View.VISIBLE);
                linearIconHome.setVisibility(View.GONE);
                break;
            case 3 :
                replaceFragment(profileFragment);
                txtKataPertama.setText("Profile");
                txtKataKedua.setVisibility(View.GONE);
                imgKeranjang.setVisibility(View.GONE);
                linearIconHome.setVisibility(View.GONE);
                break;
        }
    }

    private void getChatOnline() {

        ConfigRetrofit.service.chatOnline(id_dokter).enqueue(new Callback<ResponseChatOnline>() {
            @Override
            public void onResponse(Call<ResponseChatOnline> call, Response<ResponseChatOnline> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataChat = response.body().getData();

                    for (int i = 0; i<dataChat.size(); i++){

                        status_konsultasi = dataChat.get(i).getStatusKonsultasi();
                        nama_dokter = dataChat.get(i).getNama();
                        token_dokter = dataChat.get(i).getToken();
                        player_id = dataChat.get(i).getPlayerId();

                    }



                    mUser1 = new DemoUser(preferencedConfig.getPreferenceToken(), preferencedConfig.getPreferenceNama());
                    mUser2 = new DemoUser(token_dokter, nama_dokter);

                    mesiboInit(mUser1, mUser2);

                    Log.d("statusKonsultasiHome", "onResponse: "+status_konsultasi);

                    if (status_konsultasi != null) {

                        if (status_konsultasi.equals("1")) {
                            preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_DOKTER, "");
                        }
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Gagal memuat data chat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChatOnline> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void mesiboInit(DemoUser mUser1, DemoUser mUser2) {

        Mesibo api = Mesibo.getInstance();
        api.init(getApplicationContext());

        Mesibo.addListener(this);
        Mesibo.setSecureConnection(true);
        Mesibo.setAccessToken(mUser1.token);
        Mesibo.setDatabase("mydb", 0);
        Mesibo.start();

        mRemoteUser = mUser2;
        mProfile = new Mesibo.UserProfile();
        mProfile.address = mUser2.address;
        mProfile.name = mUser2.address;
        Mesibo.setUserProfile(mProfile, false);

        MesiboCall mesiboCall = MesiboCall.getInstance();
        mesiboCall.init(this);


        // Read receipts are enabled only when App is set to be in foreground
        Mesibo.setAppInForeground(this, 0, true);
        mReadSession = new Mesibo.ReadDbSession(mRemoteUser.address, this);
        mReadSession.enableReadReceipt(true);
        mReadSession.read(100);

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_home, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void Mesibo_onConnectionStatus(int i) {
        if (i == 1){
            Log.d("checkKoneksiHome", "Mesibo_onConnectionStatus: ONLINE");
        }else{
            Log.d("checkKoneksiHome", "Mesibo_onConnectionStatus: OFFLINE");
        }
    }

    @Override
    public boolean Mesibo_onMessage(Mesibo.MessageParams messageParams, byte[] bytes) {
        return false;
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
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        if (!id_dokter.equals("") || id_dokter !=null){

            getChatOnline();

        }
    }
}