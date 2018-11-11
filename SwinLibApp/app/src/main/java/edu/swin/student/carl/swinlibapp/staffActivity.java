package edu.swin.student.carl.swinlibapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class staffActivity extends AppCompatActivity implements
        StaffViewHighlvl.OnFragmentInteractionListener,
        StaffBookViewDetails.OnFragmentInteractionListener,
        staffViewstdHighLvl.OnFragmentInteractionListener,
        staffViewStdDetails.OnFragmentInteractionListener,
        staffAddUser.OnFragmentInteractionListener,
        staffremoveUser.OnFragmentInteractionListener,
        staffCHGbkStatus.OnFragmentInteractionListener

{


    private String id;
    TextView hello;
    Toolbar myToolbar;
    Spinner spinner;
    MenuItem item;
    ArrayAdapter<String> adapter;
    BaseExpandableListAdapter exAdapter;
    FrameLayout fl;
    LinearLayout.LayoutParams lp;
    StaffViewHighlvl stafViewfFrag;
    StaffBookViewDetails stafViewDetails;
    staffViewstdHighLvl staffViewstdHighLvlfrag;
    staffViewStdDetails staffViewStdDetails;
    staffAddUser staffAddUserfrag;
    staffremoveUser staffremoveUserfrag;
    staffCHGbkStatus staffCHGbkStatusfrag;
    addUser addUserfrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        fl = findViewById(R.id.fragment_container);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //toolbar
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        hello = findViewById(R.id.idmm);

        studentParcel stu = getIntent().getParcelableExtra("id");

        hello.setText("Hi " + stu.Name + "!");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.me, menu);


        item = myToolbar.getMenu().findItem(R.id.menustu);
        spinner = (Spinner) item.getActionView();
        final List listA = new ArrayList();
        List listB = new ArrayList();
        List listC = new ArrayList();
        List listD = new ArrayList();

        stafViewfFrag = StaffViewHighlvl.newInstance("g", "v");
        staffViewstdHighLvlfrag = staffViewstdHighLvl.newInstance("g", "v");
       staffAddUserfrag=staffAddUser.newInstance("g", "v");
        staffremoveUserfrag= staffremoveUser.newInstance("g", "v");
        staffCHGbkStatusfrag=staffCHGbkStatus.newInstance("g","v");
        //addUserfrag= addUser.newInstance("g", "v");

        listA.add("Select");
        listA.add("Manage book DB");
        listA.add("Users");
        listA.add("Logout");


        adapter = new ArrayAdapter<String>(this, R.layout.memu_spinner, listA) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {


                View v = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) v;
                tv.setTextColor(getResources().getColor(R.color.colorWhite));
                tv.setBackgroundColor(Color.parseColor("BLACK"));

                return v;

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

                    getSupportFragmentManager().beginTransaction().add(fl.getId(),stafViewfFrag, "one").commit();
                    spinner.setSelection(0);


                    Log.d("KKKkKKKKKKKKKKKKKKKK", "onItemSelected: 1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                } else if (sp == 2) {

                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }

                    showFilterPopup(view);
                    spinner.setSelection(0);


                    Log.d("DDD", "onItemSelected: 2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                } else if (sp == 3) {

                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);

                } else if (sp == 0) {

                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        return true;
    }

    private void showFilterPopup(final View view) {
        PopupMenu popup = new PopupMenu(this, view);
        // Inflate the menu from xml
        popup.inflate(R.menu.popup_filter);
        fl.setLayoutParams(lp);
        // Setup menu item selection
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addU:
                       //

                        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        }

                        getSupportFragmentManager().beginTransaction().add(fl.getId(), staffAddUserfrag, "five").commit();




                        Toast.makeText(getApplicationContext(), "Keyword!", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.removeU:

                        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        }

                        getSupportFragmentManager().beginTransaction().add(fl.getId(), staffremoveUserfrag, "six").commit();
                        Toast.makeText(getApplicationContext(), "Popularity!", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.mU:

                     //   fl.setLayoutParams(lp);

                        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        }

                        getSupportFragmentManager().beginTransaction().add(fl.getId(), staffViewstdHighLvlfrag, "three").commit();
                        Log.d("ggg", "add 1 ");


                        Toast.makeText(getApplicationContext(), "Popularity!", Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        return false;
                }


            }
        });
        // Handle dismissal with: popup.setOnDismissListener(...);
        // Show the menu
        popup.show();
    }


    public void onFragmentInteractionStaffView(String position, int row) {

        if (!(position.isEmpty())) {

            String[] place = position.split(",");// process string
            Log.d("splited", place[0] + " " + place[1] + " " + place[2] + " " + place[3]
                    + " " + place[4] + " " + place[5] + " " + place[6] + " " + place[7]);

            stafViewDetails = stafViewDetails.newInstance("g", "v");
            stafViewDetails.getBookID(Integer.toString(row));
            //   fl = findViewById(R.id.fragment_container);
            //   LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            //    fl.setLayoutParams(lp);
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            getSupportFragmentManager().beginTransaction().add(fl.getId(), stafViewDetails, "two").commit();
            Log.d("ggg", "add 1 ");


        }


    }

    public void ViewStudentDetails(String position, int row) {
        Log.d("how are you", position);

        if (!(position.isEmpty())) {
            String[] place = position.split(",");// process string
            Log.d("splited", place[0] + " " + place[1] + " " + place[2]);


            staffViewStdDetails = staffViewStdDetails.newInstance("g", "v");
            staffViewStdDetails.getBookID(Integer.parseInt(place[0]));
            //   fl = findViewById(R.id.fragment_container);
            //   LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            //    fl.setLayoutParams(lp);
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            getSupportFragmentManager().beginTransaction().add(fl.getId(), staffViewStdDetails, "four").commit();
            Log.d("ggg", "add 1 ");


        }

    }

    public void staffCHGBKAail(String position, int row) {

        if (!(position.isEmpty())) {


            stafViewDetails = stafViewDetails.newInstance("g", "v");
            staffCHGbkStatusfrag.getBookID(position,row);
            //   fl = findViewById(R.id.fragment_container);
            //   LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            //    fl.setLayoutParams(lp);
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }

            getSupportFragmentManager().beginTransaction().add(fl.getId(), staffCHGbkStatusfrag, "seven").commit();
            Log.d("ggg", "add 1 ");


        }
        Toast.makeText(this, "Ok Done!", Toast.LENGTH_SHORT).show();

    }
    public void addUser(Uri uri) {

    }
    public void removeUser(Uri uri) {

    }

    public void onFragmentInteractionStafViewDetails(Uri uri) {


    }

    public void onFragmentInteractionEnd(Uri uri) {

    }


}
