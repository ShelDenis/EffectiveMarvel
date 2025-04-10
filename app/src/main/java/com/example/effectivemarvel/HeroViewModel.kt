package com.example.effectivemarvel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.security.MessageDigest
import java.util.Base64
import retrofit2.Callback
import retrofit2.await
import retrofit2.awaitResponse

//class HeroViewModel(private val characterId: Int) : ViewModel() {
//    private val _characters = MutableStateFlow<List<MarvelCharacter>>(emptyList())
//    val characters: StateFlow<List<MarvelCharacter>> get() = _characters
//
//    init {
//        viewModelScope.launch {
//            val public_key = "5d103b1af37466dcc9374d4349a2c10f"
////            val private_key = "d262b8e973bf6ada12e9d8c5234a8c0742fc2ef2"
//            val timestamp = "1710250461"
//            val hash_value = "c357422eaa6746cdbb3a9bdf4d4a0a69"
//
////            val hashInput = "$timestamp$private_key$public_key"
////            val md5Digest = MessageDigest.getInstance("MD5").digest(hashInput.toByteArray(Charsets.UTF_8))
////            val hash_value = Base64.getEncoder().encodeToString(md5Digest)
//
//            val call = marvelApi.getCharacterById(characterId, timestamp, public_key, hash_value)
//
//            call.enqueue(object : Callback<MarvelCharactersResponse> {
//                override fun onResponse(call: Call<MarvelCharactersResponse>, response: Response<MarvelCharactersResponse>) {
//                    if (response.isSuccessful) {
//                        val marvelCharactersResponse = response.body()
//                        if (marvelCharactersResponse != null) {
//                            _characters.value = marvelCharactersResponse.data.results.toList()
//                        }
//                    } else {
////                        _characters.value = arrayListOf<MarvelCharacter>()
//                        _characters.value = listOf<MarvelCharacter>()
//                    }
//                }
//
//                override fun onFailure(call: Call<MarvelCharactersResponse>, t: Throwable) {
//                }
//            })
//        }
//    }
//}

//class HeroViewModel(private val characterId: Int) : ViewModel() {
//    private val _characters = MutableStateFlow<List<MarvelCharacter>>(emptyList())
//    val characters: StateFlow<List<MarvelCharacter>> get() = _characters
//
//    init {
//        fetchCharacterById()
//    }
//
//    private fun fetchCharacterById() {
//        viewModelScope.launch {
//            try {
//                val publicKey = "5d103b1af37466dcc9374d4349a2c10f"
//                val timestamp = "1710250461"
//                val hashValue = "c357422eaa6746cdbb3a9bdf4d4a0a69"
//
//                val response = marvelApi.getCharacterById(characterId, timestamp, publicKey, hashValue).awaitResponse()
//
//                if (response.isSuccessful) {
//                    response.body()?.let { marvelCharactersResponse ->
//                        _characters.value = marvelCharactersResponse.data.results
//                    } ?: run {
//                        _characters.value = emptyList()
//                    }
//                } else {
//                    _characters.value = emptyList()
//                    // Логирование ошибки или уведомление пользователя
//                }
//            } catch (e: Exception) {
//                // Обработка исключений, которые могут возникнуть при выполнении запроса
//                _characters.value = emptyList()
//                // Логирование ошибки или уведомление пользователя
//            }
//        }
//    }
//}

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch

//    private val marvelRepository: MarvelRepository
//class HeroViewModel() : ViewModel() {
//
//    private val _characterState = MutableStateFlow<MarvelCharactersResponse?>(null)
//    val characterState: StateFlow<MarvelCharactersResponse?> = _characterState
//
//    fun loadCharacterById(characterId: Int, timestamp: String, publicKey: String, hash: String) {
//        viewModelScope.launch {
//            val response = marvelApi.getCharacterById(characterId, timestamp, publicKey, hash).await()
//            _characterState.value = response
//        }
//    }
//}

class MarvelCharacterViewModel : ViewModel() {
    private val _characterState = MutableStateFlow<MarvelCharactersResponse?>(null)
    val characterState: StateFlow<MarvelCharactersResponse?> = _characterState

    fun loadCharacterById(characterId: Int, timestamp: String, publicKey: String, hash: String) {
        viewModelScope.launch {
            val response = marvelApi.getCharacterById(characterId, timestamp, publicKey, hash).await()
            _characterState.value = response
        }
    }
}

//class HeroViewModel(private val characterId: Int) : ViewModel() {
//    private val _characters = MutableStateFlow<List<MarvelCharacter>?>(null)
//    val characters: StateFlow<List<MarvelCharacter>?> get() = _characters
//
//    init {
//        viewModelScope.launch {
//            val publicKey = "5d103b1af37466dcc9374d4349a2c10f"
//            val timestamp = System.currentTimeMillis().toString()
//            val hashValue = calculateHash(timestamp, publicKey)
//
//            val response = withContext(Dispatchers.IO) {
//                marvelApi.getCharacterById(characterId, timestamp, publicKey, hashValue).execute()
//            }
//
//            if (response.isSuccessful) {
//                _characters.value = response.body()?.data?.results
//            } else {
//                _characters.value = null
//            }
//        }
//    }
//
//    private fun calculateHash(timestamp: String, publicKey: String): String {
//        val privateKey = "d262b8e973bf6ada12e9d8c5234a8c0742fc2ef2"
//        val hashInput = "$timestamp$privateKey$publicKey"
//        val md5Digest = MessageDigest.getInstance("MD5").digest(hashInput.toByteArray())
//        return Base64.getEncoder().encodeToString(md5Digest)
//    }
//}
//
//
//class HeroViewModelFactory(private val characterId: Int) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HeroViewModel::class.java)) {
//            return HeroViewModel(characterId) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
//class HeroViewModel(private val characterId: Int) : ViewModel() {
//    private val _hero = MutableStateFlow<MarvelCharacter?>(null)
//    val hero: StateFlow<MarvelCharacter?> get() = _hero
//
//    init {
//        viewModelScope.launch {
//            val publicKey = "5d103b1af37466dcc9374d4349a2c10f"
//            val timestamp = System.currentTimeMillis().toString()
//            val hashValue = calculateHash(timestamp, publicKey)
//
//            val call = marvelApi.getCharacterById(characterId, timestamp, publicKey, hashValue)
//
//            call.enqueue(object : Callback<MarvelCharactersResponse> {
//                override fun onResponse(call: Call<MarvelCharactersResponse>, response: Response<MarvelCharactersResponse>) {
//                    if (response.isSuccessful) {
//                        val responseBody = response.body()
//                        if (responseBody != null) {
//                            val results = responseBody.data.results
//                            if (results.isNotEmpty()) {
//                                _hero.value = results.first()
//                            } else {
//                                _hero.value = null
//                            }
//                        } else {
//                            _hero.value = null
//                        }
//                    } else {
//                        _hero.value = null
//                    }
//                }
//
//                override fun onFailure(call: Call<MarvelCharactersResponse>, t: Throwable) {
//                    _hero.value = null
//                }
//            })
//        }
//    }
//
//    private fun calculateHash(timestamp: String, publicKey: String): String {
//        val privateKey = "d262b8e973bf6ada12e9d8c5234a8c0742fc2ef2"
//        val hashInput = "$timestamp$privateKey$publicKey"
//        val md5Digest = MessageDigest.getInstance("MD5").digest(hashInput.toByteArray())
//        return Base64.getEncoder().encodeToString(md5Digest)
//    }
//}