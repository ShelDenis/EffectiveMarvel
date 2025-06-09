package com.example.effectivemarvel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterDataClass(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: HeroImage?
)

fun CharacterDataClass.likeCharacterUI(): CharacterUI {
    return CharacterUI(
        id = id,
        name = name.orEmpty(),
        description = description.orEmpty(),
        img_path = thumbnail?.path + "." + thumbnail?.extension
    )
}

fun CharacterDataClass.likeMarvelCharacter(): MarvelCharacter {
    return MarvelCharacter(
        id = id,
        name = name.orEmpty(),
        description = description.orEmpty(),
        thumbnail = thumbnail!!
    )
}