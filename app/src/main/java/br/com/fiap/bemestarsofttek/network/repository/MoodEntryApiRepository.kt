package br.com.fiap.bemestarsofttek.network.repository

import android.util.Log
import br.com.fiap.bemestarsofttek.network.ApiClient
import br.com.fiap.bemestarsofttek.network.ApiConfig
import br.com.fiap.bemestarsofttek.network.dto.MoodEntryRequest
import br.com.fiap.bemestarsofttek.network.dto.MoodEntryResponse
import br.com.fiap.bemestarsofttek.network.service.MoodEntryService
import com.google.gson.Gson

class MoodEntryApiRepository {
    private val moodEntryService: MoodEntryService = ApiClient.createService(MoodEntryService::class.java)
    private val gson = Gson()
    
    companion object {
        private const val TAG = "MoodEntryApiRepository"
    }
    
    suspend fun createMoodEntry(token: String, moodEntry: MoodEntryRequest): Result<MoodEntryResponse> {
        return try {
            Log.d(TAG, "🔥🔥🔥 INICIANDO ENVIO DE MOOD ENTRY 🔥🔥🔥")
            Log.d(TAG, "Token: Bearer ${token.take(20)}...")
            Log.d(TAG, "MoodEntryRequest: $moodEntry")
            Log.d(TAG, "MoodEntryRequest JSON: ${gson.toJson(moodEntry)}")
            Log.d(TAG, "URL da API: ${ApiConfig.BASE_URL}/api/mood-entries/salvar")
            Log.d(TAG, "=====================================")
            
            val response = moodEntryService.createMoodEntry("Bearer $token", moodEntry)
            
            Log.d(TAG, "🔥🔥🔥 RESPOSTA DA API 🔥🔥🔥")
            Log.d(TAG, "Código HTTP: ${response.code()}")
            Log.d(TAG, "Headers: ${response.headers()}")
            Log.d(TAG, "Body: ${response.body()}")
            Log.d(TAG, "Error Body: ${response.errorBody()?.string()}")
            Log.d(TAG, "======================")
            
            if (response.isSuccessful) {
                val createdMoodEntry = response.body()
                if (createdMoodEntry != null) {
                    Log.d(TAG, "✅ Mood entry criada com sucesso!")
                    Result.success(createdMoodEntry)
                } else {
                    Log.e(TAG, "❌ Resposta vazia do servidor")
                    Result.failure(Exception("Resposta vazia do servidor"))
                }
            } else {
                Log.e(TAG, "❌ Erro HTTP: ${response.code()} - ${response.errorBody()?.string()}")
                Result.failure(Exception("Erro ao criar mood entry: ${response.code()} - ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "❌ Erro na requisição", e)
            Result.failure(e)
        }
    }
}
