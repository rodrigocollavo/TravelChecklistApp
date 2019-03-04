package com.app.travelchecklist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.app.travelchecklist.adapter.TravelAdapter
import com.app.travelchecklist.model.Travel
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        title = getString(R.string.app_name)

        fab.setOnClickListener { _ ->
            intent = Intent(this, NewTravelActivity::class.java)
            this.startActivity(intent)
        }

        loadTravels()
    }

    private fun loadTravels() {
        val realm = Realm.getDefaultInstance()
        val travelResults = realm.where(Travel::class.java).findAll()
        val travelList = travelResults.toList()

        val listView = findViewById<ListView>(R.id.travel_list)
        listView.adapter = TravelAdapter(this, travelList)

        listView.setOnItemClickListener({ adapterView, view, i, l ->
            var intent = Intent(this, ChecklistActivity::class.java)
            intent.putExtra("travel_id", travelList[i].TravelID)
            startActivity(intent)
        })
    }
}
