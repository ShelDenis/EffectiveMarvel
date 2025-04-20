package com.example.effectivemarvel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAll(): List<CharacterDataClass>

    @Query("SELECT * FROM characters WHERE id IN (:characterIds)")
    fun loadAllByIds(characterIds: IntArray): List<CharacterDataClass>

    @Query("SELECT * FROM characters WHERE name LIKE :first AND " +
            "description LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): CharacterDataClass

    @Insert
    fun insertAll(vararg characters: CharacterDataClass)

    @Delete
    fun delete(character: CharacterDataClass)
}

