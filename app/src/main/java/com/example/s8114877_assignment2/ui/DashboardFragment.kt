package com.example.s8114877_assignment2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8114877_assignment2.R
import com.example.s8114877_assignment2.ui.Adapter.ProjectAdapter

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Lấy RecyclerView từ layout
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProjects)

        // Thiết lập layoutManager cho RecyclerView (hướng ngang)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Danh sách các project (dữ liệu giả)
        val projectList = listOf(
            Project("Project A"),
            Project("Project B"),
            Project("Project C"),
            Project("Project D"),
            Project("Project E"),
            Project("Project F")
        )

        // Gắn Adapter vào RecyclerView
        recyclerView.adapter = ProjectAdapter(projectList)

        return view
    }
}
