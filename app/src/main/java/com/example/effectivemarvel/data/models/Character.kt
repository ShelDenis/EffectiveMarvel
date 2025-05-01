package com.example.effectivemarvel

import com.squareup.moshi.Json


data class HeroImage(
    @Json(name="path") val path: String,
    @Json(name="extension") val extension: String,
)

data class MarvelCharacter(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: HeroImage,
)

data class MarvelCharactersResponse(
    @field:Json(name = "data") val data: Data,
) {
    data class Data(
        @field:Json(name = "results") val results: List<MarvelCharacter>
    )
}

fun MarvelCharacter.asCharacterDataClass(): CharacterDataClass =
    CharacterDataClass(id = id, name = name, description = description, thumbnail = thumbnail)

fun MarvelCharacter.asCharacterUI(): CharacterUI = CharacterUI(
        id = id,
        name = name,
        description = description,
        img_path = thumbnail.path + "." + thumbnail.extension
    )
