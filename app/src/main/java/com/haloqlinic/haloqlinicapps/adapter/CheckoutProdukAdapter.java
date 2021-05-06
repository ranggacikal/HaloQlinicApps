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

import java.util.List;

public class CheckoutProdukAdapter extends RecyclerView.Adapter<CheckoutProdukAdapter.CheckoutProdukViewHolder> {

    Context context;
    List<DataItem> dataPesanan;

    public CheckoutProdukAdapter(Context context, List<DataItem> dataPesanan) {
        this.context = context;
        this.dataPesanan = dataPesanan;
    }

    @NonNull
    @Override
    public CheckoutProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout, parent, false);
        return new CheckoutProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutProdukViewHolder holder, int position) {

        List<ProdukItem> dataProduk = null;

        dataProduk = dataPesanan.get(position).getProduk();

        for (int i = 0; i<dataProduk.size(); i++){

            holder.txtNamaProduk.setText(dataProduk.get(i).getNamaProduk());
            holder.txtVariasi.setText(dataProduk.get(i).getVariasi());
            holder.txtJumlah.setText("x"+dataProduk.get(i).getJumlah());

            Glide.with(context)
                    .load(dataProduk.get(i).getImg())
                    .error(R.mipmap.ic_launcher)
                    .into(holder.imgProduk);

        }

    }

    @Override
    public int getItemCount() {
        return dataPesanan.size();
    }

    public class CheckoutProdukViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNamaProduk, txtVariasi, txtJumlah;

        public CheckoutProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_checkout);
            txtNamaProduk = itemView.findViewById(R.id.text_nama_item_checkout);
            txtVariasi = itemView.findViewById(R.id.text_variasi_item_checkout);
            txtJumlah = itemView.findViewById(R.id.text_jumlah_item_checkout);
        }
    }
}
