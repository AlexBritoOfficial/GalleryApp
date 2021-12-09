package com.example.draperlabssecond.views.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.draperlabssecond.R;
import com.google.android.material.card.MaterialCardView;

public class ImageGalleryChildViewHolder extends RecyclerView.ViewHolder {

    private MaterialCardView materialCardView;
    private ImageView imageView;

    public ImageGalleryChildViewHolder(View itemView) {
        super(itemView);
        materialCardView = itemView.findViewById(R.id.materialCardView);
        imageView = itemView.findViewById(R.id.sdCardImageView);
    }

    public MaterialCardView getMaterialCardView() {
        return materialCardView;
    }

    public void setMaterialCardView(MaterialCardView materialCardView) {
        this.materialCardView = materialCardView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
