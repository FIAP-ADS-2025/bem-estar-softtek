package br.com.fiap.bemestarsofttek.network.service

import br.com.fiap.bemestarsofttek.network.dto.AssessmentRequest
import br.com.fiap.bemestarsofttek.network.dto.AssessmentResponse
import retrofit2.Response
import retrofit2.http.*

interface AssessmentService {
    @GET("/api/assessments/listar")
    suspend fun getAllAssessments(): Response<List<AssessmentResponse>>
    
    @GET("/api/assessments/listar/{id}")
    suspend fun getAssessmentById(@Path("id") id: String): Response<AssessmentResponse>
    
    @POST("/api/assessments/salvar")
    suspend fun createAssessment(@Body assessment: AssessmentRequest): Response<AssessmentResponse>
    
    @PUT("/api/assessments/atualizar")
    suspend fun updateAssessment(@Body assessment: AssessmentResponse): Response<AssessmentResponse>
    
    @DELETE("/api/assessments/deletar/{id}")
    suspend fun deleteAssessment(@Path("id") id: String): Response<Unit>
}
