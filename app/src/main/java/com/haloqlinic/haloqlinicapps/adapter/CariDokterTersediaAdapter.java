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
import com.haloqlinic.haloqlinicapps.model.cariDokter.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CariDokterTersediaAdapter extends RecyclerView.Adapter<CariDokterTersediaAdapter.CariDokterTersediaViewHolder> {

    Context context;
    List<DataItem> dataCari;

    public CariDokterTersediaAdapter(Context context, List<DataItem> dataCari) {
        this.context = context;
        this.dataCari = dataCari;
    }

    @NonNull
    @NotNull
    @Override
    public CariDokterTersediaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_tersedia, parent, false);
        return new CariDokterTersediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CariDokterTersediaViewHolder holder, int position) {

        final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/";

        Glide.with(context)
                .load(url_image+dataCari.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText(dataCari.get(position).getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDokterActivity.class);
                intent.putExtra("id_dokter", dataCari.get(position).getIdDokter());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataCari.size();
    }

    public class CariDokterTersediaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama;

        public CariDokterTersediaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter_tersedia);
            txtNama = itemView.findViewById(R.id.text_item_nama_dokter_tersedia);
        }
    }
}
