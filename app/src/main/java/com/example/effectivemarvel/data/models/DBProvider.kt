package com.example.effectivemarvel

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    lateinit var database: AppDatabase
    lateinit var repository: CharacterRepository

    fun initDb(context: Context) {
        database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "marvel_db"
        ).build()

        repository = CharacterRepository(database.characterDao())
    }
}