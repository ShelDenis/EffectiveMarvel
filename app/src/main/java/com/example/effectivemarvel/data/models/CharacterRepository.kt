package com.example.effectivemarvel

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class CharacterRepository(private val dao: CharacterDao) {
    suspend fun fetchAndSaveCharacters(nameFilter: String?) {
        try {
            val response = when (nameFilter.isNullOrEmpty()) {
                true -> marvelApiService.getCharacters(null)
                else -> marvelApiService.getCharacters(nameFilter)
            }

            // Используем Moshi для парсинга JSON
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
                dao.insertAll(*charactersList.toTypedArray())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}

