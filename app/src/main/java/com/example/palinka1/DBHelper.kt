package com.example.palinka1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "palinkak"
val TABLE_NAME = "palinka"
val COL_ID = "id"
val COL_FOZO = "fozo"
val COL_GYUMOLCS = "gyumolcs"
val COL_ALKOHOL = "alkohol"

class DBHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_FOZO VARCHAR(40), " +
                "$COL_GYUMOLCS VARCHAR(20), " +
                "$COL_ALKOHOL INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertPalinka(p :Palinka) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_FOZO, p.fozo)
        cv.put(COL_GYUMOLCS, p.gyumolcs)
        cv.put(COL_ALKOHOL, p.alkohol)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Nem sikerult a felvetel", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Sikeres felvetel", Toast.LENGTH_SHORT).show()
        }
    }

    fun readPalinka() : ArrayList<Palinka> {
        var list : ArrayList<Palinka> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var id = result.getString(result.getColumnIndex(COL_ID)).toString().toInt()
                var fozo = result.getString(result.getColumnIndex(COL_FOZO)).toString()
                var gyumolcs = result.getString(result.getColumnIndex(COL_GYUMOLCS)).toString()
                var alkohol = result.getString(result.getColumnIndex(COL_ALKOHOL)).toString().toInt()
                var p = Palinka(id, fozo, gyumolcs, alkohol)
                list.add(p)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun findPalinka(fozo :String, gyumolcs :String) : ArrayList<Palinka> {
        var list : ArrayList<Palinka> = ArrayList()

        val db = this.readableDatabase

        val query = "$COL_GYUMOLCS = '$gyumolcs' AND $COL_FOZO = '$fozo'"
        val result = db.query(TABLE_NAME, null, query, null, null, null, null, null)
        if (result.moveToFirst()) {
            do {
                var id = result.getString(result.getColumnIndex(COL_ID)).toString().toInt()
                var fozo = result.getString(result.getColumnIndex(COL_FOZO)).toString()
                var gyumolcs = result.getString(result.getColumnIndex(COL_GYUMOLCS)).toString()
                var alkohol = result.getString(result.getColumnIndex(COL_ALKOHOL)).toString().toInt()
                var p = Palinka(id, fozo, gyumolcs, alkohol)
                list.add(p)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
}