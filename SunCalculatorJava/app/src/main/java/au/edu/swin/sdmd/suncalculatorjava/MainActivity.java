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
import au.edu.swin.sdmd.suncalculatorjava.dummy.DummyContent;


public class MainActivity extends AppCompatActivity implements places.OnFragmentInteractionListener,sunTimeRangeFragment.OnListFragmentInteractionListener {


    String placeS="Melbourne";
    double Latitude=-37.81;
    double Longitude=144.96;
    int year, month, day;
    Date srise, sset;
    int subscreensOnTheStack;

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

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        fl.setLayoutParams(lp);

        places firstFragment = places.newInstance("g", "v");
        sunTimeRangeFragment sndFragment = sunTimeRangeFragment.newInstance(2);





        switch (item.getItemId()) {


            case R.id.share:
                // User chose the "Settings" item, show the app settings UI...

                shareIt();
                 break;

            case R.id.info:

                // User chose the "Favorite" action, mark the current item
                // as a favorite...


                if( getSupportFragmentManager().findFragmentByTag("one") != null){
                    Log.d("ggg", "find 1 ");
                    getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("one")).commit();
                }
                else{

                    getSupportFragmentManager().beginTransaction().add(fl.getId(),firstFragment,"one").commit();
                    Log.d("ggg", "add 1 ");
                }

                if( getSupportFragmentManager().findFragmentByTag("two") != null){
                    //if the other fragment is visible, hide it.
                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("two")).commit();
                }
                break;


            case R.id.search:


                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                if( getSupportFragmentManager().findFragmentByTag("two") != null){
                    Log.d("ggg", "find 2 ");
                    getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("sndFragment")).commit();
                }
                else{

                    getSupportFragmentManager().beginTransaction().add(fl.getId(), sndFragment, "two")
                   .commit();
                    Log.d("ggg", "add 2 ");
                }

                if( getSupportFragmentManager().findFragmentByTag("one") != null){
                    //if the other fragment is visible, hide it.
                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("one")).commit();
                }
                break;



            case R.id.action_settings:
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
               return super.onOptionsItemSelected(item);


        }

        return true;
    }

    public void onFragmentInteraction(String position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        Log.d("yes", position);

        if(!(position.isEmpty())){ // Only if message is not empty

            FrameLayout fl=findViewById(R.id.fragment_container);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, 0);
            fl.setLayoutParams(lp);



        }

        //program the calculatoion

        if(!(position.isEmpty())) {


            String[] place = position.split(",");// process string
            Log.d("splited", place[0] + " " + place[1] + " " + place[2]);

            // update title

            TextView lTV= findViewById(R.id.locationTV);

            placeS=place[0];
            Latitude=Double.parseDouble(place[1]);
            Longitude=Double.parseDouble(place[2]);
            lTV.setText(placeS +",AU");

            initializeUI();
        }

    }


    public void onListFragmentInteraction2( DummyContent.DummyItem position){

        Log.d("mygod", "what?");


    }
    private void shareIt(){

        //get data

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String st="At " + year+"-"+month +"-"+day + " Sunrise at " + sdf.format(srise) + " Sunset at " + sdf.format(sset);

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = st;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, placeS);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void initializeUI() {
        DatePicker dp = findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
        updateTime(year, month, day);
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
        TimeZone tz = TimeZone.getDefault();
        GeoLocation geolocation = new GeoLocation(placeS, Latitude, Longitude, tz);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        srise = ac.getSunrise();
        sset = ac.getSunset();

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
