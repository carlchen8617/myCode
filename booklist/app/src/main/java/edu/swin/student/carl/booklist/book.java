package edu.swin.student.carl.booklist;



import android.widget.ImageView;


public class book {
    private String title, rating,icon;


    public book() {
    }

    public book(String title, String rating, String icon) {
        this.title = title;
        this.rating = rating;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
