package com.app.travelchecklist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast
import com.app.travelchecklist.model.Travel
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_checklist.*

class ChecklistActivity : AppCompatActivity() {
    var travel: Travel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        var travelId: String = intent.getStringExtra("travel_id")
        var realm = Realm.getDefaultInstance()
        travel = realm.where(Travel::class.java).equalTo("TravelID", travelId).findFirst()

        if (travel == null)
            throw Exception("Can't find travel id ${travelId}")

        title = "${travel?.country} - ${travel?.city}"
        loadItems()
    }

    private fun loadItems() {
        Toast.makeText(
            this,
            "travel (${travel?.TravelID}): ${travel?.country}/${travel?.city}",
            Toast.LENGTH_LONG)
            .show()
    }

}
