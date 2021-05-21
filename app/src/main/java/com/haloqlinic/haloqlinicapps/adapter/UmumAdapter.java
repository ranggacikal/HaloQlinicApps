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

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UmumAdapter extends RecyclerView.Adapter<UmumAdapter.UmumViewHolder> {

    Context context;
    List<DataItem> dataDokter;

    public UmumAdapter(Context context, List<DataItem> dataDokter) {
        this.context = context;
        this.dataDokter = dataDokter;
    }

    @NonNull
    @NotNull
    @Override
    public UmumViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new UmumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UmumViewHolder holder, int position) {
        final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/";

        Glide.with(context)
                .load(url_image+dataDokter.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNamaDokter.setText(dataDokter.get(position).getNama());
        holder.txtSpesialisDokter.setText("Spesialis "+dataDokter.get(position).getSpesialis());

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

    public class UmumViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNamaDokter, txtSpesialisDokter;

        public UmumViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter);
            txtNamaDokter = itemView.findViewById(R.id.text_item_nama_dokter);
            txtSpesialisDokter = itemView.findViewById(R.id.text_item_spesialis_dokter);
        }
    }
}
