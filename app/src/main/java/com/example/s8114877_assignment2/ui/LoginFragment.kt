package com.example.s8114877_assignment2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.s8114877_assignment2.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.s8114877_assignment2.viewModel.LoginViewModel



@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val usernameInput = view.findViewById<EditText>(R.id.editTextUsername)
        val passwordInput = view.findViewById<EditText>(R.id.editTextPassword)
        val loginButton = view.findViewById<Button>(R.id.buttonLogin)

        // Observe state
        lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { result ->
                result?.onSuccess { keypass ->
                    val action = LoginFragmentDirections
                        .actionLoginFragmentToDashboardFragment(keypass)
                    findNavController().navigate(action)
                }?.onFailure {
                    Toast.makeText(requireContext(), "Login failed: ${it.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            viewModel.login(username, password)
        }

        return view
    }
}
