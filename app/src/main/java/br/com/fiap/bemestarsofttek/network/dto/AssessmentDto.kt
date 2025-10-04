package br.com.fiap.bemestarsofttek.network.dto

import br.com.fiap.bemestarsofttek.network.deserializer.AssessmentResponseDeserializer
import com.google.gson.annotations.JsonAdapter

data class AssessmentRequest(
    val employeeId: String,
    val assessmentDate: String, // ISO 8601 format
    val emojiChoice: EmojiChoiceDto,
    val feelingChoice: FeelingChoiceDto,
    val workloadLevel: WorkloadLevelDto,
    val workloadAffectsLife: FrequencyLevelDto,
    val worksOvertime: FrequencyLevelDto,
    val hasSymptoms: FrequencyLevelDto,
    val mentalHealthAffectsWork: FrequencyLevelDto,
    val relationshipScores: RelationshipScoresDto,
    val leadershipScores: LeadershipScoresDto,
    val observations: String
)

@JsonAdapter(AssessmentResponseDeserializer::class)
data class AssessmentResponse(
    val id: String,
    val employeeId: String,
    val assessmentDate: String,
    val emojiChoice: EmojiChoiceDto,
    val feelingChoice: FeelingChoiceDto,
    val workloadLevel: WorkloadLevelDto,
    val workloadAffectsLife: FrequencyLevelDto,
    val worksOvertime: FrequencyLevelDto,
    val hasSymptoms: FrequencyLevelDto,
    val mentalHealthAffectsWork: FrequencyLevelDto,
    val relationshipScores: RelationshipScoresDto,
    val leadershipScores: LeadershipScoresDto,
    val observations: String,
    val assessmentScore: Int,
    val riskLevel: String,
    val createdAt: String,
    val updatedAt: String
)


data class EmojiChoiceDto(
    val displayName: String,
    val emoji: String
)

data class FeelingChoiceDto(
    val displayName: String
)

data class WorkloadLevelDto(
    val displayName: String
)

data class FrequencyLevelDto(
    val displayName: String
)

data class RelationshipScoresDto(
    val boss: Int,
    val colleagues: Int,
    val respectFromColleagues: Int,
    val teamCollaboration: Int,
    val freedomToExpress: Int,
    val teamWelcoming: Int,
    val teamCooperation: Int
)

data class LeadershipScoresDto(
    val clearInstructions: Int,
    val openCommunication: Int,
    val efficientInformation: Int,
    val clearGoals: Int,
    val leadershipCares: Int,
    val leadershipAvailable: Int,
    val comfortableReporting: Int,
    val recognizedByLeadership: Int,
    val trustInLeadership: Int
)
