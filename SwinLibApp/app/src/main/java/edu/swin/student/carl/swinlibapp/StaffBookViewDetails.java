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
import android.widget.Button;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StaffBookViewDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StaffBookViewDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaffBookViewDetails extends Fragment implements Spinner.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String bkID;
    Spinner spinner;
    List listA = new ArrayList();
    ArrayAdapter<String> adapter;

    TextView title;
    TextView author;
    TextView publisher;
    TextView datey;
    TextView cate;
    TextView isbenthirteen, lang;
    TextView avail;
    String[] place;


    int ctr=0;
    List<String> resultList = new ArrayList<String>();


    private OnFragmentInteractionListener mListener;

    public StaffBookViewDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaffBookViewDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static StaffBookViewDetails newInstance(String param1, String param2) {
        StaffBookViewDetails fragment = new StaffBookViewDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    public void getBookID(String id){
        this.bkID=id;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        listA.add("On Loan");
        listA.add("Available");
        listA.add("On Order");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View myv=inflater.inflate(R.layout.fragment_staff_book_view_details, container, false);

        title=myv.findViewById(R.id.titleName);
        author=myv.findViewById(R.id.authorName);
        publisher=myv.findViewById(R.id.pubName);
        datey=myv.findViewById(R.id.dateName);
        cate=myv.findViewById(R.id.cateName);
        lang=myv.findViewById(R.id.langlName);
        isbenthirteen=myv.findViewById(R.id.isbn13Name);
        avail=myv.findViewById(R.id.availName);


        resultList.clear();
        try {
            String csvLine;

            // FileInputStream fin = new FileInputStream(list);

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "bookdb.csv");

            FileInputStream fin = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fin);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // fin =myv.getContext().openFileInput("bookdb.csv");

            // InputStream fin =myv.getContext().getResources().openRawResource(R.raw.bookdb);
            //  reader = new BufferedReader(new InputStreamReader(fin));

            while ((csvLine = reader.readLine()) != null) {

                Log.d("kkkhhhh", csvLine);

                resultList.add(csvLine.toString());

                //Log.d("kkkhhhh", csvLine);

            }

            fin.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        place=resultList.get(Integer.parseInt(bkID)).split(",");

        for(int g=0; g < resultList.size(); g++){

            Log.d("counter!!!!!!!", resultList.get(g));
        }


        title.setText(place[0]);
        author.setText(place[1]);
        publisher.setText(place[2]);
        datey.setText(place[3]);
        cate.setText(place[4]);
        isbenthirteen.setText(place[5]);
        lang.setText(place[6]);

        avail.setText(place[7]);


        /**
        spinner = (Spinner) myv.findViewById(R.id.hview_spinner);
        adapter = new ArrayAdapter(myv.getContext(),
                android.R.layout.simple_spinner_item, listA);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

         **/
        return myv;
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback and this is REQUIRED CALLBACK, dont delete
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionStafViewDetails(uri);
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
        void onFragmentInteractionStafViewDetails(Uri uri);
    }
}
