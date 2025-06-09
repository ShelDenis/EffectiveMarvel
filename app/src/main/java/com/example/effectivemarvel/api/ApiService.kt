package com.example.effectivemarvel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Query

interface ApiService {
    @GET("public/characters")
    fun getCharacters(
        @Query("ts") timestamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String
    ): Call<MarvelCharactersResponse>

    @GET("public/characters/{characterId}")
    fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("ts") timestamp: String,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String
    ): Call<MarvelCharactersResponse>
}