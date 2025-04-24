package com.example.effectivemarvel

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class CharacterRepository(private val dao: CharacterDao) {
    suspend fun fetchAndSaveCharacters(nameFilter: String?) {
        try {
//            val public_key = "5d103b1af37466dcc9374d4349a2c10f"
//            val timestamp = "1710250461"
//            val hash_value = "c357422eaa6746cdbb3a9bdf4d4a0a69"

//            val response = marvelApi.getCharacters(timestamp, public_key, hash_value)
            val response = marvelApiService.getCharacters()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val adapter: JsonAdapter<MarvelCharactersResponse> = moshi.adapter(MarvelCharactersResponse::class.java)
            val apiResponse = adapter.fromJson(response.string()) ?: throw IllegalArgumentException("Failed to parse JSON")

            val charactersList = apiResponse.data.results.map { result ->
                CharacterDataClass(id = result.id, name = result.name,
                    description = result.description, thumbnail = result.thumbnail)
            }

            withContext(Dispatchers.IO) {
                dao.clearTable()
                dao.insertAll(charactersList)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    suspend fun getAllCharacters(): List<CharacterDataClass> {
        return dao.getAll()
    }

    suspend fun deleteAll() {
        dao.clearTable()
    }

    suspend fun insertAll(lst: List<CharacterDataClass>) {
        dao.insertAll(lst)
    }
}

