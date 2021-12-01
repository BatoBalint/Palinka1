package com.example.palinka1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AdatFelvetelActivity : AppCompatActivity() {
    lateinit var btnVissza :Button
    lateinit var btnFelvetel :Button
    lateinit var ETFozo :EditText
    lateinit var ETGyumolcs :EditText
    lateinit var ETAlkohol :EditText
    lateinit var db :DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adat_felvetel)

        init()
    }

    fun init() {
        btnFelvetel = findViewById(R.id.btnFelvetelAtFelvetel)
        btnVissza = findViewById(R.id.btnVisszaAtFelvetel)
        ETFozo = findViewById(R.id.fozoET)
        ETGyumolcs = findViewById(R.id.gyumolcsET)
        ETAlkohol = findViewById(R.id.alkoholET)

        btnVissza.setOnClickListener { visszaBtnClick() }
        btnFelvetel.setOnClickListener { felvetelBtnClick() }

        db = DBHelper(this)
    }

    fun visszaBtnClick() {
        var main = Intent(this, MainActivity::class.java)
        startActivity(main)
        finish()
    }

    fun felvetelBtnClick() {
        var fozo = ETFozo.text.toString()
        var gyumolcs = ETGyumolcs.text.toString()
        var alkohol = ETAlkohol.text.toString()
        if (alkohol == "") alkohol = "0"

        if (fozo.trim() != "" && gyumolcs.trim() != "") {
            var p = Palinka(fozo, gyumolcs, alkohol.toInt())
            db.insertPalinka(p)
        } else {
            Toast.makeText(this, "Egyik mezo sem maradthat ures", Toast.LENGTH_LONG).show()
        }
    }
}