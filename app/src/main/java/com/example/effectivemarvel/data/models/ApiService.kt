package com.example.effectivemarvel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
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

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://gateway.marvel.com/v1/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

val marvelApi = retrofit.create(ApiService::class.java)