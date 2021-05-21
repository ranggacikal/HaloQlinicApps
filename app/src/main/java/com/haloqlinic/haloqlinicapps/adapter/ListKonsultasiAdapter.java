package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.CheckoutKonsultasiActivity;
import com.haloqlinic.haloqlinicapps.HistoryKonsultasiActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.model.listKonsultasi.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListKonsultasiAdapter extends RecyclerView.Adapter<ListKonsultasiAdapter.ListKonsultasiViewHolder> {

    Context context;
    List<DataItem> dataKonsultasi;

    private SharedPreferencedConfig preferencedConfig;

    public ListKonsultasiAdapter(Context context, List<DataItem> dataKonsultasi) {
        this.context = context;
        this.dataKonsultasi = dataKonsultasi;
    }

    @NonNull
    @NotNull
    @Override
    public ListKonsultasiViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_konsultasi, parent, false);
        return new ListKonsultasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListKonsultasiViewHolder holder, int position) {

        holder.txtNamaDokter.setText("Dr. " + dataKonsultasi.get(position).getNama());
        holder.txtSpesialis.setText("Spesialis " + dataKonsultasi.get(position).getSpesialis());
        holder.txtJadwal.setText(dataKonsultasi.get(position).getJadwal());

        preferencedConfig = new SharedPreferencedConfig(context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (preferencedConfig.getPreferencePositionFragment().equals("1")) {

                    Intent intent = new Intent(context, CheckoutKonsultasiActivity.class);
                    intent.putExtra("id_transaksi", dataKonsultasi.get(position).getIdTransaksi());
                    intent.putExtra("id_dokter", dataKonsultasi.get(position).getIdDokter());
                    intent.putExtra("jadwal_dokter", dataKonsultasi.get(position).getJadwal());
                    intent.putExtra("biaya", dataKonsultasi.get(position).getBiaya());
                    context.startActivity(intent);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataKonsultasi.size();
    }

    public class ListKonsultasiViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNamaDokter, txtSpesialis, txtJadwal;


        public ListKonsultasiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_dokter_history_konsultasi);
            txtNamaDokter = itemView.findViewById(R.id.text_item_nama_history_konsultasi);
            txtSpesialis = itemView.findViewById(R.id.text_item_spesialis_history_konsultasi);
            txtJadwal = itemView.findViewById(R.id.text_item_tanggal_history_konsultasi);
        }
    }
}
