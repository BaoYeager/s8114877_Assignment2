package com.example.s8114877_assignment2.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8114877_assignment2.R
import org.json.JSONObject

class DetailAdapter(private val entity: JSONObject) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private val properties = entity.keys().asSequence().map { it to entity.getString(it) }.toList()

    class DetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textKey: TextView = view.findViewById(R.id.textKey)
        val textValue: TextView = view.findViewById(R.id.textValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_property, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val (key, value) = properties[position]
        holder.textKey.text = key
        holder.textValue.text = value
    }

    override fun getItemCount(): Int = properties.size
}
