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
import com.haloqlinic.haloqlinicapps.DetailTransaksiActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.DataItem;
import com.haloqlinic.haloqlinicapps.model.statusTransaksiModel.ProdukItem;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class BelumBayarAdapter extends RecyclerView.Adapter<BelumBayarAdapter.BelumBayarViewHolder> {

    Context context;
    List<DataItem> dataBelumBayar;

    public BelumBayarAdapter(Context context, List<DataItem> dataBelumBayar) {
        this.context = context;
        this.dataBelumBayar = dataBelumBayar;
    }

    @NonNull
    @NotNull
    @Override
    public BelumBayarViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new BelumBayarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BelumBayarViewHolder holder, int position) {
        String urlImage = "";
        String nama = "";
        int harga = 0;

        List<ProdukItem> produkItems = dataBelumBayar.get(position).getProduk();

        for (int i = 0; i<produkItems.size(); i++){

            urlImage = produkItems.get(i).getImg();
            nama = produkItems.get(i).getNamaProduk();
            harga = Integer.parseInt(produkItems.get(i).getHarga());

        }

        Glide.with(context)
                .load(urlImage)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgHistory);

        holder.txtNamaProduk.setText(nama);
        holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(harga));
        holder.txtIdHistory.setText(dataBelumBayar.get(position).getIdTransaksi());
        holder.txtStatus.setText(dataBelumBayar.get(position).getStatusPesanan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTransaksiActivity.class);
                intent.putExtra("id_transaksi", dataBelumBayar.get(position).getIdTransaksi());
                intent.putExtra("id_member", dataBelumBayar.get(position).getIdMember());
                intent.putExtra("fragmentStatusTransaksi", "belumbayar");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBelumBayar.size();
    }

    public class BelumBayarViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHistory;
        TextView txtIdHistory, txtNamaProduk, txtHarga, txtStatus;

        public BelumBayarViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgHistory = itemView.findViewById(R.id.img_item_history);
            txtIdHistory = itemView.findViewById(R.id.text_item_id_history);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_history);
            txtHarga = itemView.findViewById(R.id.text_item_harga_history);
            txtStatus = itemView.findViewById(R.id.text_item_status_history);
        }
    }
}
