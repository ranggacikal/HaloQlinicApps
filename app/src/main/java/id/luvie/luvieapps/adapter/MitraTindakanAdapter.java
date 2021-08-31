package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.luvie.luvieapps.CheckoutProdukActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.resepTindakan.DataItem;
import id.luvie.luvieapps.model.resepTindakan.ResponseResepTindakan;
import id.luvie.luvieapps.model.resepTindakan.TindakanItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MitraTindakanAdapter extends RecyclerView.Adapter<MitraTindakanAdapter.MitraTindakanViewHolder> {

    Context context;
    List<DataItem> dataTindakan;
    CheckoutProdukActivity checkoutProdukActivity;

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public MitraTindakanAdapter(Context context, List<DataItem> dataTindakan, CheckoutProdukActivity checkoutProdukActivity) {
        this.context = context;
        this.dataTindakan = dataTindakan;
        this.checkoutProdukActivity = checkoutProdukActivity;
    }

    @NonNull
    @Override
    public MitraTindakanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mitra_tindakan, parent, false);
        return new MitraTindakanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MitraTindakanViewHolder holder, int position) {
        holder.txtNamaMitra.setText(dataTindakan.get(position).getNamaToko());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvTindakan.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        holder.rvTindakan.setLayoutManager(layoutManager);
        holder.rvTindakan.setHasFixedSize(true);

        String id_transaksi = checkoutProdukActivity.id_transaksi;

        List<TindakanItem> listTindakan = dataTindakan.get(position).getTindakan();

        ConfigRetrofit.service.resepTindakan(id_transaksi).enqueue(new Callback<ResponseResepTindakan>() {
            @Override
            public void onResponse(Call<ResponseResepTindakan> call, Response<ResponseResepTindakan> response) {
                if (response.isSuccessful()){

                    ResepTindakanAdapter adapter = new ResepTindakanAdapter(context, listTindakan);
                    holder.rvTindakan.setAdapter(adapter);
                    holder.rvTindakan.setRecycledViewPool(viewPool);

                }else{
                    Toast.makeText(context, "Gagal Memuat Data Tindakan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseResepTindakan> call, Throwable t) {
                Toast.makeText(context, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataTindakan.size();
    }

    public class MitraTindakanViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaMitra;
        RecyclerView rvTindakan;

        public MitraTindakanViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaMitra = itemView.findViewById(R.id.text_tindakan_item_mitra);
            rvTindakan = itemView.findViewById(R.id.rv_item_checkout_tindakan_mitra);
        }
    }
}
