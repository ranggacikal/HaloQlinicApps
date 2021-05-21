package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.DetailDokterActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.listDokterAktif.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DokterTersediaAdapter extends RecyclerView.Adapter<DokterTersediaAdapter.DokterTersediaViewHolder> {

    Context context;
    List<DataItem> dataDokter;

    public DokterTersediaAdapter(Context context, List<DataItem> dataDokter) {
        this.context = context;
        this.dataDokter = dataDokter;
    }

    @NonNull
    @NotNull
    @Override
    public DokterTersediaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_tersedia, parent, false);
        return new DokterTersediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DokterTersediaViewHolder holder, int position) {

        String urlImage = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/"+dataDokter.get(position).getImg();

        Glide.with(context)
                .load(urlImage)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText(dataDokter.get(position).getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDokterActivity.class);
                intent.putExtra("id_dokter", dataDokter.get(position).getIdDokter());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataDokter.size();
    }

    public class DokterTersediaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama;

        public DokterTersediaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter_tersedia);
            txtNama = itemView.findViewById(R.id.text_item_nama_dokter_tersedia);
        }
    }
}
