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
import com.haloqlinic.haloqlinicapps.model.mitraKlinik.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MitraHomeAdapter extends RecyclerView.Adapter<MitraHomeAdapter.MitraHomeViewHolder> {

    Context context;
    List<DataItem> dataMitra;

    public MitraHomeAdapter(Context context, List<DataItem> dataMitra) {
        this.context = context;
        this.dataMitra = dataMitra;
    }

    @NonNull
    @NotNull
    @Override
    public MitraHomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mitra_home, parent, false);
        return new MitraHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MitraHomeViewHolder holder, int position) {

        String url = (String) dataMitra.get(position).getImg();

        Glide.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgMitra);

        holder.txtNama.setText(dataMitra.get(position).getNamaToko());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataMitra.size();
    }

    public class MitraHomeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMitra;
        TextView txtNama;

        public MitraHomeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgMitra = itemView.findViewById(R.id.img_item_mitra_home);
            txtNama = itemView.findViewById(R.id.text_nama_mitra_home);
        }
    }
}
