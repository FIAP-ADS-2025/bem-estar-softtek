package br.com.fiap.bemestarsofttek.network

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import br.com.fiap.bemestarsofttek.network.dto.LoginRequest
import br.com.fiap.bemestarsofttek.network.dto.LoginResponse
import br.com.fiap.bemestarsofttek.network.dto.RegisterRequest
import br.com.fiap.bemestarsofttek.network.dto.RegisterResponse
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
                    Log.d("AuthManager", "Token recebido: ${loginResponse.token}")
                    saveToken(loginResponse.token)
                    saveEmail(loginResponse.email)
                    Log.d("AuthManager", "Token salvo com sucesso")
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
    
    suspend fun register(name: String, email: String, password: String): Result<RegisterResponse> {
        return try {
            val registerRequest = RegisterRequest(name, email, password)
            val response: Response<RegisterResponse> = authService.register(registerRequest)
            
            if (response.isSuccessful) {
                val registerResponse = response.body()
                if (registerResponse != null) {
                    Log.d("AuthManager", "Usuário cadastrado: ${registerResponse.email}")
                    Result.success(registerResponse)
                } else {
                    Result.failure(Exception("Resposta vazia do servidor"))
                }
            } else {
                Result.failure(Exception("Erro no cadastro: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getToken(): String? {
        val token = prefs.getString(TOKEN_KEY, null)
        Log.d("AuthManager", "Token recuperado: ${token?.take(20)}...")
        return token
    }
    
    fun getEmail(): String? {
        val email = prefs.getString(EMAIL_KEY, null)
        Log.d("AuthManager", "Email recuperado: $email")
        return email
    }
    
    fun isLoggedIn(): Boolean {
        val loggedIn = getToken() != null
        Log.d("AuthManager", "Usuário logado: $loggedIn")
        return loggedIn
    }
    
    fun logout() {
        Log.d("AuthManager", "Fazendo logout...")
        prefs.edit().clear().apply()
        Log.d("AuthManager", "Logout concluído")
    }
    
    private fun saveToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }
    
    private fun saveEmail(email: String) {
        prefs.edit().putString(EMAIL_KEY, email).apply()
    }
}
