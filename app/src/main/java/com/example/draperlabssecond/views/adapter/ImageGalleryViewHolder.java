package com.example.draperlabssecond.views.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.draperlabssecond.R;

public class ImageGalleryViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public ImageGalleryViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.unsplashImage);
    }

    public ImageView getImageView() {
        return imageView;
    }

}
