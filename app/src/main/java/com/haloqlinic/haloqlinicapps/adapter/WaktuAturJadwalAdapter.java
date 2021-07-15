package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.AturJadwalActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.jadwalDokter.ListItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class WaktuAturJadwalAdapter extends RecyclerView.Adapter<WaktuAturJadwalAdapter.WaktuAturJadwalViewHolder> {

    Context context;
    List<ListItem> dataJadwal;
    AturJadwalActivity aturJadwalActivity;
    int row_index;

    private static int lastClickedPosition = -1;
    private int selectedItem;

    public WaktuAturJadwalAdapter(Context context, List<ListItem> dataJadwal, AturJadwalActivity aturJadwalActivity) {
        this.context = context;
        this.dataJadwal = dataJadwal;
        this.aturJadwalActivity = aturJadwalActivity;
        selectedItem = 0;
    }

    @NonNull
    @NotNull
    @Override
    public WaktuAturJadwalViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_detail_dokter, parent, false);
        return new WaktuAturJadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WaktuAturJadwalViewHolder holder, int position) {

        holder.cardWaktu.setCardBackgroundColor(context.getResources().getColor(R.color.grey));
        holder.txtHari.setTextColor(context.getResources().getColor(R.color.black));

        if (selectedItem == position){
            holder.cardWaktu.setCardBackgroundColor(context.getResources().getColor(R.color.red));
            holder.txtHari.setTextColor(context.getResources().getColor(R.color.white));
            aturJadwalActivity.tanggal = dataJadwal.get(position).getTanggal();
            aturJadwalActivity.loadJamDokter();
        }

        holder.txtHari.setText(dataJadwal.get(position).getTanggal());

        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int previousItem = selectedItem;
                        selectedItem = position;

                        notifyItemChanged(previousItem);
                        notifyItemChanged(position);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return dataJadwal.size();
    }

    public class WaktuAturJadwalViewHolder extends RecyclerView.ViewHolder {

        TextView txtHari;
        CardView cardWaktu;

        public WaktuAturJadwalViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtHari = itemView.findViewById(R.id.text_hari_jadwal_detail_dokter);
            cardWaktu = itemView.findViewById(R.id.card_waktu_detail_dokter);
        }
    }
}
