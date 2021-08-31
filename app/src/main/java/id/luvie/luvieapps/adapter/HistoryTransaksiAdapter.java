package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.historyTransaksi.DataItem;
import id.luvie.luvieapps.model.historyTransaksi.ProdukItem;

import java.text.NumberFormat;
import java.util.List;

public class HistoryTransaksiAdapter extends RecyclerView.Adapter<HistoryTransaksiAdapter.HistoryTransaksiViewHolder> {

    Context context;
    List<DataItem> dataHistoryTransaksi;

    public HistoryTransaksiAdapter(Context context, List<DataItem> dataHistoryTransaksi) {
        this.context = context;
        this.dataHistoryTransaksi = dataHistoryTransaksi;
    }

    @NonNull
    @Override
    public HistoryTransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryTransaksiViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryTransaksiViewHolder holder, int position) {

        String urlImage = "";
        String nama = "";
        int harga = 0;

        List<ProdukItem> produkItems = dataHistoryTransaksi.get(position).getProduk();

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
        holder.txtIdHistory.setText(dataHistoryTransaksi.get(position).getIdTransaksi());
        holder.txtStatus.setText(dataHistoryTransaksi.get(position).getStatusPesanan());

    }

    @Override
    public int getItemCount() {
        return dataHistoryTransaksi.size();
    }

    public class HistoryTransaksiViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHistory;
        TextView txtIdHistory, txtNamaProduk, txtHarga, txtStatus;

        public HistoryTransaksiViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHistory = itemView.findViewById(R.id.img_item_history);
            txtIdHistory = itemView.findViewById(R.id.text_item_id_history);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_history);
            txtHarga = itemView.findViewById(R.id.text_item_harga_history);
            txtStatus = itemView.findViewById(R.id.text_item_status_history);

        }
    }
}
