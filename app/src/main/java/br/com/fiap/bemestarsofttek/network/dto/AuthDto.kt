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

data class RegisterRequest(
    val nome: String,
    val email: String,
    val senha: String
)

data class RegisterResponse(
    val id: String,
    val nome: String,
    val email: String,
    val message: String
)
