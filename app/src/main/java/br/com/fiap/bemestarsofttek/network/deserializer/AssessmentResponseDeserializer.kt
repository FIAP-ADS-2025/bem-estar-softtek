package br.com.fiap.bemestarsofttek.network.deserializer

import br.com.fiap.bemestarsofttek.network.dto.AssessmentResponse
import br.com.fiap.bemestarsofttek.network.dto.EmojiChoiceDto
import br.com.fiap.bemestarsofttek.network.dto.FeelingChoiceDto
import br.com.fiap.bemestarsofttek.network.dto.WorkloadLevelDto
import br.com.fiap.bemestarsofttek.network.dto.FrequencyLevelDto
import br.com.fiap.bemestarsofttek.network.dto.RelationshipScoresDto
import br.com.fiap.bemestarsofttek.network.dto.LeadershipScoresDto
import com.google.gson.*
import java.lang.reflect.Type

class AssessmentResponseDeserializer : JsonDeserializer<AssessmentResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): AssessmentResponse {
        if (json == null || !json.isJsonObject) {
            throw JsonParseException("Invalid JSON for AssessmentResponse")
        }
        
        val obj = json.asJsonObject
        
        // Extrair ID de forma robusta
        val id = extractId(obj)
        
        // Deserializar outros campos normalmente
        val gson = Gson()
        
        return AssessmentResponse(
            id = id,
            employeeId = obj.get("employeeId")?.asString ?: "",
            assessmentDate = obj.get("assessmentDate")?.asString ?: "",
            emojiChoice = gson.fromJson(obj.get("emojiChoice"), EmojiChoiceDto::class.java),
            feelingChoice = gson.fromJson(obj.get("feelingChoice"), FeelingChoiceDto::class.java),
            workloadLevel = gson.fromJson(obj.get("workloadLevel"), WorkloadLevelDto::class.java),
            workloadAffectsLife = gson.fromJson(obj.get("workloadAffectsLife"), FrequencyLevelDto::class.java),
            worksOvertime = gson.fromJson(obj.get("worksOvertime"), FrequencyLevelDto::class.java),
            hasSymptoms = gson.fromJson(obj.get("hasSymptoms"), FrequencyLevelDto::class.java),
            mentalHealthAffectsWork = gson.fromJson(obj.get("mentalHealthAffectsWork"), FrequencyLevelDto::class.java),
            relationshipScores = gson.fromJson(obj.get("relationshipScores"), RelationshipScoresDto::class.java),
            leadershipScores = gson.fromJson(obj.get("leadershipScores"), LeadershipScoresDto::class.java),
            observations = obj.get("observations")?.asString ?: "",
            assessmentScore = obj.get("assessmentScore")?.asInt ?: 0,
            riskLevel = obj.get("riskLevel")?.asString ?: "",
            createdAt = obj.get("createdAt")?.asString ?: "",
            updatedAt = obj.get("updatedAt")?.asString ?: ""
        )
    }
    
    private fun extractId(obj: JsonObject): String {
        val idElement = obj.get("id")
        
        return when {
            idElement == null || idElement.isJsonNull -> {
                "unknown_${System.currentTimeMillis()}"
            }
            idElement.isJsonPrimitive -> {
                // Se for uma string simples
                idElement.asString
            }
            idElement.isJsonObject -> {
                // Se for um objeto MongoDB { "$oid": "valor" } ou similar
                val idObj = idElement.asJsonObject
                when {
                    idObj.has("\$oid") -> idObj.get("\$oid")?.asString ?: "unknown"
                    idObj.has("oid") -> idObj.get("oid")?.asString ?: "unknown"
                    idObj.has("id") -> idObj.get("id")?.asString ?: "unknown"
                    else -> idObj.toString()
                }
            }
            else -> {
                idElement.toString()
            }
        }
    }
}
