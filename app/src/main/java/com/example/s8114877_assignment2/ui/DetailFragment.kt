package com.example.s8114877_assignment2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8114877_assignment2.R
import com.example.s8114877_assignment2.ui.Adapter.DetailAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val entityJson = arguments?.getString("entityJson")
        if (entityJson.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "No entity data received", Toast.LENGTH_SHORT).show()
            return view
        }

        val entity = JSONObject(entityJson)

        // Gán RecyclerView hiển thị các property
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDetail)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DetailAdapter(entity)

        // Xử lý bottom navigation
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.detailBottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_back -> {
                    findNavController().navigateUp() // Quay lại Dashboard
                    true
                }
                else -> false
            }
        }

        return view
    }
}
