package id.luvie.luvieapps.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.CheckoutKonsultasiActivity;
import id.luvie.luvieapps.OpsiBayarActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.model.kategoriXendit.DataItem;

import java.util.List;

public class KategoriBayarAdapter extends RecyclerView.Adapter<KategoriBayarAdapter.KategoriBayarViewHolder> {

    Context context;
    List<DataItem> dataKategori;

    private SharedPreferencedConfig preferencedConfig;

    public KategoriBayarAdapter(Context context, List<DataItem> dataKategori) {
        this.context = context;
        this.dataKategori = dataKategori;
    }

    @NonNull
    @Override
    public KategoriBayarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori_bayar, parent, false);
        return new KategoriBayarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriBayarViewHolder holder, @SuppressLint("RecyclerView") int position) {

        preferencedConfig = new SharedPreferencedConfig(context);

        holder.txtNamakategori.setText(dataKategori.get(position).getKategori());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OpsiBayarActivity.class);
                intent.putExtra("kategoriBayar", dataKategori.get(position).getKategori());
                intent.putExtra("idKategori", dataKategori.get(position).getId());
                intent.putExtra("from_activity", "checkout_produk");
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_KATEGORI_BAYAR, dataKategori.get(position).getId());
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KATEGORI_BAYAR, dataKategori.get(position).getKategori());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataKategori.size();
    }

    public class KategoriBayarViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamakategori;

        public KategoriBayarViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamakategori = itemView.findViewById(R.id.text_item_nama_kategori_bayar);
        }
    }
}
