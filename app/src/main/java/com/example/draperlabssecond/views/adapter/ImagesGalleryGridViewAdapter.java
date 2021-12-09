package com.example.draperlabssecond.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.draperlabssecond.R;
import com.example.draperlabssecond.model.Image;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ImagesGalleryGridViewAdapter extends ArrayAdapter<Image> {

    private Context context;
    private List<Image> data;
    private LayoutInflater inflater;
    private View mView;
    private MaterialCardView materialCardView;
    private ImageView imageView;

    public ImagesGalleryGridViewAdapter(Context context, int resource, List<Image> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        mView = view;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageView = view.findViewById(R.id.sdCardImageView);
        return mView;
    }

}
