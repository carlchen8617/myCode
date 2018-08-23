package edu.swin.student.carl.imageshower;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    ImageView rice, fish,pizza, indian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     pizza=findViewById(R.id.pizza);
     fish=findViewById(R.id.FnC);
     rice=findViewById(R.id.rice);
     indian=findViewById(R.id.vindaloo);

        pizza.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button

                launchActivity("pizza");

            }


        });

        fish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button

                launchActivity("fish");

            }


        });

        rice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button

                launchActivity("rice");

            }


        });

        indian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This button listener launches the food selector
                // Code here executes on main thread after user presses button

                launchActivity("vindaloo");

            }


        });


    }

    private void launchActivity(String food) { //the launch worker function


            Intent intent = new Intent(this, display.class);
            Bundle bb= new Bundle();
            bb.putString("food", food);
            intent.putExtras(bb);
            startActivity(intent);


    }
}
