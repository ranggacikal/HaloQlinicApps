package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.databinding.ActivityHistoryKonsultasiBinding;
import com.haloqlinic.haloqlinicapps.fragmentHistoryKonsultasi.KonsultasiAccFragment;
import com.haloqlinic.haloqlinicapps.fragmentHistoryKonsultasi.KonsultasiPendingFragment;
import com.haloqlinic.haloqlinicapps.fragmentHistoryKonsultasi.KonsultasiSelesaiFragment;

public class HistoryKonsultasiActivity extends AppCompatActivity {

    private ActivityHistoryKonsultasiBinding binding;

    private KonsultasiPendingFragment konsultasiPendingFragment;
    private KonsultasiAccFragment konsultasiAccFragment;
    private KonsultasiSelesaiFragment konsultasiSelesaiFragment;
    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryKonsultasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferencedConfig = new SharedPreferencedConfig(HistoryKonsultasiActivity.this);

        binding.imgBackHistoryKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupTabLayout();
        bindWidgetsWithAnEvent();
    }

    private void bindWidgetsWithAnEvent() {

        binding.tabLayoutHistoryKonsultasi.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
                int position = tab.getPosition();
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_POSITION_FRAGMENT, String.valueOf(position));
                Log.d("checkPosition", "onTabSelected: "+position);
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
                replaceFragment(konsultasiPendingFragment);
                break;
            case 1:
                replaceFragment(konsultasiAccFragment);
                break;
            case 2:
                replaceFragment(konsultasiSelesaiFragment);
                break;

        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_history_konsultasi, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void setupTabLayout() {

        konsultasiPendingFragment = new KonsultasiPendingFragment();
        konsultasiAccFragment = new KonsultasiAccFragment();
        konsultasiSelesaiFragment = new KonsultasiSelesaiFragment();

        binding.tabLayoutHistoryKonsultasi.addTab(binding.tabLayoutHistoryKonsultasi.newTab().setText("Pending"));
        binding.tabLayoutHistoryKonsultasi.addTab(binding.tabLayoutHistoryKonsultasi.newTab().setText("Diterima"));
        binding.tabLayoutHistoryKonsultasi.addTab(binding.tabLayoutHistoryKonsultasi.newTab().setText("Selesai"));

    }
}