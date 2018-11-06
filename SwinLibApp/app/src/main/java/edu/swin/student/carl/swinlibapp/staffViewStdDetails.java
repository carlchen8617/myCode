package edu.swin.student.carl.swinlibapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link staffViewStdDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link staffViewStdDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class staffViewStdDetails extends Fragment {
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
    String result;
    int test;

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
        name=myv.findViewById(R.id.StudentName);
        head.setText("student " + stdID);

        result="";
        new  processStudent().execute(stdID);




        return  myv;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.staffViewStdDetailsListener(uri);
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
        void staffViewStdDetailsListener(Uri uri);
    }

    //Do processing in background

    private class processStudent extends AsyncTask<Integer, Void, String> {
        protected String doInBackground(Integer... integers) {

            int stid = integers[0];

            Log.d("kkkk", "doInBackground: "+stid);

            String[] pr;

            try {
                String csvLine;

                //read raw input

                InputStream fin =getContext().getResources().openRawResource(R.raw.student);

                BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                while ((csvLine = reader.readLine()) != null) {

                    pr=csvLine.split(",");

                    if(Integer.parseInt(pr[0])==stid){

                        result=csvLine;
                        Log.d("kkkhhhh", csvLine);

                        test=1;

                        return result;

                    }



                }

                fin.close();


                Log.d("kkk", getContext().getFilesDir().toString());

                //read from internal file

                if(test==0){

                    fin =getContext().openFileInput("student.csv");

                    reader = new BufferedReader(new InputStreamReader(fin));
                    while ((csvLine = reader.readLine()) != null) {
                        Log.d("kkkhhhh", csvLine);
                        pr=csvLine.split(",");
                        if(Integer.parseInt(pr[0])==stid){

                            result=csvLine;
                            Log.d("kkkhhhh", csvLine);

                            test=1;

                            return result;

                        }

                    }

                    fin.close();

                }



            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }




           return result;


        }



        protected void onPostExecute(String result) {

            String[] stu=result.split(",");
            Log.d("see me?", "onCreateView: "+ stu[0]);
             name.setText(stu[1]);

        }
    }
}
