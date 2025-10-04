package br.com.fiap.bemestarsofttek.network.service

import br.com.fiap.bemestarsofttek.network.dto.LoginRequest
import br.com.fiap.bemestarsofttek.network.dto.LoginResponse
import br.com.fiap.bemestarsofttek.network.dto.RegisterRequest
import br.com.fiap.bemestarsofttek.network.dto.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/usuarios/autenticar")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    
    @POST("/usuarios/cadastrar")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}
