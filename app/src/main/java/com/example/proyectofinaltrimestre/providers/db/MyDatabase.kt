package com.example.proyectofinaltrimestre.providers.db

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.proyectofinaltrimestre.Aplicacion

class MyDatabase(): SQLiteOpenHelper(Aplicacion.appContext, Aplicacion.DB, null, Aplicacion.VERSION) {
    private val q="create table ${Aplicacion.TABLA}(" +
            "id integer primary key autoincrement," +
            "nombre text not null," +
            "apellidos text not null," +
            "email text not null," +
            "login text not null unique," +
            "password text not null);"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(q)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(newVersion>oldVersion){
            val borrarTabla="drop table ${Aplicacion.TABLA};"
            db?.execSQL(borrarTabla)
            onCreate(db)
        }
    }
}







