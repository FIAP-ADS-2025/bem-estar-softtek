package br.com.fiap.bemestarsofttek.network

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenInterceptor(private val context: Context) : Interceptor {
    
    companion object {
        private const val TAG = "TokenInterceptor"
    }
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        
        // Verificar se a requisição já tem Authorization header
        val hasAuthHeader = originalRequest.header("Authorization") != null
        
        if (!hasAuthHeader) {
            // Adicionar token automaticamente se não tiver header de auth
            val authManager = AuthManager(context)
            val token = authManager.getToken()
            
            if (token != null) {
                Log.d(TAG, "Adicionando token automaticamente à requisição")
                val newRequest = originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                
                return chain.proceed(newRequest)
            } else {
                Log.d(TAG, "Nenhum token disponível para adicionar à requisição")
            }
        }
        
        return chain.proceed(originalRequest)
    }
}
