package edu.swin.student.carl.swinlibapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link staffViewStdDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link staffViewStdDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class staffViewStdDetails extends Fragment implements Spinner.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int stdID;
    TextView head;
    TextView name;
    List resultList = new ArrayList();
    HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
    List ct = new ArrayList();
    Spinner spinner;
    String result;
    int test;
    String o;
    int r, all=1,we=1;
    String booklisTitle="Book_name,List_of_author(s),Publisher,Published date,Category, ISBN,Language, Status";


    private OnFragmentInteractionListener mListener;

    public staffViewStdDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment staffViewStdDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static staffViewStdDetails newInstance(String param1, String param2) {
        staffViewStdDetails fragment = new staffViewStdDetails();
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

    public void getBookID(int id){
        this.stdID=id;

        Log.d("get ID", "getBookID: "+stdID);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myv= inflater.inflate(R.layout.fragment_staff_view_std_details, container, false);

        head=myv.findViewById(R.id.head);

        spinner = (Spinner) myv.findViewById(R.id.stddetails_spinner);


        head.setText("student " + stdID);

        result="";
       // new  processStudent().execute(stdID);

        resultList.clear();
      //  resultList.add(booklisTitle);
        ct.clear();
        all=-1;
        we=1;
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
                String p=".*"+stdID+"$";
                String pt="Book_name,List_of_author.*";
                if(csvLine.trim().matches(p) || csvLine.trim().matches(pt)){
                    resultList.add(csvLine);
                    ct.add(all);

                }

                all++;

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

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, resultList);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return  myv;
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
        void staffCHGBKAail(String item, int pos);
    }




    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {


        o = parent.getSelectedItem().toString();
        String kk=o;
        int selected =parent.getSelectedItemPosition();
        int bb=Integer.parseInt(ct.get(selected).toString());
        Log.d("BBBBBBBB", "onItemSelected: "+ selected);

        Log.d("GGGGGGGGGGG", "onItemSelected: "+  bb);

        r= bb;


        Log.d("okkkkkkkkkkkkkkkkkk", "ojjjjjjjjjjjjj" + o);

        if (o != null && selected != 0) {

            mListener.staffCHGBKAail(kk,r);

        }
        parent.setSelection(0); //This line took me 3 days to figure out, the fragement doesn't die, you have to kill the selection


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback and this is REQUIRED CALLBACK, dont delete
    }
}
