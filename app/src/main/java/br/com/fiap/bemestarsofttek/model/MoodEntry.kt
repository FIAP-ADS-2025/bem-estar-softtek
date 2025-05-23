package br.com.fiap.bemestarsofttek.model

import java.time.LocalDate

data class MoodEntry(
    val id: Int,
    val date: LocalDate,
    val emoji: String,
    val mood: String,
    val feeling: String,
    val workload: String,
    val symptoms: String,
    val bossRelationship: Int,
    val colleaguesRelationship: Int,
    val observations: String = ""
)

data class WeeklyMoodData(
    val day: String,
    val anxious: Int,
    val stressed: Int,
    val happy: Int
)

data class DailyAssessment(
    val emojiChoice: EmojiChoice? = null,
    val feelingChoice: FeelingChoice? = null,
    val workloadLevel: WorkloadLevel? = null,
    val workloadAffectsLife: FrequencyLevel? = null,
    val worksOvertime: FrequencyLevel? = null,
    val hasSymptoms: FrequencyLevel? = null,
    val mentalHealthAffectsWork: FrequencyLevel? = null,
    val relationshipWithBoss: Int = 0,
    val relationshipWithColleagues: Int = 0,
    val respectFromColleagues: Int = 0,
    val teamCollaboration: Int = 0,
    val freedomToExpress: Int = 0,
    val teamWelcoming: Int = 0,
    val teamCooperation: Int = 0,
    val clearInstructions: Int = 0,
    val openCommunicationWithLeadership: Int = 0,
    val efficientInformation: Int = 0,
    val clearGoals: Int = 0,
    val leadershipCares: Int = 0,
    val leadershipAvailable: Int = 0,
    val comfortableReporting: Int = 0,
    val recognizedByLeadership: Int = 0,
    val trustInLeadership: Int = 0,
    val observations: String = ""
)

enum class EmojiChoice(val displayName: String, val emoji: String) {
    SAD("Triste", "😢"),
    HAPPY("Alegre", "😄"),
    TIRED("Cansado", "😴"),
    ANXIOUS("Ansioso", "😰"),
    FEAR("Medo", "😨"),
    ANGRY("Raiva", "😡")
}

enum class FeelingChoice(val displayName: String) {
    MOTIVATED("Motivado"),
    TIRED("Cansado"),
    WORRIED("Preocupado"),
    STRESSED("Estressado"),
    EXCITED("Animado"),
    SATISFIED("Satisfeito")
}

enum class WorkloadLevel(val displayName: String) {
    VERY_LIGHT("Muito Leve"),
    LIGHT("Leve"),
    MEDIUM("Média"),
    HIGH("Alta"),
    VERY_HIGH("Muito Alta")
}

enum class FrequencyLevel(val displayName: String) {
    NEVER("Não"),
    RARELY("Raramente"),
    SOMETIMES("Algumas vezes"),
    FREQUENTLY("Frequentemente"),
    ALWAYS("Sempre")
}

// Simplified option classes for MoodTrackerScreen
data class MoodOption(val displayName: String, val emoji: String)
data class FeelingOption(val displayName: String) 