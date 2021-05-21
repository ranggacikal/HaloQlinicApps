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

import com.haloqlinic.haloqlinicapps.adapter.DokterAktifAdapter;
import com.haloqlinic.haloqlinicapps.adapter.MitraHomeAdapter;
import com.haloqlinic.haloqlinicapps.adapter.UserAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.User;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;
import com.haloqlinic.haloqlinicapps.model.mitraKlinik.ResponseDataMitra;
import com.haloqlinic.haloqlinicapps.model.userMesibo.ResponseGetUserMesibo;
import com.haloqlinic.haloqlinicapps.model.userMesibo.UsersItem;
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
    LinearLayout linearDokterSpesialis;

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

        loadDokterOnline();
        loadMitraKlinik();


        return rootview;
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

        String status = "0";

        ConfigRetrofit.service.dataDokterAktif(status).enqueue(new Callback<ResponseDataDokterAktif>() {
            @Override
            public void onResponse(Call<ResponseDataDokterAktif> call, Response<ResponseDataDokterAktif> response) {
                if (response.isSuccessful()){

                    List<DataItem> dokterAktifItems = response.body().getData();
                    DokterAktifAdapter adapter = new DokterAktifAdapter(getActivity(), dokterAktifItems);
                    rvDokterOnline.setAdapter(adapter);

                }else{
                    Toast.makeText(getActivity(), "Gagal Load Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataDokterAktif> call, Throwable t) {

                Toast.makeText(getActivity(), "Terjadi Kesalahan di server : "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}