package com.example.effectivemarvel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DBCharacterViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<CharacterDataClass>>(emptyList())
    val characters: StateFlow<List<CharacterDataClass>> = _characters

    init {
        viewModelScope.launch {
            updateCharacters()
        }
    }

    private suspend fun updateCharacters() {
        val chars = DatabaseProvider.repository.getAllCharacters()
        _characters.update { chars }
    }
}
