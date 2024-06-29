package com.jennyfer.sapallanay.tecswap.data.model


data class RegisterRequest(
    val name: String,
    val lastname: String,
    val phone: String,
    val email: String,
    val password: String,
    val carrera: String,
)