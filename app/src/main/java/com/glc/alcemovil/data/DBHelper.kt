package com.glc.alcemovil.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

//import com.github.yuweiguocn.library.greendao.MigrationHelper


class DBHelper {
    private val TAG = "DBHelper"
    private var _db: SQLiteDatabase? = null
    //private val _session: DaoSession? = null
    private var context: Context? = null

    /*fun DBHelper(context: Context?) {
        this.context = context
    }*/

    /*private fun getMaster(): DaoMaster {
        if (_db == null) {
            val DB_NAME = "los_cerritos_db"
            _db = getDatabase(DB_NAME)
        }
        return DaoMaster(_db)
    }*/

    @Synchronized
    private fun getDatabase(name: String): String {
        val s = "getDB($name,readonly=false)"
        return s
    }


    /*private class MyOpenHelper internal constructor(
        context: Context?,
        name: String?,
        //factory: CursorFactory?
    ) :

        DaoMaster.OpenHelper(context, name, factory) {
        val writableDatabase: SQLiteDatabase? = null

        fun onCreate(db: SQLiteDatabase?) {
            Log.i("DBHelper", "Create DB-Schema (version SCHEMA_VERSION)")
            super.onCreate(db)
        }
    }*/

        /*fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            Log.i(
                TAG,
                "Update DB-Schema to version: " + Integer.toString(oldVersion) + "->" + Integer.toString(
                    newVersion
                )
            )
            switch (oldVersion) {
                default:
                    break;
            }
            MigrationHelper.migrate(
                db,
                VisitaDao::class.java
            )
        }
    }*/

}
