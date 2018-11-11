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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link staffCHGbkStatus.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link staffCHGbkStatus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class staffCHGbkStatus extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String bkID;
    int stuID;
    String booklisTitle="Book_name,List_of_author(s),Publisher,Published date,Category, ISBN,Language, Status";
    String writeback;
    TextView title;
    TextView author;
    TextView publisher;
    TextView datey;
    TextView cate;
    TextView isbenthirteen, lang;
    TextView avail;
    String[] place, please;
    Button borrow;
    Spinner spinner;

    int ctr=0, set=0;
    List<String> resultList = new ArrayList<String>();
    List<String> chgS = new ArrayList<String>();

    private OnFragmentInteractionListener mListener;

    public staffCHGbkStatus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment staffCHGbkStatus.
     */
    // TODO: Rename and change types and number of parameters
    public static staffCHGbkStatus newInstance(String param1, String param2) {
        staffCHGbkStatus fragment = new staffCHGbkStatus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void getBookID(String id, int stuid){
        this.bkID=id;
        this.stuID=stuid;

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
        View myv=inflater.inflate(R.layout.fragment_staff_chgbk_status, container, false);

        title=myv.findViewById(R.id.titleName);
        author=myv.findViewById(R.id.authorName);
        publisher=myv.findViewById(R.id.pubName);
        datey=myv.findViewById(R.id.dateName);
        cate=myv.findViewById(R.id.cateName);
        lang=myv.findViewById(R.id.langlName);
        isbenthirteen=myv.findViewById(R.id.isbn13Name);
        avail=myv.findViewById(R.id.availName);
        borrow= myv.findViewById(R.id.borrow);
        spinner=myv.findViewById(R.id.chg_spinner);

        please = bkID.split(",");// process string

        Log.d("mmmmmmmmmmm", "onCreateView: "+ please[5]);

        resultList.clear();

        ctr=0;
        set=0;
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

                String  p = ".*"+ please[5]+".*";

                if(csvLine.matches(p)){

                   set=ctr;
                }


                resultList.add(csvLine.toString());

                ctr++;

                //Log.d("kkkhhhh", csvLine);

            }

            fin.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        place=resultList.get(set).split(",");

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
        chgS.clear();
        chgS.add(place[7]);
        chgS.add("available");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(myv.getContext(),
                android.R.layout.simple_spinner_item, chgS);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);




        borrow.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View myv) {
                // TODO Auto-generated method stub
                // ***Do what you want with the click here***

                place[7]=String.valueOf(spinner.getSelectedItem());
                writeback=place[0] + "," + place[1] + "," + place[2] + "," + place[3]
                        + "," + place[4] + "," + place[5] + "," + place[6] + "," + place[7];
                resultList.set(set,writeback);


                try {

                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "bookdb.csv");
                    FileOutputStream fout = new FileOutputStream(file,false);

                    fout.write(booklisTitle.getBytes());
                    fout.close();

                    fout = new FileOutputStream(file,true);

                    //OutputStream fout =myv.getContext().openFileOutput("bookdb.csv",Context.MODE_PRIVATE);

                    // InputStream fin =myv.getContext().getResources().openRawResource(R.raw.bookdb);
                    for(int g=1; g < resultList.size(); g++){

                        fout.write(("\n"+resultList.get(g).toString()).getBytes());
                    }



                    fout.close();

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }


                Toast.makeText(getContext(), "Ok Changed!", Toast.LENGTH_SHORT).show();


            }
        });

        return myv;
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
        void onFragmentInteractionEnd(Uri uri);
    }
}
