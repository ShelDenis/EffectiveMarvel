package com.example.effectivemarvel.data.models

import retrofit2.http.GET
import okhttp3.ResponseBody
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("/v1/public/characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): ResponseBody

    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("startId") startId: String?): ResponseBody
}