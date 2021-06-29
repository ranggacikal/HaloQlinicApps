package com.haloqlinic.haloqlinicapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.adapter.DokterAktifAdapter;
import com.haloqlinic.haloqlinicapps.adapter.DokterAktifHomeAdapter;
import com.haloqlinic.haloqlinicapps.adapter.MitraHomeAdapter;
import com.haloqlinic.haloqlinicapps.adapter.UserAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.User;
import com.haloqlinic.haloqlinicapps.model.getPlayerId.ResponseGetPlayerId;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;
import com.haloqlinic.haloqlinicapps.model.listDokterAktifHome.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokterAktifHome.ResponseDokterAktifHome;
import com.haloqlinic.haloqlinicapps.model.mitraKlinik.ResponseDataMitra;
import com.haloqlinic.haloqlinicapps.model.userMesibo.ResponseGetUserMesibo;
import com.haloqlinic.haloqlinicapps.model.userMesibo.UsersItem;
import com.onesignal.OSDeviceState;
import com.onesignal.OneSignal;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvDokterOnline, rvMitra;
    TextView txtLihatSemuaDokterTersedia, txtLihatSemuaMitra;
    LinearLayout linearDokterSpesialis, linearSkincare, linearJadwalKonsultasi;

    String token, token_from, user_id, user_id_from;

    private static final String ONESIGNAL_APP_ID = "67314311-5f01-4b4e-b20c-1e0f6fb9958c";

    private SharedPreferencedConfig preferencedConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        rvDokterOnline = rootview.findViewById(R.id.recycler_dokter_online_home);
        txtLihatSemuaDokterTersedia = rootview.findViewById(R.id.text_lihat_semua_dokter_tersedia);
        linearDokterSpesialis = rootview.findViewById(R.id.linear_dokter_spesialis_home);
        rvMitra = rootview.findViewById(R.id.recycler_mitra_klinik_home);
        txtLihatSemuaMitra = rootview.findViewById(R.id.text_lihat_semua_mitra_klinik);
        linearSkincare = rootview.findViewById(R.id.linear_skincare_home);
        linearJadwalKonsultasi = rootview.findViewById(R.id.linear_jadwal_home);

        preferencedConfig = new SharedPreferencedConfig(getActivity());

        OneSignal.initWithContext(getContext());
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        OSDeviceState device = OneSignal.getDeviceState();

        token = device.getPushToken();
        user_id = device.getUserId();

        getPlayerId();

        Log.d("checkOneSignal", "token: "+token);
        Log.d("checkOneSignal", "user_id: "+user_id);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerMitra = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvDokterOnline.setHasFixedSize(true);
        rvDokterOnline.setLayoutManager(layoutManager);

        rvMitra.setHasFixedSize(true);
        rvMitra.setLayoutManager(layoutManagerMitra);

        PushDownAnim.setPushDownAnimTo(txtLihatSemuaDokterTersedia)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), DokterTersediaActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(linearDokterSpesialis)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), DokterSpesialisActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(txtLihatSemuaMitra)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), MitraKlinikActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(linearJadwalKonsultasi)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), JadwalKonsultasiActivity.class));
                    }
                });

        PushDownAnim.setPushDownAnimTo(linearSkincare)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), KategoriActivity.class));
                    }
                });

        loadDokterOnline();
        loadMitraKlinik();


        return rootview;
    }

    private void getPlayerId() {

        ConfigRetrofit.service.getPlayerId(preferencedConfig.getPreferenceIdCustomer(), user_id)
                .enqueue(new Callback<ResponseGetPlayerId>() {
                    @Override
                    public void onResponse(Call<ResponseGetPlayerId> call, Response<ResponseGetPlayerId> response) {
                        if (response.isSuccessful()){

                            String test = response.body().getPlayerId();

                            Log.d("statusPushPlayer", "berhasil: "+test);

                        }else{
                            Log.d("statusPushPlayer", "GAGAL PUSH ");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseGetPlayerId> call, Throwable t) {
                        Log.d("statusPushPlayer", "OnFailure: "+t.getMessage());
                    }
                });

    }

    private void loadMitraKlinik() {

        String status = "0";

        ConfigRetrofit.service.dataMitra(status).enqueue(new Callback<ResponseDataMitra>() {
            @Override
            public void onResponse(Call<ResponseDataMitra> call, Response<ResponseDataMitra> response) {
                if (response.isSuccessful()){
                    List<com.haloqlinic.haloqlinicapps.model.mitraKlinik.DataItem> dataMitra = response.body().getData();
                    MitraHomeAdapter adapter = new MitraHomeAdapter(getActivity(), dataMitra);
                    rvMitra.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseDataMitra> call, Throwable t) {

            }
        });

    }

    private void loadDokterOnline() {

        ConfigRetrofit.service.dokterAktifHome("home", "1", "1").enqueue(new Callback<ResponseDokterAktifHome>() {
            @Override
            public void onResponse(Call<ResponseDokterAktifHome> call, Response<ResponseDokterAktifHome> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataItems = response.body().getData();
                    DokterAktifHomeAdapter adapter = new DokterAktifHomeAdapter(getActivity(), dataItems);
                    rvDokterOnline.setAdapter(adapter);

                }else{
                    Toast.makeText(getActivity(), "Gagal memuat data dokter online", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDokterAktifHome> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}