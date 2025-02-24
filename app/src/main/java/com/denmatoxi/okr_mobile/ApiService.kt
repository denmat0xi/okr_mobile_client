package com.denmatoxi.okr_mobile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("passes")
    fun createPass(@Body request: PassRequest): Call<PassResponse>

    @GET("passes/{id}")
    fun getPass(@Path("id") passId: String): Call<PassResponse>
}