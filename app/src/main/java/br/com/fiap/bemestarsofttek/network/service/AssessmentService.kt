package br.com.fiap.bemestarsofttek.network.service

import br.com.fiap.bemestarsofttek.network.dto.AssessmentRequest
import br.com.fiap.bemestarsofttek.network.dto.AssessmentResponse
import retrofit2.Response
import retrofit2.http.*

interface AssessmentService {
    @GET("/api/assessments/listar")
    suspend fun getAllAssessments(@Header("Authorization") token: String): Response<List<AssessmentResponse>>
    
    @GET("/api/assessments/listar/{id}")
    suspend fun getAssessmentById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<AssessmentResponse>
    
    @POST("/api/assessments/salvar")
    suspend fun createAssessment(
        @Header("Authorization") token: String,
        @Body assessment: AssessmentRequest
    ): Response<AssessmentResponse>
    
    @PUT("/api/assessments/atualizar")
    suspend fun updateAssessment(
        @Header("Authorization") token: String,
        @Body assessment: AssessmentResponse
    ): Response<AssessmentResponse>
    
    @DELETE("/api/assessments/deletar/{id}")
    suspend fun deleteAssessment(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>
}
