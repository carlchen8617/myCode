package au.edu.swin.sdmd.suncalculatorjava;

import android.app.Activity;
import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link weatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link weatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class weatherFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView ww;
    String json;

    JSONObject data;
    String loc;
    StringBuffer jjson;

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
    FusedLocationProviderClient mFusedLocationClient;
    double lat, longt;

    View myv;

    public weatherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment weatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static weatherFragment newInstance(String param1, String param2) {
        weatherFragment fragment = new weatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         myv = inflater.inflate(R.layout.fragment_weather, container, false);

        cityField = (TextView) myv.findViewById(R.id.city_field);
        updatedField = (TextView) myv.findViewById(R.id.updated_field);
        detailsField = (TextView) myv.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) myv.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView) myv.findViewById(R.id.weather_icon);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getContext());

        try {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener((Activity) this.getContext(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
                               lat= Math.round(location.getLatitude()*100.0)/100.0;
                               longt=Math.round(location.getLongitude()*100.0)/100.0;
                                Log.d("", "onSuccess: "+ lat + " " +longt);
                            }
                        }
                    });
        } catch (SecurityException se) {
            Log.d("TAG", "SE CAUGHT");
            se.printStackTrace();
        }


        try {

            String urlStr="http://api.openweathermap.org/data/2.5/weather?lat="+ lat +"&lon=" + longt+"&APPID=812cb447094b7b0eb95c777dc88ff717&units=metric";
            URL url=new URL(urlStr);

            // URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat=-33.86&lon=151.20&APPID=812cb447094b7b0eb95c777dc88ff717&units=metric");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            jjson = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine()) != null)
                jjson.append(tmp).append("\n");
            reader.close();

            /**  for (String line; (line = reader.readLine()) != null; ) {
             // System.out.println(line);
             json = json + line;
             Log.d("", "onCreate: "+line);
             }
             */

        } catch (MalformedURLException e) {
            Log.d("URL", "URL something is wrong");
        } catch (IOException e) {
            Log.d("IO", "IO is wrong");
        }

        try {
            data = new JSONObject(jjson.toString());
            cityField.setText(data.getString("name").toUpperCase(Locale.US) +
                    ", " +
                    data.getJSONObject("sys").getString("country"));
            JSONObject details = data.getJSONArray("weather").getJSONObject(0);
            JSONObject main = data.getJSONObject("main");
            detailsField.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

            currentTemperatureField.setText(
                    String.format("%.2f", main.getDouble("temp")) + " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(data.getLong("dt") * 1000));
            updatedField.setText("Last update: " + updatedOn);


            Log.d("json", data.toString());

        } catch (JSONException e) {
            Log.d("IO", "IO is wrong");
        }


        //ww = myv.findViewById(R.id.weather);
        //ww.setText(loc);
        // ww.setText(json);
        // Inflate the layout for this fragment
        return myv;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


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
        void onFragmentInteraction(Uri uri);
    }
}
