package com.example.draperlabssecond.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.draperlabssecond.R;
import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.utils.ImageClickListener;

import java.util.List;

public class ImageGalleryChildAdapter extends RecyclerView.Adapter<ImageGalleryChildViewHolder> {

    private Context context;
    private List<Image> data;
    private ImageClickListener imageClickListener;

    public ImageGalleryChildAdapter() {
    }

    public ImageGalleryChildAdapter(Context context, List<Image> data,  ImageClickListener imageClickListener) {
        this.context = context;
        this.data = data;
        this.imageClickListener = imageClickListener;
    }


    @Override
    public ImageGalleryChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_recycler_view, parent, false);

        return new ImageGalleryChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageGalleryChildViewHolder holder, int position) {

//        Image image = data.get(position);
//        holder.getImageView().setImageURI(image.getUriResourceId());
        Glide.with(context)
                .load(data.get(position).getPath())
                .apply(new RequestOptions().centerCrop())
                .into(holder.getImageView());

        holder.getImageView().setOnClickListener(e -> {
            imageClickListener.onImageClicked(context, holder, position, data);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
