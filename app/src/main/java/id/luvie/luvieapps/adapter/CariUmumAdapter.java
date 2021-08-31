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

public class CariUmumAdapter extends RecyclerView.Adapter<CariUmumAdapter.CariUmumViewHolder> {

    Context context;
    List<DataItem> dataCari;

    public CariUmumAdapter(Context context, List<DataItem> dataCari) {
        this.context = context;
        this.dataCari = dataCari;
    }

    @NonNull
    @NotNull
    @Override
    public CariUmumViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new CariUmumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CariUmumViewHolder holder, int position) {

        final String url_image = "https://luvie.co.id/file/dokter/profile/";
        int biaya = Integer.parseInt(dataCari.get(position).getBiaya());
        String pengalaman = (String) dataCari.get(position).getPengalaman();

        Glide.with(context)
                .load(url_image+dataCari.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNamaDokter.setText(dataCari.get(position).getNama());
        holder.txtSpesialisDokter.setText("Spesialis "+dataCari.get(position).getSpesialis());
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
                        intent.putExtra("id_dokter", dataCari.get(position).getIdDokter());
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

    public class CariUmumViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDokter;
        TextView txtNamaDokter, txtSpesialisDokter, txtHarga, txtPengalaman;
        Button btnChat;

        public CariUmumViewHolder(@NonNull @NotNull View itemView) {
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
