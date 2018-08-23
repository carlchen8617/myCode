package edu.swin.student.carl.booklist;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView  bookcyclerView;
    RecyclerView.LayoutManager mgr;
    RecyclerView.Adapter mAdapter;
    String bb="place";


    private List<book> bookList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookcyclerView = (RecyclerView) findViewById(R.id.book);
        bookcyclerView.setHasFixedSize(true);

        


        mgr = new LinearLayoutManager(this);
        bookcyclerView.setLayoutManager(mgr);
        bookcyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyAdapter(bookList);
        bookcyclerView.setAdapter(mAdapter);

        prepareBookData();

    }

    private void prepareBookData() {







        book book = new book("The Storyteller's Secret: A Novel", "5 stars", bb);
        bookList.add(book);

        book = new book("Two Weeks Notice", "4 1/2 stars", bb);
        bookList.add(book);

        book = new book("Unhinged: An Insider's Account of the Trump", "4 stars", bb);
        bookList.add(book);

        book = new book("Not So Nice Guy", "3 stars", bb);
        bookList.add(book);



        book = new book("The Very Hungry Caterpillar", "3 stars", bb);
        bookList.add(book);

        book = new book("Girl, Wash Your Face: Stop Believing the Lies...", "1 stars", bb);
        bookList.add(book);

        book = new book("Goodnight Moon", "4 stars", bb);
        bookList.add(book);

        book = new book("Baby Touch and Feel: Animals", "3 1/2 stars", bb);
        bookList.add(book);

        book = new book("Goodbye, Things: The New Japanese Minimalism", "2 stars", bb);
        bookList.add(book);

        book = new book("Born a Crime: Stories from a South African...", "3 stars", bb);
        bookList.add(book);

        book = new book("The 5 Love Languages: The Secret to Love that...", "2 stars", bb);
        bookList.add(book);

        book = new book("Katie Daisy 2018 - 2019 On-the-Go Weekly...", "5 stars", bb);
        bookList.add(book);

        book = new book("Obama: An Intimate Portrait", "3 stars", bb);
        bookList.add(book);

        book = new book("Barnyard Dance! (Boynton on Board)", "3 stars", bb);
        bookList.add(book);

        book = new book("Hamilton: The Revolutionr", "3 1/w2 stars", bb);
        bookList.add(book);

        book = new book("Make Life Your Bitch: Motivational adult...", "4 stars", bb);
        bookList.add(book);

        mAdapter.notifyDataSetChanged();
    }
}
