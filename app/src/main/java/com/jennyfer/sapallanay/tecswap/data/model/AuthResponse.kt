package com.jennyfer.sapallanay.tecswap.data.model

data class AuthResponse(
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val name: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val carrera: String
)