package com.example.palinka1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var btnFelvetel :Button
    lateinit var btnKeres :Button
    lateinit var btnListaz :Button
    lateinit var listView :ListView
    lateinit var palinkaList :ArrayList<Palinka>
    lateinit var adap :ListAdapter
    lateinit var db :DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    fun init() {
        btnFelvetel = findViewById(R.id.btnPalinkaFelvetele)
        btnKeres = findViewById(R.id.btnPalinkaKeresese)
        btnListaz = findViewById(R.id.btnPalinkaListazasa)
        listView = findViewById(R.id.listView)

        palinkaList = ArrayList()
        adap = ArrayAdapter(this, R.layout.list_item_white_text, R.id.list_content, palinkaList)
        listView.adapter = adap

        btnFelvetel.setOnClickListener { felveszBtnClick() }
        btnKeres.setOnClickListener { keresBtnClick() }
        btnListaz.setOnClickListener { listazasBtnClick() }

        db = DBHelper(this)
    }

    fun felveszBtnClick() {
        var felvesz = Intent(this, AdatFelvetelActivity::class.java)
        startActivity(felvesz)
        finish()
    }

    fun keresBtnClick() {
        var keres = Intent(this, AdatKeresActivity::class.java)
        startActivity(keres)
        finish()
    }

    fun listazasBtnClick() {
        palinkaList.clear()
        var a = db.readPalinka()
        for (i in 0..a.size-1) {
            palinkaList.add(a.get(i))
        }
        listView.adapter = adap
    }
}