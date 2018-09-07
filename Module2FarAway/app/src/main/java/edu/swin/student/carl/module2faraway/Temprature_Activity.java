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

public class Temprature_Activity extends AppCompatActivity {

    TextView temp_title, FOrC, myResult;
    EditText temp;
    CheckBox check_temp;
    Button c_temp, clear_temp, goback;
    double zero=0.0, tempFind;
    String ff,gg="Celsius";
    int metreSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temprature_);

        temp_title=findViewById(R.id.tempText);
        temp= findViewById(R.id.farenEdit);
        FOrC=findViewById(R.id.faren);
        ff=FOrC.getText().toString();
        check_temp= findViewById(R.id.temp_cb);
        c_temp= findViewById(R.id.temp_convert);
        clear_temp= findViewById(R.id.temp_clear);
        goback=findViewById(R.id.goback);
        myResult=findViewById(R.id.temp_re);

        goback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                launchActivity();

                }
        });

        c_temp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (check_temp.isChecked()){

                    metreSet=0;  //c to f

                }
                else{
                    metreSet=1; //f to c

                }

                tempFind=findIt(temp.getId(), metreSet);

                if(metreSet==0){



                 myResult.setText(temp.getText()+ "  Celsius is " + String.format("%.1f", tempFind) + " "+ "Fahrenheit");

                }
                else{

                   myResult.setText(temp.getText()+ "  Fahrenheit is " + String.format("%.1f", tempFind) + " "+ "Celsius");
                }

            }
        });


        clear_temp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                FOrC.setText(ff);
                temp.setText(String.valueOf(zero));
                check_temp.setChecked(false);
            }
        });

        check_temp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(check_temp.isChecked()){//c to f
                    FOrC.setText(gg);
                }
                else{

                    FOrC.setText(ff); //f to c

                }
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

    private double findIt(int id, int metreSet){

        double result;
        EditText myEdit= findViewById(id);
        float originalValue= Float.valueOf( myEdit.getText().toString());

        if(metreSet==0){ //c to f
            result=(originalValue*1.8)+32;
            return result;

        }
        else if(metreSet==1){ // f to c
            result=(originalValue - 32)/1.8;
            return result;

        }
        else{

            result=0.0;
            return result;
        }



    }

    private void launchActivity() {


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


    }

}
