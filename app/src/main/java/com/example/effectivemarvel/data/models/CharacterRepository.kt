package com.example.effectivemarvel

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class CharacterRepository(private val dao: CharacterDao) {
    suspend fun fetchAndSaveCharacters(nameFilter: String?) {
        try {
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

    suspend fun insertOrUpdate(characters: List<CharacterDataClass>) {
        characters.forEach { character ->
            val existingRecord = dao.findById(character.id)
            if (existingRecord == null) {
                dao.insert(character)
            } else {
                dao.update(character)
            }
        }
    }
}

