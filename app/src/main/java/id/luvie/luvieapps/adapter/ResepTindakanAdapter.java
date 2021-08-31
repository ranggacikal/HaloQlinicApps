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

import java.text.NumberFormat;
import java.util.List;

import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.resepTindakan.DataItem;
import id.luvie.luvieapps.model.resepTindakan.TindakanItem;

public class ResepTindakanAdapter extends RecyclerView.Adapter<ResepTindakanAdapter.ResepTindakanViewHolder> {

    Context context;
    List<TindakanItem> dataTindakan;

    public ResepTindakanAdapter(Context context, List<TindakanItem> dataTindakan) {
        this.context = context;
        this.dataTindakan = dataTindakan;
    }

    @NonNull
    @Override
    public ResepTindakanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tebus_obat, parent, false);
        return new ResepTindakanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepTindakanViewHolder holder, int position) {
        Log.d("cekPosition", "onBindViewHolder: "+position);

        int harga = Integer.parseInt(dataTindakan.get(position).getHarga());

        holder.txtNamaProduk.setText(dataTindakan.get(position).getNamaProduk());
        holder.txtHarga.setText("Rp" + NumberFormat.getInstance().format(harga));
        holder.txtJumlah.setText("x" + dataTindakan.get(position).getJumlah());
        holder.txtVariasi.setText(dataTindakan.get(position).getVariasi());

        Glide.with(context)
                .load(dataTindakan.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgObat);
    }

    @Override
    public int getItemCount() {
        return dataTindakan.size();
    }

    public class ResepTindakanViewHolder extends RecyclerView.ViewHolder {
        ImageView imgObat;
        TextView txtNamaProduk, txtHarga, txtJumlah, txtVariasi;
        public ResepTindakanViewHolder(@NonNull View itemView) {
            super(itemView);
            imgObat = itemView.findViewById(R.id.img_item_tebus_obat);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk_tebus_obat);
            txtHarga = itemView.findViewById(R.id.text_item_harga_produk_tebus_obat);
            txtJumlah = itemView.findViewById(R.id.text_item_jumlah_produk_tebus_obat);
            txtVariasi = itemView.findViewById(R.id.text_item_variasi_tebus_obat);
        }
    }
}
