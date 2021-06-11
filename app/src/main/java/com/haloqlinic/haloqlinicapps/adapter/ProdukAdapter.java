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
import com.haloqlinic.haloqlinicapps.DetailDokterActivity;
import com.haloqlinic.haloqlinicapps.DetailProdukActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.produk.DataItem;

import java.text.NumberFormat;
import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ProdukViewHolder> {

    Context context;
    List<DataItem> dataItems;

    public ProdukAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, int position) {

        Glide.with(context)
                .load(dataItems.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduk);

        holder.txtNamaProduk.setText(dataItems.get(position).getNamaProduk());
        holder.txtHargaProduk.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(dataItems.get(position).getHarga())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProdukActivity.class);
                intent.putExtra("id_produk", dataItems.get(position).getIdProduk());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public void addList(List<DataItem> list_data){
        dataItems.addAll(list_data);
        notifyDataSetChanged();
    }

    public void clear(){
        dataItems.clear();
        notifyDataSetChanged();
    }

    public class ProdukViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNamaProduk, txtHargaProduk;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_produk);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk);
            txtHargaProduk = itemView.findViewById(R.id.text_item_harga_produk);
        }
    }
}
