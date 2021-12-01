package com.example.palinka1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AdatKeresActivity : AppCompatActivity() {
    lateinit var btnVissza :Button
    lateinit var btnKeres :Button
    lateinit var ETFozo :EditText
    lateinit var ETGyumolcs :EditText
    lateinit var textView :TextView
    lateinit var db :DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adat_keres)

        init()
    }

    fun init() {
        btnKeres = findViewById(R.id.btnKeresesAtKeres)
        btnVissza = findViewById(R.id.btnVisszaAtKeres)
        ETFozo = findViewById(R.id.fozoETAtKeres)
        ETGyumolcs = findViewById(R.id.gyumolcsETAtKeres)
        textView = findViewById(R.id.textView)

        db = DBHelper(this)

        btnVissza.setOnClickListener { visszaBtnClick() }
        btnKeres.setOnClickListener { keresBtnClick() }
    }

    fun visszaBtnClick() {
        var main = Intent(this, MainActivity::class.java)
        startActivity(main)
        finish()
    }

    fun keresBtnClick() {
        var text = ""
        var fozo = ETFozo.text.toString()
        var gyumolcs = ETGyumolcs.text.toString()
        if (fozo.trim() != "" && gyumolcs.trim() != "") {
            var a = db.findPalinka(fozo, gyumolcs)
            for (i in 0..a.size-1) {
                text += "${a[i]}\n"
            }
            textView.text = text
        } else {
            Toast.makeText(this, "Egyik mezo sem lehet ures", Toast.LENGTH_SHORT).show()
        }
    }
}