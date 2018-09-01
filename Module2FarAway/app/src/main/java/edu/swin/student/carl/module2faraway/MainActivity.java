package edu.swin.student.carl.module2faraway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button myButton,  myButton_temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton=findViewById(R.id.distance);
        myButton_temp=findViewById(R.id.main_temp_click);



        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the distance conveter
                // Code here executes on main thread after user presses button

                launchActivity(1);

            }


    });

        myButton_temp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the Temperature  conveter
                // Code here executes on main thread after user presses button

                launchActivity(2);

            }


        });

    }


    private void launchActivity(int y) { //the launch worker function

        if (y == 1) {
            Intent intent = new Intent(this, Distance__Activity.class);
            startActivity(intent);
        } else if (y == 2) {

            Intent intent = new Intent(this, Temprature_Activity.class);
            startActivity(intent);
        }

    }

}