package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.listRecipe.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResepObatAdapter extends RecyclerView.Adapter<ResepObatAdapter.ResepObatViewHolder> {

    Context context;
    List<DataItem> dataResep;

    public ResepObatAdapter(Context context, List<DataItem> dataResep) {
        this.context = context;
        this.dataResep = dataResep;
    }

    @NonNull
    @NotNull
    @Override
    public ResepObatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resep_obat, parent, false);
        return new ResepObatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ResepObatViewHolder holder, int position) {

        String id_transaksi = dataResep.get(position).getIdTransaksi();
        String id_dokter = dataResep.get(position).getIdDokter();
        String nama = dataResep.get(position).getNama();
        String jadwal = dataResep.get(position).getJadwal();

        holder.txtIdKonsultasi.setText(id_transaksi);
        holder.txtNamaDokter.setText(nama);
        holder.txtJadwal.setText(jadwal);

    }

    @Override
    public int getItemCount() {
        return dataResep.size();
    }

    public class ResepObatViewHolder extends RecyclerView.ViewHolder {

        TextView txtIdKonsultasi, txtNamaDokter, txtJadwal;

        public ResepObatViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtIdKonsultasi = itemView.findViewById(R.id.text_item_id_transaksi_resep);
            txtNamaDokter = itemView.findViewById(R.id.text_item_nama_dokter_resep);
            txtJadwal = itemView.findViewById(R.id.text_item_jadwal_resep);
        }
    }
}
