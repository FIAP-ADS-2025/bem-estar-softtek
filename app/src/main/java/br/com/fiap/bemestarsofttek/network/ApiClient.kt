package br.com.fiap.bemestarsofttek.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private var context: Context? = null
    
    fun initialize(context: Context) {
        this.context = context
    }
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = when (NetworkConfig.LOG_LEVEL) {
            "NONE" -> HttpLoggingInterceptor.Level.NONE
            "BASIC" -> HttpLoggingInterceptor.Level.BASIC
            "HEADERS" -> HttpLoggingInterceptor.Level.HEADERS
            "BODY" -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.BODY
        }
    }
    
    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(ApiConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(ApiConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(ApiConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        
        // Adicionar interceptors se context estiver disponível
        context?.let { ctx ->
            // TokenInterceptor deve vir antes do AuthInterceptor
            builder.addInterceptor(TokenInterceptor(ctx))
            builder.addInterceptor(AuthInterceptor(ctx))
        }
        
        return builder.build()
    }
    
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}
