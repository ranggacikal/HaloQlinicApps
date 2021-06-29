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

import java.text.NumberFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CariSpesialisAdapter extends RecyclerView.Adapter<CariSpesialisAdapter.CariSpesialisViesHolder> {

    Context context;
    List<DataItem> dataCariDokter;

    public CariSpesialisAdapter(Context context, List<DataItem> dataCariDokter) {
        this.context = context;
        this.dataCariDokter = dataCariDokter;
    }

    @NonNull
    @NotNull
    @Override
    public CariSpesialisViesHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new CariSpesialisViesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CariSpesialisViesHolder holder, int position) {

        final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/";
        int biaya = Integer.parseInt(dataCariDokter.get(position).getBiaya());
        String pengalaman = (String) dataCariDokter.get(position).getPengalaman();

        Glide.with(context)
                .load(url_image+dataCariDokter.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNamaDokter.setText(dataCariDokter.get(position).getNama());
        holder.txtSpesialisDokter.setText("Spesialis "+dataCariDokter.get(position).getSpesialis());
        holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(biaya));

        if (pengalaman != null){
            holder.txtPengalaman.setText(pengalaman+" tahun");
        }else{
            holder.txtPengalaman.setText("null tahun");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDokterActivity.class);
                intent.putExtra("id_dokter", dataCariDokter.get(position).getIdDokter());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataCariDokter.size();
    }

    public class CariSpesialisViesHolder extends RecyclerView.ViewHolder {
        ImageView imgDokter;
        TextView txtNamaDokter, txtSpesialisDokter, txtHarga, txtPengalaman;
        public CariSpesialisViesHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter);
            txtNamaDokter = itemView.findViewById(R.id.text_item_nama_dokter);
            txtSpesialisDokter = itemView.findViewById(R.id.text_item_spesialis_dokter);
            txtHarga = itemView.findViewById(R.id.text_harga_dokter);
            txtPengalaman = itemView.findViewById(R.id.jumlah_tahun_dokter);
        }
    }
}
