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
import id.luvie.luvieapps.DetailProdukActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.cariProdukKategori.DataItem;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class CariProdukKategoriAdapter extends RecyclerView.Adapter<CariProdukKategoriAdapter.CariProdukKategoriViewHolder> {

    Context context;
    List<DataItem> dataCari;

    public CariProdukKategoriAdapter(Context context, List<DataItem> dataCari) {
        this.context = context;
        this.dataCari = dataCari;
    }

    @NonNull
    @NotNull
    @Override
    public CariProdukKategoriViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new CariProdukKategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CariProdukKategoriViewHolder holder, int position) {
        Glide.with(context)
                .load(dataCari.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduk);

        holder.txtNamaProduk.setText(dataCari.get(position).getNamaProduk());
        holder.txtHargaProduk.setText("Rp" + NumberFormat.getInstance()
                .format(Integer.parseInt(dataCari.get(position).getHarga())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProdukActivity.class);
                intent.putExtra("id_produk", dataCari.get(position).getIdProduk());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataCari.size();
    }

    public class CariProdukKategoriViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNamaProduk, txtHargaProduk;

        public CariProdukKategoriViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_produk);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk);
            txtHargaProduk = itemView.findViewById(R.id.text_item_harga_produk);
        }
    }
}
