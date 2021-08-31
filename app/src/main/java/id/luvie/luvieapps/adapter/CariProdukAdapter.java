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

import id.luvie.luvieapps.DetailProdukActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.cariProduk.DataItem;

import java.text.NumberFormat;
import java.util.List;

public class CariProdukAdapter extends RecyclerView.Adapter<CariProdukAdapter.CariProdukViewHolder> {

    Context context;
    List<DataItem> cariProdukList;

    public CariProdukAdapter(Context context, List<DataItem> cariProdukList) {
        this.context = context;
        this.cariProdukList = cariProdukList;
    }

    @NonNull
    @Override
    public CariProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new CariProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CariProdukViewHolder holder, int position) {

        Glide.with(context)
                .load(cariProdukList.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduk);

        holder.txtNamaProduk.setText(cariProdukList.get(position).getNamaProduk());
        holder.txtHargaProduk.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(cariProdukList.get(position).getHarga())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProdukActivity.class);
                intent.putExtra("id_produk", cariProdukList.get(position).getIdProduk());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cariProdukList.size();
    }

    public class CariProdukViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView txtNamaProduk, txtHargaProduk;

        public CariProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduk = itemView.findViewById(R.id.img_item_produk);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk);
            txtHargaProduk = itemView.findViewById(R.id.text_item_harga_produk);
        }
    }
}
