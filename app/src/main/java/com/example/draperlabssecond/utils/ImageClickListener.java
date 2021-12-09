package com.example.draperlabssecond.utils;

import android.content.Context;

import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.views.adapter.ImageGalleryChildViewHolder;
import com.example.draperlabssecond.views.adapter.ImageGalleryViewHolder;
import java.util.List;

public interface ImageClickListener {

    void onImageClicked(Context context, ImageGalleryChildViewHolder imageGalleryChildViewHolder, int position, List<Image> data);
}
