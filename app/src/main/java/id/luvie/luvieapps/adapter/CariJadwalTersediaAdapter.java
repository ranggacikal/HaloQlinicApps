package id.luvie.luvieapps.adapter;

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
import id.luvie.luvieapps.DetailDokterActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.cariDokter.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class CariJadwalTersediaAdapter extends RecyclerView.Adapter<CariJadwalTersediaAdapter.CariJadwalTersediaViewHolder> {

    Context context;
    List<DataItem> dataCari;

    public CariJadwalTersediaAdapter(Context context, List<DataItem> dataCari) {
        this.context = context;
        this.dataCari = dataCari;
    }

    @NonNull
    @NotNull
    @Override
    public CariJadwalTersediaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new CariJadwalTersediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CariJadwalTersediaViewHolder holder, int position) {

        String link = "https://luvie.co.id/file/dokter/profile/";
        String image = dataCari.get(position).getImg();
        String nama = dataCari.get(position).getNama();
        String spesialis = dataCari.get(position).getSpesialis();
        String pengalaman = (String) dataCari.get(position).getPengalaman();
        int harga = Integer.parseInt(dataCari.get(position).getBiaya());

        holder.btnChat.setVisibility(View.GONE);
        holder.btnBuatJadwal.setVisibility(View.VISIBLE);

        Glide.with(context)
                .load(link+image)
                .error(R.drawable.icon_dokter)
                .into(holder.imgDokter);

        holder.txtNama.setText("Dr. "+nama);
        holder.txtSpesialis.setText("Spesialis "+spesialis);

        if (pengalaman == null){
            holder.txtPengalaman.setText("null pengalaman");
        }else{
            holder.txtPengalaman.setText(pengalaman+" tahun");
        }

        holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(harga));

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dataCari.get(position).getIdDokter());
                        intent.putExtra("status", "offline");
                        context.startActivity(intent);
                    }
                });

        PushDownAnim.setPushDownAnimTo(holder.btnBuatJadwal)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dataCari.get(position).getIdDokter());
                        intent.putExtra("status", "offline");
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataCari.size();
    }

    public class CariJadwalTersediaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama, txtSpesialis, txtPengalaman, txtHarga;
        Button btnChat, btnBuatJadwal;

        public CariJadwalTersediaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter);
            txtNama = itemView.findViewById(R.id.text_item_nama_dokter);
            txtSpesialis = itemView.findViewById(R.id.text_item_spesialis_dokter);
            txtPengalaman = itemView.findViewById(R.id.jumlah_tahun_dokter);
            txtHarga = itemView.findViewById(R.id.text_harga_dokter);
            btnChat = itemView.findViewById(R.id.btn_item_chat_dokter);
            btnBuatJadwal = itemView.findViewById(R.id.btn_item_buat_jadwal_dokter);
        }
    }
}
