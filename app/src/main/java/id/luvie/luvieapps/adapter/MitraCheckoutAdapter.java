package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.CheckoutProdukActivity;
import id.luvie.luvieapps.PilihKurirActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.listPesanan.DataItem;
import id.luvie.luvieapps.model.listPesanan.ProdukItem;
import id.luvie.luvieapps.model.listPesanan.ResponseListPesanan;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class MitraCheckoutAdapter extends RecyclerView.Adapter<MitraCheckoutAdapter.MitraCheckoutViewHolder> {

    Context context;
    List<DataItem> dataPesanan;
    CheckoutProdukActivity checkoutProdukActivity;

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public MitraCheckoutAdapter(Context context, List<DataItem> dataPesanan, CheckoutProdukActivity checkoutProdukActivity) {
        this.context = context;
        this.dataPesanan = dataPesanan;
        this.checkoutProdukActivity = checkoutProdukActivity;
    }

    @NonNull
    @NotNull
    @Override
    public MitraCheckoutViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout_mitra, parent, false);
        return new MitraCheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MitraCheckoutViewHolder holder, int position) {

        holder.txtNamaToko.setText(dataPesanan.get(position).getNamaToko());

        String kurir = dataPesanan.get(position).getKurir();
        String ongkir = dataPesanan.get(position).getOngkir();
        int setOngkir = Integer.parseInt(ongkir);

        if (ongkir.equals("0")){
            holder.linearDataKurir.setVisibility(View.GONE);
            holder.relativePilihKurir.setVisibility(View.VISIBLE);
        }else{
            holder.linearDataKurir.setVisibility(View.VISIBLE);
            holder.relativePilihKurir.setVisibility(View.GONE);
            holder.txtNamaKurir.setText(dataPesanan.get(position).getEkspedisi());
            holder.txtLayanan.setText(dataPesanan.get(position).getLayananKurir());
            holder.txtOngkir.setText("Rp" + NumberFormat.getInstance().format(setOngkir));
        }

        PushDownAnim.setPushDownAnimTo(holder.relativePilihKurir)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PilihKurirActivity.class);
                        intent.putExtra("id_kecamatan", checkoutProdukActivity.id_kecamatan);
                        intent.putExtra("berat", dataPesanan.get(position).getTotalBerat());
                        intent.putExtra("id_kota", dataPesanan.get(position).getKota());
                        intent.putExtra("id_transaksi", checkoutProdukActivity.id_transaksi);
                        intent.putExtra("id_member", dataPesanan.get(position).getIdMember());
                        context.startActivity(intent);
                    }
                });

        PushDownAnim.setPushDownAnimTo(holder.txtGantiKurir)
                .setScale( MODE_SCALE, 0.89f  )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, PilihKurirActivity.class);
                        intent.putExtra("id_kecamatan", checkoutProdukActivity.id_kecamatan);
                        intent.putExtra("berat", dataPesanan.get(position).getTotalBerat());
                        intent.putExtra("id_kota", dataPesanan.get(position).getKota());
                        intent.putExtra("id_transaksi", checkoutProdukActivity.id_transaksi);
                        intent.putExtra("id_member", dataPesanan.get(position).getIdMember());
                        context.startActivity(intent);
                    }
                });

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvProduk.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        holder.rvProduk.setLayoutManager(layoutManager);
        holder.rvProduk.setHasFixedSize(true);

        String id_transaksi = checkoutProdukActivity.id_transaksi;

        List<ProdukItem> dataProduk = dataPesanan.get(position).getProduk();

        ConfigRetrofit.service.listPesanan(id_transaksi).enqueue(new Callback<ResponseListPesanan>() {
            @Override
            public void onResponse(Call<ResponseListPesanan> call, Response<ResponseListPesanan> response) {
                if (response.isSuccessful()){

                    CheckoutProdukAdapter adapterProduk = new CheckoutProdukAdapter(context, dataProduk);
                    holder.rvProduk.setAdapter(adapterProduk);
                    holder.rvProduk.setRecycledViewPool(viewPool);

                }else{
                    Toast.makeText(context, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListPesanan> call, Throwable t) {
                Toast.makeText(context, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataPesanan.size();
    }

    public class MitraCheckoutViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaToko, txtNamaProduk, txtVariasi, txtJumlah, txtHargaProduk, txtJumlahProduk
                ,txtTotalProduk, txtNamaKurir, txtLayanan, txtOngkir, txtGantiKurir;;
        ImageView imgProduk;
        RelativeLayout relativePilihKurir;
        LinearLayout linearDataKurir;
        RecyclerView rvProduk;

        public MitraCheckoutViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            txtNamaToko = itemView.findViewById(R.id.text_toko_item_mitra);
            relativePilihKurir = itemView.findViewById(R.id.relative_kurir_mitra);
            txtNamaKurir = itemView.findViewById(R.id.text_item_nama_data_kurir_mitra);
            txtLayanan = itemView.findViewById(R.id.text_item_layanan_data_kurir_mitra);
            txtOngkir = itemView.findViewById(R.id.text_item_ongkir_data_kurir_mitra);
            txtGantiKurir = itemView.findViewById(R.id.text_item_ganti_data_kurir_mitra);
            linearDataKurir = itemView.findViewById(R.id.linear_data_kurir_mitra);
            rvProduk = itemView.findViewById(R.id.rv_item_checkout_produk_mitra);

        }
    }
}
