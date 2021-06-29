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

import com.google.android.material.tabs.TabLayout;
import com.haloqlinic.haloqlinicapps.databinding.ActivityDokterTersediaBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityKategoriBinding;
import com.haloqlinic.haloqlinicapps.fragmentDokterTersedia.BuatJadwalDrTersediaFragment;
import com.haloqlinic.haloqlinicapps.fragmentDokterTersedia.OnlineDrTersediaFragment;

public class DokterTersediaActivity extends AppCompatActivity {


    private ActivityDokterTersediaBinding binding;

    OnlineDrTersediaFragment dokterTersediaFragment;
    BuatJadwalDrTersediaFragment buatJadwalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDokterTersediaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dokterTersediaFragment = new OnlineDrTersediaFragment();
        buatJadwalFragment = new BuatJadwalDrTersediaFragment();

        binding.imgBackDokterTersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        replaceFragment(dokterTersediaFragment);

        binding.btnOnlineDokterTersedia.setBackgroundResource(R.drawable.background_btn_green_border);

        binding.btnOnlineDokterTersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnOnlineDokterTersedia.setBackgroundResource(R.drawable.background_btn_green_border);
                replaceFragment(dokterTersediaFragment);
                binding.btnBuatJadwalDokterTersedia.setBackgroundResource(R.drawable.background_btn_grey);
            }
        });

        binding.btnBuatJadwalDokterTersedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnBuatJadwalDokterTersedia.setBackgroundResource(R.drawable.background_btn_green_border);
                replaceFragment(buatJadwalFragment);
                binding.btnOnlineDokterTersedia.setBackgroundResource(R.drawable.background_btn_grey);
            }
        });

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_dokter_tersedia, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}