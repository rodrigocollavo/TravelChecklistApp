package com.app.travelchecklist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.KeyEvent
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.app.travelchecklist.adapter.TravelAdapter
import com.app.travelchecklist.model.Travel
import com.app.travelchecklist.model.TravelItem
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_checklist.*

class ChecklistActivity : AppCompatActivity() {
    var travel: Travel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        var newItemInput = findViewById<TextInputEditText>(R.id.input_new_item)
        newItemInput.setOnEditorActionListener { textView, i, keyEvent ->
            var text = newItemInput.text?.toString()
            if (!text?.isNullOrEmpty()!!) {
                createItem(text!!)
                loadItems()
                newItemInput.text?.clear()
            }
            return@setOnEditorActionListener false
        }

        var travelId: String = intent.getStringExtra("travel_id")
        var realm = Realm.getDefaultInstance()
        travel = realm.where(Travel::class.java).equalTo("TravelID", travelId).findFirst()

        if (travel == null)
            throw Exception("Can't find travel id ${travelId}")

        title = "${travel!!.country} - ${travel!!.city}"
        loadItems()
    }

    private fun createItem(text: String) {
        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        var travelItem = TravelItem()
        travelItem.name = text
        travel!!.items?.add(travelItem)

        realm.commitTransaction()
    }

    private fun loadItems() {
        val listView = findViewById<ListView>(R.id.items_list)

        var items = mutableListOf<String>()
        travel!!.items?.forEach { item -> items.add(item.name!!) }
        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
    }

}
