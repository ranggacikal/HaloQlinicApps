package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import id.luvie.luvieapps.DetailDokterActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.listDokterAktifHome.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DokterAktifHomeAdapter extends RecyclerView.Adapter<DokterAktifHomeAdapter.DokterAktifHomeViewHolder> {

    Context context;
    List<DataItem> dokterAktifList;

    public DokterAktifHomeAdapter(Context context, List<DataItem> dokterAktifList) {
        this.context = context;
        this.dokterAktifList = dokterAktifList;
    }

    @NonNull
    @NotNull
    @Override
    public DokterAktifHomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_online, parent, false);
        return new DokterAktifHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DokterAktifHomeViewHolder holder, int position) {
        final String url_image = "https://luvie.co.id/file/dokter/profile/";

        Glide.with(context)
                .load(url_image+dokterAktifList.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText(dokterAktifList.get(position).getNama());
        
        String status = dokterAktifList.get(position).getStatus();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (status.equals("3")){
                    Toast.makeText(context, "Mohon maaf Dokter sedang melayani pasien, " +
                            "silakan menghubungi kembali atau konsultasi dengan dokter lain.", Toast.LENGTH_LONG).show();
                }else {

                    Intent intent = new Intent(context, DetailDokterActivity.class);
                    intent.putExtra("id_dokter", dokterAktifList.get(position).getIdDokter());
                    intent.putExtra("status", "online");
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dokterAktifList.size();
    }

    public class DokterAktifHomeViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNama;

        public DokterAktifHomeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter_online);
            txtNama = itemView.findViewById(R.id.text_item_nama_dokter_aktif);
        }
    }
}
