package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.ChatActivity;
import id.luvie.luvieapps.DetailHistoryActivity;

import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.SummaryActivity;
import id.luvie.luvieapps.model.listKonsultasi.DataItem;

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

        String status_transaksi = dataKonsultasi.get(position).getStatusTransaksi();
        String id_transaksi = dataKonsultasi.get(position).getIdTransaksi();

        preferencedConfig = new SharedPreferencedConfig(context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SummaryActivity.class);
                intent.putExtra("id_transaksi", dataKonsultasi.get(position).getIdTransaksi());
                context.startActivity(intent);

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
