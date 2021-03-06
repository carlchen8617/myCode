package edu.swin.student.carl.swinlibapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link staffAddUser.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link staffAddUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class staffAddUser extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText nobedit;
    EditText nameedit;
    EditText passedit;
    Button adduser;

    private OnFragmentInteractionListener mListener;

    public staffAddUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment staffAddUser.
     */
    // TODO: Rename and change types and number of parameters
    public static staffAddUser newInstance(String param1, String param2) {
        staffAddUser fragment = new staffAddUser();
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
        // Inflate the layout for this fragment
        View myv = inflater.inflate(R.layout.fragment_staff_add_user, container, false);

        nobedit=myv.findViewById(R.id.titleName);
        nameedit=myv.findViewById(R.id.nameedit);
        passedit =myv.findViewById(R.id.passi);

        adduser= myv.findViewById(R.id.addUUU);

        adduser.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View myv) {
                // TODO Auto-generated method stub
                // ***Do what you want with the click here***

                String adduserStr= "\n"+ nobedit.getText()+"," + nameedit.getText() +"," + passedit.getText();


                try {

                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "student.csv");
                    FileOutputStream fout = new FileOutputStream(file,true);

                    fout.write(adduserStr.getBytes());
                    fout.close();


                    //OutputStream fout =myv.getContext().openFileOutput("bookdb.csv",Context.MODE_PRIVATE);

                    // InputStream fin =myv.getContext().getResources().openRawResource(R.raw.bookdb);



                    fout.close();

                } catch (FileNotFoundException e) {

                } catch (IOException e) {

                }


                Toast.makeText(getContext(), "Ok Changed!", Toast.LENGTH_SHORT).show();


            }
        });


        return myv;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.addUser(uri);
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
        void addUser(Uri uri);
    }
}
