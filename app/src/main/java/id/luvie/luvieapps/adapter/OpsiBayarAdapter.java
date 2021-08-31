package id.luvie.luvieapps.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import id.luvie.luvieapps.CheckoutProdukActivity;
import id.luvie.luvieapps.OpsiBayarActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.SharedPreference.SharedPreferencedConfig;
import id.luvie.luvieapps.model.opsiBayar.DataItem;

import java.util.List;

public class OpsiBayarAdapter extends RecyclerView.Adapter<OpsiBayarAdapter.OpsiBayarViewHolder> {

    Context context;
    List<DataItem> dataOpsiBayar;
    OpsiBayarActivity opsiBayarActivity;
    CheckoutProdukActivity checkoutProdukActivity;

    int row_index;

    private SharedPreferencedConfig preferencedConfig;

    public OpsiBayarAdapter(Context context, List<DataItem> dataOpsiBayar, OpsiBayarActivity opsiBayarActivity) {
        this.context = context;
        this.dataOpsiBayar = dataOpsiBayar;
        this.opsiBayarActivity = opsiBayarActivity;
        row_index = 0;
    }

    @NonNull
    @Override
    public OpsiBayarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opsi_bayar, parent, false);
        return new OpsiBayarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpsiBayarViewHolder holder, @SuppressLint("RecyclerView") int position) {

        preferencedConfig = new SharedPreferencedConfig(context);

        String urlImage = "https://luvie.co.id/android/xendit/img/" + dataOpsiBayar.get(position).getIcon();

        String from_activity = opsiBayarActivity.from_activity;

        if (from_activity.equals("checkout_konsultasi")) {

            holder.cardOpsiBayar.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.txtNama.setTextColor(Color.parseColor("#000000"));


            if (row_index == position) {
                holder.cardOpsiBayar.setCardBackgroundColor(Color.parseColor("#D4B44E"));
                holder.txtNama.setTextColor(Color.parseColor("#000000"));
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, dataOpsiBayar.get(position).getId());
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, dataOpsiBayar.get(position).getKode());
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, dataOpsiBayar.get(position).getOpsiBayar());
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, urlImage);
                opsiBayarActivity.loadBiayaAdmin();
                Toast.makeText(context, "Anda Memilih : " + dataOpsiBayar.get(position).getOpsiBayar(),
                        Toast.LENGTH_SHORT).show();
            }

        }

        Glide.with(context)
                .load(urlImage)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgIcon);

        holder.txtNama.setText(dataOpsiBayar.get(position).getOpsiBayar());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (from_activity.equals("checkout_konsultasi")) {

                    int previousItem = row_index;
                    row_index = position;
                    notifyItemChanged(previousItem);
                    notifyItemChanged(position);

                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, dataOpsiBayar.get(position).getId());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, dataOpsiBayar.get(position).getKode());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, dataOpsiBayar.get(position).getOpsiBayar());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, urlImage);
                    opsiBayarActivity.loadBiayaAdmin();
//                ((OpsiBayarActivity) context).finish();
                } else {
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, dataOpsiBayar.get(position).getId());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, dataOpsiBayar.get(position).getKode());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, dataOpsiBayar.get(position).getOpsiBayar());
                    preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, urlImage);
//                    opsiBayarActivity.loadBiayaAdmin();
                    ((OpsiBayarActivity) context).finish();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataOpsiBayar.size();
    }

    public class OpsiBayarViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView txtNama;
        CardView cardOpsiBayar;

        public OpsiBayarViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_icon_opsi_bayar);
            txtNama = itemView.findViewById(R.id.text_item_nama_opsi_bayar);
            cardOpsiBayar = itemView.findViewById(R.id.card_item_opsi_bayar);
        }
    }
}
