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

import com.haloqlinic.haloqlinicapps.adapter.UserAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.model.User;
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

        String op = "usersget";
        String token = getString(R.string.token);

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();

        ConfigRetrofit.service.getUser(op, token).enqueue(new Callback<ResponseGetUserMesibo>() {
            @Override
            public void onResponse(Call<ResponseGetUserMesibo> call, Response<ResponseGetUserMesibo> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    boolean result = response.body().isResult();

                    Log.d("logResult", "onResponse: "+result);

                    if (result==true){

                        List<UsersItem> userList = response.body().getUsers();
                        UserAdapter adapter = new UserAdapter(userList, getActivity());
                        Log.d("logDataUser", "onResponse: "+userList);
                        rvDokterOnline.setAdapter(adapter);

                    }else{
                        Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetUserMesibo> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}