package com.example.draperlabssecond.model;

/** PACKAGES **/
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import java.util.ArrayList;
import java.util.List;

public class PicturesFolder {

    /**
     * CLASS MEMBER PROPERTIES
     **/
    private Cursor cursor;
    private Uri collection;
    private List<Image> photos;
    private Context context;

    /*** IMAGES ***/
    public final static String MEDIA_STORE_ID = MediaStore.Images.Media._ID;
    public final static String MEDIA_STORE_TITLE = MediaStore.Images.Media.TITLE;
    public final static String MEDIA_STORE_DATE_ADDED = MediaStore.Images.Media.DATE_ADDED;
    public final static String MEDIA_STORE_DATA = MediaStore.Images.ImageColumns.DATA;
    public final static String MEDIA_STORE_DISPLAY_NAME = MediaStore.Images.Media.DISPLAY_NAME;
    public final static String MEDIA_STORE_BUCKET_DISPLAY_NAME = MediaStore.Images.Media.BUCKET_DISPLAY_NAME;
    public final static String MEDIA_STORE_BUCKET_ID = MediaStore.Images.Media.BUCKET_ID;

    /*** DOWNLOADS ***/
    public final static String MEDIA_STORE_Downloads_ID = MediaStore.Downloads._ID;
    public final static String MEDIA_STORE_Downloads_TITLE = MediaStore.Downloads.TITLE;
    public final static String MEDIA_STORE_Downloads_DATE_ADDED = MediaStore.Downloads.DATE_ADDED;

    /**
     * Projection Array
     **/
    public static String[] projection;

    /**
     * Empty Constructor
     **/
    public PicturesFolder() {
        photos = new ArrayList<>();
        collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    /** PicturesFolder CONSTRUCTOR **/
    public PicturesFolder(Context context, Uri collection) {
        this.context = context;
        photos = new ArrayList<>();
        this.collection = collection;
    }

    /**
     * Retrieve Images from the SDCard
     **/
    public List<Image> getPhotos() {

        projection = new String[]{
                MEDIA_STORE_ID,
                MEDIA_STORE_DATA,
                MEDIA_STORE_TITLE,
                MEDIA_STORE_DATE_ADDED
        };

        try {
            cursor = context.getContentResolver().query(collection, projection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            do {

                /*** Get values of columns for a given image. ***/

                // IMAGE ID
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MEDIA_STORE_ID));

                String path = cursor.getString(cursor.getColumnIndexOrThrow(MEDIA_STORE_DATA));

                // IMAGE NAME
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MEDIA_STORE_TITLE));

                // IMAGE DATE EPOCH
                long epoch = cursor.getLong(cursor.getColumnIndexOrThrow(MEDIA_STORE_DATE_ADDED));

                // IMAGE URI
                Uri contentUri = ContentUris.withAppendedId(collection, id);

                // IMAGE URI String
                String contentUriString = contentUri.toString();

                // Create an Image Instance
                Image image = new Image(id, path, name, epoch, contentUriString);

                // ADD IMAGE TO PHOTOS LIST
                this.photos.add(image);
            } while (cursor.moveToNext());
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return photos;
    }
}
