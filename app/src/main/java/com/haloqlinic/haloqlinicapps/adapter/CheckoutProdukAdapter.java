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
import com.haloqlinic.haloqlinicapps.model.listPesanan.DataItem;
import com.haloqlinic.haloqlinicapps.model.listPesanan.ProdukItem;

import java.text.NumberFormat;
import java.util.List;

public class CheckoutProdukAdapter extends RecyclerView.Adapter<CheckoutProdukAdapter.CheckoutProdukViewHolder> {

    Context context;
    List<ProdukItem> dataProduk;
    List<DataItem> dataPesanan;

    public CheckoutProdukAdapter(Context context, List<ProdukItem> dataProduk) {
        this.context = context;
        this.dataProduk = dataProduk;
    }

    @NonNull
    @Override
    public CheckoutProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout, parent, false);
        return new CheckoutProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutProdukViewHolder holder, int position) {

        int hargaProduk = Integer.parseInt(dataProduk.get(position).getHarga());
        int totalHarga = Integer.parseInt(dataProduk.get(position).getSubtotal());

        holder.txtNamaProduk.setText(dataProduk.get(position).getNamaProduk());
        holder.txtVariasi.setText(dataProduk.get(position).getVariasi());
        holder.txtJumlah.setText("x" + dataProduk.get(position).getJumlah());
        holder.txtHargaProduk.setText("Rp" + NumberFormat.getInstance().format(hargaProduk));
        holder.txtTotalHarga.setText("Rp" + NumberFormat.getInstance().format(totalHarga));

        Glide.with(context)
                .load(dataProduk.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduk);

    }

    @Override
    public int getItemCount() {
        return dataProduk.size();
    }

    public class CheckoutProdukViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNamaProduk, txtVariasi, txtJumlah, txtHargaProduk, txtTotalHarga;

        public CheckoutProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_checkout);
            txtNamaProduk = itemView.findViewById(R.id.text_nama_item_checkout);
            txtVariasi = itemView.findViewById(R.id.text_variasi_item_checkout);
            txtJumlah = itemView.findViewById(R.id.text_jumlah_item_checkout);
            txtHargaProduk = itemView.findViewById(R.id.text_harga_checkout_produk);
            txtTotalHarga = itemView.findViewById(R.id.text_total_harga_checkout_produk);
        }
    }
}
