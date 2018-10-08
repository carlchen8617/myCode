package edu.swin.student.carl.swinlibapp;

import android.os.Parcel;
import android.os.Parcelable;

class staffParcel implements Parcelable{

    String Name;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);

    }

    public staffParcel(String id){

        this.Name=id;
    }

    protected staffParcel(Parcel in) {

        this.Name=in.readString();
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
