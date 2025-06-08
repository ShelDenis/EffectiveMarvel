package com.example.effectivemarvel

class CharacterRepository(private val dao: CharacterDao) {
    suspend fun getAllCharacters(): List<CharacterDataClass> {
        return dao.getAll()
    }

    suspend fun insertOrUpdate(characters: List<CharacterDataClass>) {
        characters.forEach { character ->
            dao.upsert(character)
        }
    }

    suspend fun getCharacterById(id: Int): CharacterDataClass? {
        return dao.findById(id)
    }
}

