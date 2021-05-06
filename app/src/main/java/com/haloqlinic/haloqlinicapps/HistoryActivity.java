package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.haloqlinic.haloqlinicapps.statusTransaksi.BelumBayarFragment;
import com.haloqlinic.haloqlinicapps.statusTransaksi.DikemasFragment;
import com.haloqlinic.haloqlinicapps.statusTransaksi.DikirimFragment;
import com.haloqlinic.haloqlinicapps.statusTransaksi.SelesaiFragment;
import com.haloqlinic.haloqlinicapps.statusTransaksi.SemuaFragment;

public class HistoryActivity extends AppCompatActivity {

    TabLayout tabLayoutHistory;
    FrameLayout frameHistory;

    SemuaFragment semuaFragment;
    BelumBayarFragment belumBayarFragment;
    DikemasFragment dikemasFragment;
    DikirimFragment dikirimFragment;
    SelesaiFragment selesaiFragment;

    ImageView imgBackHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tabLayoutHistory = findViewById(R.id.tab_layout_history);
        frameHistory = findViewById(R.id.frame_history);
        imgBackHistory = findViewById(R.id.img_back_history);

        bindWidgetsWithAnEvent();
        setupTabLayout();

        imgBackHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupTabLayout() {

        semuaFragment = new SemuaFragment();
        belumBayarFragment = new BelumBayarFragment();
        dikemasFragment = new DikemasFragment();
        dikirimFragment = new DikirimFragment();
        selesaiFragment = new SelesaiFragment();

        tabLayoutHistory.addTab(tabLayoutHistory.newTab().setText("Semua"));
        tabLayoutHistory.addTab(tabLayoutHistory.newTab().setText("Belum Bayar"));
        tabLayoutHistory.addTab(tabLayoutHistory.newTab().setText("Dikemas"));
        tabLayoutHistory.addTab(tabLayoutHistory.newTab().setText("Dikirim"));
        tabLayoutHistory.addTab(tabLayoutHistory.newTab().setText("Selesai"));

    }

    private void bindWidgetsWithAnEvent() {
        tabLayoutHistory.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                replaceFragment(semuaFragment);
                break;
            case 1 :
                replaceFragment(belumBayarFragment);
                break;
            case 2 :
                replaceFragment(dikemasFragment);
                break;
            case 3 :
                replaceFragment(dikirimFragment);
                break;
            case 4 :
                replaceFragment(selesaiFragment);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_history, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}