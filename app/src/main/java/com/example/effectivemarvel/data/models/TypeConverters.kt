package com.example.effectivemarvel

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


object Converters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val heroImageType = HeroImage::class.java
    private val adapter: JsonAdapter<HeroImage> = moshi.adapter(heroImageType)

    @TypeConverter
    fun stringToHeroImage(value: String?): HeroImage? {
        return value?.let { adapter.fromJson(it) }
    }

    @TypeConverter
    fun heroImageToString(image: HeroImage?): String? {
        return image?.let { adapter.toJson(it) }
    }
}