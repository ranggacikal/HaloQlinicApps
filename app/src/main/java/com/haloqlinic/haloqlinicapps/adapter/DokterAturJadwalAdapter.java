package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DokterAturJadwalAdapter extends RecyclerView.Adapter<DokterAturJadwalAdapter.DokterAturJadwalViewHolder> {

    Context context;
    List<DataItem> dataDokter;

    public DokterAturJadwalAdapter(Context context, List<DataItem> dataDokter) {
        this.context = context;
        this.dataDokter = dataDokter;
    }

    @NonNull
    @NotNull
    @Override
    public DokterAturJadwalViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_atur_jadwal, parent, false);
        return new DokterAturJadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DokterAturJadwalViewHolder holder, int position) {

        holder.txtNama.setText("Konsultasi Dengan Dr. "+dataDokter.get(position).getNama());
        holder.txtSpesialis.setText("Spesialis "+dataDokter.get(position).getSpesialis());

    }

    @Override
    public int getItemCount() {
        return dataDokter.size();
    }

    public class DokterAturJadwalViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama, txtSpesialis;

        public DokterAturJadwalViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_dokter_atur_jadwal);
            txtNama = itemView.findViewById(R.id.text_nama_atur_jadwal);
            txtSpesialis = itemView.findViewById(R.id.text_spesialis_atur_jadwal);
        }
    }
}