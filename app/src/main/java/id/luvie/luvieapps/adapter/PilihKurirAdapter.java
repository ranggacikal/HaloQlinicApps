package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.luvie.luvieapps.CheckoutProdukActivity;
import id.luvie.luvieapps.PilihKurirActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.dataEkspedisi.ResponseDataEkspedisi;
import id.luvie.luvieapps.model.listPesanan.DataItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihKurirAdapter extends RecyclerView.Adapter<PilihKurirAdapter.PilihKurirViewHolder> {

    Context context;
    List<DataItem> dataPesanan;
    List<id.luvie.luvieapps.model.dataEkspedisi.DataItem> dataEkspedisi = new ArrayList<>();
    CheckoutProdukActivity checkoutProdukActivity;


    String kurir;
    int dataItems;

    public PilihKurirAdapter(Context context, List<DataItem> dataPesanan, CheckoutProdukActivity checkoutProdukActivity) {
        this.context = context;
        this.dataPesanan = dataPesanan;
        this.checkoutProdukActivity = checkoutProdukActivity;
    }

    @NonNull
    @Override
    public PilihKurirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pilih_kurir, parent, false);
        return new PilihKurirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihKurirViewHolder holder, int position) {

        checkoutProdukActivity.dataKurir = new ArrayList<>(getItemCount());

        List<Object> dataTest = new ArrayList<>(getItemCount());

        holder.txtNamaToko.setText(dataPesanan.get(position).getNamaToko());

        String kurir = dataPesanan.get(position).getKurir();
        String ongkir = dataPesanan.get(position).getOngkir();
        int setOngkir = Integer.parseInt(ongkir);

        if (ongkir.equals("0")){
            holder.linearDataKurir.setVisibility(View.GONE);
            holder.relativeLayoutPilihKurir.setVisibility(View.VISIBLE);
        }else{
            holder.linearDataKurir.setVisibility(View.VISIBLE);
            holder.relativeLayoutPilihKurir.setVisibility(View.GONE);
            holder.txtNamaKurir.setText(dataPesanan.get(position).getEkspedisi());
            holder.txtLayanan.setText(dataPesanan.get(position).getLayananKurir());
            holder.txtOngkir.setText("Rp" + NumberFormat.getInstance().format(setOngkir));
        }

        dataItems = getItemCount();

        holder.relativeLayoutPilihKurir.setOnClickListener(new View.OnClickListener() {
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

        holder.txtGantiKurir.setOnClickListener(new View.OnClickListener() {
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

//        dataTest.add(position, kurir);
//
//
//        Log.d("dataKurir", "onItemSelected: " + dataTest);

    }

    @Override
    public int getItemCount() {
        return dataPesanan.size();
    }

    public class PilihKurirViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaToko, txtNamaKurir, txtLayanan, txtOngkir, txtGantiKurir;
        Spinner spinnerEkspedisi, spinnerLayanan;
        RelativeLayout relativeLayoutPilihKurir;
        LinearLayout linearDataKurir;

        public PilihKurirViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaToko = itemView.findViewById(R.id.text_toko_item_kurir);
            spinnerEkspedisi = itemView.findViewById(R.id.spinner_ekspedisi_item_kurir);
            spinnerLayanan = itemView.findViewById(R.id.spinner_layanan_item_kurir);
            relativeLayoutPilihKurir = itemView.findViewById(R.id.linear_kurir);
            txtNamaKurir = itemView.findViewById(R.id.text_item_nama_data_kurir);
            txtLayanan = itemView.findViewById(R.id.text_item_layanan_data_kurir);
            txtOngkir = itemView.findViewById(R.id.text_item_ongkir_data_kurir);
            txtGantiKurir = itemView.findViewById(R.id.text_item_ganti_data_kurir);
            linearDataKurir = itemView.findViewById(R.id.linear_data_kurir);
        }
    }

    private void initSpinnerEkspedisi(Spinner spinnerEkspedisi) {

        ConfigRetrofit.service.dataEkspedisi().enqueue(new Callback<ResponseDataEkspedisi>() {
            @Override
            public void onResponse(Call<ResponseDataEkspedisi> call, Response<ResponseDataEkspedisi> response) {
                if (response.isSuccessful()) {

                    dataEkspedisi = response.body().getData();
                    List<String> listSpinnerEkspedisi = new ArrayList<String>();
                    for (int i = 0; i < dataEkspedisi.size(); i++) {

                        listSpinnerEkspedisi.add(dataEkspedisi.get(i).getEkspedisi());

                    }

                    ArrayAdapter<String> adapterEkspedisi = new ArrayAdapter<String>(context,
                            R.layout.spinner_item, listSpinnerEkspedisi);

                    adapterEkspedisi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinnerEkspedisi.setAdapter(adapterEkspedisi);
                } else {
                    Toast.makeText(context, "Gagal memuat ekspedisi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataEkspedisi> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
