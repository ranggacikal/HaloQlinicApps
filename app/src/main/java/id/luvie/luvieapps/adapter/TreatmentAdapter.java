package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.ProfileMitraActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.profileMitra.ListItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

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

        holder.txtTreatment.setText(dataTreatment.get(position).getNamaProduk());

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
