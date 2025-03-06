package com.denmatoxi.okr_mobile

import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.FileUploadResponse
import com.denmatoxi.okr_mobile.dataClasses.LoginRequest
import com.denmatoxi.okr_mobile.dataClasses.Pass
import com.denmatoxi.okr_mobile.dataClasses.PassListResponse
import com.denmatoxi.okr_mobile.dataClasses.PassRequest
import com.denmatoxi.okr_mobile.dataClasses.PassResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @GET("passes/{id}")
    fun getPass(@Path("id") passId: Int): Call<Pass>

    @Multipart
    @POST("passes/upload")
    fun uploadFile(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ): Call<FileUploadResponse>

    @POST("passes/create")
    fun createPass(@Body request: PassRequest): Call<PassResponse>

    @GET("passes")
    fun getPasses(): Call<PassListResponse>
}