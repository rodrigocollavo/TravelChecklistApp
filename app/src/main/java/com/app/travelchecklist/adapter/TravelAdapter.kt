package com.app.travelchecklist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app.travelchecklist.R
import com.app.travelchecklist.model.Travel

class TravelAdapter(var context: Context, var travels: List<Travel>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflater = LayoutInflater.from(context)
        val view: View
        val holder: TravelViewHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.adapter_travel, parent, false)
            var country = view.findViewById<TextView>(R.id.title_country)
            var city = view.findViewById<TextView>(R.id.title_city)
            var date = view.findViewById<TextView>(R.id.title_date)
            var itemsAmount = view.findViewById<TextView>(R.id.items_amount)
            holder = TravelViewHolder(country, city, date, itemsAmount)
            view.setTag(holder)
        }
        else {
            view = convertView
            holder = convertView.getTag() as TravelViewHolder
        }

        val travel = travels[position]
        holder.country.text = travel.country
        holder.city.text = travel.city
        holder.date.text = travel.from.toString()
        holder.itemsAmount.text = "${travel.items?.size}/${travel.items?.size}"

        return view
    }
    override fun getItem(i: Int): Any {
        return travels[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return travels.size
    }

    private class TravelViewHolder(val country: TextView, val city: TextView, val date: TextView, val itemsAmount: TextView)
}
