package com.example.albumapp.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemCatalogDTO(@Json(name="id") val id: Int,
                          @Json(name="product") val product: String,
                          @Json(name="price") val price: String,
                          @Json(name="description") val description: String,
                          @Json(name="image") val image: String)