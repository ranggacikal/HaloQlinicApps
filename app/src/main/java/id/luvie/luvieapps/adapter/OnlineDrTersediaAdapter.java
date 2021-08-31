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
import id.luvie.luvieapps.model.dokterOnTersedia.DataItem;

import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class OnlineDrTersediaAdapter extends RecyclerView.Adapter<OnlineDrTersediaAdapter.OnlineTersediaViewHolder> {

    Context context;
    List<id.luvie.luvieapps.model.dokterOnTersedia.DataItem> dataDokterTersedia;

    public OnlineDrTersediaAdapter(Context context, List<id.luvie.luvieapps.model.dokterOnTersedia.DataItem> dataDokterTersedia) {
        this.context = context;
        this.dataDokterTersedia = dataDokterTersedia;
    }

    @NonNull
    @NotNull
    @Override
    public OnlineTersediaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new OnlineTersediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OnlineTersediaViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String link = "https://luvie.co.id/file/dokter/profile/";
        String image = dataDokterTersedia.get(position).getImg();
        String nama = dataDokterTersedia.get(position).getNama();
        String spesialis = dataDokterTersedia.get(position).getSpesialis();
        String pengalaman = (String) dataDokterTersedia.get(position).getPengalaman();
        int harga = Integer.parseInt(dataDokterTersedia.get(position).getBiaya());

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
                        String status = dataDokterTersedia.get(position).getStatus();

                        if (status.equals("3")){
                            Toast.makeText(context, "Mohon maaf Dokter sedang melayani pasien, " +
                                    "silakan menghubungi kembali atau konsultasi dengan dokter lain.", Toast.LENGTH_LONG).show();
                        }else {

                            Intent intent = new Intent(context, DetailDokterActivity.class);
                            intent.putExtra("id_dokter", dataDokterTersedia.get(position).getIdDokter());
                            intent.putExtra("status", "online");
                            context.startActivity(intent);
                        }
                    }
                });

        PushDownAnim.setPushDownAnimTo(holder.btnChat)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String status = dataDokterTersedia.get(position).getStatus();

                        if (status.equals("3")){
                            Toast.makeText(context, "Mohon maaf Dokter sedang melayani pasien, " +
                                    "silakan menghubungi kembali atau konsultasi dengan dokter lain.", Toast.LENGTH_LONG).show();
                        }else {
                            Intent intent = new Intent(context, DetailDokterActivity.class);
                            intent.putExtra("id_dokter", dataDokterTersedia.get(position).getIdDokter());
                            intent.putExtra("status", "online");
                            context.startActivity(intent);
                        }
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataDokterTersedia.size();
    }

    public void addList(List<DataItem> list_data){
        dataDokterTersedia.addAll(list_data);
        notifyDataSetChanged();
    }

    public void clear(){
        dataDokterTersedia.clear();
        notifyDataSetChanged();
    }

    public class OnlineTersediaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNama, txtSpesialis, txtPengalaman, txtHarga;
        Button btnChat;

        public OnlineTersediaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter);
            txtNama = itemView.findViewById(R.id.text_item_nama_dokter);
            txtSpesialis = itemView.findViewById(R.id.text_item_spesialis_dokter);
            txtPengalaman = itemView.findViewById(R.id.jumlah_tahun_dokter);
            txtHarga = itemView.findViewById(R.id.text_harga_dokter);
            btnChat = itemView.findViewById(R.id.btn_item_chat_dokter);
        }
    }
}
