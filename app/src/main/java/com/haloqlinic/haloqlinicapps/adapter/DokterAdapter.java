package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.DetailDokterActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.listDokter.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.text.NumberFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

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

        int biaya = Integer.parseInt(dokterList.get(position).getBiaya());
        String pengalaman = (String) dokterList.get(position).getPengalaman();

        Glide.with(context)
                .load(url_image+dokterList.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNamaDokter.setText(dokterList.get(position).getNama());
        holder.txtSpesialisDokter.setText("Spesialis "+dokterList.get(position).getSpesialis());
        holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(biaya));

        if (pengalaman != null){
            holder.txtPengalaman.setText(pengalaman+" tahun");
        }else{
            holder.txtPengalaman.setText("null tahun");
        }

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dokterList.get(position).getIdDokter());
                        intent.putExtra("status", "offline");
                        context.startActivity(intent);
                    }
                });

        PushDownAnim.setPushDownAnimTo(holder.btnChat)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dokterList.get(position).getIdDokter());
                        intent.putExtra("status", "offline");
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dokterList.size();
    }

    public void addList(List<DataItem> list_data){
        dokterList.addAll(list_data);
        notifyDataSetChanged();
    }

    public void clear(){
        dokterList.clear();
        notifyDataSetChanged();
    }

    public class DokterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNamaDokter, txtSpesialisDokter, txtHarga, txtPengalaman;
        Button btnChat;

        public DokterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter);
            txtNamaDokter = itemView.findViewById(R.id.text_item_nama_dokter);
            txtSpesialisDokter = itemView.findViewById(R.id.text_item_spesialis_dokter);
            txtHarga = itemView.findViewById(R.id.text_harga_dokter);
            txtPengalaman = itemView.findViewById(R.id.jumlah_tahun_dokter);
            btnChat = itemView.findViewById(R.id.btn_item_chat_dokter);
        }
    }
}
