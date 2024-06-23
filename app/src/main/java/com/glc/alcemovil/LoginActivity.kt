package com.glc.alcemovil

//import androidx.preference.PreferenceManager
import com.glc.alcemovil.data.MySingleton
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.glc.alcemovil.menu.MenuActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class LoginActivitykt : AppCompatActivity() {
    private var etUser: EditText? = null
    private var etPassword: EditText? = null

    private var serverIP : String ="http://10.10.100.17:82/ALCE/mobile_api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUser = findViewById(R.id.etUser)
        etPassword = findViewById(R.id.etPassword)
    }

    override fun onResume() {
        super.onResume()
        //val spref = PreferenceManager.getDefaultSharedPreferences(this)
        //val serverIP = spref.getString("pref_db_server1", "appacopio.cerritos.loc:82")
        //serverIP = "http://10.10.100.17:82/ALCE/mobile_api"
    }

    fun entrarOnClick(view: View?) {
        serverIP += "?method=login&name="+etUser?.text.toString()+"&password="+ etPassword?.text.toString()
        //Toast.makeText(this,serverIP,Toast.LENGTH_LONG).show()
        var response = obtenerRespuestaDesdeURL(serverIP)
        Toast.makeText(this,response,Toast.LENGTH_LONG).show()

    }



    fun obtenerRespuestaDesdeURL(urlString: String): String {
        val url = URL(urlString)
        val conexion = url.openConnection() as HttpURLConnection

        return try {
            conexion.connect()
            val inputStream = conexion.inputStream
            inputStream.bufferedReader().use { it.readText() }
        } finally {
            conexion.disconnect()
        }
    }
/*
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 4
    }*/



    /*private fun attemptLogin() {
        if (mAuthTask != null) {
            return
        }

        // Reset errors.
        etUser?.error = null
        etPassword?.error = null

        // Store values at the time of the login attempt.
        var varUser: String = etUser?.text.toString()
        var varPassword: String = etPassword?.text.toString()
        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(varPassword)) {
            etPassword?.error = (getString(R.string.error_field_required))
            focusView = etPassword
            cancel = true
        } else if (!isPasswordValid(varPassword)) {
            etPassword?.error = (getString(R.string.error_invalid_password))
            focusView = etPassword
            cancel = true
        }

        // Check for a valid userName.
        if (TextUtils.isEmpty(varUser)) {
            etUser?.error = (getString(R.string.error_field_required))
            focusView = etUser
            cancel = true
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task
            //showProgress(true)
            //mAuthTask = MyUserLoginTask(varUser, varPassword, this, mobileApiURL)
            //mAuthTask!!.execute()
            Toast.makeText(
                this,
                "mobileApiUrl: $mobileApiURL",
                Toast.LENGTH_LONG
            ).show()
        }
    }*/
/*
    protected fun onPostExecute(response: String) {
        mAuthTask = null
        //showProgress(false)
        //Log.d(LoginActivity.TAG, "response = $response")
        if (response.isEmpty()) {
            Toast.makeText(
                this,
                "Error al intentar iniciar sesión, revise que el servidor sea correcto y su conexión al servidor.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            try {
                val json = JSONObject(response)
                val status = json.getInt("status")
                //Log.d(LoginActivity.TAG, "status = $status")
                if (status == 200) {
                    val token = json.getString("token")
                    val rol = json.getString("rol")
                    Log.v("Login", token)
                    val userId = json.getInt("user_id")
                    val sharedPref: SharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(this)//context
                    val editor = sharedPref.edit()
                    editor.putString("pref_user_token", token)
                    editor.putString("pref_user_email", etUser?.text.toString())
                    editor.putString("pref_user_rol", rol)
                    editor.putInt("pref_user_id", userId)
                    editor.apply()
                    MySingleton.setUserId(this)//context
                    val intent: Intent = Intent(
                        this,
                        MenuActivity::class.java
                    )  //Aqui el activity al que saltara cuando el login sea correcto
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) //clear backstack
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) //it will looks like the transition inside the app, so user will not notice login activity, instead of default animation, which look like starting other app.
                    startActivity(intent)
                    finish()
                } else if (status == 401) {
                    Toast.makeText(this, getString(R.string.error_auth), Toast.LENGTH_LONG)
                        .show()
                } else if (status == 500) {
                    Toast.makeText(this, "Error al intentar iniciar sesión.", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(
                        this,
                        "Error al intentar iniciar sesión, revise que el servidor sea correcto y su conexión al servidor.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (t: Throwable) {
                //Log.e(LoginActivity.TAG, "Could not parse JSON: \"$response\"")
                Toast.makeText(
                    this,
                    "Could not parse JSON: \"$response\"",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }*/
