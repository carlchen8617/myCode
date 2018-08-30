package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentReport.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentReport extends Fragment {



    private OnFragmentInteractionListener mListener;
    RecyclerView suncyclerView;
    RecyclerView.LayoutManager mgr;
    RecyclerView.Adapter mAdapter;

    private List<sunRiseAndSet> sunList = new ArrayList<>();

    public FragmentReport() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public  List<sunRiseAndSet> newInstance(List<sunRiseAndSet> sunlist) {
        sunList=sunlist;
        return sunList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myv = inflater.inflate(R.layout.fragment_report, container, false);
        suncyclerView =myv.findViewById(R.id.sunrpt);
        suncyclerView.setHasFixedSize(true);

        mgr = new LinearLayoutManager(getContext());
        suncyclerView.setLayoutManager(mgr);
        suncyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new myAdapter(sunList);
        suncyclerView.setAdapter(mAdapter);

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
        void onFragmentInteraction4(Uri uri);
    }
}
