package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.support.v7.widget.Toolbar;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;


public class MainActivity extends AppCompatActivity
        implements
        places.OnFragmentInteractionListener,
        calSelector.OnFragmentInteractionListener,
        mainish.OnFragmentInteractionListener,
        tyFragment.OnFragmentInteractionListener

{


    String placeS = "Melbourne";
    double Latitude = -37.81;
    double Longitude = 144.96;
    String Country ="AU";
    int year, month, day;
    Date srise, sset;

    Toolbar myToolbar;
    Spinner spinner;
    places firstFragment;
    calSelector thirdFrag;
    mapActivity mp;
    mainish mainFrag;
    tyFragment ty;
    FrameLayout fl;
    LinearLayout.LayoutParams lp;
    MenuItem item, shareit;
    ArrayAdapter<String> adapter;

    private List<sunRiseAndSet> sunListA = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this ia needed for items to appear on the toolbar

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        initializeUI();
    }

    // this ia needed for items to appear on the toolbar

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nnn, menu);

        //lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //fl.setLayoutParams(lp);


        fl = findViewById(R.id.fragment_container);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        fl.setLayoutParams(lp);
        firstFragment = places.newInstance("g", "v");
        //  sunTimeRangeFragment sndFragment = sunTimeRangeFragment.newInstance(1);
        thirdFrag = calSelector.newInstance("g", "n");
        //mainish
        mainFrag = mainish.newInstance("Sydney,AU", -33.86, 151.20);
        mainFrag.ok("Sydney", -33.86, 151.20);

        // share
        shareit = myToolbar.getMenu().findItem(R.id.share);

        shareit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // open SMS sending activity
                shareIt();
                return true;
            }


        });


        item = myToolbar.getMenu().findItem(R.id.menuSort);
        spinner = (Spinner) item.getActionView();
        List listA = new ArrayList();

        listA.add("Select");
        listA.add("Select City");
        listA.add("Monthly Report");
        listA.add("My Location");


        adapter = new ArrayAdapter<String>(this, R.layout.menu_spinner, listA) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(getResources().getColor(R.color.colorWhite));
                tv.setBackgroundColor(Color.parseColor("RED"));


               /*
                if(position%2 == 1) {
                    // Set the item text color
                    tv.setTextColor(Color.parseColor("#FF7C7967"));
                    // Set the item background color
                    tv.setBackgroundColor(Color.parseColor("#FFC3C0AA"));
                }
                else {
                    // Set the alternate item text color
                    tv.setTextColor(Color.parseColor("#FF657A86"));
                    // Set the alternate item background color
                    tv.setBackgroundColor(Color.parseColor("#FFB5DCE8"));
                }

                */
                return view;
            }
        };


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // set spinner listener
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int sp = spinner.getSelectedItemPosition();

                if (sp == 1) {

                    // User chose the "Favorite" action, mark the current item
                    // as a favorite...


                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    getSupportFragmentManager().beginTransaction().add(fl.getId(), firstFragment, "one").commit();
                    Log.d("ggg", "add 1 ");


                } else if (sp == 2) {

                    // User chose the "Favorite" action, mark the current item
                    // as a favorite...

                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    getSupportFragmentManager().beginTransaction().add(fl.getId(), thirdFrag, "three").commit();
                    Log.d("ggg", "add 1 ");


                } else if (sp == 0) {

                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    getSupportFragmentManager().beginTransaction().add(fl.getId(), mainFrag, "four").commit();
                    Log.d("ggg", "add 1 ");


                } else if (sp == 3) {
                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    launchActivity();

                }


                Log.d("ok", spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }

                getSupportFragmentManager().beginTransaction().add(fl.getId(), mainFrag, "four").commit();
                Log.d("ggg", "add 1 ");
            }
        });


        return true;
    }


    public void onFragmentInteraction4(Uri uri) {

        Log.d("report", "ok got here");
    }

    public void onFragmentInteraction5(Uri uri) {

        Log.d("map", "ok got here");
    }


    public void onFragmentInteraction3(int rptYear, int rptMonth, String MonthName, String position) {

        sunListA.clear();

        Log.d("report", "onFragmentInteraction3: " + rptYear + " " + rptMonth);

        String[] placerpt = position.split(",");// process string

        Calendar calendar = Calendar.getInstance();
        int year = rptYear;
        int month = rptMonth;
        int date = 1;
        // We have a new date of 2017-02-01
        calendar.set(year, month, date);

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Log.d("month days", "onFragmentInteraction3 Month: " + maxDay);

        TimeZone tz = TimeZone.getDefault();
        GeoLocation geolocation = new GeoLocation(placerpt[0], Float.parseFloat(placerpt[1]), Float.parseFloat(placerpt[2]), tz);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        sunRiseAndSet test;

        test = new sunRiseAndSet(placerpt[0], String.valueOf(year), MonthName);
        sunListA.add(test);

        for (int i = 1; i <= maxDay; i++) {

            ac.getCalendar().set(year, month, i);
            srise = ac.getSunrise();
            sset = ac.getSunset();
            int ii = i;

            Log.d(rptYear + "melbourne", "Sunrise: " + srise + ", Sunset: " + sset);

            test = new sunRiseAndSet(String.valueOf(ii), "SunRise: " + sdf.format(srise), "SunSet: " + sdf.format(sset));
            sunListA.add(test);

        }


        FragmentReport sndFragment = new FragmentReport();
        sndFragment.newInstance(sunListA);


        FrameLayout fl = findViewById(R.id.fragment_container);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        fl.setLayoutParams(lp);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
         }

            getSupportFragmentManager().beginTransaction().add(fl.getId(), sndFragment, "two").commit();
            Log.d("ggg", "add 2 ");


    }

    public void onFragmentInteraction(String position) {
        //This method takes input from places callback and process the string.

        Log.d("yes", position);

        //program the calculatoion

        if (!(position.isEmpty())) {


            String[] place = position.split(",");// process string
            Log.d("splited", place[0] + " " + place[1] + " " + place[2] + " " + place[3]);

            // update title


            placeS = place[0];
            Latitude = Double.parseDouble(place[1]);
            Longitude = Double.parseDouble(place[2]);
            Country=place[3];

            ty = tyFragment.newInstance(placeS, Latitude, Longitude);
            ty.ok(placeS, Latitude, Longitude,Country);

            /**
             * Only if message is not empty, minimize the fragment container view,
             * which holds all 3 fragments
             *
             **/

            FrameLayout fl = findViewById(R.id.fragment_container);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            fl.setLayoutParams(lp);
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

                getSupportFragmentManager().beginTransaction().add(fl.getId(), ty, "five").commit();
                Log.d("ggg", "add 1 ");



        }

    }

    private void initializeUI() {
        DatePicker dp = findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year, month, day, dateChangeHandler); // setup initial values and reg. handler
        updateTime(year, month, day);
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
        TimeZone tz = TimeZone.getDefault();
        GeoLocation geolocation = new GeoLocation("Melbourne", -37.50, 145.01, tz);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        srise = ac.getSunrise();
        sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        TextView sunriseTV = findViewById(R.id.sunriseTimeTV);
        TextView sunsetTV = findViewById(R.id.sunsetTimeTV);
        Log.d("SUNRISE Unformatted", srise + "");

        sunriseTV.setText(sdf.format(srise));
        sunsetTV.setText(sdf.format(sset));
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener() {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth) {
            updateTime(year, monthOfYear, dayOfMonth);
        }
    };


    private void shareIt() {

        //get data


        String st = "At " + year + "-" + month + "-" + day + " Sunrise at " + sdf.format(srise) + " Sunset at " + sdf.format(sset);

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = st;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, placeS);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    private void launchActivity() { //the launch worker function

        Intent intent2 = new Intent(this, mapActivity.class);

        startActivity(intent2);


    }

}
