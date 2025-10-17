package com.example.s8114877_assignment2.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8114877_assignment2.R
//import com.example.s8114877_assignment2.adapter.CoAuthorAdapter

class DiscoverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerCoAuthors)
//
//        val coAuthors = listOf(
//            CoAuthor(Color.parseColor("#B0B0B0"), "Story A", "Author A"),
//            CoAuthor(Color.parseColor("#A0A0A0"), "Story B", "Author B"),
//            CoAuthor(Color.parseColor("#909090"), "Story C", "Author C"),
//            CoAuthor(Color.parseColor("#A0A0A0"), "Story D", "Author D"),
//            CoAuthor(Color.parseColor("#909090"), "Story E", "Author E")

//        )

//        recyclerView.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.adapter = CoAuthorAdapter(coAuthors)

        return view
    }
}
