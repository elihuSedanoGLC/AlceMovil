package com.glc.alcemovil;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.glc.alcemovil.R;
import com.glc.alcemovil.accesos.AccesosActivity;
import com.glc.alcemovil.fruta.FrutaActivity;
import com.glc.alcemovil.menu.MenuActivity;
import com.glc.alcemovil.siniestros.SiniestrosActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//-----------------------imports AppCopio
import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.preference.PreferenceManager;
//import com.david.david.loscerritos.data.MySingleton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {
    private UserLoginTask mAuthTask = null;
    private EditText etUser = null;
    private EditText etPassword = null;
    private ImageView imConfig = null;
    private String mobileApiUrl;
    private String serverIP = Constantes.serverIP;
    final static String TAG = "LoginActivity";
    String mobileApiURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        /*mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });*/


        finds();

        //---onClick icono Ajustes
        imConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click conf");
                Intent intent = new Intent(getApplicationContext(), FrutaActivity.class);
                startActivity(intent);
            }
        });     //---onClick icono Ajustes


    }



        @Override
        public void onResume(){
            super.onResume();
            SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
            String serverIP = spref.getString("pref_db_server1","10.10.100.17:82/ALCE/public");
            mobileApiURL = "http://" + serverIP + "/mobile_api?";
        }

        public void btnLoginOnClick(View v){attemptLogin();}

        private void attemptLogin() {
            if (mAuthTask != null) {
                return;
            }

            // Reset errors.
  /*          etUser.seterror(null);
            etPassword.setError(null);
*/
            // Store values at the time of the login attempt.
            String email = etUser.getText().toString();
            String password = etPassword.getText().toString();

            boolean cancel = false;
            View focusView = null;

            // Check for a valid password, if the user entered one.
            if (TextUtils.isEmpty(password)){
                etPassword.setError(getString(R.string.error_field_required));
                focusView = etPassword;
                cancel = true;
            }else if (!isPasswordValid(password)) {
                etPassword.setError(getString(R.string.error_invalid_password));
                focusView = etPassword;
                cancel = true;
            }

            // Check for a valid email address.
            if (TextUtils.isEmpty(email)) {
                etUser.setError(getString(R.string.error_field_required));
                focusView = etUser;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                // Show a progress spinner, and kick off a background task to
                // perform the user login attempt.
                //showProgress(true);
                mAuthTask = new UserLoginTask(email, password, this);
                mAuthTask.execute((String) null);
            }
        }

        private boolean isPasswordValid(String password) {
            return password.length() >= 4;
        }



  /*      private void showProgress(final boolean show) {
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
*/
        /**
         * Represents an asynchronous login/registration task used to authenticate
         * the user.
         */
        public class UserLoginTask extends AsyncTask<String, String, String> {
            private final String mEmail;
            private final String mPassword;
            private Context context;
            HttpURLConnection urlConnection;

            UserLoginTask(String email, String password, Context context) {
                mEmail = email;
                mPassword = password;
                this.context = context;
            }

            @Override
            protected String doInBackground(String... params) {
                StringBuilder result = new StringBuilder();

                try {
                    Log.d(TAG, "start request");

                    StringBuilder builder = new StringBuilder();
                    builder.append(mobileApiURL);
                    builder.append("method=login");
                    builder.append("&user=");
                    builder.append(mEmail);
                    builder.append("&password=");
                    builder.append(mPassword);

                    Log.d(TAG, "ALCE url: "+builder.toString());

                    URL url = new URL(builder.toString());
                    urlConnection = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                }catch(Exception e) {
                    e.printStackTrace();
                    return result.toString();
                }
                finally {
                    urlConnection.disconnect();
                }

                return result.toString();
            }

            @Override
            protected void onPostExecute(String response) {
                mAuthTask = null;
                //showProgress(false);
                Log.d(TAG, "response = " + response);

                if (response.isEmpty()) {
                    Toast.makeText(context, "Error al intentar iniciar sesión, revise que el servidor sea correcto y su conexión al servidor.", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject json = new JSONObject(response);
                        int status = json.getInt("status");
                        Log.d(TAG, "status = " + status);
                        if (status == 200) {
                            String token = json.getString("token");
                            String rol = json.getString("rol");
                            Log.v("Login", token);
                            int userId = json.getInt("user_id");
                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("pref_user_token", token);
                            editor.putString("pref_user_email", mEmail);
                            editor.putString("pref_user_rol", rol);
                            editor.putInt("pref_user_id", userId);
                            editor.apply();

                            //MySingleton.setUserId(context);

                            Intent intent = new Intent(context, MenuActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //clear backstack
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION); //it will looks like the transition inside the app, so user will not notice login activity, instead of default animation, which look like starting other app.
                            startActivity(intent);
                            finish();
                        } else if (status == 401) {
                            Toast.makeText(context, getString(R.string.error_auth), Toast.LENGTH_LONG).show();
                        } else if (status == 500) {
                            Toast.makeText(context, "Error al intentar iniciar sesión.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Error al intentar iniciar sesión, revise que el servidor sea correcto y su conexión al servidor.", Toast.LENGTH_LONG).show();
                        }
                    } catch (Throwable t) {
                        Log.e(TAG, "Could not parse JSON: \"" + response + "\"");
                    }
                }
            }

            @Override
            protected void onCancelled() {
                Toast.makeText(context,"Cancelled...",Toast.LENGTH_LONG).show();
                //mAuthTask = null;
                //showProgress(false);
            }
        }
    private void finds(){
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        imConfig = findViewById(R.id.icon_conf);
    }

} //Cierre de la clase