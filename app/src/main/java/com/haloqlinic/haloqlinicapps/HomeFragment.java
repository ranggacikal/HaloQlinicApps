package com.haloqlinic.haloqlinicapps;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haloqlinic.haloqlinicapps.adapter.DokterAktifAdapter;
import com.haloqlinic.haloqlinicapps.adapter.UserAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.User;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.DataItem;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.ResponseDataDokterAktif;
import com.haloqlinic.haloqlinicapps.model.userMesibo.ResponseGetUserMesibo;
import com.haloqlinic.haloqlinicapps.model.userMesibo.UsersItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView rvDokterOnline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        rvDokterOnline = rootview.findViewById(R.id.recycler_dokter_online_home);
        rvDokterOnline.setHasFixedSize(true);
        rvDokterOnline.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadDokterOnline();


        return rootview;
    }

    private void loadDokterOnline() {

        ConfigRetrofit.service.dataDokterAktif().enqueue(new Callback<ResponseDataDokterAktif>() {
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