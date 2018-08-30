package au.edu.swin.sdmd.suncalculatorjava;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class sunRiseAndSet implements Parcelable {


    private String day, rise, set;


    public sunRiseAndSet() {
    }

    public sunRiseAndSet(String day, String rise, String set) {
        this.day = day;
        this.rise = rise;
        this.set = set;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.day);
        dest.writeString(this.rise);
        dest.writeString(this.set);
    }

    protected sunRiseAndSet(Parcel in) {
        this.day = in.readString();
        this.rise = in.readString();
        this.set = in.readString();
    }

    public static final Parcelable.Creator<sunRiseAndSet> CREATOR = new Parcelable.Creator<sunRiseAndSet>() {
        @Override
        public sunRiseAndSet createFromParcel(Parcel source) {
            return new sunRiseAndSet(source);
        }

        @Override
        public sunRiseAndSet[] newArray(int size) {
            return new sunRiseAndSet[size];
        }
    };
}