package br.com.fiap.bemestarsofttek.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoodEntryViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodEntryViewModel::class.java)) {
            return MoodEntryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
