package id.luvie.luvieapps.adapter;

import android.annotation.SuppressLint;
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
import id.luvie.luvieapps.model.listDokterumum.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class DokterUmumAdapter extends RecyclerView.Adapter<DokterUmumAdapter.DokterUmumViewHolder> {

    Context context;
    List<DataItem> dokterList;

    public DokterUmumAdapter(Context context, List<DataItem> dokterList) {
        this.context = context;
        this.dokterList = dokterList;
    }

    @NonNull
    @NotNull
    @Override
    public DokterUmumViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new DokterUmumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DokterUmumViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final String url_image = "https://luvie.co.id/file/dokter/profile/";

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

                        String status = dokterList.get(position).getStatus();

                        if (status.equals("2")){
                            Toast.makeText(context, "Jadwal Belum tersedia",
                                    Toast.LENGTH_SHORT).show();
                        }else {

                            Intent intent = new Intent(context, DetailDokterActivity.class);
                            intent.putExtra("id_dokter", dokterList.get(position).getIdDokter());
                            intent.putExtra("status", "offline");
                            context.startActivity(intent);

                        }
                    }
                });

        PushDownAnim.setPushDownAnimTo(holder.btnChat)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String status = dokterList.get(position).getStatus();

                        if (status.equals("2")){
                            Toast.makeText(context, "Dokter yang anda pilih sudah full booked, silahkan pilih dokter yang lain",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(context, DetailDokterActivity.class);
                            intent.putExtra("id_dokter", dokterList.get(position).getIdDokter());
                            intent.putExtra("status", "offline");
                            context.startActivity(intent);
                        }
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

    public class DokterUmumViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNamaDokter, txtSpesialisDokter, txtHarga, txtPengalaman;
        Button btnChat;

        public DokterUmumViewHolder(@NonNull @NotNull View itemView) {
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
