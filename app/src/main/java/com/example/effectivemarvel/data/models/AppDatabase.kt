package com.example.effectivemarvel

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterDataClass::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): CharacterDao
}