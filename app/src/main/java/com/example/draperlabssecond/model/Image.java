package com.example.draperlabssecond.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// DAY ENUM ////////////
enum Day {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    Day(String string) {
    }
}

enum Month {
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March");

    Month(String string) {
    }
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

    /**
     * IMAGE UNIFORM RESOURCE ID String
     **/
    private String uriResourceIdString;

    /**
     * IMAGE EPOCH DATE FORMATTED String
     **/
    public String getDateStringFromEpoch() {
        return dateStringFromEpoch;
    }

    public void setDateStringFromEpoch(String dateStringFromEpoch) {
        this.dateStringFromEpoch = dateStringFromEpoch;
    }

    /*** String Date from Epoch ***/
    private String dateStringFromEpoch;

    /*** Date from Epoch List ***/
    List<String> dateStringFromEpochList;

    /*** CALENDAR ***/
    Calendar calendar;

    /*** DATE ***/
    Date date;

    /*** DAY ***/
    Day day;

    /*** Public Constructor ***/
    public Image(int imageId, String path, String imageTitle, long epoch, String uriResourceIdString) {
        this.imageId = imageId;
        this.path = path;
        this.imageTitle = imageTitle;
        this.epoch = epoch;
        this.uriResourceIdString = uriResourceIdString;
        convertEpochToDateString();
    }

    /**
     * Parcelable Constructor
     **/
    protected Image(Parcel in) {
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

    /**
     * 	String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));
     * **/

    /*** Convert EPOCH to String Date ***/
    private void convertEpochToDateString() {
        setDateStringFromEpoch(new java.text.SimpleDateFormat("E MM/dd/yyyy").format(new java.util.Date(getEpoch() * 1000)));
        parseEpochToDateString();
    }

    private void parseEpochToDateString(){
        String[] parsedString = getDateStringFromEpoch().split("\\s+|/");
        dateStringFromEpochList = Arrays.asList(parsedString);
        setDay();
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

    public void setDay() {
        String dayOfWeek = dateStringFromEpochList.get(0);

        switch (dayOfWeek) {
            case "Sun":
                day = Day.SUNDAY;
                break;

            case "Mon":
                this.day = Day.MONDAY;
                break;

            case "Tues":
                this.day = Day.TUESDAY;
                break;

            case "Wed":
                this.day = Day.WEDNESDAY;
                break;

            case "Thu":
                this.day = Day.THURSDAY;
                break;

            case "Fri":
                this.day = Day.FRIDAY;
                break;

            case "Sat":
                this.day = Day.SATURDAY;
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
