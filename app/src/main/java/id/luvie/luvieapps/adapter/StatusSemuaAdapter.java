package id.luvie.luvieapps.adapter;

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
import id.luvie.luvieapps.DetailTransaksiActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.statusTransaksiModel.DataItem;
import id.luvie.luvieapps.model.statusTransaksiModel.ProdukItem;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class StatusSemuaAdapter extends RecyclerView.Adapter<StatusSemuaAdapter.StatusSemuaViewHolder> {

    Context context;
    List<DataItem> dataStatusSemua;

    public StatusSemuaAdapter(Context context, List<DataItem> dataStatusSemua) {
        this.context = context;
        this.dataStatusSemua = dataStatusSemua;
    }

    @NonNull
    @NotNull
    @Override
    public StatusSemuaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new StatusSemuaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StatusSemuaViewHolder holder, int position) {

        String urlImage = "";
        String nama = "";
        int harga = 0;

        List<ProdukItem> produkItems = dataStatusSemua.get(position).getProduk();

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
        holder.txtIdHistory.setText(dataStatusSemua.get(position).getIdTransaksi());
        holder.txtStatus.setText(dataStatusSemua.get(position).getStatusPesanan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTransaksiActivity.class);
                intent.putExtra("id_transaksi", dataStatusSemua.get(position).getIdTransaksi());
                intent.putExtra("id_member", dataStatusSemua.get(position).getIdMember());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataStatusSemua.size();
    }

    public class StatusSemuaViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHistory;
        TextView txtIdHistory, txtNamaProduk, txtHarga, txtStatus;

        public StatusSemuaViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgHistory = itemView.findViewById(R.id.img_item_history);
            txtIdHistory = itemView.findViewById(R.id.text_item_id_history);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_history);
            txtHarga = itemView.findViewById(R.id.text_item_harga_history);
            txtStatus = itemView.findViewById(R.id.text_item_status_history);
        }
    }
}
