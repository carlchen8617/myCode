package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import static android.content.Context.CONTEXT_IGNORE_SECURITY;
import static android.content.Context.MODE_APPEND;


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
    String townStr, latStr, lonStr, tzStr, addIn, addClaim;
    int addTimes;
    TextView againView;
    List resultList = new ArrayList();
    boolean goAhead;
    AlertDialog.Builder builder;


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

        addTimes=0;



        final View myv = inflater.inflate(R.layout.fragment_user_add, container, false);
        go = myv.findViewById(R.id.button);
        town = myv.findViewById(R.id.town);
        lat = myv.findViewById(R.id.lat);
        lon = myv.findViewById(R.id.lon);
        tz = myv.findViewById(R.id.timez);
        againView = myv.findViewById(R.id.againV);


       /**
        initial add

        String csvLine;
        File list = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/list.csv");
        Log.d("fffffffffffffff", list.toString());

        try {
            FileInputStream fin = new FileInputStream(list);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            FileOutputStream fout = myv.getContext().openFileOutput("list.csv",Context.MODE_APPEND);
            while ((csvLine = reader.readLine()) != null) {
                Log.d("kkkhhhh", csvLine);
                csvLine=csvLine+"\n";
                fout.write(csvLine.getBytes());

            }

            fin.close();
            fout.close();
        }catch (FileNotFoundException e){

        }catch (IOException e){

        }
        **/

        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Log.d("dssds", "addOK ");
                        goAhead=true;
                        addTimes++;
                        try{
                        addClaim = "You have add " + addTimes + " entry to the database, you can add again";
                        againView.setText(addClaim);
                        Log.d("kadd", getContext().getFilesDir().toString());

                        // FileOutputStream fout = new FileOutputStream(list);
                        FileOutputStream fout = myv.getContext().openFileOutput("list.csv",Context.MODE_APPEND);


                        fout.write(addIn.getBytes());

                        /**
                         Iterator<String> iterator = resultList.iterator();
                         while (iterator.hasNext()) {
                         Log.d("", iterator.next());
                         fout.write(iterator.next().getBytes());
                         }
                         ***/
                        fout.close();
                        goAhead=false;

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }



                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };




        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                townStr = town.getText().toString();
                latStr = lat.getText().toString();
                lonStr = lon.getText().toString();
                tzStr = tz.getText().toString();

                addIn = townStr + "," + latStr + "," + lonStr + "," + tzStr +"\n";

                Log.d("lol", addIn);
                goAhead=false;
                builder=new  AlertDialog.Builder(myv.getContext());

                builder.setMessage("Double check all information entered are correct, the App might crash if wrong information are entered, Proceed?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                /**
                if(goAhead){


                addTimes++;


                 Iterator<String> iterator = resultList.iterator();
                 while (iterator.hasNext()) {
                 Log.d("", iterator.next());
                 }


                try {

                    addClaim = "You have add " + addTimes + " entry to the database, you can add again";
                    againView.setText(addClaim);
                    Log.d("kadd", getContext().getFilesDir().toString());

                   // FileOutputStream fout = new FileOutputStream(list);
                    FileOutputStream fout = myv.getContext().openFileOutput("list.csv",Context.MODE_APPEND);


                    fout.write(addIn.getBytes());


                     Iterator<String> iterator = resultList.iterator();
                     while (iterator.hasNext()) {
                     Log.d("", iterator.next());
                     fout.write(iterator.next().getBytes());
                     }

                    fout.close();
                    goAhead=false;

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }

                }
                **/

                // Code here executes on main thread after user presses button
                if (mListener != null) {

                }
            }
        });
        // Inflate the layout for this fragment
        return myv;
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
