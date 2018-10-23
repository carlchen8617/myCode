package edu.swin.student.carl.swinlibapp;

import android.graphics.Color;
import android.net.Uri;
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

public class staff extends AppCompatActivity implements
        StaffViewHighlvl.OnFragmentInteractionListener,
        StaffBookViewDetails.OnFragmentInteractionListener,
        staffViewstdHighLvl.OnFragmentInteractionListener
          {


    private String id;
    TextView hello;
    Toolbar myToolbar;
    Spinner spinner;
    MenuItem item;
    ArrayAdapter<String> adapter;
    FrameLayout fl;
    LinearLayout.LayoutParams lp;
    StaffViewHighlvl stafViewfFrag;
    StaffBookViewDetails stafViewDetails;
    staffViewstdHighLvl staffViewstdHighLvlfrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        //toolbar
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        hello = findViewById(R.id.idmm);

        studentParcel stu= getIntent().getParcelableExtra("id");

        hello.setText("Hi"+ stu.Name);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.me, menu);


        item = myToolbar.getMenu().findItem(R.id.menustu);
        spinner = (Spinner) item.getActionView();
        List listA = new ArrayList();

        stafViewfFrag = StaffViewHighlvl.newInstance("g", "v");
        staffViewstdHighLvlfrag=staffViewstdHighLvl.newInstance("g", "v");

        listA.add("Select");
        listA.add("View book DB");
        listA.add("Manage users");

        fl = findViewById(R.id.fragment_container);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);



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


                if (sp == 1) {

                    fl.setLayoutParams(lp);

                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    getSupportFragmentManager().beginTransaction().add(fl.getId(), stafViewfFrag, "one").commit();
                    Log.d("ggg", "add 1 ");



                    Log.d("KKKkKKKKKKKKKKKKKKKK", "onItemSelected: 1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
                else if (sp == 2){

                    fl.setLayoutParams(lp);

                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    getSupportFragmentManager().beginTransaction().add(fl.getId(), staffViewstdHighLvlfrag, "three").commit();
                    Log.d("ggg", "add 1 ");



                    Log.d("DDD", "onItemSelected: 2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
                else if (sp == 0){

                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });



        return true;
    }

    public void onFragmentInteractionStaffView(String position,int row) {

        if (!(position.isEmpty())) {

            String[] place = position.split(",");// process string
            Log.d("splited", place[0] + " " + place[1] + " " + place[2] + " " + place[3]
                    + " " + place[4] + " " + place[5] + " " + place[6] + " " + place[7]  );

            stafViewDetails = stafViewDetails.newInstance("g", "v");
            stafViewDetails.getBookID(Integer.toString(row));
            fl = findViewById(R.id.fragment_container);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            fl.setLayoutParams(lp);
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            getSupportFragmentManager().beginTransaction().add(fl.getId(),  stafViewDetails, "two").commit();
            Log.d("ggg", "add 1 ");




        }


    }

    public void onFragmentInteractionStafViewDetails(Uri uri) {


    }

    public void ViewStudentAll(Uri uri) {

        }




}
