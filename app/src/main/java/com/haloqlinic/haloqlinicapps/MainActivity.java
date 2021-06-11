package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;

    private HomeFragment homeFragment;
    private DokterFragment dokterFragment;
    private ProdukFragment produkFragment;
    private ProfileFragment profileFragment;

    ImageView imgPesan, imgKeranjang;
    TextView txtKataPertama, txtKataKedua;

    public String tokenUser;
    public String addressUser;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencedConfig = new SharedPreferencedConfig(this);

        tabLayout = findViewById(R.id.tab_layout_home);
        imgPesan = findViewById(R.id.img_pesan_home);
        txtKataPertama = findViewById(R.id.text_header_kata_pertama);
        txtKataKedua = findViewById(R.id.text_header_kata_kedua);
        imgKeranjang = findViewById(R.id.img_keranjang_home);

        tokenUser = getIntent().getStringExtra("tokenUser");
        addressUser = getIntent().getStringExtra("addressUser");

        imgKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KeranjangActivity.class));
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
                imgPesan.setVisibility(View.VISIBLE);
                imgKeranjang.setVisibility(View.GONE);
                break;
            case 1 :
                replaceFragment(dokterFragment);
                txtKataPertama.setText("Data");
                txtKataKedua.setText("Dokter");
                txtKataKedua.setVisibility(View.VISIBLE);
                imgPesan.setVisibility(View.GONE);
                imgKeranjang.setVisibility(View.GONE);
                break;
            case 2 :
                replaceFragment(produkFragment);
                txtKataPertama.setText("Produk");
                txtKataKedua.setText("Kecantikan");
                txtKataKedua.setVisibility(View.VISIBLE);
                imgPesan.setVisibility(View.GONE);
                imgKeranjang.setVisibility(View.VISIBLE);
                break;
            case 3 :
                replaceFragment(profileFragment);
                txtKataPertama.setText("Profile");
                txtKataKedua.setVisibility(View.GONE);
                imgPesan.setVisibility(View.GONE);
                imgKeranjang.setVisibility(View.GONE);
                break;
        }
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_home, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}