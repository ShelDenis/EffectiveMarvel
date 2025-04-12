package com.example.effectivemarvel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.await

//
//class MarvelCharacterViewModel : ViewModel() {
//    private val _characterState = MutableStateFlow<MarvelCharactersResponse?>(null)
//    val characterState: StateFlow<MarvelCharactersResponse?> = _characterState
//
//    fun loadCharacterById(characterId: Int, timestamp: String, publicKey: String, hash: String) {
//        try {
//            viewModelScope.launch {
//                val response = marvelApi.getCharacterById(characterId, timestamp, publicKey, hash).await()
//                _characterState.value = response
//            }
//        }
//        catch (e: Exception) {
//            _characterState.value = null
//        }
//    }
//
//    fun clearCharacterState() {
//        _characterState.value = null
//    }
//}
//

class MarvelCharacterViewModel : ViewModel() {
    private val _characterState = MutableStateFlow<MarvelCharactersResponse?>(null)
    val characterState: StateFlow<MarvelCharactersResponse?> = _characterState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun loadCharacterById(characterId: Int, timestamp: String, publicKey: String, hash: String) {
        try {
            viewModelScope.launch {
                val response = marvelApi.getCharacterById(characterId, timestamp, publicKey, hash).await()
                _characterState.value = response
                _errorState.value = null
            }
        } catch (e: Exception) {
            _characterState.value = null
            _errorState.value = "Произошла ошибка: ${e.message}"
        }
    }

    fun clearCharacterState() {
        _characterState.value = null
        _errorState.value = null
    }
}

//class MarvelCharacterViewModel : ViewModel() {
//    private val _characterState = MutableStateFlow<MarvelCharactersResponse?>(null)
//    val characterState: StateFlow<MarvelCharactersResponse?> = _characterState
//
//    private val _errorState = MutableStateFlow<String?>(null)
//    val errorState: StateFlow<String?> = _errorState
//
//    fun loadCharacterById(characterId: Int, timestamp: String, publicKey: String, hash: String) {
//        try {
//            viewModelScope.launch {
//                val response = marvelApi.getCharacterById(characterId, timestamp, publicKey, hash).await()
//                _characterState.value = response
//                _errorState.value = null // Сбрасываем ошибку после успешного запроса
//            }
//        } catch (e: Exception) {
//            _characterState.value = null
//            _errorState.value = "Ошибка: ${e.message}" // Устанавливаем сообщение об ошибке
//            Log.e("MarvelApp", "Error loading character", e) // Логируем ошибку
//        }
//    }
//
//    fun clearCharacterState() {
//        _characterState.value = null
//        _errorState.value = null  // Сбрасываем ошибку
//    }
//}