/*
    protected fun onCancelled() {
        mAuthTask = null
        //showProgress(false)
    }*/
/*
    abstract class UserLoginTask internal constructor(
        private val varUser: String,
        private val varPassword: String,
        private val context: Context,
        private val mobileApiURL: String = ""
    ) :
        AsyncTask<String?, String?, String>() {







        override fun onPostExecute(response: String) {
            var mAuthTask = null
            //showProgress(false)
            //Log.d(LoginActivity.TAG, "response = $response")
            if (response.isEmpty()) {
                Toast.makeText(
                    context,
                    "Error al intentar iniciar sesión, revise que el servidor sea correcto y su conexión al servidor.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                try {
                    val json = JSONObject(response)
                    val status = json.getInt("status")
                    //Log.d(LoginActivity.TAG, "status = $status")
                    if (status == 200) {
                        val token = json.getString("token")
                        val rol = json.getString("rol")
                        Log.v("Login", token)
                        val userId = json.getInt("user_id")
                        val sharedPref: SharedPreferences =
                            PreferenceManager.getDefaultSharedPreferences(
                                context
                            )
                        val editor = sharedPref.edit()
                        editor.putString("pref_user_token", token)
                        editor.putString("pref_user_email", varUser)
                        editor.putString("pref_user_rol", rol)
                        editor.putInt("pref_user_id", userId)
                        editor.apply()
                        MySingleton.setUserId(context)
                        //AQUI startActivity MENU

                        //finish()
                    } else if (status == 401) {
                        Toast.makeText(context, (R.string.error_auth), Toast.LENGTH_LONG)
                            .show()
                    } else if (status == 500) {
                        Toast.makeText(
                            context,
                            "Error al intentar iniciar sesión.",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Error al intentar iniciar sesión, revise que el servidor sea correcto y su conexión al servidor.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (t: Throwable) {
                    //Log.e(LoginActivity.TAG, "Could not parse JSON: \"$response\"")
                    Toast.makeText(
                        context,
                        "Could not parse JSON: \"$response\"",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    abstract class MyUserLoginTask(
        varUser: String,
        varPassword: String,
        context: Context,
        mobileApiURL: String
    ) : UserLoginTask(varUser, varPassword, context, mobileApiURL) {


    }*/

    /*@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)

    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
            mLoginFormView.setVisibility(if (show) View.GONE else View.VISIBLE)
            mLoginFormView.animate().setDuration(shortAnimTime.toLong()).alpha(
                (
                        if (show) 0 else 1).toFloat()
            ).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mLoginFormView.setVisibility(if (show) View.GONE else View.VISIBLE)
                }
            })
            mProgressView.setVisibility(if (show) View.VISIBLE else View.GONE)
            mProgressView.animate().setDuration(shortAnimTime.toLong()).alpha(
                (
                        if (show) 1 else 0).toFloat()
            ).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    mProgressView.setVisibility(if (show) View.VISIBLE else View.GONE)
                }
            })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(if (show) View.VISIBLE else View.GONE)
            mLoginFormView.setVisibility(if (show) View.GONE else View.VISIBLE)
        }
    }*/


}//cierre de la clase