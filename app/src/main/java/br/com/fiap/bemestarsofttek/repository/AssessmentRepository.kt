package br.com.fiap.bemestarsofttek.repository

import android.util.Log
import br.com.fiap.bemestarsofttek.network.ApiClient
import br.com.fiap.bemestarsofttek.network.dto.AssessmentRequest
import br.com.fiap.bemestarsofttek.network.dto.AssessmentResponse
import br.com.fiap.bemestarsofttek.network.service.AssessmentService

class AssessmentRepository {
    private val assessmentService: AssessmentService = ApiClient.createService(AssessmentService::class.java)
    
    suspend fun getAllAssessments(): Result<List<AssessmentResponse>> {
        return try {
            val response = assessmentService.getAllAssessments()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Erro ao buscar assessments: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getAssessmentById(id: String): Result<AssessmentResponse> {
        return try {
            val response = assessmentService.getAssessmentById(id)
            if (response.isSuccessful) {
                val assessment = response.body()
                if (assessment != null) {
                    Result.success(assessment)
                } else {
                    Result.failure(Exception("Assessment não encontrado"))
                }
            } else {
                Result.failure(Exception("Erro ao buscar assessment: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun createAssessment(assessment: AssessmentRequest): Result<AssessmentResponse> {
        return try {
            Log.d("AssessmentRepository", "Enviando assessment: $assessment")
            val response = assessmentService.createAssessment(assessment)
            Log.d("AssessmentRepository", "Resposta recebida - Código: ${response.code()}")
            Log.d("AssessmentRepository", "Resposta body: ${response.body()}")
            Log.d("AssessmentRepository", "Resposta error: ${response.errorBody()?.string()}")
            
            if (response.isSuccessful) {
                val createdAssessment = response.body()
                if (createdAssessment != null) {
                    Log.d("AssessmentRepository", "Assessment criado com sucesso: ${createdAssessment.id}")
                    Result.success(createdAssessment)
                } else {
                    Log.e("AssessmentRepository", "Resposta vazia do servidor")
                    Result.failure(Exception("Resposta vazia do servidor"))
                }
            } else {
                Log.e("AssessmentRepository", "Erro HTTP: ${response.code()} - ${response.errorBody()?.string()}")
                Result.failure(Exception("Erro ao criar assessment: ${response.code()} - ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Log.e("AssessmentRepository", "Erro na requisição", e)
            Result.failure(e)
        }
    }
    
    suspend fun updateAssessment(assessment: AssessmentResponse): Result<AssessmentResponse> {
        return try {
            val response = assessmentService.updateAssessment(assessment)
            if (response.isSuccessful) {
                val updatedAssessment = response.body()
                if (updatedAssessment != null) {
                    Result.success(updatedAssessment)
                } else {
                    Result.failure(Exception("Resposta vazia do servidor"))
                }
            } else {
                Result.failure(Exception("Erro ao atualizar assessment: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun deleteAssessment(id: String): Result<Unit> {
        return try {
            val response = assessmentService.deleteAssessment(id)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Erro ao deletar assessment: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
