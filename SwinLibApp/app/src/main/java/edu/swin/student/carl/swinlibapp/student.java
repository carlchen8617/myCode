package edu.swin.student.carl.swinlibapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class student extends AppCompatActivity  implements
        bookBrowse.OnFragmentInteractionListener,
        bookDetails.OnFragmentInteractionListener {

    private String id;
    TextView hello;
    String[] stuName;
    Toolbar myToolbar;
    Spinner spinner;
    MenuItem item;
    ArrayAdapter<String> adapter;
    FrameLayout fl;
    LinearLayout.LayoutParams lp;
    bookBrowse bookBrowseFrag;
    bookDetails bookDetailsFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        //toolbar
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        hello = findViewById(R.id.idm);

        studentParcel stu= getIntent().getParcelableExtra("id");
        stuName = stu.Name.split(" ");
        hello.setText("Hi "+ stuName[0]);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.me, menu);



        bookBrowseFrag = bookBrowse.newInstance("g", "v");


        item = myToolbar.getMenu().findItem(R.id.menustu);
        spinner = (Spinner) item.getActionView();
        List listA = new ArrayList();

        listA.add("Borrow a book");
        listA.add("View my details");
        listA.add("Change password");

        adapter = new ArrayAdapter<String>(this, R.layout.memu_spinner, listA) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(getResources().getColor(R.color.colorWhite));
                tv.setBackgroundColor(Color.parseColor("BLACK"));

                return view;
            }
        };


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // set spinner listener
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int sp = spinner.getSelectedItemPosition();

                fl = findViewById(R.id.fragment_container);
                lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                fl.setLayoutParams(lp);

                if (sp == 1) {

                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    getSupportFragmentManager().beginTransaction().add(fl.getId(), bookBrowseFrag, "one").commit();
                    Log.d("ggg", "add 1 ");



                    Log.d("KKKkKKKKKKKKKKKKKKKK", "onItemSelected: 1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
                else if (sp == 0){


                    Log.d("DDD", "onItemSelected: 2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
                else if (sp == 2){


                    Log.d("DDD", "onItemSelected: 3!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        return true;
    }

    public void onFragmentInteractionDetails(Uri position) {

    }

    public void onFragmentInteractionBookBrowse(String position) {
        Log.d("report", "ok got here");

        if (!(position.isEmpty())) {

            String[] place = position.split(",");// process string
            Log.d("splited", place[0] + " " + place[1] + " " + place[2] + " " + place[3]
                    + " " + place[4] + " " + place[5] + " " + place[6] + " " + place[7]  );

            bookDetailsFrag = bookDetails.newInstance("g", "v");

            fl = findViewById(R.id.fragment_container);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            fl.setLayoutParams(lp);
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            getSupportFragmentManager().beginTransaction().add(fl.getId(), bookDetailsFrag, "two").commit();
            Log.d("ggg", "add 1 ");




        }
    }


}
