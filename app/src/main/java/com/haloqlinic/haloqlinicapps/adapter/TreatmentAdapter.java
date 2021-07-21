package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haloqlinic.haloqlinicapps.ProfileMitraActivity;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.profileMitra.ListItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder> {

    Context context;
    List<ListItem> dataTreatment;
    ProfileMitraActivity profileMitraActivity;

    public TreatmentAdapter(Context context, List<ListItem> dataTreatment, ProfileMitraActivity profileMitraActivity) {
        this.context = context;
        this.dataTreatment = dataTreatment;
        this.profileMitraActivity = profileMitraActivity;
    }

    @NonNull
    @NotNull
    @Override
    public TreatmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treatment, parent, false);
        return new TreatmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TreatmentViewHolder holder, int position) {

        holder.txtTreatment.setText(dataTreatment.get(position).getTreatment());

    }

    @Override
    public int getItemCount() {
        return dataTreatment.size();
    }

    public class TreatmentViewHolder extends RecyclerView.ViewHolder {

        TextView txtTreatment;

        public TreatmentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtTreatment = itemView.findViewById(R.id.text_item_treatment);
        }
    }
}
