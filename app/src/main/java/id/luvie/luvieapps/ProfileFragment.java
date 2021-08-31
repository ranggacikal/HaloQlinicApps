package id.luvie.luvieapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import id.luvie.luvieapps.R;

import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RelativeLayout relativeEditProfile, relativeLogout, relativeKeranjang, relativeHistory, relativeHistoryChat,
    relativeRecipe, relativeUbahPassword, relative_bantuan, relativeAlergiObat;
    private SharedPreferencedConfig preferencedConfig;
    TextView txtNamaUser;
    ImageView imgProfile;

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
        relativeRecipe = rootview.findViewById(R.id.relative_recipe);
        imgProfile = rootview.findViewById(R.id.img_profile_user);
        relativeUbahPassword = rootview.findViewById(R.id.relative_ubah_password);
        relative_bantuan = rootview.findViewById(R.id.relative_bantuan_profile);
        relativeAlergiObat = rootview.findViewById(R.id.relative_alergi_obat_profile);

        txtNamaUser.setText(preferencedConfig.getPreferenceNama());

        initGambar();

        relativeRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ResepObatActivity.class));
            }
        });

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

        relativeUbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UbahPasswordActivity.class));
            }
        });

        relativeAlergiObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AlergiObatActivity.class));
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

        relative_bantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@haloqlinic.co.id"});
                email.putExtra(Intent.EXTRA_SUBJECT, "");
                email.putExtra(Intent.EXTRA_TEXT, "");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditAkunActivity.class));
            }
        });

        txtNamaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditAkunActivity.class));
            }
        });

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
        initGambar();
    }

    private void initGambar() {

        String url = "https://luvie.co.id/file/customer/profile/"+preferencedConfig.getPreferenceImg();
        Glide.with(getActivity())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(imgProfile);

    }

    private void keluarAkun() {

        Toast.makeText(getActivity(), "Keluar akun", Toast.LENGTH_SHORT).show();
        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, false);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

    }
}