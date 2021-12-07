package com.example.draperlabssecond.viewmodel;

/** PACKAGES **/
import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.ViewModel;
import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.model.PicturesFolder;
import com.example.draperlabssecond.repository.ImageGalleryRepository;
import java.util.List;

public class ImageGalleryViewModel extends ViewModel {

    /** CLASS MEMBERS **/
    private Context context;
    private Uri collection;
    private List<Image> data;
    private PicturesFolder picturesFolder;
    private ImageGalleryRepository imageGalleryRepository;

    /** ImageGalleryViewModel CONSTRUCTOR **/
    public ImageGalleryViewModel(Context context, Uri collection) {
        this.context = context;
        this.collection = collection;
        this.picturesFolder = new PicturesFolder(this.context, this.collection);
        imageGalleryRepository = new ImageGalleryRepository(picturesFolder);
        imageGalleryRepository.getPhotos();
    }

    /** GET DATA FROM THE ImageGalleryRepository **/
    public void getPhotos(){
        imageGalleryRepository.getPhotos();
    }


    /** GET DATA FROM THE ImageGalleryRepository **/
    public List<Image> getData(){
        return data = imageGalleryRepository.getData();
    }

}
