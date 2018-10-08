package edu.swin.student.carl.swinlibapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class student extends AppCompatActivity  {

    private String id;
    TextView hello;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        hello = findViewById(R.id.idm);

        studentParcel stu= getIntent().getParcelableExtra("id");

        hello.setText("Hi"+ stu.Name);

    }






}
