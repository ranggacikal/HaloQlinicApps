package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.detailDokter.DataItem;

import java.util.List;

public class JadwalDetailDokterAdapter extends RecyclerView.Adapter<JadwalDetailDokterAdapter.ViewHolder> {

    Context context;
    List<DataItem> dataJadwal;

    public JadwalDetailDokterAdapter(Context context, List<DataItem> dataJadwal) {
        this.context = context;
        this.dataJadwal = dataJadwal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_detail_dokter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtHari.setText(dataJadwal.get(position).getJadwal());

    }

    @Override
    public int getItemCount() {
        return dataJadwal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHari;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHari = itemView.findViewById(R.id.text_hari_jadwal_detail_dokter);
        }
    }
}
