package au.edu.swin.sdmd.suncalculatorjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class weatherActivity extends AppCompatActivity {

    String  json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Berlin&APPID=812cb447094b7b0eb95c777dc88ff717");

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));


            for (String line; (line = reader.readLine()) != null; ) {
                // System.out.println(line);
                json = json + line;
                Log.d("", "onCreate: "+line);
            }

        } catch (MalformedURLException e) {
            Log.d("URL", "URL something is wrong");
        } catch (IOException e) {
            Log.d("IO", "IO is wrong");
        }

    }
}
