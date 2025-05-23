package br.com.fiap.bemestarsofttek.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "mood_entry")
data class MoodEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
