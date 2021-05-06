package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.CheckoutProdukActivity;
import com.haloqlinic.haloqlinicapps.OpsiBayarActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.SharedPreference.SharedPreferencedConfig;
import com.haloqlinic.haloqlinicapps.model.opsiBayar.DataItem;
import com.xendit.Xendit;

import java.util.List;

public class OpsiBayarAdapter extends RecyclerView.Adapter<OpsiBayarAdapter.OpsiBayarViewHolder> {

    Context context;
    List<DataItem> dataOpsiBayar;
    OpsiBayarActivity opsiBayarActivity;
    CheckoutProdukActivity checkoutProdukActivity;

    private SharedPreferencedConfig preferencedConfig;

    public OpsiBayarAdapter(Context context, List<DataItem> dataOpsiBayar, OpsiBayarActivity opsiBayarActivity) {
        this.context = context;
        this.dataOpsiBayar = dataOpsiBayar;
        this.opsiBayarActivity = opsiBayarActivity;
    }

    @NonNull
    @Override
    public OpsiBayarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_opsi_bayar, parent, false);
        return new OpsiBayarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpsiBayarViewHolder holder, int position) {

        preferencedConfig = new SharedPreferencedConfig(context);

        String urlImage = "https://aplikasicerdas.net/haloqlinic/android/xendit/img/" + dataOpsiBayar.get(position).getIcon();

        Glide.with(context)
                .load(urlImage)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgIcon);

        holder.txtNama.setText(dataOpsiBayar.get(position).getOpsiBayar());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_OPSI_BAYAR, dataOpsiBayar.get(position).getId());
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_KODE_OPSI_BAYAR, dataOpsiBayar.get(position).getKode());
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_OPSI_BAYAR, dataOpsiBayar.get(position).getOpsiBayar());
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE_OPSI_BAYAR, urlImage);
                ((OpsiBayarActivity) context).finish();

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

        public OpsiBayarViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_icon_opsi_bayar);
            txtNama = itemView.findViewById(R.id.text_item_nama_opsi_bayar);
        }
    }
}
