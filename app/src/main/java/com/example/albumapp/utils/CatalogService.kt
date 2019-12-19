package com.example.albumapp.utils

import com.example.albumapp.dto.ItemCatalogDTO
import retrofit2.Call
import retrofit2.http.GET

interface CatalogService {
    @GET("/catalog")
    fun getCatalogList() : Call<MutableList<ItemCatalogDTO>>
}