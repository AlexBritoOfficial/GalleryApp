package com.example.draperlabssecond.views.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.draperlabssecond.R;
import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.model.PicturesFolder;
import com.example.draperlabssecond.utils.ImageClickListener;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryViewHolder> {

    private String TAG = "ImageGalleryAdapter";
    private Context context;
    private List<Image> data;
    private Activity activity;
    private ImageClickListener imageClickListener;
    int value;


    public ImageGalleryAdapter(Context context,List data, ImageClickListener imageClickListener) {
        this.context = context;
        this.data = data;
        this.imageClickListener = imageClickListener;
    }

    public ImageGalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_gallery_fragment_view_holder, parent, false);

        return new ImageGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageGalleryViewHolder holder, int position) {

        Glide.with(context)
                .load(data.get(position).getPath())
                .apply(new RequestOptions().centerCrop())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
