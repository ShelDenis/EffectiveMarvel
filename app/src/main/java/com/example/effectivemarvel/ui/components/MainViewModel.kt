package com.example.effectivemarvel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.security.MessageDigest


fun generateMarvelApiRequestParts(publicKey: String, privateKey: String): Map<String, Any> {
    val timestamp = System.currentTimeMillis().toString()

    val preHashString = "$timestamp$privateKey$publicKey"

    val md5HashBytes = MessageDigest.getInstance("MD5").digest(preHashString.toByteArray())
    val hexHash = bytesToHex(md5HashBytes)

    return mapOf(
        "ts" to timestamp,
        "hash" to hexHash
    )
}

private fun bytesToHex(bytes: ByteArray): String =
    bytes.joinToString("") { byte ->
        "%02x".format(byte)
    }


class MarvelViewModel : ViewModel() {
    private val _characters = MutableStateFlow<List<MarvelCharacter>>(emptyList())
    val characters: StateFlow<List<MarvelCharacter>> = _characters

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    private val repository: CharacterRepository = CharacterRepository(DatabaseProvider.database.characterDao())

    fun loadCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val localCharacters = repository.getAllCharacters()
            _characters.emit(localCharacters.map{it.likeMarvelCharacter()})

            try {
                val public_key = "1cb26014ffc866eed9b84770d32f5ff5"
                val private_key = "06cb168af4b4d497a1911b59f2858816247d5bae"

                val request_parts = generateMarvelApiRequestParts(public_key, private_key)

                val timestamp = request_parts["ts"] as String

                val hash_value = request_parts["hash"] as String

                val response = NetworkModule.marvelApi.getCharacters(timestamp, public_key, hash_value).execute()

                if (response.isSuccessful && response.body() != null) {
                    val marvelCharactersResponse = response.body()!!
                    val characters = marvelCharactersResponse.data.results.map { it.asCharacterDataClass() }
                    repository.insertOrUpdate(characters)

                    val localCharacters = repository.getAllCharacters()
                    _characters.emit(localCharacters.map{it.likeMarvelCharacter()})
                } else {
                    _characters.emit(emptyList())
                }
            } catch (e: Exception) {
                if (localCharacters.size == 0) {
                    _errorState.emit("Error! Check your Internet connection")
                }
            }
        }
    }

    init {
        loadCharacters()
    }

    fun clearErrorState() {
        _errorState.value = null
    }
}