package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.layananEkspedisi.CostItem;
import com.haloqlinic.haloqlinicapps.model.layananEkspedisi.CostsItem;
import com.haloqlinic.haloqlinicapps.model.layananEkspedisi.ResultsItem;

import java.text.NumberFormat;
import java.util.List;

public class LayananKurirAdapter extends RecyclerView.Adapter<LayananKurirAdapter.LayananKurirViewHolder> {

    Context context;
    List<ResultsItem> dataLayanan;

    public LayananKurirAdapter(Context context, List<ResultsItem> dataLayanan) {
        this.context = context;
        this.dataLayanan = dataLayanan;
    }

    @NonNull
    @Override
    public LayananKurirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layanan_kurir, parent, false);
        return new LayananKurirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LayananKurirViewHolder holder, int position) {

        List<CostsItem> dataCosts;
        List<CostItem> dataHarga;

        dataCosts = dataLayanan.get(position).getCosts();

        for (int a = 0; a<dataCosts.size(); a++){
            holder.txtKode.setText(dataCosts.get(a).getService());
            holder.txtDeskripsi.setText(dataCosts.get(a).getDescription());

            dataHarga = dataCosts.get(a).getCost();

            for (int b = 0; b<dataHarga.size(); b++){
                holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(dataHarga.get(position).getValue()));
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataLayanan.size();
    }

    public class LayananKurirViewHolder extends RecyclerView.ViewHolder {

        TextView txtDeskripsi, txtKode, txtHarga;

        public LayananKurirViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDeskripsi = itemView.findViewById(R.id.text_item_deskripsi_kurir);
            txtHarga = itemView.findViewById(R.id.text_item_harga_kurir);
            txtKode = itemView.findViewById(R.id.text_item_kode_kurir);
        }
    }
}
