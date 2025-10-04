package br.com.fiap.bemestarsofttek.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.bemestarsofttek.model.DailyAssessment
import br.com.fiap.bemestarsofttek.network.dto.AssessmentRequest
import br.com.fiap.bemestarsofttek.network.dto.AssessmentResponse
import br.com.fiap.bemestarsofttek.repository.AssessmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AssessmentViewModel : ViewModel() {
    private val repository = AssessmentRepository()
    
    private val _assessments = MutableStateFlow<List<AssessmentResponse>>(emptyList())
    val assessments: StateFlow<List<AssessmentResponse>> = _assessments.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    fun loadAssessments() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            repository.getAllAssessments()
                .onSuccess { assessmentsList ->
                    _assessments.value = assessmentsList
                }
                .onFailure { exception ->
                    _errorMessage.value = exception.message
                }
            
            _isLoading.value = false
        }
    }
    
    fun submitAssessment(dailyAssessment: DailyAssessment, employeeId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val assessmentRequest = convertToAssessmentRequest(dailyAssessment, employeeId)
            
            repository.createAssessment(assessmentRequest)
                .onSuccess { createdAssessment ->
                    // Adicionar o novo assessment à lista
                    _assessments.value = _assessments.value + createdAssessment
                }
                .onFailure { exception ->
                    _errorMessage.value = exception.message
                }
            
            _isLoading.value = false
        }
    }
    
    private fun convertToAssessmentRequest(
        dailyAssessment: DailyAssessment,
        employeeId: String
    ): AssessmentRequest {
        val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        
        return AssessmentRequest(
            employeeId = employeeId,
            assessmentDate = currentDate,
            emojiChoice = br.com.fiap.bemestarsofttek.network.dto.EmojiChoiceDto(
                displayName = dailyAssessment.emojiChoice?.displayName ?: "",
                emoji = dailyAssessment.emojiChoice?.emoji ?: ""
            ),
            feelingChoice = br.com.fiap.bemestarsofttek.network.dto.FeelingChoiceDto(
                displayName = dailyAssessment.feelingChoice?.displayName ?: ""
            ),
            workloadLevel = br.com.fiap.bemestarsofttek.network.dto.WorkloadLevelDto(
                displayName = dailyAssessment.workloadLevel?.displayName ?: ""
            ),
            workloadAffectsLife = br.com.fiap.bemestarsofttek.network.dto.FrequencyLevelDto(
                displayName = dailyAssessment.workloadAffectsLife?.displayName ?: ""
            ),
            worksOvertime = br.com.fiap.bemestarsofttek.network.dto.FrequencyLevelDto(
                displayName = dailyAssessment.worksOvertime?.displayName ?: ""
            ),
            hasSymptoms = br.com.fiap.bemestarsofttek.network.dto.FrequencyLevelDto(
                displayName = dailyAssessment.hasSymptoms?.displayName ?: ""
            ),
            mentalHealthAffectsWork = br.com.fiap.bemestarsofttek.network.dto.FrequencyLevelDto(
                displayName = dailyAssessment.mentalHealthAffectsWork?.displayName ?: ""
            ),
            relationshipScores = br.com.fiap.bemestarsofttek.network.dto.RelationshipScoresDto(
                boss = dailyAssessment.relationshipWithBoss,
                colleagues = dailyAssessment.relationshipWithColleagues,
                respectFromColleagues = dailyAssessment.respectFromColleagues,
                teamCollaboration = dailyAssessment.teamCollaboration,
                freedomToExpress = dailyAssessment.freedomToExpress,
                teamWelcoming = dailyAssessment.teamWelcoming,
                teamCooperation = dailyAssessment.teamCooperation
            ),
            leadershipScores = br.com.fiap.bemestarsofttek.network.dto.LeadershipScoresDto(
                clearInstructions = dailyAssessment.clearInstructions,
                openCommunication = dailyAssessment.openCommunicationWithLeadership,
                efficientInformation = dailyAssessment.efficientInformation,
                clearGoals = dailyAssessment.clearGoals,
                leadershipCares = dailyAssessment.leadershipCares,
                leadershipAvailable = dailyAssessment.leadershipAvailable,
                comfortableReporting = dailyAssessment.comfortableReporting,
                recognizedByLeadership = dailyAssessment.recognizedByLeadership,
                trustInLeadership = dailyAssessment.trustInLeadership
            ),
            observations = dailyAssessment.observations
        )
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
}
