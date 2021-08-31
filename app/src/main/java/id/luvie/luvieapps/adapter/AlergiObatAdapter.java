package id.luvie.luvieapps.adapter;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.DetailAlergiActivity;

import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.listAlergiObat.DataItem;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlergiObatAdapter extends RecyclerView.Adapter<AlergiObatAdapter.AlergiObatViewHolder> {

    Context context;
    List<DataItem> dataObat;

    public AlergiObatAdapter(Context context, List<DataItem> dataObat) {
        this.context = context;
        this.dataObat = dataObat;
    }

    @NonNull
    @NotNull
    @Override
    public AlergiObatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alergi_obat, parent, false);
        return new AlergiObatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AlergiObatViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtNamaObat.setText(dataObat.get(position).getObat());

        PushDownAnim.setPushDownAnimTo(holder.btnDetail)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailAlergiActivity.class);
                        intent.putExtra("id", dataObat.get(position).getId());
                        context.startActivity(intent);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return dataObat.size();
    }

    public class AlergiObatViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaObat;
        Button btnDetail;

        public AlergiObatViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtNamaObat = itemView.findViewById(R.id.text_item_nama_alergi_obat);
            btnDetail = itemView.findViewById(R.id.btn_detail_alergi);
        }
    }
}
