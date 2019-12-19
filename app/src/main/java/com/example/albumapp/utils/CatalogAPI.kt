package com.example.albumapp.utils

import android.util.Log
import com.example.albumapp.dto.RequestLoginDTO
import com.example.albumapp.enum.BaseUrlEnum
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.example.albumapp.enum.BaseUrlEnum.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class CatalogAPI {
    val retrofit: Retrofit = getCatalogApi()

    fun getCatalogApi(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL.url)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }
}