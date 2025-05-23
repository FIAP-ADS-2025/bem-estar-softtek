package br.com.fiap.bemestarsofttek.database.repository

import android.content.Context
import br.com.fiap.bemestarsofttek.database.AppDatabase
import br.com.fiap.bemestarsofttek.database.entity.MoodEntryEntity

class MoodEntryRepository(context: Context) {

    private val dao = AppDatabase.getDatabase(context).moodEntryDao()

    suspend fun insert(entry: MoodEntryEntity) = dao.insert(entry)

    suspend fun update(entry: MoodEntryEntity) = dao.update(entry)

    suspend fun delete(entry: MoodEntryEntity) = dao.delete(entry)

    suspend fun getAll() = dao.getAll()

    suspend fun getById(id: Int) = dao.getById(id)
}
