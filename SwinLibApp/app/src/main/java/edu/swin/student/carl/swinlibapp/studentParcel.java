package edu.swin.student.carl.swinlibapp;

import android.os.Parcel;
import android.os.Parcelable;

class studentParcel implements Parcelable {

    String Name;
    String id;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(id);

    }

    public studentParcel(String name, String id){

        this.Name=name;
        this.id=id;
    }

    protected studentParcel(Parcel in) {

        this.Name=in.readString();
        this.id=in.readString();

    }

    public static final Parcelable.Creator<studentParcel> CREATOR = new Parcelable.Creator<studentParcel>() {
        @Override
        public studentParcel createFromParcel(Parcel source) {
            return new studentParcel(source);
        }

        @Override
        public studentParcel[] newArray(int size) {
            return new studentParcel[size];
        }
    };

}
