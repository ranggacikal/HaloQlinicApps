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

public class MitraKlinikAdapter extends RecyclerView.Adapter<MitraKlinikAdapter.MitraKlinikViewHolder> {

    Context context;
    List<DataItem> dataMitra;

    public MitraKlinikAdapter(Context context, List<DataItem> dataMitra) {
        this.context = context;
        this.dataMitra = dataMitra;
    }

    @NonNull
    @NotNull
    @Override
    public MitraKlinikViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mitra_klinik, parent, false);
        return new MitraKlinikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MitraKlinikViewHolder holder, int position) {

        String url = (String) dataMitra.get(position).getImg();

        Glide.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgMitra);

        holder.txtNamaMitra.setText(dataMitra.get(position).getNamaToko());

    }

    @Override
    public int getItemCount() {
        return dataMitra.size();
    }

    public class MitraKlinikViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMitra;
        TextView txtNamaMitra;

        public MitraKlinikViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imgMitra = itemView.findViewById(R.id.img_item_mitra_klinik);
            txtNamaMitra = itemView.findViewById(R.id.text_item_mitra_klinik);
        }
    }
}
