package edu.swin.student.carl.booklist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<book> booksList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, rating;
        public ImageView icon;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            rating = (TextView) view.findViewById(R.id.rating);
            icon =  view.findViewById(R.id.icon);
        }
    }

    public MyAdapter(List<book> booksList) {
        this.booksList = booksList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_layout, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        book book = booksList.get(position);


        holder.title.setText(book.getTitle());
        holder.rating.setText(book.getRating());
        holder.icon.setImageResource(R.mipmap.kk);
    }

    public int getItemCount() {
        return booksList.size();
    }

}
