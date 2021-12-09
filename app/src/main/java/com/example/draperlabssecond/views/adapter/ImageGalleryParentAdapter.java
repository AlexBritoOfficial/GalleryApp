package com.example.draperlabssecond.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.draperlabssecond.R;
import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.utils.ImageClickListener;
import com.example.draperlabssecond.views.activity.MainActivity;

import java.util.List;

public class ImageGalleryParentAdapter extends RecyclerView.Adapter<ImageGalleryParentViewHolder> {

    private Context context;
    private List<Image> data;
    private ImageClickListener imageClickListener;

    public ImageGalleryParentAdapter(Context context, List<Image> data, ImageClickListener imageClickListener) {
        this.context = context;
        this.data = data;
        this.imageClickListener = imageClickListener;
    }

    public ImageGalleryParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.image_gallery_parent_layout_recycler_items, parent, false);

        return new ImageGalleryParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageGalleryParentViewHolder holder, int position) {

        Image image = data.get(position);
        holder.getDateFormatTextView().setText("Sun 12/05/2021");

        RecyclerView recyclerView = holder.getChildRecyclerView();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        ImageGalleryChildAdapter imageGalleryChildAdapter = new ImageGalleryChildAdapter(context, data, imageClickListener);
        recyclerView.setAdapter(imageGalleryChildAdapter);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
