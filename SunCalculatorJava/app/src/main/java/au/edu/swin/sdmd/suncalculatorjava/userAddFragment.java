package au.edu.swin.sdmd.suncalculatorjava;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link userAddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link userAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class userAddFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Button go;
    EditText town, lat, lon, tz;
    String townStr, latStr, lonStr, tzStr, addIn;
    List resultList = new ArrayList();


    public userAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static userAddFragment newInstance(String param1, String param2) {
        userAddFragment fragment = new userAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myv= inflater.inflate(R.layout.fragment_user_add, container, false);
               go= myv.findViewById(R.id.button);
               town= myv.findViewById(R.id.town);
               lat= myv.findViewById(R.id.lat);
               lon= myv.findViewById(R.id.lon);
               tz= myv.findViewById(R.id.timez);

                go.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        townStr=town.getText().toString();
                        latStr=lat.getText().toString();
                        lonStr=lon.getText().toString();
                        tzStr=tz.getText().toString();

                        addIn=townStr +"," + latStr +"," + lonStr +"," + tzStr;

                        Log.d("lol", addIn);


                        File list = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()+"/list.csv");
                        Log.d("fffffffffffffff", list.toString());

                        if(list.exists())

                        /**
                        Iterator<String> iterator = resultList.iterator();
                        while (iterator.hasNext()) {
                            Log.d("", iterator.next());
                        }
                        **/

                            try {

                                FileOutputStream fout = new FileOutputStream(list);

                                Iterator<String> iterator = resultList.iterator();
                                while (iterator.hasNext()) {
                                  fout.write(iterator.next().toString().getBytes());
                                }

                                fout.close();

                            } catch (FileNotFoundException e) {

                            } catch (IOException e) {

                            }


                        // Code here executes on main thread after user presses button
                        if (mListener != null) {

                        }
                    }
                });
        // Inflate the layout for this fragment
        return myv;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionUserAdd(uri);
        }
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
        void onFragmentInteractionUserAdd(Uri uri);
    }
}
