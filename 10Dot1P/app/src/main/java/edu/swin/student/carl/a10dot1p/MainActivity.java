package edu.swin.student.carl.a10dot1p;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button go;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        go= findViewById(R.id.button);
        status=findViewById(R.id.status);

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new asyncc().execute(3);



            }
        });

    }

   private class asyncc extends AsyncTask<Integer, Integer, Integer> {
        protected Integer doInBackground(Integer... intIn) {
            int myint=intIn[0];

            try {
                for (int i=myint; i >= 0; i--) {
                    Thread.sleep(1000);
                    publishProgress((int)(i));

                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

          return intIn[0];
        }

       protected void onProgressUpdate(Integer... progress) {
           status.setText( progress[0].toString());
       }
    }
}
