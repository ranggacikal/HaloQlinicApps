package id.luvie.luvieapps.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import id.luvie.luvieapps.DetailProdukMitraActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.produkMitra.DataItem;

import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class PromoMitraAdapter extends RecyclerView.Adapter<PromoMitraAdapter.PromoMitraViewHolder> {

    Context context;
    List<id.luvie.luvieapps.model.produkMitra.DataItem> dataPromo;

    public PromoMitraAdapter(Context context, List<DataItem> dataPromo) {
        this.context = context;
        this.dataPromo = dataPromo;
    }

    @NonNull
    @NotNull
    @Override
    public PromoMitraViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new PromoMitraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PromoMitraViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String link_image = dataPromo.get(position).getImg();
        int harga = Integer.parseInt(dataPromo.get(position).getHarga());
        int hargaPromo = Integer.parseInt(dataPromo.get(position).getHargaJual());


        holder.txtHarga.setVisibility(View.GONE);
        holder.linearDiskon.setVisibility(View.VISIBLE);

        Glide.with(context)
                .load(link_image)
                .into(holder.imgProduk);

        holder.txtNama.setText(dataPromo.get(position).getNamaProduk());
        holder.txtHargaAwal.setText("Rp" + NumberFormat.getInstance().format(harga));
        holder.txtHargaAwal.setPaintFlags(holder.txtHargaAwal.getPaintFlags() |
                Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtHargaAwal.setVisibility(View.GONE);
        holder.txtHargaDiskon.setText("Rp" + NumberFormat.getInstance().format(hargaPromo));

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailProdukMitraActivity.class);
                        intent.putExtra("id_produk", dataPromo.get(position).getIdProduk());
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataPromo.size();
    }

    public class PromoMitraViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNama, txtHarga, txtHargaAwal, txtHargaDiskon;
        LinearLayout linearDiskon;

        public PromoMitraViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_produk);
            txtNama = itemView.findViewById(R.id.text_item_nama_produk);
            txtHarga = itemView.findViewById(R.id.text_item_harga_produk);
            linearDiskon = itemView.findViewById(R.id.linear_item_diskon_produk);
            txtHargaAwal = itemView.findViewById(R.id.text_item_harga_produk_awal);
            txtHargaDiskon = itemView.findViewById(R.id.text_item_harga_produk_diskon);
        }
    }
}
