package br.com.fiap.bemestarsofttek.network

import android.content.Context
import android.content.SharedPreferences
import br.com.fiap.bemestarsofttek.network.dto.LoginRequest
import br.com.fiap.bemestarsofttek.network.dto.LoginResponse
import br.com.fiap.bemestarsofttek.network.service.AuthService
import retrofit2.Response

class AuthManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private val authService: AuthService = ApiClient.createService(AuthService::class.java)
    
    companion object {
        private const val TOKEN_KEY = "jwt_token"
        private const val EMAIL_KEY = "user_email"
    }
    
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val loginRequest = LoginRequest(email, password)
            val response: Response<LoginResponse> = authService.login(loginRequest)
            
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    saveToken(loginResponse.token)
                    saveEmail(loginResponse.email)
                    Result.success(loginResponse)
                } else {
                    Result.failure(Exception("Resposta vazia do servidor"))
                }
            } else {
                Result.failure(Exception("Erro de autenticação: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }
    
    fun getEmail(): String? {
        return prefs.getString(EMAIL_KEY, null)
    }
    
    fun isLoggedIn(): Boolean {
        return getToken() != null
    }
    
    fun logout() {
        prefs.edit().clear().apply()
    }
    
    private fun saveToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }
    
    private fun saveEmail(email: String) {
        prefs.edit().putString(EMAIL_KEY, email).apply()
    }
}
