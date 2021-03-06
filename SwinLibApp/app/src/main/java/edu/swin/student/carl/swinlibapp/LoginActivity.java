package edu.swin.student.carl.swinlibapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    Toolbar myToolbar;
    Spinner spinner;
    MenuItem item;
    ArrayAdapter<String> adapter;
    int staffOrStud;
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //toolbar
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.me, menu);
        item = myToolbar.getMenu().findItem(R.id.menuSort);
        spinner = (Spinner) item.getActionView();
        List listA = new ArrayList();

        listA.add("Student Login");
        listA.add("Staff Login");

        adapter = new ArrayAdapter<String>(this, R.layout.memu_spinner, listA) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                tv.setTextColor(getResources().getColor(R.color.colorWhite));
                tv.setBackgroundColor(Color.parseColor("BLACK"));

                return view;
            }
        };


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // set spinner listener
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int sp = spinner.getSelectedItemPosition();

                if (sp == 1) {

                    staffOrStud=1;

                    Log.d("KKKkKKKKKKKKKKKKKKKK", "onItemSelected: 1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
                else{

                    staffOrStud=0;
                    Log.d("DDD", "onItemSelected: 2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
            }


                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });


        return true;
    }





    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password,staffOrStud);
            mAuthTask.execute((Void)null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //return email.contains("@");
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }









    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private String mName;
        private final Integer mtypi;


        UserLoginTask(String email, String password, Integer typi) {
            mEmail = email;
            mPassword = password;
            mName="";
            mtypi=typi;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                // check if stuff or studentActivity
               if(mtypi==1){

                   String csvLine;
                   //InputStream fin =getResources().openRawResource(R.raw.staff);
                   File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "staff.csv");
                   if(file.exists()) {
                       Log.d("dsdsddddddddddddddddddd", file.toString() +  "exist!");
                   }
                   FileInputStream fin = new FileInputStream(file);
                   DataInputStream in = new DataInputStream(fin);
                   BufferedReader reader = new BufferedReader(new InputStreamReader(in));;
                  // BufferedReader reader = new BufferedReader(fin);
                   //BufferedReader reader = new BufferedReader(new InputStreamReader(fin));

                   while ((csvLine = reader.readLine()) != null) {
                       Log.d("kkkhhhh!!!!!!!!!!!!!!", csvLine);
                       String[] logintest = csvLine.split(",");
                       if((logintest[0].trim()).equals(mEmail.trim())   && (logintest[1].trim()).equals( mPassword.trim()) ){
                           Log.d("ok", "login OK!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

                           return true;

                       }

                   }
                   //fin.close();
               }

                if(mtypi==0){

                    String csvLine;

                    /**
                     * for the application to work, either use raw resources, that will need modify a few files as they
                     * are configured to read external resources. copy the file in Raw into Myfile/Dovuments/Documents folder and allow
                     * permission for storage and location on the phone
                     */
                    // InputStream fin =getResources().openRawResource(R.raw.student);
                    String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();


                    Log.d("herehere", path +  " exist!");
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath(), "student.csv");
                   // FileOutputStream fout = new FileOutputStream(file,true);

                   // fout.write(fin.read());
                  //  fout.close();
                    if(file.exists()) {
                        Log.d("dsdsddddddddddddddddddd", "Internal storage" + file.toString() +  " exist!");
                    }
                    else {

                        Log.d("BBBB", file.toString() +  " not exist!");
                    }

                   FileInputStream fin = new FileInputStream(file);


                    DataInputStream in = new DataInputStream(fin);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                    //BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
                    while ((csvLine = reader.readLine()) != null) {
                        Log.d("bbbbhhhh!!!!!!!!!!!!!!", csvLine);
                        String[] logintest = csvLine.split(",");
                        if((logintest[0].trim()).equals(mEmail.trim())   && (logintest[2].trim()).equals( mPassword.trim()) ){
                            Log.d("ok", "login OK!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
                            mName=logintest[1].trim();

                            return true;

                        }
                    }
                    fin.close();
                }



                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            catch (IOException e){

            }
           /**
            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
            **/

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                launchActivity(mName,mEmail,mtypi);
                Log.d("OK", "successful!!!!!!!!!!!!!!!!!!!!! "+ mEmail);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

    private void launchActivity(String name, String id, int type ) { //the launch worker function

        if(type==0)
        {
            studentParcel student= new studentParcel(name,id);

            Intent intent = new Intent(this, studentActivity.class);
            intent.putExtra("par", student);

            startActivity(intent);

        }

        if(type==1){

            staffParcel staff= new staffParcel(id);
            Intent intent = new Intent(this, staffActivity.class);
            //Intent intent = new Intent(this, expandList.class);
            intent.putExtra("id", staff);
            startActivity(intent);


        }


    }


}

