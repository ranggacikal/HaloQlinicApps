package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.util.Log;
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

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

public class TebusObatAdapter extends RecyclerView.Adapter<TebusObatAdapter.TebusObatViewHolder> {

    Context context;
    List<ProdukItem> dataProduk;

    public TebusObatAdapter(Context context, List<ProdukItem> dataProduk) {
        this.context = context;
        this.dataProduk = dataProduk;
    }

    @NonNull
    @NotNull
    @Override
    public TebusObatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tebus_obat, parent, false);
        return new TebusObatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TebusObatViewHolder holder, int position) {

        Log.d("cekPosition", "onBindViewHolder: "+position);

        int harga = Integer.parseInt(dataProduk.get(position).getHarga());

        holder.txtNamaProduk.setText(dataProduk.get(position).getNamaProduk());
        holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(harga));
        holder.txtJumlah.setText("x" + dataProduk.get(position).getJumlah());
        holder.txtVariasi.setText(dataProduk.get(position).getVariasi());

        Glide.with(context)
                .load(dataProduk.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgObat);


    }

    @Override
    public int getItemCount() {
        return dataProduk.size();
    }

    public class TebusObatViewHolder extends RecyclerView.ViewHolder {

        ImageView imgObat;
        TextView txtNamaProduk, txtHarga, txtJumlah, txtVariasi;

        public TebusObatViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imgObat = itemView.findViewById(R.id.img_item_tebus_obat);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk_tebus_obat);
            txtHarga = itemView.findViewById(R.id.text_item_harga_produk_tebus_obat);
            txtJumlah = itemView.findViewById(R.id.text_item_jumlah_produk_tebus_obat);
            txtVariasi = itemView.findViewById(R.id.text_item_variasi_tebus_obat);
        }
    }
}
