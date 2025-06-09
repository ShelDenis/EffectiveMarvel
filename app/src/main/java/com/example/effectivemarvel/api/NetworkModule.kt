package com.example.effectivemarvel

import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val marvelApi = retrofit.create(ApiService::class.java)
}