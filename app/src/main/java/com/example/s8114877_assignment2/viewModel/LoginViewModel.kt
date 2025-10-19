package com.example.s8114877_assignment2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginState = MutableStateFlow<Result<String>?>(null)
    val loginState: StateFlow<Result<String>?> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
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

                OutputStreamWriter(conn.outputStream, Charsets.UTF_8).use { it.write(jsonBody) }

                val responseCode = conn.responseCode
                val responseText = BufferedReader(
                    InputStreamReader(
                        if (responseCode in 200..299) conn.inputStream else conn.errorStream,
                        Charsets.UTF_8
                    )
                ).readText()

                if (responseCode == 200) {
                    val jsonResponse = JSONObject(responseText)
                    val keypass = jsonResponse.getString("keypass")
                    _loginState.value = Result.success(keypass)
                } else {
                    _loginState.value = Result.failure(Exception("Error: $responseText"))
                }

            } catch (e: Exception) {
                _loginState.value = Result.failure(e)
            } finally {
                conn?.disconnect()
            }
        }
    }
}
