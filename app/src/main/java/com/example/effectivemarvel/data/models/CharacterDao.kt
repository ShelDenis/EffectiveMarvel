package com.example.effectivemarvel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAll(): List<CharacterDataClass>

    @Query("SELECT * FROM characters WHERE id IN (:characterIds)")
    fun loadAllByIds(characterIds: IntArray): List<CharacterDataClass>

    @Query("SELECT * FROM characters WHERE name LIKE :first AND description LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): CharacterDataClass

    @Query("SELECT * FROM characters WHERE id=:id")
    suspend fun findById(id: Int): CharacterDataClass?

    @Insert
    suspend fun insertAll(characters: List<CharacterDataClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterDataClass)

    @Update
    suspend fun update(character: CharacterDataClass)

    @Upsert
    suspend fun upsert(character: CharacterDataClass)

    @Delete
    fun delete(character: CharacterDataClass)

    @Query("DELETE FROM characters")
    suspend fun clearTable()
}

