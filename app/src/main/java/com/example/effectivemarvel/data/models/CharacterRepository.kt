package com.example.effectivemarvel

class CharacterRepository(private val dao: CharacterDao) {
    suspend fun getAllCharacters(): List<CharacterDataClass> {
        return dao.getAll()
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

    suspend fun getCharacterById(id: Int): CharacterDataClass? {
        return dao.findById(id)
    }
}

