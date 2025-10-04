package br.com.fiap.bemestarsofttek.network.dto

data class LoginRequest(
    val email: String,
    val senha: String
)

data class LoginResponse(
    val token: String,
    val email: String,
    val tipo: String
)
