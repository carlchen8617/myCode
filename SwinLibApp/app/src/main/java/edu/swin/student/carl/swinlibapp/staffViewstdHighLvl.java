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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link staffViewstdHighLvl.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link staffViewstdHighLvl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class staffViewstdHighLvl extends Fragment implements Spinner.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List resultList = new ArrayList();
    Spinner spinner;
    String o;
    int r;

    private OnFragmentInteractionListener mListener;

    public staffViewstdHighLvl() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment staffViewstdHighLvl.
     */
    // TODO: Rename and change types and number of parameters
    public static staffViewstdHighLvl newInstance(String param1, String param2) {
        staffViewstdHighLvl fragment = new staffViewstdHighLvl();
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

        resultList.clear();
        // Inflate the layout for this fragment

        View myv= inflater.inflate(R.layout.fragment_staff_viewstd_high_lvl, container, false);

        try {
            String csvLine;
            // FileInputStream fin = new FileInputStream(list);

            //read raw input
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "student.csv");

            FileInputStream fin = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fin);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            while ((csvLine = reader.readLine()) != null) {
                Log.d("kkkhhhh", csvLine);
                resultList.add(csvLine);

            }

            fin.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        spinner = (Spinner) myv.findViewById(R.id.studentList_spinner);
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

        o = parent.getSelectedItem().toString();
        int selected =parent.getSelectedItemPosition();
        r=selected;

        parent.setSelection(0); //This line took me 3 days to figure out, the fragement doesn't die, you have to kill the selection


        Log.d("ok", "oj" + o);

        if (o != null && selected != 0) {

            mListener.ViewStudentDetails(o,r);

        }


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback and this is REQUIRED CALLBACK, dont delete
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {

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
        void ViewStudentDetails(String uri,int row);
    }
}
