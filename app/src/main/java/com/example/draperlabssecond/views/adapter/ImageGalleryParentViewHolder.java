package com.example.draperlabssecond.views.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.draperlabssecond.R;

public class ImageGalleryParentViewHolder extends RecyclerView.ViewHolder {

    /*** MEMBER TYPES ***/
    private TextView dateFormatTextView;
    private RecyclerView childRecyclerView;


    /***  CONSTRUCTOR  ***/
    public ImageGalleryParentViewHolder(View itemView) {
        super(itemView);
        dateFormatTextView = itemView.findViewById(R.id.date_text_view);
        childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
    }


    /*** GETTERS AND SETTERS ***/

    public RecyclerView getChildRecyclerView() {
        return childRecyclerView;
    }

    public void setChildRecyclerView(RecyclerView childRecyclerView) {
        this.childRecyclerView = childRecyclerView;
    }

    public TextView getDateFormatTextView() {
        return dateFormatTextView;
    }

    public void setDateFormatTextView(TextView dateFormatTextView) {
        this.dateFormatTextView = dateFormatTextView;
    }

}
