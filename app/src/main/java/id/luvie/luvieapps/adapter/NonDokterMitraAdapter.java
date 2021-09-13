package id.luvie.luvieapps.adapter;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import android.annotation.SuppressLint;
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
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import id.luvie.luvieapps.DetailDokterActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.nonDokter.DataItem;

public class NonDokterMitraAdapter extends RecyclerView.Adapter<NonDokterMitraAdapter.NonDokterMitraViewHolder> {

    Context context;
    List<DataItem> dataNonDokter;

    public NonDokterMitraAdapter(Context context, List<DataItem> dataNonDokter) {
        this.context = context;
        this.dataNonDokter = dataNonDokter;
    }

    @NonNull
    @Override
    public NonDokterMitraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_mitra,
                parent, false);
        return new NonDokterMitraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NonDokterMitraViewHolder holder, @SuppressLint("RecyclerView") int position) {

        final String url_image = "https://luvie.co.id/file/dokter/profile/";

        String status = dataNonDokter.get(position).getStatus();

        if (status.equals("1")){
            holder.txtChat.setVisibility(View.VISIBLE);
            holder.txtBuatJadwal.setVisibility(View.GONE);
        }else if (status.equals("2")){
            holder.txtChat.setVisibility(View.GONE);
            holder.txtBuatJadwal.setVisibility(View.VISIBLE);
        }else{
            holder.txtChat.setVisibility(View.GONE);
            holder.txtBuatJadwal.setVisibility(View.VISIBLE);
        }

        Glide.with(context)
                .load(url_image+dataNonDokter.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText(dataNonDokter.get(position).getNama());

        PushDownAnim.setPushDownAnimTo(holder.txtBuatJadwal)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dataNonDokter.get(position).getIdDokter());
                        intent.putExtra("status", "offline");
                        context.startActivity(intent);
                    }
                });

        PushDownAnim.setPushDownAnimTo(holder.txtChat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dataNonDokter.get(position).getIdDokter());
                        intent.putExtra("status", "online");
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataNonDokter.size();
    }

    public class NonDokterMitraViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama, txtBuatJadwal, txtChat;

        public NonDokterMitraViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_dokter_mitra);
            txtNama = itemView.findViewById(R.id.text_nama_dokter_mitra);
            txtBuatJadwal = itemView.findViewById(R.id.text_buat_jadwal_dokter_mitra);
            txtChat = itemView.findViewById(R.id.text_chat_dokter_mitra);
        }
    }
}
