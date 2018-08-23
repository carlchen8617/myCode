package edu.swin.student.carl.foodparcels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class parcelView extends AppCompatActivity {

    EditText Namy, keywords, who, rating;
    TextView datey, url;
    ToggleButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_view);

        Namy=findViewById(R.id.Name);
        keywords=findViewById(R.id.keywords);
        who=findViewById(R.id.email);
        rating=findViewById(R.id.rate);
        datey=findViewById(R.id.date);
        url=findViewById(R.id.url);
        share=findViewById(R.id.toggleButton);


        foodparcel myfood= getIntent().getParcelableExtra("foodparcel");

        Namy.setText(myfood.Name);
        keywords.setText(myfood.keyword);
        who.setText(myfood.who);
        rating.setText(myfood.rating);
        url.setText(myfood.location);
        datey.setText(myfood.dates);

        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareIt();
            }
        });




    }

    private void shareIt(){

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}
