package edu.swin.student.carl.foodparcels;

import android.os.Parcel;
import android.os.Parcelable;

public class foodparcel implements Parcelable {

    String Name, location, keyword, dates, who, rating;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(location);
        dest.writeString(keyword);
        dest.writeString(dates);

        dest.writeString(who);
        dest.writeString(rating);


    }

    public foodparcel(String name, String location, String keyword, String dates,  String who, String rating) {

        this.Name=name;
        this.location=location;
        this.keyword=keyword;
        this.dates=dates;

        this.who=who;
        this.rating=rating;

    }



    protected foodparcel(Parcel in) {

        this.Name=in.readString();
        this.location=in.readString();
        this.keyword=in.readString();
        this.dates=in.readString();

        this.who=in.readString();
        this.rating=in.readString();
    }

    public static final Parcelable.Creator<foodparcel> CREATOR = new Parcelable.Creator<foodparcel>() {
        @Override
        public foodparcel createFromParcel(Parcel source) {
            return new foodparcel(source);
        }

        @Override
        public foodparcel[] newArray(int size) {
            return new foodparcel[size];
        }
    };
}
