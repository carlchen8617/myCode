package au.edu.swin.sdmd.suncalculatorjava;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class mapActivity extends AppCompatActivity implements weatherFragment.OnFragmentInteractionListener {

    //private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;

    Toolbar myToolbar;
    MenuItem item, shareit;
    Spinner spinner;
    ArrayAdapter<String> adapter;

    String st = "I am at ";
    double lat, lon;
    String errorMessage = "";

    FrameLayout fl;
    LinearLayout.LayoutParams lp;
    weatherFragment frag;
    mapFragment mapfrag;
  //  MapView gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapfragment);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        fl = findViewById(R.id.fragment_container);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        fl.setLayoutParams(lp);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
          //mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

         // mapFragment.getMapAsync(this);

        //  GoogleMapOptions mapOptions = new GoogleMapOptions();
        //  mapOptions.useViewLifecycleInFragment(true);
        //  mapFragment.newInstance(mapOptions);
       // gm=(MapView)findViewById(R.id.mapView);
      //  gm.onCreate(savedInstanceState);


       // gm.getMapAsync(this);





    }

    public void onFragmentInteraction(Uri uri) {

        Log.d("report", "ok got here");
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.nnn, menu);


        frag = weatherFragment.newInstance("g", "v");
        mapfrag = mapFragment.newInstance("g", "v");

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

        listA.add("+");
        listA.add("local weather");


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

                if (sp == 0) {


                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }


                    getSupportFragmentManager().beginTransaction().add(fl.getId(), mapfrag, "two").commit();
                    Log.d("ggg", "add 2 ");

                } else if (sp == 1) {

                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }


                    getSupportFragmentManager().beginTransaction().add(fl.getId(), frag, "one").commit();
                    Log.d("ggg", "add 2 ");


                }

            }

            public void onNothingSelected(AdapterView<?> adapterView) {

                Log.d("ggg", "add 1 ");
            }

        });


        return true;


    }

    private void shareIt() {

        //get data
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                                //lon = location.getLongitude();
                                // lat= location.getLatitude();

                                List<Address> addresses = null;

                                try {
                                    addresses = geocoder.getFromLocation(
                                            location.getLatitude(),
                                            location.getLongitude(),
                                            // In this sample, get just a single address.
                                            1);

                                    Address address = addresses.get(0);
                                    ArrayList<String> addressFragments = new ArrayList<String>();

                                    for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                                        addressFragments.add(address.getAddressLine(i));
                                        st = st + " " + (address.getAddressLine(i).toString());
                                    }


                                    st = st + " " + addresses.toString();


                                } catch (IOException ioException) {
                                    // Catch network or other I/O problems.

                                    Log.e("", errorMessage, ioException);
                                }


                                // Logic to handle location object
                            }
                        }
                    });
        } catch (SecurityException se) {
            Log.d("TAG", "SE CAUGHT");
            se.printStackTrace();
        }


        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = st;
        //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, placeS);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */




    private void launchActivity() { //the launch worker function

        Intent intent2 = new Intent(this, weatherActivity.class);

        startActivity(intent2);


    }

}
