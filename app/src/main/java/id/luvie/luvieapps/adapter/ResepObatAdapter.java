package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.ListTebusObatActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.listRecipe.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

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

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ListTebusObatActivity.class);
                        intent.putExtra("id_transaksi", id_transaksi);
                        Log.d("idTransaksiAdapter", "onClick: "+id_transaksi);
                        context.startActivity(intent);
                    }
                });

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
