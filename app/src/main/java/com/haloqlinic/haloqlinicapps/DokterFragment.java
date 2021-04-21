package com.haloqlinic.haloqlinicapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

public class DokterFragment extends Fragment {

    public DokterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    SearchView searchDokter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dokter, container, false);

        searchDokter = rootView.findViewById(R.id.search_dokter);

        searchDokter.setQueryHint("Cari Dokter");
        searchDokter.setIconified(false);

        return rootView;
    }
}