package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.InvoiceKonsultasiActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.invoiceKonsultasi.DataItem;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class InvoiceKonsultasiAdapter extends RecyclerView.Adapter<InvoiceKonsultasiAdapter.InvoiceKonsultasiViewHolder> {

    Context context;
    List<DataItem> dataInvoice;
    InvoiceKonsultasiActivity invoiceKonsultasiActivity;

    public InvoiceKonsultasiAdapter(Context context, List<DataItem> dataInvoice, InvoiceKonsultasiActivity invoiceKonsultasiActivity) {
        this.context = context;
        this.dataInvoice = dataInvoice;
        this.invoiceKonsultasiActivity = invoiceKonsultasiActivity;
    }

    @NonNull
    @NotNull
    @Override
    public InvoiceKonsultasiViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new InvoiceKonsultasiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull InvoiceKonsultasiViewHolder holder, int position) {

        holder.txtOngkir.setVisibility(View.GONE);

        int total_belanja = Integer.parseInt(dataInvoice.get(position).getJumlahBayar());
        int biaya_admin = Integer.parseInt(dataInvoice.get(position).getBiayaAdmin());
        int jumlah_bayar = Integer.parseInt(dataInvoice.get(position).getJumlahBayar());

        String cekKonsultasi = invoiceKonsultasiActivity.cekKonsultasi;

        if (cekKonsultasi!=null){
            if (cekKonsultasi.equals("konsultasi")){
                holder.linearOngkir.setVisibility(View.GONE);
            }
        }

        holder.txtTotalBelanja.setText("Rp" + NumberFormat.getInstance().format(total_belanja));
        holder.txtBiayaAdmin.setText("Rp" + NumberFormat.getInstance().format(biaya_admin));
        holder.txtJumlahBayar.setText("Rp" + NumberFormat.getInstance().format(jumlah_bayar));

    }

    @Override
    public int getItemCount() {
        return dataInvoice.size();
    }

    public class InvoiceKonsultasiViewHolder extends RecyclerView.ViewHolder {

        TextView txtTotalBelanja, txtOngkir, txtBiayaAdmin, txtJumlahBayar;
        LinearLayout linearOngkir;

        public InvoiceKonsultasiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTotalBelanja = itemView.findViewById(R.id.text_total_belanja_invoice);
            txtOngkir = itemView.findViewById(R.id.text_total_ongkir_invoice);
            txtBiayaAdmin = itemView.findViewById(R.id.text_biaya_admin_invoice);
            txtJumlahBayar = itemView.findViewById(R.id.text_jumlah_bayar_invoice);
            linearOngkir = itemView.findViewById(R.id.linear_ongkos_kirim_invoice);
        }
    }
}
