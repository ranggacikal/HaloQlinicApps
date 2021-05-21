package com.haloqlinic.haloqlinicapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RelativeLayout relativeEditProfile, relativeLogout, relativeKeranjang, relativeHistory, relativeHistoryChat;
    private SharedPreferencedConfig preferencedConfig;
    TextView txtNamaUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);

        preferencedConfig = new SharedPreferencedConfig(getActivity());

        relativeEditProfile = rootview.findViewById(R.id.relative_edit_profile);
        relativeLogout = rootview.findViewById(R.id.relative_keluar_profile);
        txtNamaUser = rootview.findViewById(R.id.text_nama_user_profile);
        relativeKeranjang = rootview.findViewById(R.id.relative_keranjang_profile);
        relativeHistory = rootview.findViewById(R.id.relative_history_profile);
        relativeHistoryChat = rootview.findViewById(R.id.relative_history_konsultasi);

        txtNamaUser.setText(preferencedConfig.getPreferenceNama());

        relativeEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditAkunActivity.class));
            }
        });

        relativeKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KeranjangActivity.class));
            }
        });

        relativeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
            }
        });

        relativeHistoryChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoryKonsultasiActivity.class));
            }
        });

        relativeLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Keluar Akun?")
                        .setMessage("Anda yakin ingin keluar dari akun ini?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                keluarAkun();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });

        return rootview;
    }

    private void keluarAkun() {

        Toast.makeText(getActivity(), "Keluar akun", Toast.LENGTH_SHORT).show();
        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, false);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

    }
}