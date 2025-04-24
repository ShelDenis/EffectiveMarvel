package com.example.effectivemarvel

import retrofit2.http.GET
import okhttp3.ResponseBody
import retrofit2.http.Path

interface MarvelApiService {
    @GET("/v1/public/characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): ResponseBody

    @GET("/v1/public/characters")
    suspend fun getCharacters(): ResponseBody
}