package edu.swin.student.carl.module1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* RelativeLayout rl = (RelativeLayout) findViewById(R.id.RL1);
        RelativeLayout rl =  new RelativeLayout(this);
        ImageView iv;
        RelativeLayout.LayoutParams params;

        iv = new ImageView(this);
        iv.setBackgroundColor(Color.YELLOW);
        params = new RelativeLayout.LayoutParams(30, 40);
        params.leftMargin = 50;
        params.topMargin = 60;
        rl.addView(iv, params);
        setContentView(rl);
        */
       setContentView(R.layout.activity_main);


    }
}
