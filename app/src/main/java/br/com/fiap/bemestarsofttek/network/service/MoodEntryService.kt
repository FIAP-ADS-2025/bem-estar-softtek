package br.com.fiap.bemestarsofttek.network.service

import br.com.fiap.bemestarsofttek.network.dto.MoodEntryRequest
import br.com.fiap.bemestarsofttek.network.dto.MoodEntryResponse
import retrofit2.Response
import retrofit2.http.*

interface MoodEntryService {
    @GET("/api/mood-entries/listar")
    suspend fun getAllMoodEntries(@Header("Authorization") token: String): Response<List<MoodEntryResponse>>
    
    @GET("/api/mood-entries/listar/{id}")
    suspend fun getMoodEntryById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<MoodEntryResponse>
    
    @GET("/api/mood-entries/listar-por-data")
    suspend fun getMoodEntriesByDateRange(
        @Header("Authorization") token: String,
        @Query("dataInicio") startDate: String,
        @Query("dataFinal") endDate: String
    ): Response<List<MoodEntryResponse>>
    
    @POST("/api/mood-entries/salvar")
    suspend fun createMoodEntry(
        @Header("Authorization") token: String,
        @Body moodEntry: MoodEntryRequest
    ): Response<MoodEntryResponse>
    
    @PUT("/api/mood-entries/atualizar")
    suspend fun updateMoodEntry(
        @Header("Authorization") token: String,
        @Body moodEntry: MoodEntryResponse
    ): Response<MoodEntryResponse>
    
    @DELETE("/api/mood-entries/deletar/{id}")
    suspend fun deleteMoodEntry(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>
}
