package com.example.albumapp.utils

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Server {
    fun getServer(): Retrofit? {
        return  Retrofit
            .Builder()
            .baseUrl("https://private-f30abd-naruto1.apiary-mock.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}