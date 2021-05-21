package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DokterPembayaranAdapter extends RecyclerView.Adapter<DokterPembayaranAdapter.DokterPembayaranViewHolder> {

    Context context;
    List<DataItem> dataDokter;

    @NonNull
    @NotNull
    @Override
    public DokterPembayaranViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_atur_jadwal, parent, false);
        return new DokterPembayaranViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DokterPembayaranViewHolder holder, int position) {

        final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/"+dataDokter.get(position).getImg();

        Glide.with(context)
                .load(url_image)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText("Konsultasi Dengan Dr. "+dataDokter.get(position).getNama());
        holder.txtSpesialis.setText("Spesialis "+dataDokter.get(position).getSpesialis());

    }

    @Override
    public int getItemCount() {
        return dataDokter.size();
    }

    public class DokterPembayaranViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama, txtSpesialis;

        public DokterPembayaranViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_dokter_atur_jadwal);
            txtNama = itemView.findViewById(R.id.text_nama_atur_jadwal);
            txtSpesialis = itemView.findViewById(R.id.text_spesialis_atur_jadwal);
        }
    }
}
