package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mainish.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mainish#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainish extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "0";
    private static final String ARG_PARAM3 = "0";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private double mParam2;
    private double mParam3;
    int year, month, day;
    Date srise, sset;
    DatePicker dp;
    TextView sunriseTV,sunsetTV,locationTV;

    private OnFragmentInteractionListener mListener;

    public mainish() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     *
     * @return A new instance of fragment mainish.
     */
    // TODO: Rename and change types and number of parameters
    public  static  mainish newInstance(String place, double lat, double lon) {
        mainish fragment = new mainish();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, place);
        args.putDouble(ARG_PARAM2,lat);
        args.putDouble(ARG_PARAM3, lon);

        Log.d("here at Main", "onCreate: lat "+ lat +"Lon" + lon);
        fragment.setArguments(args);

        return fragment;
    }
    public  int ok (String place, double lat, double lon) {
      mParam1=place;
      mParam2=lat;
      mParam3=lon;
      return 0;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam1=getArguments().getString(ARG_PARAM1);



        if (getArguments() != null) {

            Log.d("here at Main", "onCreate: lat "+ mParam1 +"Lon" + mParam3);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myv=inflater.inflate(R.layout.fragment_mainish, container, false);

        dp = myv.findViewById(R.id.datePicker);
        sunriseTV = myv.findViewById(R.id.sunriseTimeTV);
        sunsetTV = myv.findViewById(R.id.sunsetTimeTV);
        locationTV=myv.findViewById(R.id.locationTV);


        initializeUI();

        // Inflate the layout for this fragment
        return myv;
    }

    private void initializeUI() {

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year, month, day, dateChangeHandler); // setup initial values and reg. handler
        updateTime(year, month, day);
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
        TimeZone tz = TimeZone.getDefault();
        GeoLocation geolocation = new GeoLocation( mParam1,mParam2,mParam3 , tz);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        srise = ac.getSunrise();
        sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


        Log.d("SUNRISE Unformatted", srise + "");

        sunriseTV.setText(sdf.format(srise));
        sunsetTV.setText(sdf.format(sset));
        locationTV.setText(mParam1);
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener() {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth) {
            updateTime(year, monthOfYear, dayOfMonth);
        }
    };




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionManish(Uri uri);
    }
}

/**
 Use getView() or the View parameter from implementing the  onViewCreated method.
 It returns the root view for the fragment (the one returned by onCreateView() method).
 With this you can call findViewById().

 @Override
 public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
 ImageView imageView = (ImageView) getView().findViewById(R.id.foo);
 // or  (ImageView) view.findViewById(R.id.foo);
 As getView() works only after onCreateView(), you can't use it inside onCreate() or onCreateView() methods of the fragment .

 */
