package edu.swin.student.carl.module2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textview;
    float value = 0;
    float dpi=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView)findViewById(R.id.TV);

        value = getResources().getDisplayMetrics().density;
        dpi= value*160;
        textview.setText("Screen Density = " + String.valueOf(value)+ " equal to " + String.valueOf(dpi) +"dpi" );

    }
}
