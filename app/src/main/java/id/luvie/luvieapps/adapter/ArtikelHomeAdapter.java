package id.luvie.luvieapps.adapter;

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
import id.luvie.luvieapps.DetailArtikelActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.artikel.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class ArtikelHomeAdapter extends RecyclerView.Adapter<ArtikelHomeAdapter.ArtikelHomeViewHolder> {

    Context context;
    List<DataItem> dataArtikel;

    public ArtikelHomeAdapter(Context context, List<DataItem> dataArtikel) {
        this.context = context;
        this.dataArtikel = dataArtikel;
    }

    @NonNull
    @NotNull
    @Override
    public ArtikelHomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel, parent, false);
        return new ArtikelHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArtikelHomeViewHolder holder, int position) {

        String link_image = "https://luvie.co.id/img/artikel/";

        Glide.with(context)
                .load(link_image+dataArtikel.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgArtikel);

        holder.txtJudul.setText(dataArtikel.get(position).getJudul());
        holder.txtPenulis.setText(dataArtikel.get(position).getCreatedBy());

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailArtikelActivity.class);
                        intent.putExtra("id_artikel", dataArtikel.get(position).getIdArtikel());
                        context.startActivity(intent);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return dataArtikel.size();
    }

    public class ArtikelHomeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgArtikel;
        TextView txtJudul, txtPenulis;

        public ArtikelHomeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgArtikel = itemView.findViewById(R.id.img_artikel);
            txtJudul = itemView.findViewById(R.id.text_judul_artikel);
            txtPenulis = itemView.findViewById(R.id.text_penulis_artikel);
        }
    }
}
