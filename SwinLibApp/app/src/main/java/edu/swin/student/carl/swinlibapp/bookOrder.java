package edu.swin.student.carl.swinlibapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bookOrder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bookOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bookOrder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String book_id="9781785766480";
    TextView title;
    TextView author;
    TextView publisher;
    TextView datey;
    TextView isbnten;
    TextView rating;
    TextView isbnthirteen;
    TextView avail;
    EditText isbn;
    Button searchb;
    String[] place;
    JSONObject root;
    JSONArray book;
    boolean tester;


    int ctr=0;
    List<String> resultList = new ArrayList<String>();

    private String QueryURL = "";
    private final String googleBase10 = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private final String googleBase13 = "https://www.googleapis.com/books/v1/volumes?q=isbn13:";

    private final String myAPIkey = "AIzaSyAGTo-Im7lbyjJrKsDj09u94r2WilW0wZk";
    private final String isbnType = "^978.*";

    String endResults;
    StringBuffer response;
    List<String> procList;
    String Finalinfo = "";


    private OnFragmentInteractionListener mListener;

    public bookOrder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bookDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static bookOrder newInstance(String param1, String param2) {
        bookOrder fragment = new bookOrder();
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        View myv=inflater.inflate(R.layout.fragment_book_order, container, false);
        title=myv.findViewById(R.id.titlemem);
        author=myv.findViewById(R.id.authorName);
        publisher=myv.findViewById(R.id.pubName);
        datey=myv.findViewById(R.id.dateName);
        isbnten=myv.findViewById(R.id.isbn10Name);
        isbnthirteen=myv.findViewById(R.id.isbn13Name);
        avail=myv.findViewById(R.id.availName);
        isbn=myv.findViewById(R.id.nnn);
        searchb=myv.findViewById(R.id.search);
        rating = myv.findViewById(R.id.rating);

        searchb.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

              book_id = isbn.getText().toString();
              getBookDetails( book_id);

            }
        });

        return myv;
    }

    private boolean getbookinfo(String queryString){

        String qq=queryString;

        try {

            // connect to google API
            URI uri = new URI(qq);
            String mm = uri.toString(); // format URI

            System.out.println(mm);

            URL obj = new URL(mm); // make URL

            // connect and send GET
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            // read reply

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine + "\n");
                //procList.add(inputLine);

                // response.append("\n");

            }

            in.close();

            root = new JSONObject(response.toString());

            String checks=root.getString("totalItems");

            System.out.println("ppppppppppppp"+checks);
            Log.d("ppppp", checks );

            if(Integer.parseInt(checks)==0){
                tester= false;
            }
            else{
                tester= true;
            }
            //book = root.getJSONArray("items");

        } catch (IOException | URISyntaxException | JSONException e ) {

        } finally {

        }


         return tester;

    }


    private void getBookDetails(String book_id) {


        if (book_id.matches(isbnType)) {

            QueryURL = googleBase13 + book_id + "&key=" + myAPIkey;
            getbookinfo(QueryURL);

            if (!getbookinfo(QueryURL)) {
                QueryURL = googleBase10 + book_id + "&key=" + myAPIkey;
                getbookinfo(QueryURL);
            }


        } else {

            QueryURL = googleBase10 + book_id + "&key=" + myAPIkey;
            getbookinfo(QueryURL);

        }

        System.out.println("get here 1");

        try {

            // connect to google API


            book = root.getJSONArray("items");
            isbnten.setText(root.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getJSONArray("categories").toString());
            isbnthirteen.setText(root.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getString("language"));
            rating.setText(root.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getString("averageRating"));
            title.setText(root.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getString("title"));
            publisher.setText(root.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getString("publisher"));
            datey.setText(root.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getString("publishedDate"));

            author.setText(root.getJSONArray("items").getJSONObject(0)
                    .getJSONObject("volumeInfo").getJSONArray("authors").toString());


            // print result

        } catch (JSONException e) {

        } finally {

        }
        String[] StringArr = response.toString().split("\n");


    }


    private String processJason(String responseStr) {
        

            return "The university policy is books with average rating  of 3.5 "
                    + "or above can be purchased, the book cannot "
                    + "be purchased as its rating is 0.0";

    }

// class end


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionOrder(uri);
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
        void onFragmentInteractionOrder(Uri uri);
    }
}
