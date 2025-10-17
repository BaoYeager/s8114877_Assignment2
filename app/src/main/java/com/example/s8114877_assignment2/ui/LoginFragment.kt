package com.example.s8114877_assignment2.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.s8114877_assignment2.R

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
        }

        return view
    }
}
