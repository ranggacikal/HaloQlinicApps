package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.invoice.DataItem;

import java.text.NumberFormat;
import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    Context context;
    List<DataItem> dataInvoice;

    public InvoiceAdapter(Context context, List<DataItem> dataInvoice) {
        this.context = context;
        this.dataInvoice = dataInvoice;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {

        int total_belanja = Integer.parseInt(dataInvoice.get(position).getJumlahBayar());
        int ongkir = Integer.parseInt(dataInvoice.get(position).getOngkir());
        int biaya_admin = Integer.parseInt(dataInvoice.get(position).getBiayaAdmin());
        int jumlah_bayar = Integer.parseInt(dataInvoice.get(position).getJumlahBayar());

        holder.txtTotalBelanja.setText("Rp" + NumberFormat.getInstance().format(total_belanja));
        holder.txtOngkir.setText("Rp" + NumberFormat.getInstance().format(ongkir));
        holder.txtBiayaAdmin.setText("Rp" + NumberFormat.getInstance().format(biaya_admin));
        holder.txtJumlahBayar.setText("Rp" + NumberFormat.getInstance().format(jumlah_bayar));

    }

    @Override
    public int getItemCount() {
        return dataInvoice.size();
    }

    public class InvoiceViewHolder extends RecyclerView.ViewHolder {

        TextView txtTotalBelanja, txtOngkir, txtBiayaAdmin, txtJumlahBayar;

        public InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalBelanja = itemView.findViewById(R.id.text_total_belanja_invoice);
            txtOngkir = itemView.findViewById(R.id.text_total_ongkir_invoice);
            txtBiayaAdmin = itemView.findViewById(R.id.text_biaya_admin_invoice);
            txtJumlahBayar = itemView.findViewById(R.id.text_jumlah_bayar_invoice);
        }
    }
}
