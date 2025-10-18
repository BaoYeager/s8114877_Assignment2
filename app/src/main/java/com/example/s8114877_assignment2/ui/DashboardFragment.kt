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
import com.example.s8114877_assignment2.ui.Adapter.ProjectAdapter

class DashboardFragment : Fragment() {

    private lateinit var keypass: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProjects)
        keypass = DashboardFragmentArgs.fromBundle(requireArguments()).keypass

        Toast.makeText(requireContext(), "Keypass: $keypass", Toast.LENGTH_SHORT).show()

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val projectList = listOf(
            Project("Project A"),
            Project("Project B"),
            Project("Project C"),
            Project("Project D"),
            Project("Project E"),
            Project("Project F")
        )

        val adapter = ProjectAdapter(projectList) { selectedProject ->
            val action = DashboardFragmentDirections
                .actionDashboardFragmentToDetailFragment(selectedProject.name)
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter
        return view
    }
}
