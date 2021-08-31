package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import id.luvie.luvieapps.DetailDokterActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.dokterSpesialis.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class BuatJadwalSpesialisAdapter extends RecyclerView.Adapter<BuatJadwalSpesialisAdapter
        .BuatjadwalSpesialisViewHolder> {

    Context context;
    List<DataItem> dataDokter;

    public BuatJadwalSpesialisAdapter(Context context, List<DataItem> dataDokter) {
        this.context = context;
        this.dataDokter = dataDokter;
    }

    @NonNull
    @NotNull
    @Override
    public BuatjadwalSpesialisViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new BuatjadwalSpesialisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BuatjadwalSpesialisViewHolder holder, int position) {
        String link = "https://luvie.co.id/file/dokter/profile/";
        String image = dataDokter.get(position).getImg();
        String nama = dataDokter.get(position).getNama();
        String spesialis = dataDokter.get(position).getSpesialis();
        String pengalaman = (String) dataDokter.get(position).getPengalaman();
        int harga = Integer.parseInt(dataDokter.get(position).getBiaya());

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

                        String status = dataDokter.get(position).getStatus();

                        if (status.equals("2")){
                            Toast.makeText(context, "Dokter yang anda pilih sudah full booked, silahkan pilih dokter yang lain",
                                    Toast.LENGTH_SHORT).show();
                        }else {

                            Intent intent = new Intent(context, DetailDokterActivity.class);
                            intent.putExtra("id_dokter", dataDokter.get(position).getIdDokter());
                            intent.putExtra("status", "offline");
                            context.startActivity(intent);

                        }
                    }
                });

        PushDownAnim.setPushDownAnimTo(holder.btnBuatJadwal)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String status = dataDokter.get(position).getStatus();

                        if (status.equals("2")){
                            Toast.makeText(context, "Dokter yang anda pilih sudah full booked, silahkan pilih dokter yang lain",
                                    Toast.LENGTH_SHORT).show();
                        }else {

                            Intent intent = new Intent(context, DetailDokterActivity.class);
                            intent.putExtra("id_dokter", dataDokter.get(position).getIdDokter());
                            intent.putExtra("status", "offline");
                            context.startActivity(intent);
                        }
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataDokter.size();
    }

    public void addList(List<DataItem> list_data){
        dataDokter.addAll(list_data);
        notifyDataSetChanged();
    }

    public void clear(){
        dataDokter.clear();
        notifyDataSetChanged();
    }

    public class BuatjadwalSpesialisViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama, txtSpesialis, txtPengalaman, txtHarga;
        Button btnChat, btnBuatJadwal;

        public BuatjadwalSpesialisViewHolder(@NonNull @NotNull View itemView) {
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
