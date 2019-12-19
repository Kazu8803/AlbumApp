package com.example.albumapp.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class RequestLoginDTO(@Json(name="fullName") val fullName: String,
                           @Json(name="userType") val userType: String,
                           @Json(name="code") val code: String,
                           @Json(name="message") val message: String,
                           @Json(name="sessionToken") val sessionToken: String )