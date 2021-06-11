package com.haloqlinic.haloqlinicapps.adapter;

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
import com.haloqlinic.haloqlinicapps.DetailProdukActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.produkKategori.DataItem;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class ProdukKategoriAdapter extends RecyclerView.Adapter<ProdukKategoriAdapter.ProdukKategoriViewHolder> {

    Context context;
    List<DataItem> dataProduk;

    public ProdukKategoriAdapter(Context context, List<DataItem> dataProduk) {
        this.context = context;
        this.dataProduk = dataProduk;
    }

    @NonNull
    @NotNull
    @Override
    public ProdukKategoriViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ProdukKategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProdukKategoriViewHolder holder, int position) {

        Glide.with(context)
                .load(dataProduk.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduk);

        holder.txtNamaProduk.setText(dataProduk.get(position).getNamaProduk());
        holder.txtHargaProduk.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(dataProduk.get(position).getHarga())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProdukActivity.class);
                intent.putExtra("id_produk", dataProduk.get(position).getIdProduk());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataProduk.size();
    }

    public class ProdukKategoriViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduk;
        TextView txtNamaProduk, txtHargaProduk;
        public ProdukKategoriViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_produk);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk);
            txtHargaProduk = itemView.findViewById(R.id.text_item_harga_produk);
        }
    }

    public void addList(List<DataItem> list_data){
        dataProduk.addAll(list_data);
        notifyDataSetChanged();
    }
}
