package com.example.draperlabssecond.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.draperlabssecond.R;
import com.example.draperlabssecond.databinding.FullImageBinding;
import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.model.PicturesFolder;
import com.example.draperlabssecond.views.adapter.ImageGalleryViewHolder;
import com.google.android.material.appbar.MaterialToolbar;

import org.jetbrains.annotations.NotNull;

import java.security.PrivilegedAction;
import java.util.List;

public class FullPictureFragment extends Fragment {

    private FullImageBinding fullImageBinding;
    private MaterialToolbar toolbar;
    private Context context;
    private ImageGalleryViewHolder imageGalleryViewHolder;
    private List<Image> data;
    private int position;
    private ImageView fullImage;

    public FullPictureFragment(){

    }

    public FullPictureFragment(Context context, ImageGalleryViewHolder imageGalleryViewHolder, int position, List<Image> data){
        this.context = context;
        this.imageGalleryViewHolder = imageGalleryViewHolder;
        this.data = data;
        this.position = position;
    }

    public static FullPictureFragment newInstance(Context context, ImageGalleryViewHolder imageGalleryViewHolder, List<Image> data, int position){
        return new FullPictureFragment(context, imageGalleryViewHolder, position, data);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       fullImageBinding = DataBindingUtil.inflate(inflater, R.layout.full_image, container, false );
       fullImage = fullImageBinding.imageView;


       return fullImageBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FullPictureFragmentArgs fullPictureFragmentArgs = FullPictureFragmentArgs.fromBundle(getArguments());
        Image image = fullPictureFragmentArgs.getFullImageParcelable();
        fullImage.setImageURI(image.getUriResourceId());

        toolbar = getActivity().findViewById(R.id.top_app_tool_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_fullPictureFragment_to_imageGalleryFragment);
            }
        });
    }

}
