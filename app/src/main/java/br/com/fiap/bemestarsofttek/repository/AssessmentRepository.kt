package br.com.fiap.bemestarsofttek.repository

import br.com.fiap.bemestarsofttek.network.ApiClient
import br.com.fiap.bemestarsofttek.network.dto.AssessmentRequest
import br.com.fiap.bemestarsofttek.network.dto.AssessmentResponse
import br.com.fiap.bemestarsofttek.network.service.AssessmentService

class AssessmentRepository {
    private val assessmentService: AssessmentService = ApiClient.createService()
    
    suspend fun getAllAssessments(token: String): Result<List<AssessmentResponse>> {
        return try {
            val response = assessmentService.getAllAssessments("Bearer $token")
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Erro ao buscar assessments: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getAssessmentById(token: String, id: String): Result<AssessmentResponse> {
        return try {
            val response = assessmentService.getAssessmentById("Bearer $token", id)
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
    
    suspend fun createAssessment(token: String, assessment: AssessmentRequest): Result<AssessmentResponse> {
        return try {
            val response = assessmentService.createAssessment("Bearer $token", assessment)
            if (response.isSuccessful) {
                val createdAssessment = response.body()
                if (createdAssessment != null) {
                    Result.success(createdAssessment)
                } else {
                    Result.failure(Exception("Resposta vazia do servidor"))
                }
            } else {
                Result.failure(Exception("Erro ao criar assessment: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateAssessment(token: String, assessment: AssessmentResponse): Result<AssessmentResponse> {
        return try {
            val response = assessmentService.updateAssessment("Bearer $token", assessment)
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
    
    suspend fun deleteAssessment(token: String, id: String): Result<Unit> {
        return try {
            val response = assessmentService.deleteAssessment("Bearer $token", id)
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
