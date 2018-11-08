package edu.swin.student.carl.swinlibapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class expandList extends AppCompatActivity {

    BaseExpandableListAdapter bl;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    Toolbar myToolbar;
    ExpandableListView spinner;
    ExpandableListView ll;
    MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_list);
        ll=findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        bl = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        //bl.(android.R.layout.simple_spinner_dropdown_item);
        ll.setAdapter(bl);


    }


}
