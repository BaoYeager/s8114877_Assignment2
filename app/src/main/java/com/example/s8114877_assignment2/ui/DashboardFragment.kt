package com.example.s8114877_assignment2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8114877_assignment2.R
import com.example.s8114877_assignment2.ui.Adapter.EntityAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerEntities)

        val keypass = arguments?.getString("keypass")
        if (keypass.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Missing keypass!", Toast.LENGTH_SHORT).show()
            return view
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val url = URL("https://nit3213api.onrender.com/dashboard/$keypass")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                conn.setRequestProperty("Accept", "application/json")
                conn.connectTimeout = 10000
                conn.readTimeout = 10000
                conn.doInput = true

                val responseCode = conn.responseCode
                val responseText = if (responseCode == 200) {
                    conn.inputStream.bufferedReader(Charsets.UTF_8).readText()
                } else {
                    conn.errorStream?.bufferedReader(Charsets.UTF_8)?.readText()
                        ?: "Unknown error"
                }

                Log.d("DashboardDebug", "Response code = $responseCode")
                Log.d("DashboardDebug", "Response text = $responseText")

                if (responseCode == 200) {
                    val jsonResponse = JSONObject(responseText)
                    val entities = jsonResponse.getJSONArray("entities")

                    val list = mutableListOf<JSONObject>()
                    for (i in 0 until entities.length()) {
                        list.add(entities.getJSONObject(i))
                    }

                    withContext(Dispatchers.Main) {
                        recyclerView.adapter = EntityAdapter(list) { entity ->
                            val jsonString = entity.toString()
                            val action = DashboardFragmentDirections
                                .actionDashboardFragmentToDetailFragment(jsonString)
                            findNavController().navigate(action)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error: $responseText", Toast.LENGTH_LONG).show()
                    }
                }

                conn.disconnect()
            } catch (e: Exception) {
                Log.e("DashboardDebug", "Exception: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        return view
    }
}





//package com.example.s8114877_assignment2.ui
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.s8114877_assignment2.R
//import com.example.s8114877_assignment2.ui.Adapter.ProjectAdapter
//
//class DashboardFragment : Fragment() {
//
//    private lateinit var keypass: String
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
//
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerProjects)
//        keypass = DashboardFragmentArgs.fromBundle(requireArguments()).keypass
//
//        Toast.makeText(requireContext(), "Keypass: $keypass", Toast.LENGTH_SHORT).show()
//
//        recyclerView.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//
//        val projectList = listOf(
//            Project("Project A"),
//            Project("Project B"),
//            Project("Project C"),
//            Project("Project D"),
//            Project("Project E"),
//            Project("Project F")
//        )
//
//        val adapter = ProjectAdapter(projectList) { selectedProject ->
//            val action = DashboardFragmentDirections
//                .actionDashboardFragmentToDetailFragment(selectedProject.name)
//            findNavController().navigate(action)
//        }
//
//        recyclerView.adapter = adapter
//        return view
//    }
//}
