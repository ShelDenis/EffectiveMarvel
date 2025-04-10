package com.example.effectivemarvel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.await


class MarvelCharacterViewModel : ViewModel() {
    private val _characterState = MutableStateFlow<MarvelCharactersResponse?>(null)
    val characterState: StateFlow<MarvelCharactersResponse?> = _characterState

    fun loadCharacterById(characterId: Int, timestamp: String, publicKey: String, hash: String) {
        try {
            viewModelScope.launch {
                val response = marvelApi.getCharacterById(characterId, timestamp, publicKey, hash).await()
                _characterState.value = response
            }
        }
        catch (e: Exception) {
            _characterState.value = null
        }
    }

    fun clearCharacterState() {
        _characterState.value = null
    }
}

