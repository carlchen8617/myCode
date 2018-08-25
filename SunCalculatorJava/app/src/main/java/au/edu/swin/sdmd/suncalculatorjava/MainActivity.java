package au.edu.swin.sdmd.suncalculatorjava;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import android.support.v7.widget.Toolbar;
import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;

public class MainActivity extends AppCompatActivity implements places.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this ia needed for items to appear on the toolbar

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        initializeUI();
    }


    // this ia needed for items to appear on the toolbar

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nnn, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        FrameLayout fl=findViewById(R.id.fragment_container);
        switch (item.getItemId()) {
            case R.id.share:
                // User chose the "Settings" item, show the app settings UI...

                shareIt();
                return true;

            case R.id.search:
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                fl.setLayoutParams(lp);
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
              places firstFragment = places.newInstance("g", "v");

                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager()
                        .beginTransaction();

                ft.replace(fl.getId(), firstFragment);

                ft.commit();


                return true;
            case R.id.action_settings:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void onFragmentInteraction(int position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
    }

    private void shareIt(){

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void initializeUI() {
        DatePicker dp = findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
        updateTime(year, month, day);
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
        TimeZone tz = TimeZone.getDefault();
        GeoLocation geolocation = new GeoLocation("Sydney", -37.81
                , 144.96, tz);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Date srise = ac.getSunrise();
        Date sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        TextView sunriseTV = findViewById(R.id.sunriseTimeTV);
        TextView sunsetTV = findViewById(R.id.sunsetTimeTV);
        Log.d("SUNRISE Unformatted", srise+"");

        sunriseTV.setText(sdf.format(srise));
        sunsetTV.setText(sdf.format(sset));
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
        {
            updateTime(year, monthOfYear, dayOfMonth);
        }
    };


}
