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
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    //Account
    @POST("/registration")
    fun register()

    @POST("/login")
    fun getPass()

    @POST("/logout")
    fun uploadFile()

    @GET("/profile")
    fun createPass()

    @PUT("/profile")
    fun getPasses()

    //Application
    @GET("/application")
    fun name1()

    @POST("/application")
    fun name2()

    @PUT("/application/{id}")
    fun name3()

    @DELETE("/application/{id}")
    fun name4()

    @GET("/application/my")
    fun name5()

    @POST("/application/{id}/status")
    fun name6()

    //ExtensionApplication
    @POST("/extensionApplication/{applicationId}")
    fun ext1()

    @PUT("/extensionApplication/{id}")
    fun ext2()

    @DELETE("/extensionApplication/{id}")
    fun ext3()

    @POST("/extensionApplication/{id}/status")
    fun ext4()


    //Users
    @GET("/roles")
    fun usr1()

    @POST("/user/{id}/role")
    fun usr2()

    @GET("/users")
    fun usr3()



}