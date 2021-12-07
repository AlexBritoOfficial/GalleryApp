package com.example.draperlabssecond.views.fragment;

/** PACKAGES **/
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.draperlabssecond.R;
import com.example.draperlabssecond.databinding.ImageGalleryFragmentBinding;
import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.utils.ImageClickListener;
import com.example.draperlabssecond.viewmodel.ImageGalleryViewModel;
import com.example.draperlabssecond.views.adapter.ImageGalleryAdapter;
import com.example.draperlabssecond.views.adapter.ImageGalleryViewHolder;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class ImageGalleryFragment extends Fragment implements ImageClickListener {

    /** CLASS MEMBERS **/
    private static final int READ_STORAGE_PERMISSION_REQUEST_CODE = 0;
    private NavController navController;
    private ImageGalleryFragmentBinding imageGalleryFragmentBinding;
    private ImageGalleryViewModel imageGalleryViewModel;
    private ImageGalleryAdapter imageGalleryAdapter;
    private Uri collection;

    /** Empty Constructor **/
    public ImageGalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /** CHECK PERMISSIONS ON USERS PHONE. **/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            /** Get the MediaStore.Images.Media Collection **/
            collection =  MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);

            if(!(checkPermissionForReadExtertalStorage())){
                try {
                    requestPermissionForReadExtertalStorage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{
           collection =  MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        /** Set FragmentImageGalleryBinding **/
        imageGalleryFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.image_gallery_fragment, container, false);


        /** Set RecyclerView to have GridLayoutManager **/
        imageGalleryFragmentBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        imageGalleryViewModel = new ImageGalleryViewModel(getContext(), collection);
        imageGalleryAdapter = new ImageGalleryAdapter(getContext(), imageGalleryViewModel.getData(),this);

        /** Populate RecyclerView with Data **/
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized (this){
                    imageGalleryFragmentBinding.recyclerView.setAdapter(imageGalleryAdapter);
                }
            }
        });

        return imageGalleryFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    /**  CHECK PERMISSION TO READ EXTERNAL STORAGE **/
    public boolean checkPermissionForReadExtertalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = getContext().checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    /** REQUEST PERMISSION TO READ EXTERNAL STORE **/
    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void onImageClicked(Context context, ImageGalleryViewHolder imageGalleryViewHolder, int position, List<Image> data) {
        FullPictureFragment fullPictureFragment = new FullPictureFragment(getContext(),imageGalleryViewHolder, position, data);
        Bundle bundle = new Bundle();
        bundle.putParcelable("FullImageParcelable", data.get(position));

        navController.navigate(R.id.action_imageGalleryFragment_to_fullPictureFragment, bundle);
    }

    /***
     * Populate the recyclerview with the data from sdcard
     ***/
//    public void populateRecyclerView(){
//
//        /** Update the UI on the UI Thread**/
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                /** Logging to make sure I am completing this task on main thread **/
//                Log.i("UI Thread Name: ", Thread.currentThread().getName());
//                // Set RecyclerView Adapter
//                imageGalleryFragmentBinding.recyclerView.setAdapter(new ImageGalleryAdapter(getContext(), imageGalleryViewModel.getData()));
//            }
//        });
//    }

/** Brute Force:  Methods I was playing with to get started. Then I cleaned everything putting the methods,
 *  in their respective classes. Following the 'separation of concern' design pattern (MVVM)
 **/

//    public void getData(){
//        new Runnable() {
//            @Override
//            public void run() {
//                Log.i("MyThread Name: ", Thread.currentThread().getName());
//                synchronized (this){
//                    data = picturesFolder.getPhotos();
//                }
//            }
//        };
//    }
//
//    class MyThread implements Runnable{
//        PicturesFolder picturesFolder;
//        List<Image> data;
//        MyThread(PicturesFolder picturesFolder){
//            this.picturesFolder = picturesFolder;
//        }
//
//        @Override
//        public void run() {
//            Log.i("MyThread Name: ", Thread.currentThread().getName());
//            synchronized (this){
//                    data = this.picturesFolder.getPhotos();
//            }
//        }
//
//        public List<Image> getData() {
//            return data;
//        }
//    }


}
