package edu.swin.student.carl.swinlibapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

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
    String booklisTitle="Book_name,List_of_author(s),Publisher,Published date,Category, ISBN,Language, Status";
    TextView title;
    TextView author;
    TextView publisher;
    TextView datey;
    TextView isbnten;
    TextView rating;
    TextView isbnthirteen;
    TextView avail;
    TextView noorder;
    EditText isbn;
    Button searchb,order;
    String addTodatbase;
    JSONObject root;
    OutputStream fout;
    List<String> resultList = new ArrayList<String>();

    boolean tester;



    private String QueryURL = "";
    private final String googleBase10 = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private final String googleBase13 = "https://www.googleapis.com/books/v1/volumes?q=isbn13:";

    private final String myAPIkey = "AIzaSyAGTo-Im7lbyjJrKsDj09u94r2WilW0wZk";
    private final String isbnType = "^978.*";

    String endResults;
    StringBuffer response  = new StringBuffer();
    List<String> procList;
    String Finalinfo = "";
    String myID;


    private OnFragmentInteractionListener mListener;

    public bookOrder() {
        // Required empty public constructor
    }

    public void getMyID(String id){

        this.myID=id;



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

        final View myv=inflater.inflate(R.layout.fragment_book_order, container, false);
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
        order = myv.findViewById(R.id.button);
        noorder = myv.findViewById(R.id.noorder);

        try {
            String csvLine;
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

        searchb.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                response.delete(0, response.length());
                isbnten.setText(null);
                isbnthirteen.setText(null);
                rating.setText(null);
                title.setText(null);
                publisher.setText(null);
                datey.setText(null);

                author.setText(null);
                order.setVisibility(View.GONE);
                noorder.setVisibility(View.INVISIBLE);
                rating.setText(" ");


              book_id = isbn.getText().toString();
              getBookDetails( book_id);

            }
        });


        order.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick (View v){
                System.out.println("save!");

                new recordy().execute(root);
             order.setText("Done!");

            }
        });


        return myv;
    }




    private void getBookDetails(String book_id) {


        if (book_id.matches(isbnType)) {

            QueryURL = googleBase13 + book_id + "&key=" + myAPIkey;
            response.delete(0, response.length());
            new asyncc().execute(QueryURL);

            if (!tester) {
                QueryURL = googleBase10 + book_id + "&key=" + myAPIkey;
                response.delete(0, response.length());
                new asyncc().execute(QueryURL);
            }


        } else {

            QueryURL = googleBase10 + book_id + "&key=" + myAPIkey;
            response.delete(0, response.length());
            new asyncc().execute(QueryURL);


        }

        System.out.println("get here 1");



    }

    private class recordy extends AsyncTask<JSONObject, Void, Void> {
        protected Void doInBackground(JSONObject... entry) {

        JSONObject rr = entry[0];

            String Title,author,publisher,publishe_date,cate, isbn,lang,Status=myID;


            try {

                Log.d("kadd", getContext().getFilesDir().toString());

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "bookdb.csv");
                FileOutputStream fout = new FileOutputStream(file,false);

                fout.write(booklisTitle.getBytes());
                fout.close();

                fout = new FileOutputStream(file,true);



                Title = root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("title");
                author = root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getJSONArray("authors").toString();
                publisher = root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("publisher");
                publishe_date = root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("publishedDate");
                cate = root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getJSONArray("categories").toString();
                isbn = book_id.toString();

                lang = root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("language");


                for(int g=1; g < resultList.size(); g++){

                    fout.write(("\n"+resultList.get(g).toString()).getBytes());
                }

                addTodatbase = Title.replace(",",":") + "," + author.replace(",",":")
                        + "," + publisher.replace(",",":") + ","
                        + publishe_date + "," + cate + "," + isbn + "," + lang + "," + Status + "\n";

                fout.write(("\n"+addTodatbase).getBytes());
                fout.close();
            }catch(IOException | JSONException e){

            }






            return null;
        }



        protected void onPostExecute() {

        }
    }


    private class asyncc extends AsyncTask<String, Void, JSONObject> {

        protected JSONObject doInBackground(String... query) {

            String qq=query[0];

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


                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine + "\n");
                    //procList.add(inputLine);

                    // response.append("\n");

                }

                in.close();

                root = new JSONObject(response.toString());

                String checks = root.getString("totalItems");

                System.out.println("ppppppppppppp" + checks);
                Log.d("ppppp", checks);

                if (Integer.parseInt(checks) == 0) {
                    tester = false;
                } else {
                    tester = true;
                }


                //book = root.getJSONArray("items");

            } catch (IOException | URISyntaxException | JSONException e ) {

            } finally {

            }

            return root;

        }

        protected void onPostExecute(JSONObject result) {
            try {

                // connect to google API



                System.out.println(root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo"));

                title.setText(root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("title"));

                author.setText(root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getJSONArray("authors").toString());
                publisher.setText(root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("publisher"));
                datey.setText(root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("publishedDate"));
                isbnten.setText(root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getJSONArray("categories").toString());

                isbnthirteen.setText(root.getJSONArray("items").getJSONObject(0)
                        .getJSONObject("volumeInfo").getString("language"));

                if(root.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo")
                        .has("averageRating")){
                    rating.setText(root.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo")
                            .getString("averageRating"));
                }
                else{
                    rating.setText("-1.0");
                }




                String orderAble= rating.getText().toString();

                if(Float.parseFloat(orderAble)>=3.0){

                    order.setVisibility(View.VISIBLE);
                }
                else if (Float.parseFloat(orderAble)<0.0){

                    noorder.setVisibility(View.VISIBLE);
                    rating.setText("Not available ");

                }
                else {
                    noorder.setVisibility(View.VISIBLE);
                }







                // print result

            } catch (JSONException e) {

            } finally {

            }

        }



    }


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
