package br.com.fiap.bemestarsofttek.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class DebugInterceptor : Interceptor {
    
    companion object {
        private const val TAG = "DebugInterceptor"
    }
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
        // Log da requisição
        Log.d(TAG, "=== REQUISIÇÃO HTTP ===")
        Log.d(TAG, "URL: ${request.url}")
        Log.d(TAG, "Method: ${request.method}")
        Log.d(TAG, "Headers: ${request.headers}")
        
        // Log do body da requisição
        if (request.body != null) {
            try {
                val buffer = Buffer()
                request.body!!.writeTo(buffer)
                val bodyString = buffer.readUtf8()
                Log.d(TAG, "Request Body: $bodyString")
                Log.d(TAG, "Request Body Length: ${bodyString.length}")
            } catch (e: IOException) {
                Log.e(TAG, "Erro ao ler body da requisição", e)
            }
        } else {
            Log.w(TAG, "⚠️ REQUEST BODY É NULL!")
        }
        
        // Fazer a requisição
        val response = chain.proceed(request)
        
        // Log da resposta
        Log.d(TAG, "=== RESPOSTA HTTP ===")
        Log.d(TAG, "Status Code: ${response.code}")
        Log.d(TAG, "Headers: ${response.headers}")
        
        // Log do body da resposta
        val responseBody = response.body
        if (responseBody != null) {
            try {
                val source = responseBody.source()
                source.request(Long.MAX_VALUE)
                val buffer = source.buffer
                val bodyString = buffer.clone().readUtf8()
                Log.d(TAG, "Response Body: $bodyString")
            } catch (e: IOException) {
                Log.e(TAG, "Erro ao ler body da resposta", e)
            }
        }
        
        Log.d(TAG, "======================")
        
        return response
    }
}
