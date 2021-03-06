package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SkincareActivity;
import id.luvie.luvieapps.model.kategoriProduk.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder> {

    Context context;
    List<DataItem> dataKategori;

    public KategoriAdapter(Context context, List<DataItem> dataKategori) {
        this.context = context;
        this.dataKategori = dataKategori;
    }

    @NonNull
    @NotNull
    @Override
    public KategoriViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori, parent, false);
        return new KategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull KategoriViewHolder holder, int position) {

        String link = "https://luvie.co.id/img/kategori/";

        String img = dataKategori.get(position).getIcon();
        String nama = dataKategori.get(position).getKategori();
        String id_kategori = dataKategori.get(position).getIdKategori();

        Glide.with(context)
                .load(link+img)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgKategori);

        holder.txtKategori.setText(nama);

        holder.cardKategori.setCardBackgroundColor(Color.parseColor(dataKategori.get(position).getColor()));

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, SkincareActivity.class);
                        intent.putExtra("id_kategori", id_kategori);
                        intent.putExtra("nama_kategori", nama);
                        intent.putExtra("img_kategori", link+img);
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataKategori.size();
    }

    public class KategoriViewHolder extends RecyclerView.ViewHolder {

        ImageView imgKategori;
        TextView txtKategori;
        CardView cardKategori;

        public KategoriViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgKategori = itemView.findViewById(R.id.img_item_kategori);
            txtKategori = itemView.findViewById(R.id.text_item_nama_kategori);
            cardKategori = itemView.findViewById(R.id.card_kategori_produk);
        }
    }
}
