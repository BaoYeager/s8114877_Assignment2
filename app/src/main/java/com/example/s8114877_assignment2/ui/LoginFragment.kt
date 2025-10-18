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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val usernameInput = view.findViewById<EditText>(R.id.editTextUsername)
        val passwordInput = view.findViewById<EditText>(R.id.editTextPassword)
        val loginButton = view.findViewById<Button>(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            lifecycleScope.launch {
                val result = withContext(Dispatchers.IO) {
                    var conn: HttpURLConnection? = null
                    try {
                        val url = URL("https://nit3213api.onrender.com/footscray/auth")
                        conn = (url.openConnection() as HttpURLConnection).apply {
                            requestMethod = "POST"
                            setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                            setRequestProperty("Accept", "application/json")
                            readTimeout = 15000
                            connectTimeout = 15000
                            doOutput = true
                            doInput = true
                        }

                        val jsonBody = JSONObject().apply {
                            put("username", username)
                            put("password", password)
                        }.toString()

                        OutputStreamWriter(conn.outputStream, Charsets.UTF_8).use { w ->
                            w.write(jsonBody)
                        }

                        val responseCode = conn.responseCode
                        val responseText = run {
                            val stream = if (responseCode in 200..299) conn.inputStream else conn.errorStream
                            if (stream != null) {
                                BufferedReader(InputStreamReader(stream, Charsets.UTF_8)).use { br ->
                                    br.readText()
                                }
                            } else ""
                        }

                        if (responseCode == 200) {
                            val jsonResponse = JSONObject(responseText)
                            val keypass = jsonResponse.getString("keypass")
                            Result.success(keypass)
                        } else {
                            Result.failure(RuntimeException(responseText.ifBlank { "Unknown error" }))
                        }
                    } catch (e: Exception) {
                        Result.failure(e)
                    } finally {
                        conn?.disconnect()
                    }
                }

                result.onSuccess { keypass ->
                    val action = LoginFragmentDirections
                        .actionLoginFragmentToDashboardFragment(keypass)
                    findNavController().navigate(action)
                }.onFailure { e ->
                    Toast.makeText(
                        requireContext(),
                        "Login failed: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }
}
