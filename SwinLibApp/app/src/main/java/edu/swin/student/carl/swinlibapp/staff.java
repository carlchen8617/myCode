package edu.swin.student.carl.swinlibapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class staff extends AppCompatActivity {


    private String id;
    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        hello = findViewById(R.id.idmm);

        studentParcel stu= getIntent().getParcelableExtra("id");

        hello.setText("Hi"+ stu.Name);

    }
}
