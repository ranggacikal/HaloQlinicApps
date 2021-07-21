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
import com.haloqlinic.haloqlinicapps.model.dokterMitra.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class DokterMitraAdapter extends RecyclerView.Adapter<DokterMitraAdapter.DokterMitraViewholder> {

    Context context;
    List<DataItem> dataDokter;

    public DokterMitraAdapter(Context context, List<DataItem> dataDokter) {
        this.context = context;
        this.dataDokter = dataDokter;
    }

    @NonNull
    @NotNull
    @Override
    public DokterMitraViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_mitra,
                parent, false);

        return new DokterMitraViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DokterMitraViewholder holder, int position) {

        String link = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/";
        String image = dataDokter.get(position).getImg();

        Glide.with(context)
                .load(link+image)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText("Dr. "+dataDokter.get(position).getNama());

        PushDownAnim.setPushDownAnimTo(holder.txtBuatJadwal)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dataDokter.get(position).getIdDokter());
                        intent.putExtra("status", "offline");
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataDokter.size();
    }

    public class DokterMitraViewholder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama, txtBuatJadwal;

        public DokterMitraViewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_dokter_mitra);
            txtNama = itemView.findViewById(R.id.text_nama_dokter_mitra);
            txtBuatJadwal = itemView.findViewById(R.id.text_buat_jadwal_dokter_mitra);
        }
    }
}
