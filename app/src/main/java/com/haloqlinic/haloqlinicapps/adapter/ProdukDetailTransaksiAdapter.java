package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ProdukItem;
import com.haloqlinic.haloqlinicapps.model.detailTransaksi.ResponseDetailTransaksi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProdukDetailTransaksiAdapter extends RecyclerView.Adapter<ProdukDetailTransaksiAdapter.ProdukDetailTransaksiViewHolder> {

    Context context;
    List<ProdukItem> dataProduk;

    public ProdukDetailTransaksiAdapter(Context context, List<ProdukItem> dataProduk) {
        this.context = context;
        this.dataProduk = dataProduk;
    }

    @NonNull
    @NotNull
    @Override
    public ProdukDetailTransaksiViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk_detail_transaksi, parent, false);
        return new ProdukDetailTransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProdukDetailTransaksiViewHolder holder, int position) {

        String url = dataProduk.get(position).getImg();

        Glide.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduk);

        holder.txtNama.setText(dataProduk.get(position).getNamaProduk());
        holder.txtJumlah.setText(dataProduk.get(position).getJumlah());
        holder.txtVariasi.setText(dataProduk.get(position).getVariasi());

    }

    @Override
    public int getItemCount() {
        return dataProduk.size();
    }

    public class ProdukDetailTransaksiViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNama, txtJumlah, txtVariasi;

        public ProdukDetailTransaksiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            imgProduk = itemView.findViewById(R.id.img_item_produk_detail_transaksi);
            txtNama = itemView.findViewById(R.id.text_item_nama_detail_transaksi);
            txtJumlah = itemView.findViewById(R.id.text_item_jumlah_detail_transaksi);
            txtVariasi = itemView.findViewById(R.id.text_item_variasi_detail_transaksi);
        }
    }
}
