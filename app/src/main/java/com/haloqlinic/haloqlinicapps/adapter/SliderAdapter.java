package com.haloqlinic.haloqlinicapps.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.haloqlinic.haloqlinicapps.R;
import com.haloqlinic.haloqlinicapps.model.detailArtikel.ListItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {

    Context context;
    List<ListItem> dataImage;

    public SliderAdapter(Context context, List<ListItem> dataImage) {
        this.context = context;
        this.dataImage = dataImage;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_carousel_item, null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {


        String link = "https://aplikasicerdas.net/haloqlinic/img/artikel_img/";

        Glide.with(context)
                .load(link+dataImage.get(position).getImages())
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.imgSlider);

    }

    @Override
    public int getCount() {
        return dataImage.size();
    }

    class SliderViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView imgSlider;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imgSlider = itemView.findViewById(R.id.img_carousel_detail_artikel);
        }
    }
}
