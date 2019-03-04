package com.app.travelchecklist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.app.travelchecklist.model.Travel
import io.realm.Realm
import java.util.*

class NewTravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_travel)

        title = getString(R.string.activity_title_new_travel)

        var spinner = findViewById<Spinner>(R.id.country_list)
        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getCountries())

        var button = findViewById<Button>(R.id.button_create)
        button.setOnClickListener {
            //get country value
            var travelCountry = spinner.selectedItem.toString()

            var city = findViewById<EditText>(R.id.edit_city_name)
            var travelCity = city.text.toString()

            var fromDate = findViewById<EditText>(R.id.edit_start_date)
            var travelDate = fromDate.text.toString()

            if (!checkField(travelCountry, "invalid country"))
                return@setOnClickListener
            if (!checkField(travelCity, "invalid city"))
                return@setOnClickListener
            if (!checkField(travelDate, "invalid travel date"))
                return@setOnClickListener

            var travel = createTravel(travelCountry, travelCity, Date(travelDate))

            var intent = Intent(this, ChecklistActivity::class.java)
            intent.putExtra("travel_id", travel.TravelID)
            startActivity(intent)
            finish()
        }
    }

    private fun createTravel(country: String, city: String, date: Date): Travel {
        var travel = Travel()
        travel.country = country
        travel.city = city
        travel.from = date

        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealm(travel)
        realm.commitTransaction()
        return travel
    }

    private fun checkField(value: String?, toastMessage: String): Boolean {
        if (value.isNullOrEmpty()) {
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun getCountries(): List<String> {
        var countries = mutableListOf<String>()
        Locale.getAvailableLocales().forEach { locale ->
            var c = locale.displayCountry
            if (c.length > 0 && !countries.contains(c))
                countries.add(locale.displayCountry)
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER)
        return countries
    }
}
