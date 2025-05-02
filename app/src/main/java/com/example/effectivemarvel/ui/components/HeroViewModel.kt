package com.example.effectivemarvel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MarvelCharacterViewModel() : ViewModel() {

    private val database = DatabaseProvider.database
    private val repository = CharacterRepository(database.characterDao())

    private val _characterState = MutableStateFlow<CharacterDataClass?>(null)
    val characterState: StateFlow<CharacterDataClass?> = _characterState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun loadCharacterById(id: Int, timestamp: String, public_key: String, hash: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val charFromDB = repository.getCharacterById(id)
            if (charFromDB != null) {
                _characterState.emit(charFromDB)
            }

            try {
                val call = marvelApi.getCharacterById(id, timestamp, public_key, hash)

                val response = call.execute()

                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!.data.results.firstOrNull()
                    val charFromNet = result?.asCharacterDataClass()

                    if (charFromNet != null) {
                        repository.insertOrUpdate(listOf(charFromNet))
                        _characterState.emit(charFromNet)
                    }
                }
            } catch (e: Exception) {
                if (charFromDB == null) {
                    _errorState.emit(e.message ?: "Something went wrong")
                }
            }
        }
    }

    fun clearCharacterState() {
        _characterState.value = null
        _errorState.value = null
    }
}