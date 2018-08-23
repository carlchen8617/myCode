package au.edu.swin.sdmd.staticclockjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.text.format.DateFormat;

public class MainActivity extends AppCompatActivity {


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    /** Initialise the User Interface and show the current time */
    private void initializeUI()
    {
        long sysTime = System.currentTimeMillis();
        String timeStr = DateFormat.format("kk:mm:ss", sysTime).toString();
        TextView ctv = findViewById(R.id.timeTextView);
        ctv.setText(timeStr);
    }
}
