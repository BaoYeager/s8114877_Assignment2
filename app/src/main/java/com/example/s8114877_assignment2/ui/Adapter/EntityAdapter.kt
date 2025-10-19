package com.example.s8114877_assignment2.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8114877_assignment2.R
import org.json.JSONObject

class EntityAdapter(
    private val entities: List<JSONObject>,
    private val onItemClick: (JSONObject) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textProperty1: TextView = view.findViewById(R.id.textProperty1)
        val textProperty2: TextView = view.findViewById(R.id.textProperty2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]

        val keys = entity.keys().asSequence().filter { it != "description" }.toList()
        if (keys.isNotEmpty()) holder.textProperty1.text = "${keys[0]}: ${entity.getString(keys[0])}"
        if (keys.size > 1) holder.textProperty2.text = "${keys[1]}: ${entity.getString(keys[1])}"

        holder.itemView.setOnClickListener { onItemClick(entity) }
    }

    override fun getItemCount(): Int = entities.size
}
