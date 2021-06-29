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

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class TebusObatAdapter extends RecyclerView.Adapter<TebusObatAdapter.TebusObatViewHolder> {

    Context context;
    List<com.haloqlinic.haloqlinicapps.model.listPesanan.DataItem> dataObat;

    public TebusObatAdapter(Context context, List<DataItem> dataObat) {
        this.context = context;
        this.dataObat = dataObat;
    }

    @NonNull
    @NotNull
    @Override
    public TebusObatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tebus_obat, parent, false);
        return new TebusObatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TebusObatViewHolder holder, int position) {

        List<ProdukItem> dataProduk = null;

        dataProduk = dataObat.get(position).getProduk();

        for (int i = 0; i<dataProduk.size(); i++){

            int harga = Integer.parseInt(dataProduk.get(i).getHarga());

            holder.txtNamaProduk.setText(dataProduk.get(i).getNamaProduk());
            holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(harga));
            holder.txtJumlah.setText("x"+dataProduk.get(i).getJumlah());

            Glide.with(context)
                    .load(dataProduk.get(i).getImg())
                    .error(R.mipmap.ic_launcher)
                    .into(holder.imgObat);
        }


    }

    @Override
    public int getItemCount() {
        return dataObat.size();
    }

    public class TebusObatViewHolder extends RecyclerView.ViewHolder {

        ImageView imgObat;
        TextView txtNamaProduk, txtHarga, txtJumlah;

        public TebusObatViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgObat = itemView.findViewById(R.id.img_item_tebus_obat);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk_tebus_obat);
            txtHarga = itemView.findViewById(R.id.text_item_harga_produk_tebus_obat);
            txtJumlah = itemView.findViewById(R.id.text_item_jumlah_produk_tebus_obat);
        }
    }
}
