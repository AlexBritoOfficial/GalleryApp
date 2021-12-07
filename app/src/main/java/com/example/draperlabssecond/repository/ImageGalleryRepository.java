package com.example.draperlabssecond.repository;

/** PACKAGES **/
import android.util.Log;
import com.example.draperlabssecond.model.Image;
import com.example.draperlabssecond.model.PicturesFolder;
import java.util.List;

public class ImageGalleryRepository {

    /** CLASS MEMBERS **/
    private final String TAG = "ImageGalleryRepository";
    private PicturesFolder picturesFolder;
    private List<Image> data;

    /** ImageGalleryRepository CONSTRUCTOR **/
    public ImageGalleryRepository(PicturesFolder picturesFolder) {
        this.picturesFolder = picturesFolder;
    }

   /** GET PHOTOS ON A SEPARATE THREAD **/
   public void getPhotos(){
       Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this){
                    /** Logging to make sure I am completing this task on a separate thread **/
                    Log.i("Runnable Thread Name: ", Thread.currentThread().getName());
                    data = picturesFolder.getPhotos();
                }
            }
        };

        /** Pass Runnable into Thread Class **/
        Thread thread = new Thread(runnable);

        /** Start thread **/
        thread.start();

        try {
            /** wait for the completion of thread **/
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   }

   /** GET DATA **/
   public List<Image> getData(){
        return data;
   }

}
