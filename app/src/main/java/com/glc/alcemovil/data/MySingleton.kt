package com.glc.alcemovil.data

import com.glc.alcemovil.data.DBHelper
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Environment
import android.preference.PreferenceManager
import android.util.Base64
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.Collections


object MySingleton {
    private const val TAG = "singleton"
    var instance: MySingleton? = null
        /*get() {
            if (field == null) field = MySingleton()
            return field
        }*/
    private var productosServer: ArrayList<String>? = null
    private var calibresServer: ArrayList<String>? = null
    private var huertasServer: ArrayList<String>? = null
    private var variedadesServer: ArrayList<String>? = null
    var tons: Double? = null
        get() {
            Log.d(TAG, field.toString())
            return field
        }
    private var dbHelper: DBHelper? = null
    var userId = 0
    /*fun init(context: Context) {
        if (dbHelper == null) {
            dbHelper = DBHelper(context.applicationContext)
        }
        if (versions.isEmpty()) {
            val versiones: ArrayList<Version> = ArrayList<Version>()
            versiones.add(Version(null, "producto", 0))
            versiones.add(Version(null, "calibre", 0))
            versiones.add(Version(null, "enfermedad", 0))
            versiones.add(Version(null, "plaga", 0))
            versiones.add(Version(null, "huerta", 0))
            versiones.add(Version(null, "variedad", 0))
            val versionDao: VersionDao = dbHelper.getSession().getVersionDao()
            versionDao.insertInTx(versiones)
        }
        getProductosServer()
        getCalibresServer()
        getPlagasServer()
        getEnfermedadesServer()
        getHuertasServer()
        getVariedadesServer()
        setUserId(context)
        try {
            writeToSD(context)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }*/

    fun setUserId(context: Context?) {
        val spref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        userId = spref.getInt("pref_user_id", 0)
        Log.d(TAG, "userId = " + userId)
    }

    @Throws(IOException::class)
    fun writeToSD(context: Context) {
        val DB_NAME = "alcemovil_db"
        val DB_PATH: String
        DB_PATH = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.filesDir.absolutePath.replace("files", "databases") + File.separator
        } else {
            context.filesDir.path + context.packageName + "/databases/"
        }
        val sd = Environment.getExternalStorageDirectory()
        if (sd.canWrite()) {
            val backupDBPath = "backup_los_cerritos.sqlite"
            val currentDB = File(DB_PATH, DB_NAME)
            val backupDB = File(sd, backupDBPath)
            if (currentDB.exists()) {
                val src = FileInputStream(currentDB).channel
                val dst = FileOutputStream(backupDB).channel
                dst.transferFrom(src, 0, src.size())
                src.close()
                dst.close()
            }
        }
    }


/*
    fun addVisita(visita: Visita) {
        val visitaDao: VisitaDao = dbHelper.getSession().getVisitaDao()
        visita.setUserId(userId)
        visitaDao.insert(visita)
        getVisitas()!!.add(visita)
    }*/


    /*fun getFotoReport(fotoID: Long?): String {
        val builder = StringBuilder()
        val daoSession: DaoSession = dbHelper.getSession()
        val fotoDao: FotoDao = daoSession.getFotoDao()
        try {
            daoSession.runInTx(Runnable { //foto
                val foto: Foto =
                    fotoDao.queryBuilder().where(FotoDao.Properties.Id.eq(fotoID)).build().unique()
                var bm = BitmapFactory.decodeFile(foto.getData())
                var baos: ByteArrayOutputStream? = ByteArrayOutputStream()
                bm!!.compress(Bitmap.CompressFormat.JPEG, 50, baos!!)
                bm = null
                val b = baos.toByteArray()
                baos = null
                builder.append(Base64.encodeToString(b, Base64.DEFAULT))
                System.gc()
            })
        } catch (e: Exception) {
            Log.d(TAG, "Error al intentar generar String base64")
            e.printStackTrace()
        }
        return builder.toString()
    }*/



} //Cierre de la clase

