package id.luvie.luvieapps.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.luvie.luvieapps.CheckoutKonsultasiActivity;
import id.luvie.luvieapps.OpsiBayarActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.model.kategoriXendit.DataItem;

public class KategoriBayarKonsultasiAdapter extends RecyclerView.Adapter<KategoriBayarKonsultasiAdapter.KategoriBayarKonsulViewHolder> {

    Context context;
    List<DataItem> dataKategori;
    CheckoutKonsultasiActivity checkoutKonsultasiActivity;

    private SharedPreferencedConfig preferencedConfig;

    public KategoriBayarKonsultasiAdapter(Context context, List<DataItem> dataKategori, CheckoutKonsultasiActivity checkoutKonsultasiActivity) {
        this.context = context;
        this.dataKategori = dataKategori;
        this.checkoutKonsultasiActivity = checkoutKonsultasiActivity;
    }

    @NonNull
    @Override
    public KategoriBayarKonsulViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori_bayar, parent, false);
        return new KategoriBayarKonsulViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriBayarKonsulViewHolder holder, @SuppressLint("RecyclerView") int position) {
        preferencedConfig = new SharedPreferencedConfig(context);

        holder.txtNamakategori.setText(dataKategori.get(position).getKategori());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OpsiBayarActivity.class);
                intent.putExtra("kategoriBayar", dataKategori.get(position).getKategori());
                intent.putExtra("idKategori", dataKategori.get(position).getId());
                intent.putExtra("id_transaksi", checkoutKonsultasiActivity.id_transaksi);
                intent.putExtra("biaya", String.valueOf(checkoutKonsultasiActivity.biaya));
                Log.d("intentBiaya", "onClick: "+String.valueOf(checkoutKonsultasiActivity.biaya));
                intent.putExtra("id_dokter", checkoutKonsultasiActivity.id_dokter);
                intent.putExtra("jadwal_dokter", checkoutKonsultasiActivity.jadwal_dokter);
                intent.putExtra("status", checkoutKonsultasiActivity.status);
                intent.putExtra("external_id", checkoutKonsultasiActivity.external_id);
                intent.putExtra("token_dokter", checkoutKonsultasiActivity.token_dokter);
                intent.putExtra("nama_dokter", checkoutKonsultasiActivity.nama_dokter);
                intent.putExtra("player_id_dokter", checkoutKonsultasiActivity.player_id_dokter);
                intent.putExtra("buat_jadwal", checkoutKonsultasiActivity.buat_jadwal);
                intent.putExtra("image_dokter", checkoutKonsultasiActivity.image_dokter);
                intent.putExtra("spesialis_dokter", checkoutKonsultasiActivity.spesialis_dokter);
                intent.putExtra("from_activity", "checkout_konsultasi");
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

    public class KategoriBayarKonsulViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamakategori;

        public KategoriBayarKonsulViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamakategori = itemView.findViewById(R.id.text_item_nama_kategori_bayar);
        }
    }
}
