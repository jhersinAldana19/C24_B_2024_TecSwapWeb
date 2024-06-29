package com.jennyfer.sapallanay.tecswap.data.network

import com.jennyfer.sapallanay.tecswap.data.model.AuthResponse
import com.jennyfer.sapallanay.tecswap.data.model.LoginRequest
import com.jennyfer.sapallanay.tecswap.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
//    @GET("productos")
//    suspend fun getProductos(): List<Producto>
//
//    @POST("producto")
//    suspend fun createProducto(@Body producto: Producto): Response<Producto>

    @POST("register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): Response<AuthResponse>


}