package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ListItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WaktuAturJadwalAdapter extends RecyclerView.Adapter<WaktuAturJadwalAdapter.WaktuAturJadwalViewHolder> {

    Context context;
    List<ListItem> dataJadwal;

    public WaktuAturJadwalAdapter(Context context, List<ListItem> dataJadwal) {
        this.context = context;
        this.dataJadwal = dataJadwal;
    }

    @NonNull
    @NotNull
    @Override
    public WaktuAturJadwalViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_detail_dokter, parent, false);
        return new WaktuAturJadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WaktuAturJadwalViewHolder holder, int position) {
        holder.txtHari.setText(dataJadwal.get(position).getJadwal());
    }

    @Override
    public int getItemCount() {
        return dataJadwal.size();
    }

    public class WaktuAturJadwalViewHolder extends RecyclerView.ViewHolder {

        TextView txtHari;

        public WaktuAturJadwalViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtHari = itemView.findViewById(R.id.text_hari_jadwal_detail_dokter);
        }
    }
}
