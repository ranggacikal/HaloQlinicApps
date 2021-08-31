package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import id.luvie.luvieapps.DetailDokterActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.listDokterAktif.DataItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DokterAktifAdapter extends RecyclerView.Adapter<DokterAktifAdapter.DokterAktifViewHolder> {

    Context context;
    List<DataItem> dokterAktifList;

    public DokterAktifAdapter(Context context, List<DataItem> dokterAktifList) {
        this.context = context;
        this.dokterAktifList = dokterAktifList;
    }

    @NonNull
    @Override
    public DokterAktifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_online, parent, false);
        return new DokterAktifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DokterAktifViewHolder holder, int position) {

        final String url_image = "https://luvie.co.id/file/dokter/profile/";

        Glide.with(context)
                .load(url_image+dokterAktifList.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText(dokterAktifList.get(position).getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDokterActivity.class);
                intent.putExtra("id_dokter", dokterAktifList.get(position).getIdDokter());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dokterAktifList.size();
    }

    public class DokterAktifViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNama;

        public DokterAktifViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter_online);
            txtNama = itemView.findViewById(R.id.text_item_nama_dokter_aktif);
        }
    }
}
