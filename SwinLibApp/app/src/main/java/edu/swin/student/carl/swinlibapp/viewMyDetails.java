package edu.swin.student.carl.swinlibapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link viewMyDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link viewMyDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewMyDetails extends Fragment implements Spinner.OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String myID;
    String Name;
    TextView myidT;
    Spinner spinner;
    List resultList = new ArrayList();

    private OnFragmentInteractionListener mListener;

    public viewMyDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewMyDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static viewMyDetails newInstance(String param1, String param2) {
        viewMyDetails fragment = new viewMyDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void getMyID(String id, String Name){

        this.myID=id;
        this.Name= Name;


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
        // Inflate the layout for this fragment
        View myv=inflater.inflate(R.layout.fragment_view_my_details, container, false);
        myidT=myv.findViewById(R.id.myid);
        myidT.setText("Student "+ myID  + " borrowed books" );
        resultList.clear();
        try {
            String csvLine;
            // FileInputStream fin = new FileInputStream(list);

            Log.d("kkk", getContext().getFilesDir().toString());

            // InputStream fin =myv.getContext().getResources().openRawResource(R.raw.bookdb);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "bookdb.csv");

            FileInputStream fin = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fin);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((csvLine = reader.readLine()) != null) {
                Log.d("kkkhhhh", csvLine);
                String p=".*"+myID+"$";
                if(csvLine.trim().matches(p)){
                    resultList.add(csvLine);
                }


            }

            fin.close();
            /**
             fin =myv.getContext().openFileInput("bookdb.csv");
             //InputStream fin =myv.getContext().getResources().openRawResource(R.raw.bookdb);
             reader = new BufferedReader(new InputStreamReader(fin));
             while ((csvLine = reader.readLine()) != null) {
             Log.d("kkkhhhh", csvLine);
             resultList.add(csvLine);

             }

             fin.close();
             **/

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        spinner = (Spinner) myv.findViewById(R.id.mydetails_spinner);
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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

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
        void vieMydetails(Uri uri);
    }
}
