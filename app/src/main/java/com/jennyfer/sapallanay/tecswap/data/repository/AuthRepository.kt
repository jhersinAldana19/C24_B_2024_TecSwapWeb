package com.jennyfer.sapallanay.tecswap.data.repository

import com.jennyfer.sapallanay.tecswap.data.model.AuthResponse
import com.jennyfer.sapallanay.tecswap.data.model.LoginRequest
import com.jennyfer.sapallanay.tecswap.data.model.RegisterRequest
import com.jennyfer.sapallanay.tecswap.data.network.ApiService
import retrofit2.Response

class AuthRepository(private val apiService: ApiService) {
    suspend fun registerUser(request: RegisterRequest): Response<AuthResponse> {
        return apiService.registerUser(request)
    }

    suspend fun loginUser(request: LoginRequest): Response<AuthResponse> {
        return apiService.loginUser(request)
    }
}