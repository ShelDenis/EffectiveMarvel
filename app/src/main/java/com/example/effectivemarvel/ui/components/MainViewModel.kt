package com.example.effectivemarvel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MarvelViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<MarvelCharacter>>(emptyList())
    val characters: StateFlow<List<MarvelCharacter>> get() = _characters

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    init {
        viewModelScope.launch {
            val public_key = "5d103b1af37466dcc9374d4349a2c10f"
            val timestamp = "1710250461"
            val hash_value = "c357422eaa6746cdbb3a9bdf4d4a0a69"

            val call = marvelApi.getCharacters(timestamp, public_key, hash_value)

            call.enqueue(object : Callback<MarvelCharactersResponse> {
                override fun onResponse(call: Call<MarvelCharactersResponse>, response: Response<MarvelCharactersResponse>) {
                    if (response.isSuccessful) {
                        val marvelCharactersResponse = response.body()
                        if (marvelCharactersResponse != null) {
                            _characters.value = marvelCharactersResponse.data.results.toList()
                        }
                    } else {
                        _characters.value = listOf<MarvelCharacter>()
                    }
                }

                override fun onFailure(call: Call<MarvelCharactersResponse>, t: Throwable) {
                    _errorState.value = "Error! Check your Internet connection"
                }
            })
        }
        }
    }
