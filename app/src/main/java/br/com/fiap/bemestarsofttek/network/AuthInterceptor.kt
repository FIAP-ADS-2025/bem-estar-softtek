package br.com.fiap.bemestarsofttek.network

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val context: Context) : Interceptor {
    
    companion object {
        private const val TAG = "AuthInterceptor"
    }
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        
        // Verificar se a resposta é 401 (Unauthorized)
        if (response.code == 401) {
            Log.w(TAG, "Recebido erro 401 - Token inválido ou expirado")
            
            // Fazer logout do usuário
            val authManager = AuthManager(context)
            authManager.logout()
            
            // Emitir evento para redirecionar para login
            AuthEventBus.post(AuthEvent.LogoutRequired)
        }
        
        return response
    }
}

// Event bus simples para comunicação entre interceptor e UI
object AuthEventBus {
    private val listeners = mutableListOf<(AuthEvent) -> Unit>()
    
    fun subscribe(listener: (AuthEvent) -> Unit) {
        listeners.add(listener)
    }
    
    fun unsubscribe(listener: (AuthEvent) -> Unit) {
        listeners.remove(listener)
    }
    
    fun post(event: AuthEvent) {
        listeners.forEach { it(event) }
    }
}

sealed class AuthEvent {
    object LogoutRequired : AuthEvent()
}
