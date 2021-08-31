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

import id.luvie.luvieapps.model.listPesanan.ProdukItem;

import java.text.NumberFormat;
import java.util.List;

public class DetailPesananAdapter extends RecyclerView.Adapter<DetailPesananAdapter.DetailPesananViewHolder> {

    Context context;
    List<ProdukItem> dataListPesanan;

    public DetailPesananAdapter(Context context, List<ProdukItem> dataListPesanan) {
        this.context = context;
        this.dataListPesanan = dataListPesanan;
    }

    @NonNull
    @Override
    public DetailPesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail_pesanan, parent, false);
        return new DetailPesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPesananViewHolder holder, int position) {


        holder.txtNamaProduk.setText(dataListPesanan.get(position).getNamaProduk());
        holder.txtJumlah.setText(dataListPesanan.get(position).getJumlah());
        holder.txtVariasi.setText(dataListPesanan.get(position).getVariasi());
        holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(dataListPesanan.get(position).getHarga())));
        holder.txtTotalHarga.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(dataListPesanan.get(position).getSubtotal())));

        Glide.with(context)
                .load(dataListPesanan.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduk);

    }

    @Override
    public int getItemCount() {
        return dataListPesanan.size();
    }

    public class DetailPesananViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNamaProduk, txtNamaToko, txtVariasi, txtJumlah, txtHarga, txtTotalHarga;

        public DetailPesananViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_detail_pesanan);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk_detail_pesanan);
            txtNamaToko = itemView.findViewById(R.id.text_item_nama_toko_detail_pesanan);
            txtVariasi = itemView.findViewById(R.id.text_item_variasi_detail_pesanan);
            txtJumlah = itemView.findViewById(R.id.text_item_jumlah_detail_pesanan);
            txtHarga = itemView.findViewById(R.id.text_item_harga_detail_pesanan);
            txtTotalHarga = itemView.findViewById(R.id.text_item_total_harga_detail_pesanan);
        }
    }
}
