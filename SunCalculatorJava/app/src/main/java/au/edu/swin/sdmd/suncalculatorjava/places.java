package au.edu.swin.sdmd.suncalculatorjava;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link places.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link places#newInstance} factory method to
 * create an instance of this fragment.
 */
public class places extends Fragment implements Spinner.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int sp;
    Spinner spinner;
    String o;
    List resultList = new ArrayList();


    private OnFragmentInteractionListener mListener;

    public places() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment places.
     */
    // TODO: Rename and change types and number of parameters
    public static places newInstance(String param1, String param2) {


        places fragment = new places();
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

    /*
      Thia section use omCreateView method to prepare the spinner code, the
      spinner codes here are taken fromm the Android official documentation
      boil plate code but in createFromResourc function you cant use "this" keyword
      as in the documentation but have to use view.getcontect() API call.

     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        resultList.clear();

        // Inflate the layout for this fragment
        View myv = inflater.inflate(R.layout.fragment_places, container, false);



        File list = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()+"/list.csv");
        Log.d("fffffffffffffff", list.toString());



            try {
                String csvLine;
               // FileInputStream fin = new FileInputStream(list);

                Log.d("kkk", getContext().getFilesDir().toString());

               // FileInputStream fin =myv.getContext().openFileInput("list.csv");
                InputStream fin =myv.getContext().getResources().openRawResource(R.raw.list);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                while ((csvLine = reader.readLine()) != null) {
                    Log.d("kkkhhhh", csvLine);
                    resultList.add(csvLine);

                }

                fin.close();

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }




        spinner = (Spinner) myv.findViewById(R.id.places_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(myv.getContext(),
                 android.R.layout.simple_spinner_item, resultList);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        return myv;

    }

    /*
      This function provides interface that to listen to the spinner selection events

     */

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)


        o = parent.getSelectedItem().toString();
        int selected =parent.getSelectedItemPosition();

        parent.setSelection(0); //This line took me 3 days to figure out, the fragement doesn't die, you have to kill the selection


        Log.d("ok", "oj" + o);

        if (o != null && selected != 0) { //Only send call back if the selection is not empty
            mListener.onFragmentInteraction(o);

        }

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback and this is REQUIRED CALLBACK, dont delete
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
        spinner.setSelection(4);
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
        void onFragmentInteraction(String uri);
    }
}
