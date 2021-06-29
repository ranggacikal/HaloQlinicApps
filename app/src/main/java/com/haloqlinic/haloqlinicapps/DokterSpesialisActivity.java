package com.haloqlinic.haloqlinicapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.haloqlinic.haloqlinicapps.adapter.CariDokterSpesialisAdapter;
import com.haloqlinic.haloqlinicapps.adapter.DokterAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.dokterSpesialisFragment.BuatJadwalDokterSpesialisFragment;
import com.haloqlinic.haloqlinicapps.dokterSpesialisFragment.OnlineDokterSpesialisFragment;
import com.haloqlinic.haloqlinicapps.model.cariDokter.ResponseCariDokter;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokter.ResponseListDokter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterSpesialisActivity extends AppCompatActivity {

    ImageView imgBack;
    SearchView cariDokter;
    TabLayout tabLayoutDokterSpesialis;
    FrameLayout frameLayoutDokterSpesialis;
    Button btnOnline, btnBuatJadwal;

    OnlineDokterSpesialisFragment onlineFragment;
    BuatJadwalDokterSpesialisFragment buatJadwalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_spesialis);
        imgBack = findViewById(R.id.img_back_dokter_spesialis);
        tabLayoutDokterSpesialis = findViewById(R.id.tab_layout_dokter_spesialis);
        frameLayoutDokterSpesialis = findViewById(R.id.frame_dokter_spesialis);
        btnOnline = findViewById(R.id.btn_online_dokter_spesialis);
        btnBuatJadwal = findViewById(R.id.btn_buat_jadwal_dokter_spesialis);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOnline.setBackgroundResource(R.drawable.background_btn_green_border);

        btnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnline.setBackgroundResource(R.drawable.background_btn_green_border);
                replaceFragment(onlineFragment);
                btnBuatJadwal.setBackgroundResource(R.drawable.background_btn_grey);
            }
        });

        btnBuatJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBuatJadwal.setBackgroundResource(R.drawable.background_btn_green_border);
                replaceFragment(buatJadwalFragment);
                btnOnline.setBackgroundResource(R.drawable.background_btn_grey);
            }
        });

        bindWidgetsWithAnEvent();
        setupTabLayout();
    }

    private void setupTabLayout() {

        onlineFragment = new OnlineDokterSpesialisFragment();
        buatJadwalFragment = new BuatJadwalDokterSpesialisFragment();

        tabLayoutDokterSpesialis.addTab(tabLayoutDokterSpesialis.newTab().setText("Online"));
        tabLayoutDokterSpesialis.addTab(tabLayoutDokterSpesialis.newTab().setText("Buat Jadwal"));

    }

    private void bindWidgetsWithAnEvent() {

        tabLayoutDokterSpesialis.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    private void setCurrentTabFragment(int position) {

        switch (position) {
            case 0:
                replaceFragment(onlineFragment);
                break;
            case 1:
                replaceFragment(buatJadwalFragment);
                break;

        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_dokter_spesialis, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}