package id.luvie.luvieapps.adapter;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.luvie.luvieapps.DetailDokterActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.dokterMitra.DataItem;

public class DokterMitraTersediaAdapter extends RecyclerView.Adapter<DokterMitraTersediaAdapter.
        DokterMitraTersediaViewHolder> {

    Context context;
    List<DataItem> dataDokter;

    public DokterMitraTersediaAdapter(Context context, List<DataItem> dataDokter) {
        this.context = context;
        this.dataDokter = dataDokter;
    }

    @NonNull
    @Override
    public DokterMitraTersediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter_online, parent, false);
        return new DokterMitraTersediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DokterMitraTersediaViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final String url_image = "https://luvie.co.id/file/dokter/profile/";

        Glide.with(context)
                .load(url_image+dataDokter.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNama.setText(dataDokter.get(position).getNama());

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailDokterActivity.class);
                        intent.putExtra("id_dokter", dataDokter.get(position).getIdDokter());
                        intent.putExtra("status", "offline");
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return dataDokter.size();
    }

    public class DokterMitraTersediaViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNama;

        public DokterMitraTersediaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter_online);
            txtNama = itemView.findViewById(R.id.text_item_nama_dokter_aktif);
        }
    }
}
