package br.com.fiap.bemestarsofttek.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.bemestarsofttek.database.entity.MoodEntryEntity
import br.com.fiap.bemestarsofttek.database.repository.MoodEntryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoodEntryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MoodEntryRepository(application)

    private val _moodEntries = MutableStateFlow<List<MoodEntryEntity>>(emptyList())
    val moodEntries: StateFlow<List<MoodEntryEntity>> = _moodEntries

    init {
        loadMoodEntries()
    }

    fun loadMoodEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            _moodEntries.value = repository.getAll()
        }
    }

    fun addMoodEntry(entry: MoodEntryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(entry)
            loadMoodEntries()
        }
    }

    fun updateMoodEntry(entry: MoodEntryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(entry)
            loadMoodEntries()
        }
    }

    fun deleteMoodEntry(entry: MoodEntryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(entry)
            loadMoodEntries()
        }
    }
}
