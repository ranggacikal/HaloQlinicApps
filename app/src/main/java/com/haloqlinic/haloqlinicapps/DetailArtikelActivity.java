package com.haloqlinic.haloqlinicapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.adapter.SliderAdapter;
import com.haloqlinic.haloqlinicapps.api.ConfigRetrofit;
import com.haloqlinic.haloqlinicapps.databinding.ActivityArtikelBinding;
import com.haloqlinic.haloqlinicapps.databinding.ActivityDetailArtikelBinding;
import com.haloqlinic.haloqlinicapps.model.detailArtikel.DataItem;
import com.haloqlinic.haloqlinicapps.model.detailArtikel.ListItem;
import com.haloqlinic.haloqlinicapps.model.detailArtikel.ResponseDetailArtikel;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class DetailArtikelActivity extends AppCompatActivity implements Html.ImageGetter {

    private ActivityDetailArtikelBinding binding;

    String id_artikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailArtikelBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        id_artikel = getIntent().getStringExtra("id_artikel");

        PushDownAnim.setPushDownAnimTo(binding.imgBackDetailArtikel)
                .setScale(MODE_SCALE, 0.89f)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        loadData();

    }

    private void loadData() {

        ProgressDialog progressDialog = new ProgressDialog(DetailArtikelActivity.this);
        progressDialog.setMessage("Load Data");
        progressDialog.show();

        ConfigRetrofit.service.detailArtikel(id_artikel).enqueue(new Callback<ResponseDetailArtikel>() {
            @Override
            public void onResponse(Call<ResponseDetailArtikel> call, Response<ResponseDetailArtikel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();

                    List<DataItem> dataItems = response.body().getData();
                    List<ListItem> dataList = null;
                    List<ListItem> dataImage = new ArrayList<>();
                    String judul = "";
                    String penulis = "";
                    String isi = "";
                    String img = "";

                    String link = "https://aplikasicerdas.net/haloqlinic/img/artikel_img/";

                    for (int a = 0; a < dataItems.size(); a++) {

                        dataList = dataItems.get(a).getList();
                        judul = dataItems.get(a).getJudul();
                        penulis = dataItems.get(a).getCreatedBy();
                        isi = dataItems.get(a).getTeks();
                        img = dataItems.get(a).getImg();

                        for (int b = 0; b < dataList.size(); b++) {

                            dataImage.add(dataList.get(b));

                        }

                    }

                    Log.d("checkDataImage", "onResponse: " + dataImage.size());
                    Log.d("checkDataImage", "linkImage: " + link + img);

                    if (dataImage.size() != 0) {
                        SliderAdapter adapter = new SliderAdapter(DetailArtikelActivity.this, dataImage);
                        binding.imageSlider.setSliderAdapter(adapter);
                    } else {
                        binding.imageSlider.setVisibility(View.GONE);
                    }
                    binding.textJudulDetailArtikel.setText(judul);
                    binding.textPenulisDetailArtikel.setText(penulis);
                    Spanned spanned = Html.fromHtml(isi, DetailArtikelActivity.this::getDrawable, null);
                    binding.textIsiDetailArtikel.setText(spanned);

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(DetailArtikelActivity.this, "Gagal Load Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailArtikel> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(DetailArtikelActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public Drawable getDrawable(String source) {
        LevelListDrawable d = new LevelListDrawable();
        Drawable empty = getResources().getDrawable(R.drawable.ic_launcher);
        d.addLevel(0, 0, empty);
        d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

        new LoadImage().execute(source, d);

        return d;
    }

    class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d("testImageGetter", "doInBackground " + source);
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d("testImageGetter", "onPostExecute drawable " + mDrawable);
            Log.d("testImageGetter", "onPostExecute bitmap " + bitmap);
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = binding.textIsiDetailArtikel.getText();
                binding.textIsiDetailArtikel.setText(t);
            }
        }

    }



}
