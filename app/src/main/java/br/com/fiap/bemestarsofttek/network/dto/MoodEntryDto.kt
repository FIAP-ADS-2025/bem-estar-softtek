package br.com.fiap.bemestarsofttek.network.dto

data class MoodEntryRequest(
    val employeeId: String,
    val date: String, // YYYY-MM-DD format
    val emoji: String,
    val mood: String,
    val feeling: String,
    val workload: String,
    val symptoms: String,
    val bossRelationship: Int,
    val colleaguesRelationship: Int,
    val observations: String
)

data class MoodEntryResponse(
    val id: String,
    val employeeId: String,
    val date: String,
    val emoji: String,
    val mood: String,
    val feeling: String,
    val workload: String,
    val symptoms: String,
    val bossRelationship: Int,
    val colleaguesRelationship: Int,
    val observations: String,
    val createdAt: String,
    val updatedAt: String
)
