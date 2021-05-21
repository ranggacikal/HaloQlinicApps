package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.DetailDokterActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.DokterViewHolder> {

    Context context;
    List<DataItem> dokterList;

    public DokterAdapter(Context context, List<DataItem> dokterList) {
        this.context = context;
        this.dokterList = dokterList;
    }

    @NonNull
    @Override
    public DokterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new DokterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DokterViewHolder holder, int position) {

        final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/";

        Glide.with(context)
                .load(url_image+dokterList.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNamaDokter.setText(dokterList.get(position).getNama());
        holder.txtSpesialisDokter.setText("Spesialis "+dokterList.get(position).getSpesialis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDokterActivity.class);
                intent.putExtra("id_dokter", dokterList.get(position).getIdDokter());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dokterList.size();
    }

    public class DokterViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNamaDokter, txtSpesialisDokter;

        public DokterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter);
            txtNamaDokter = itemView.findViewById(R.id.text_item_nama_dokter);
            txtSpesialisDokter = itemView.findViewById(R.id.text_item_spesialis_dokter);
        }
    }
}
