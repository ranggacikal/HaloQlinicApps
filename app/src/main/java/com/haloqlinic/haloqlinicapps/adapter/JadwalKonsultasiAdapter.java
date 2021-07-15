package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.ChatActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.listKonsultasi.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class JadwalKonsultasiAdapter extends RecyclerView.Adapter<JadwalKonsultasiAdapter.JadwalKonsultasiViewHolder> {

    Context context;
    List<DataItem> dataKonsultasi;

    public JadwalKonsultasiAdapter(Context context, List<DataItem> dataKonsultasi) {
        this.context = context;
        this.dataKonsultasi = dataKonsultasi;
    }

    @NonNull
    @NotNull
    @Override
    public JadwalKonsultasiViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_konsultasi, parent, false);
        return new JadwalKonsultasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JadwalKonsultasiViewHolder holder, int position) {

        String nama_dokter = dataKonsultasi.get(position).getNama();
        String image = dataKonsultasi.get(position).getImg();
        String spesialis = dataKonsultasi.get(position).getSpesialis();
        String tanggal = dataKonsultasi.get(position).getJadwal();
        String status_konsultasi = dataKonsultasi.get(position).getStatusTransaksi();

        final String url_image = "https://aplikasicerdas.net/haloqlinic/file/dokter/profile/"+image;

        Glide.with(context)
                .load(url_image)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText("Dr. "+nama_dokter);
        holder.txtSpesialis.setText("Spesialis "+spesialis);
        holder.txtTanggal.setText(tanggal);

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String mulai_konsultasi = dataKonsultasi.get(position).getMulaiKonsultasi();
                        String batas_konsultasi = dataKonsultasi.get(position).getBatasKonsultasi();

                        Log.d("checkWaktuKonsul", "mulai: "+mulai_konsultasi);
                        Log.d("checkWaktuKonsul", "akhir: "+batas_konsultasi);

                        if (mulai_konsultasi.equals("") && batas_konsultasi.equals("")){

                            Toast.makeText(context, "Jadwal belum terbuka", Toast.LENGTH_SHORT).show();

                        }else if (mulai_konsultasi != null || !mulai_konsultasi.equals("") && batas_konsultasi != null ||
                                !batas_konsultasi.equals("") && status_konsultasi.equals("0")) {
                            Intent intentChat = new Intent(context, ChatActivity.class);
                            intentChat.putExtra("token", dataKonsultasi.get(position).getToken());
                            intentChat.putExtra("nama_dokter", dataKonsultasi.get(position).getNama());
                            intentChat.putExtra("image", dataKonsultasi.get(position).getImg());
                            intentChat.putExtra("spesialis", dataKonsultasi.get(position).getSpesialis());
                            intentChat.putExtra("player_id", dataKonsultasi.get(position).getPlayerId());
                            context.startActivity(intentChat);
                        }else{
                            Toast.makeText(context, "Anda telah menyelesaikan chat ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataKonsultasi.size();
    }

    public class JadwalKonsultasiViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNama, txtSpesialis, txtTanggal;

        public JadwalKonsultasiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_jadwal_konsultasi);
            txtNama = itemView.findViewById(R.id.text_item_nama_jadwal_konsultasi);
            txtSpesialis = itemView.findViewById(R.id.text_item_spesialis_jadwal_konsultasi);
            txtTanggal = itemView.findViewById(R.id.text_item_tanggal_jadwal_konsultasi);
        }
    }
}
