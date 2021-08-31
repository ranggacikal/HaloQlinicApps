package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import id.luvie.luvieapps.DetailDokterActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.model.cariDokter.DataItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchDokterAdapter extends RecyclerView.Adapter<SearchDokterAdapter.SearchDokterViewHolder> {

    Context context;
    List<DataItem> cariDokterList;

    public SearchDokterAdapter(Context context, List<DataItem> cariDokterList) {
        this.context = context;
        this.cariDokterList = cariDokterList;
    }

    @NonNull
    @Override
    public SearchDokterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new SearchDokterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDokterViewHolder holder, int position) {
        final String url_image = "https://luvie.co.id/file/dokter/profile/";

        Glide.with(context)
                .load(url_image+cariDokterList.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDokter);

        holder.txtNamaDokter.setText(cariDokterList.get(position).getNama());
        holder.txtSpesialisDokter.setText(cariDokterList.get(position).getSpesialis());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDokterActivity.class);
                intent.putExtra("id_dokter", cariDokterList.get(position).getIdDokter());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cariDokterList.size();
    }

    public class SearchDokterViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgDokter;
        TextView txtNamaDokter, txtSpesialisDokter;

        public SearchDokterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDokter = itemView.findViewById(R.id.img_item_dokter);
            txtNamaDokter = itemView.findViewById(R.id.text_item_nama_dokter);
            txtSpesialisDokter = itemView.findViewById(R.id.text_item_spesialis_dokter);
        }
    }
}
