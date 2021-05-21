package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.AturJadwalActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ListItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WaktuAturJadwalAdapter extends RecyclerView.Adapter<WaktuAturJadwalAdapter.WaktuAturJadwalViewHolder> {

    Context context;
    List<ListItem> dataJadwal;
    AturJadwalActivity aturJadwalActivity;
    int row_index;

    public WaktuAturJadwalAdapter(Context context, List<ListItem> dataJadwal, AturJadwalActivity aturJadwalActivity) {
        this.context = context;
        this.dataJadwal = dataJadwal;
        this.aturJadwalActivity = aturJadwalActivity;
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

        holder.txtHari.setBackground(ContextCompat.getDrawable(context, R.drawable.background_circle_white));
        holder.txtHari.setTextColor(Color.parseColor("#000000"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aturJadwalActivity.jadwal_dokter = dataJadwal.get(position).getJadwal();
                aturJadwalActivity.id_jadwal = dataJadwal.get(position).getId();
                row_index = position;
//                if (row_index==position){
//                    holder.txtHari.setBackground(ContextCompat.getDrawable(context, R.drawable.background_circle_button_green));
//                    holder.txtHari.setTextColor(Color.parseColor("#FFFFFF"));
//                    notifyDataSetChanged();
//                }
                Toast.makeText(context, "jadwal: "+aturJadwalActivity.jadwal_dokter+" id: "+
                                aturJadwalActivity.id_jadwal, Toast.LENGTH_SHORT).show();
            }
        });


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
