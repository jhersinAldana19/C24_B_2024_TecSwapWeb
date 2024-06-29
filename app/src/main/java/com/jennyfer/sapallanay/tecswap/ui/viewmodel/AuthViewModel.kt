package com.jennyfer.sapallanay.tecswap.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jennyfer.sapallanay.tecswap.data.model.AuthResponse
import com.jennyfer.sapallanay.tecswap.data.model.LoginRequest
import com.jennyfer.sapallanay.tecswap.data.model.RegisterRequest
import com.jennyfer.sapallanay.tecswap.data.repository.AuthRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val lastname = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val carrera = MutableLiveData<String>()

    fun registerUser(request: RegisterRequest, context: Context, callback: (Response<AuthResponse>) -> Unit) {
        viewModelScope.launch {
            val response = repository.registerUser(request)
            callback(response)
        }
    }

    fun loginUser(request: LoginRequest, context: Context, callback: (Response<AuthResponse>) -> Unit) {
        viewModelScope.launch {
            val response = repository.loginUser(request)
            if (response.isSuccessful) {
                val token = response.body()?.token
                if (token != null) {
                    Log.d("AuthViewModel", "Token received: $token")
                    saveToken(context, token)
                } else {
                    Log.d("AuthViewModel", "Token is null")
                }
            }
            callback(response)
        }
    }

    private fun saveToken(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("my_app_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("auth_token", token)
            apply()
        }
        Log.d("AuthViewModel", "Token saved: $token")
    }
}
