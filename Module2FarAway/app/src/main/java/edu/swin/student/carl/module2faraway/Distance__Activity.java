package edu.swin.student.carl.module2faraway;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Distance__Activity extends AppCompatActivity {
    double mile, feet,inch,zero=0.0;
    EditText myMile,myFeet,myInch;
    Button  myButton,myClear,goback_dis;
    CheckBox myMetre;
    int metreSet=0;
    TextView myMilel,myResult,titlel ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_distance);

         titlel=findViewById(R.id.TV);
         myMile= findViewById(R.id.mile);
         myMilel= findViewById(R.id.milel);
         myButton=findViewById(R.id.convert);
         goback_dis=findViewById(R.id.goback_dist);
         myClear=findViewById(R.id.clear);
         myMetre=findViewById(R.id.cb);
         myResult=findViewById(R.id.result);



         //assign values
        final Spinner spinner = (Spinner) findViewById(R.id.kind);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.convert, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        titlel.setText(R.string.title);
        myMilel.setText(R.string.mile);
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


                mile=findIt(myMile.getId(),spinner.getSelectedItemPosition(), metreSet);


                if(metreSet==0){

                    myResult.setText( myMile.getText()+ " " + spinner.getSelectedItem() + " is " + mile  + " "+ getString(R.string.cm));


                }
                else{
                    myResult.setText( myMile.getText()+ " " + spinner.getSelectedItem() + " is " + mile  + " "+ getString(R.string.metre));


                }



            }
        });

        myClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button


                myMile.setText(String.valueOf(zero));
                myMetre.setChecked(false);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //myResult.setText( myMile.getText()+ " "  + " is " + mile  + " "+ getString(R.string.metre));
        savedInstanceState.putString("m", myResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myResult.setText(savedInstanceState.getString("m"));
    }

    //worker private function to find conversion
    private double findIt(int id, int unit, int metreSet){

        double result;
        EditText myEdit= findViewById(id);

        float originalValue= Float.valueOf( myEdit.getText().toString());

        if (metreSet==0){

            //for cm
        if(unit==0)
        {
            result= originalValue * 1.6093 * 1000*100;
            return result;
        }
        else if(unit==1){

            result= originalValue * 0.3048 * 100;
            return result;

        }
        else if(unit==2){

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

            if(unit==0)
            {
                result= originalValue * 1.6093 * 1000;
                return result;
            }
            else if(unit==1){

                result= originalValue * 0.3048;
                return result;

            }
            else if(unit==2){

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
