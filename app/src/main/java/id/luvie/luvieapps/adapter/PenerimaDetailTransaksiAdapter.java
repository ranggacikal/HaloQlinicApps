package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.detailTransaksi.DataItem;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class PenerimaDetailTransaksiAdapter extends RecyclerView.Adapter<PenerimaDetailTransaksiAdapter.PenerimaDetailTransaksiViewHolder> {

    Context context;
    List<DataItem> dataPenerima;

    public PenerimaDetailTransaksiAdapter(Context context, List<DataItem> dataPenerima) {
        this.context = context;
        this.dataPenerima = dataPenerima;
    }

    @NonNull
    @NotNull
    @Override
    public PenerimaDetailTransaksiViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penerima_detail_transaksi, parent, false);
        return new PenerimaDetailTransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PenerimaDetailTransaksiViewHolder holder, int position) {

        holder.txtNama.setText(dataPenerima.get(position).getNamaPenerima());
        holder.txtAlamat.setText(dataPenerima.get(position).getAlamat());
        holder.txtOpsiBayar.setText(dataPenerima.get(position).getOpsiBayar());
        holder.txtNoResi.setText(dataPenerima.get(position).getNoResi());
        holder.txtTglTransaksi.setText(dataPenerima.get(position).getTglTransaksi());

        int jumlahBayar = Integer.parseInt(dataPenerima.get(position).getJumlahBayar());

        holder.txtJumlahBayar.setText("Rp" + NumberFormat.getInstance().format(jumlahBayar));

    }

    @Override
    public int getItemCount() {
        return dataPenerima.size();
    }

    public class PenerimaDetailTransaksiViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtAlamat, txtOpsiBayar, txtTglTransaksi, txtNoResi, txtJumlahBayar;

        public PenerimaDetailTransaksiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.text_penerima_nama_detail_transksi);
            txtAlamat = itemView.findViewById(R.id.text_penerima_alamat_detail_transksi);
            txtOpsiBayar = itemView.findViewById(R.id.text_penerima_opsi_bayar_detail_transksi);
            txtTglTransaksi = itemView.findViewById(R.id.text_penerima_tgltransaksi_detail_transksi);
            txtNoResi = itemView.findViewById(R.id.text_penerima_noresi_detail_transksi);
            txtJumlahBayar = itemView.findViewById(R.id.text_penerima_jumlahbayar_detail_transksi);

        }
    }
}
