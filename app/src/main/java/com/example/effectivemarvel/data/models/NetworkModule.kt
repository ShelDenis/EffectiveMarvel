package com.example.effectivemarvel

import com.example.effectivemarvel.data.models.MarvelApiService
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

val marvelApiService by lazy {
    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val newUrl = originalRequest.url.newBuilder()
//                .addQueryParameter("apikey", YOUR_PUBLIC_API_KEY)
//                .addQueryParameter("hash", generateHash())
//                .addQueryParameter("ts", System.currentTimeMillis().toString())
                .addQueryParameter("apikey", "5d103b1af37466dcc9374d4349a2c10f")
                .addQueryParameter("hash", "c357422eaa6746cdbb3a9bdf4d4a0a69")
                .addQueryParameter("ts", "1710250461")
                .build()
            chain.proceed(originalRequest.newBuilder().url(newUrl).build())
        }.build()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(MarvelApiService::class.java)
}

