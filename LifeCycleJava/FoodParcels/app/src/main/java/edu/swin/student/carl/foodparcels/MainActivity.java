package edu.swin.student.carl.foodparcels;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    ImageView pizza, fish, rice, indian;
    TextView d1,d2, d3,d4;
    String Namy, location, keyword, dates, Share, who, rating, today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizza = findViewById(R.id.pizza);
        fish = findViewById(R.id.FnC);
        rice = findViewById(R.id.rice);
        indian = findViewById(R.id.vindaloo);
        d1=findViewById(R.id.date1);
        d2=findViewById(R.id.date2);
        d3=findViewById(R.id.date3);
        d4=findViewById(R.id.date4);





         new DownloadImageTask(pizza).execute("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpwngbGulwWFJDoXZdTF0x13Qu8D9QW-SZbDy_d-mnseGIgV6i");
         SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
         today = fmt.format(new Date());
         d1.setText(today );


         new DownloadImageTask(fish).execute("https://media.timeout.com/images/103696692/380/285/image.jpg");

         d2.setText(today);

         new DownloadImageTask(indian).execute("https://img.buzzfeed.com/buzzfeed-static/static/2013-11/enhanced/webdr05/18/17/enhanced-buzz-orig-7491-1384813081-10.jpg?downsize=715:*&output-format=auto&output-quality=auto");

         d3.setText(today);

         new DownloadImageTask(rice).execute("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlovCXf4bzpc5u6VwtLUIb_r1kyJ5hI8hB8fhGz8I0w_s1M7xD");

         d4.setText(today);

        fish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button
                Namy="Fish and Chip";
                location="https://media.timeout.com/images/103696692/380/285/image.jpg";
                keyword="my favorite food";
                dates=today;
                who="me@sample.com";
                rating="5";


                launchActivity(Namy, location,keyword,dates,who,rating);

            }
        });

        indian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button
                Namy="Indian Vindaloo";
                location="https://img.buzzfeed.com/buzzfeed-static/static/2013-11/enhanced/webdr05/18/17/enhanced-buzz-orig-7491-1384813081-10.jpg?downsize=715:*&output-format=auto&output-quality=auto";
                keyword="my favorite food";
                dates=today;
                who="me@sample.com";
                rating="4";
                launchActivity(Namy, location,keyword,dates,who,rating);

            }
        });

        rice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button

                Namy="Fried Rice";
                location="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlovCXf4bzpc5u6VwtLUIb_r1kyJ5hI8hB8fhGz8I0w_s1M7xD";
                keyword="my favorite food";
                dates=today;
                who="me@sample.com";
                rating="5";
                launchActivity(Namy, location,keyword,dates,who,rating);

            }
        });

        pizza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button

                Namy="Pizza";
                location="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpwngbGulwWFJDoXZdTF0x13Qu8D9QW-SZbDy_d-mnseGIgV6i";
                keyword="my favorite food";
                dates=today;
                who="me@sample.com";
                rating="5";
                launchActivity(Namy, location,keyword,dates,who,rating);

            }
        });

    }

    private void launchActivity(String food, String location, String keyword, String dates, String who, String rating) { //the launch worker function
        foodparcel myfood= new foodparcel(food, location, keyword, dates,who,rating);
        Intent intent = new Intent(this, parcelView.class);
        Bundle bb= new Bundle();
        //bb.putString("food", food);
        intent.putExtra("foodparcel", myfood);
        startActivity(intent);


    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            bmImage.getLayoutParams().height=100;
            bmImage.getLayoutParams().width=100;
        }
    }
}
