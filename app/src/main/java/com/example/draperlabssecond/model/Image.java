package com.example.draperlabssecond.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

// DAY ENUM ////////////
enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

public class Image implements Parcelable {

    /*** Member Types ***/
    private int imageId;

    /*** IMAGE TITLE ***/
    private String imageTitle;

    /*** IMAGE PATH ***/
    private String path;


    /*** IMAGE EPOCH ***/
    private long epoch;

    /*** IMAGE UNIFORM RESOURCE ID ***/
    private Uri uriResourceId;

    /** IMAGE UNIFORM RESOURCE ID String**/
    private String uriResourceIdString;

    /*** CALENDAR ***/
    Calendar calendar;

    /*** DAY ***/
    Day day;

    /*** Public Constructor ***/
    public Image(int imageId, String path, String imageTitle, long epoch, String uriResourceIdString) {
        this.imageId = imageId;
        this.path = path;
        this.imageTitle = imageTitle;
        this.epoch = epoch;
        this.uriResourceIdString = uriResourceIdString;
    }

    /** Parcelable Constructor **/
    protected Image (Parcel in){
        this.imageId = in.readInt();
        this.path = in.readString();
        this.imageTitle = in.readString();
        this.epoch = in.readLong();
        this.uriResourceIdString = in.readString();
        this.uriResourceId = Uri.parse(this.uriResourceIdString);
    }

    /*** GETTERS & SETTERS IMAGE ID ***/
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /*** GETTERS & SETTERS IMAGE PATH ***/
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*** GETTER & SETTER IMAGE TITLE ***/
    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    /*** GETTER & SETTER IMAGE EPOCH ***/
    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    /*** GETTER & SETTER URI ***/
    public Uri getUriResourceId() {
        return Uri.parse(getUriResourceIdString());
    }

    public void setUriResourceId(Uri uriResourceId) {
        this.uriResourceId = uriResourceId;
    }

    /*** GETTER & SETTER URI String ***/
    public String getUriResourceIdString() {
        return uriResourceIdString;
    }

    public void setUriResourceIdString(String uriResourceIdString) {
        this.uriResourceIdString = uriResourceIdString;
    }

    /*** GETTER & SETTER CALENDAR ***/
    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    /*** GETTER & SETTER DAY ***/
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {

        Calendar c = Calendar.getInstance();

        Date date = new Date(this.epoch);

        c.setTime(date);

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case 1:
                this.day = Day.MONDAY;
                break;

            case 2:
                this.day = Day.TUESDAY;
                break;

            case 3:
                this.day = Day.WEDNESDAY;
                break;

            case 4:
                this.day = Day.THURSDAY;
                break;

            case 5:
                this.day = Day.FRIDAY;
                break;

            case 6:
                this.day = Day.SATURDAY;
                break;

            case 7:
                this.day = Day.SUNDAY;
                break;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int i) {

        destination.writeInt(this.imageId);
        destination.writeString(this.path);
        destination.writeString(this.imageTitle);
        destination.writeString(this.uriResourceIdString);
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        @Override
        public Image[] newArray(int i) {
            return new Image[i];
        }
    };
}
