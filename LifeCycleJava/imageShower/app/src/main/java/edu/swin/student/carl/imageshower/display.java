package edu.swin.student.carl.imageshower;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;

public class display extends AppCompatActivity {
   String food;
   String pp, rr, ff, vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

       pp =  getResources().getString(R.string.ita);

        Intent intent=getIntent();
        Bundle input= intent.getExtras();

        if(input!=null){

             food= input.getString("food");
        }


            // find target file on the internal storage

            File imgFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+"/"+ food+".JPG");

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                ImageView myImage = (ImageView) findViewById(R.id.mimi);
                TextView myn= findViewById(R.id.tv);
                myImage.setImageBitmap(myBitmap);

                if(food.matches("pizza")){

                    myn.setText(getResources().getString(R.string.ita));
                }
                else if(food.matches("rice")){

                    myn.setText(getResources().getString(R.string.rr));
                }
                else if(food.matches("fish")){

                    myn.setText(getResources().getString(R.string.fish));
                }
                else if(food.matches("vindaloo")){

                    myn.setText(getResources().getString(R.string.vv));
                }

            }






      //  File imgFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+"/"+"pizza.JPG");

            //System.out.println(imgFile.getName());


    }
}
