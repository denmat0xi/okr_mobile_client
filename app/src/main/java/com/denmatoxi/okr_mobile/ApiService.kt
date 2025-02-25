package com.denmatoxi.okr_mobile

import com.denmatoxi.okr_mobile.DataClasses.AuthResponse
import com.denmatoxi.okr_mobile.DataClasses.FileUploadResponse
import com.denmatoxi.okr_mobile.DataClasses.LoginRequest
import com.denmatoxi.okr_mobile.DataClasses.Pass
import com.denmatoxi.okr_mobile.DataClasses.PassListResponse
import com.denmatoxi.okr_mobile.DataClasses.PassRequest
import com.denmatoxi.okr_mobile.DataClasses.PassResponse
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