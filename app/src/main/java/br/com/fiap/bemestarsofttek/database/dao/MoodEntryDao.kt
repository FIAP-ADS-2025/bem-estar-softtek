package br.com.fiap.bemestarsofttek.database.dao

import androidx.room.*
import br.com.fiap.bemestarsofttek.database.entity.MoodEntryEntity

@Dao
interface MoodEntryDao {

    @Insert
    suspend fun insert(entry: MoodEntryEntity): Long

    @Update
    suspend fun update(entry: MoodEntryEntity)

    @Delete
    suspend fun delete(entry: MoodEntryEntity)

    @Query("SELECT * FROM mood_entry ORDER BY date DESC")
    suspend fun getAll(): List<MoodEntryEntity>

    @Query("SELECT * FROM mood_entry WHERE id = :id")
    suspend fun getById(id: Int): MoodEntryEntity?
}
