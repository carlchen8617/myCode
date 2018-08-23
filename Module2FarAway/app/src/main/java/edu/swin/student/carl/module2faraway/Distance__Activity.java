package edu.swin.student.carl.module2faraway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class Distance__Activity extends AppCompatActivity {
    double mile, feet,inch,zero=0.0;
    EditText myMile,myFeet,myInch;
    Button  myButton,myClear,goback_dis;
    CheckBox myMetre;
    int metreSet=0;
    TextView myMilel,myFeetl, myInchl,titlel ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

         titlel=findViewById(R.id.TV);
         myMile= findViewById(R.id.mile);
         myMilel= findViewById(R.id.milel);
         myFeet= findViewById(R.id.feet);
         myFeetl= findViewById(R.id.feetl);
         myInch= findViewById(R.id.inch);
         myInchl= findViewById(R.id.inchl);

         myButton=findViewById(R.id.convert);
        goback_dis=findViewById(R.id.goback_dist);
         myClear=findViewById(R.id.clear);
         myMetre=findViewById(R.id.cb);


         //assign values
        titlel.setText(R.string.title);
        myMilel.setText(R.string.mile);
        myFeetl.setText(R.string.feet);
        myInchl.setText(R.string.inch);
        myClear.setText(R.string.clear);
        myMetre.setText(R.string.metre);
        myButton.setText(R.string.convert);


      // myMetre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      //                                         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

       //                                  }
       //});

        goback_dis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // the goback listener that goes back to main activity

                launchActivity();

            }
        });


        myButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                if (myMetre.isChecked()){

                    metreSet=1;

                }
                else{
                    metreSet=0;

                }

                mile=findIt(myMile.getId(),1, metreSet);
                feet=findIt(myFeet.getId(),2, metreSet);
                inch=findIt(myInch.getId(),3, metreSet);

                if(metreSet==0){

                    myMile.setText( String.format("%.1f", mile) + " "+ getString(R.string.cm));
                    myFeet.setText( String.format("%.1f", feet) + " "+ getString(R.string.cm));
                    myInch.setText( String.format("%.1f", inch)  + " "+ getString(R.string.cm));

                }
                else{
                    myMile.setText( String.format("%.1f", mile) + " "+ getString(R.string.metre));
                    myFeet.setText( String.format("%.1f", feet) + " "+ getString(R.string.metre));
                    myInch.setText( String.format("%.1f", inch) + " "+ getString(R.string.metre));

                }



            }
        });

        myClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


                myMile.setText(String.valueOf(zero));
                myFeet.setText(String.valueOf(zero));
                myInch.setText( String.valueOf(zero));
                myMetre.setChecked(false);
            }
        });


    }

    //worker private function to find conversion
    private double findIt(int id, int unit, int metreSet){

        double result;
        EditText myEdit= findViewById(id);

        float originalValue= Float.valueOf( myEdit.getText().toString());

        if (metreSet==0){

            //for cm
        if(unit==1)
        {
            result= originalValue * 1.6093 * 1000*100;
            return result;
        }
        else if(unit==2){

            result= originalValue * 0.3048 * 100;
            return result;

        }
        else if(unit==3){

            result= originalValue * 2.54;
            return result;

        }
        else{

            result=0.0;
            return result;
            }

            //for cm
        }

        else{

            //for metre

            if(unit==1)
            {
                result= originalValue * 1.6093 * 1000;
                return result;
            }
            else if(unit==2){

                result= originalValue * 0.3048;
                return result;

            }
            else if(unit==3){

                result= originalValue * 2.54/100;
                return result;

            }
            else{

                result=0.0;
                return result;
            }

            //for metre
        }




    }

    private void launchActivity() { //the launch worker function


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

}
