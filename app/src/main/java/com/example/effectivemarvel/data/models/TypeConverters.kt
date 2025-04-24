package com.example.effectivemarvel

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object Converters {

    private val moshi = Moshi.Builder().build()

    private val heroImageType = Types.newParameterizedType(HeroImage::class.java)
    private val adapter: JsonAdapter<HeroImage> = moshi.adapter(heroImageType)

    @TypeConverter
    @JvmStatic
    fun heroImageToJson(value: HeroImage?): String? =
        value?.let { adapter.toJson(it) }

    @TypeConverter
    @JvmStatic
    fun jsonToHeroImage(value: String?): HeroImage? =
        value?.let { adapter.fromJson(it) }
}

