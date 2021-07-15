package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.AturJadwalActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.jamDokter.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class JamJadwalAdapter extends RecyclerView.Adapter<JamJadwalAdapter.JamJadwalViewHolder> {

    Context context;
    List<DataItem> datajam;
    AturJadwalActivity aturJadwalActivity;
    int row_index;

    private static int lastClickedPosition = -1;
    private int selectedItem;

    public JamJadwalAdapter(Context context, List<DataItem> datajam, AturJadwalActivity aturJadwalActivity) {
        this.context = context;
        this.datajam = datajam;
        this.aturJadwalActivity = aturJadwalActivity;
    }

    @NonNull
    @NotNull
    @Override
    public JamJadwalViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jam_jadwal, parent, false);
        return new JamJadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JamJadwalViewHolder holder, int position) {

        holder.cardJam.setCardBackgroundColor(context.getResources().getColor(R.color.grey));
        holder.txtJam.setTextColor(context.getResources().getColor(R.color.black));

        if (selectedItem == position){
            holder.cardJam.setCardBackgroundColor(context.getResources().getColor(R.color.red));
            holder.txtJam.setTextColor(context.getResources().getColor(R.color.white));
            aturJadwalActivity.id_jadwal = datajam.get(position).getId();
            aturJadwalActivity.formatTanggal = datajam.get(position).getJadwal();
        }

        holder.txtJam.setText(datajam.get(position).getJam());

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
        return datajam.size();
    }

    public class JamJadwalViewHolder extends RecyclerView.ViewHolder {

        CardView cardJam;
        TextView txtJam;

        public JamJadwalViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cardJam = itemView.findViewById(R.id.card_jam_detail_dokter);
            txtJam = itemView.findViewById(R.id.text_jam_detail_dokter);
        }
    }
}
