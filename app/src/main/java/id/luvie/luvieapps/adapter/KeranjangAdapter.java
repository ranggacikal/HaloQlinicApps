package id.luvie.luvieapps.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import id.luvie.luvieapps.KeranjangActivity;
import id.luvie.luvieapps.R;
import id.luvie.luvieapps.api.ConfigRetrofit;
import id.luvie.luvieapps.model.dataKeranjang.DataItem;
import id.luvie.luvieapps.model.hapusKeranjang.ResponseHapusKeranjang;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.KeranjangViewHolder> {

    Context context;
    List<DataItem> dataKeranjang;
    private KeranjangActivity keranjangActivity;

    public KeranjangAdapter(Context context, List<DataItem> dataKeranjang, KeranjangActivity keranjangActivity) {
        this.context = context;
        this.dataKeranjang = dataKeranjang;
        this.keranjangActivity = keranjangActivity;
    }

    @NonNull
    @Override
    public KeranjangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang, parent, false);
        return new KeranjangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeranjangViewHolder holder, int position) {

        Glide.with(context)
                .load(dataKeranjang.get(position).getImg())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgKeranjang);

        String jumlah = dataKeranjang.get(position).getJumlah();
        String harga = dataKeranjang.get(position).getHarga();

        int total = Integer.parseInt(jumlah) * Integer.parseInt(harga);
        holder.numberPicker.setNumber(jumlah);

        holder.numberPicker.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = holder.numberPicker.getNumber();
                if (number.equals("0")){
                    Toast.makeText(context, "Tidak Boleh kurang dari 1", Toast.LENGTH_SHORT).show();
                    holder.numberPicker.setNumber("1");
                }else{
                    int total = Integer.parseInt(number) * Integer.parseInt(dataKeranjang.get(position).getHargaJual());
                    Log.d("testTotal", "number: "+number+" harga: "+dataKeranjang.get(position).getHarga()+" total: "+total);
                    dataKeranjang.get(position).setHarga(String.valueOf(total));
                    dataKeranjang.get(position).setJumlah(number);
                    notifyDataSetChanged();
                }

            }
        });

        Log.d("cekData", "onBindViewHolder: "+dataKeranjang.get(position).getJumlah());

        holder.txtNamaProduk.setText(dataKeranjang.get(position).getNamaProduk());
        holder.txtHargaProduk.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(dataKeranjang.get(position).getHarga())));
        holder.txtVariasi.setText(dataKeranjang.get(position).getVariasi());

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(context)
                        .setTitle("Hapus Item?")
                        .setMessage("Anda yakin ingin menghapus item ini?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                hapusItem(dataKeranjang.get(position).getIdPesan());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });


    }

    private void hapusItem(String idPesan) {

        ConfigRetrofit.service.hapusKeranjang(idPesan).enqueue(new Callback<ResponseHapusKeranjang>() {
            @Override
            public void onResponse(Call<ResponseHapusKeranjang> call, Response<ResponseHapusKeranjang> response) {
                if (response.isSuccessful()){
                    notifyDataSetChanged();
                    Toast.makeText(context, "Berhasil Hapus Item", Toast.LENGTH_SHORT).show();
                    keranjangActivity.loadDataKeranjang();
                }else{
                    Toast.makeText(context, "Gagal hapus item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHapusKeranjang> call, Throwable t) {
                Toast.makeText(context, "Terjadi kesalahan di server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataKeranjang.size();
    }

    public class KeranjangViewHolder extends RecyclerView.ViewHolder {

        ImageView imgKeranjang;
        TextView txtNamaProduk, txtHargaProduk, txtVariasi;
        ElegantNumberButton numberPicker;
        ImageView imgDelete;

        public KeranjangViewHolder(@NonNull View itemView) {
            super(itemView);
            imgKeranjang = itemView.findViewById(R.id.img_item_keranjang);
            txtNamaProduk = itemView.findViewById(R.id.text_item_nama_produk_keranjang);
            txtHargaProduk = itemView.findViewById(R.id.text_item_harga_produk_keranjang);
            numberPicker = itemView.findViewById(R.id.elegant_nb_item_keranjang);
            imgDelete = itemView.findViewById(R.id.img_delete_item_keranjang);
            txtVariasi = itemView.findViewById(R.id.text_item_variasi_keranjang);
        }
    }
}
