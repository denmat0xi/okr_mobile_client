package com.denmatoxi.okr_mobile

import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.LoginRequest
import com.denmatoxi.okr_mobile.dataClasses.Pass
import retrofit2.Call
import retrofit2.Callback
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
    fun getPass(loginRequest: LoginRequest): Call<AuthResponse>

    @POST("/logout")
    fun uploadFile()

    /*@GET("/profile")
    fun createPass()*/

    @PUT("/profile")
    fun getProfile()

    //Application
    @GET("/application")
    fun getPasses() : Call<List<Pass>>

    @POST("/application")
    fun createPass(pass: Pass) : Call<Pass>

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