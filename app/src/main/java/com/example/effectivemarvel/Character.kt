package com.example.effectivemarvel
import android.media.Image
import java.net.URL
import java.util.Date

//data class Character(
//    val id: Int,
//    val name: String,
//    val description: String,
//    val modified: Date,
//    val resourceURI: String,
//    val urls: List<URL>,
//    val thumbnail: Image,
//    val comics: List<String>,
//    val stories: List<String>,
//    val events: List<String>,
//    val series: List<String>,
//    )

import com.squareup.moshi.Json


data class HeroImage(
    @Json(name="path") val path: String,
    @Json(name="extension") val extension: String,
)

data class MarvelCharacter(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name="thumbnail") val thumbnail: HeroImage,
//    @Json(name = "modified") val modified: Date,
//    @Json(name = "resourceURI") val resourceURI: String,
//    @Json(name = "urls") val urls: List<URL>,
//    @Json(name = "thumbnail") val thumbnail: Image,
//    @Json(name = "comics") val comics: List<String>,
//    @Json(name = "stories") val stories: List<String>,
//    @Json(name = "events") val events: List<String>,
//    @Json(name = "series") val series: List<String>,
//    @field:Json(name = "code") val code: String,
//    @field:Json(name = "message") val name: String,
)

data class MarvelCharactersResponse(
    @field:Json(name = "data") val data: Data,
) {
    data class Data(
//        @field:Json(name = "results") val results: ArrayList<MarvelCharacter>
        @field:Json(name = "results") val results: List<MarvelCharacter>
    )
}


