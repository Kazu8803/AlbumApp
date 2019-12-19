package com.example.albumapp.utils


import com.example.albumapp.dto.RequestLoginDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Query
import java.util.*


interface LoginService {
//    /login/{email}/{password}
//    /login?email="naruto@chan.com"&password="konoha"
    @GET("login")
    fun getLoginSuccess(@Query("email") email: String,
                        @Query("password") password: String) : Call<RequestLoginDTO>
}