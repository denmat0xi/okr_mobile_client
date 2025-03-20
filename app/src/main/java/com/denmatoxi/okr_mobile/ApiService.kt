package com.denmatoxi.okr_mobile

import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.LoginRequest
import com.denmatoxi.okr_mobile.dataClasses.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("/registration")
    fun register(@Body registerRequest: RegisterRequest): Call<AuthResponse>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<AuthResponse>

    @POST("/logout")
    fun logout() : Call<Unit>

    @GET("/profile")
    fun createApplication()


    @PUT("/profile")
    fun getProfile()

    //Application
    @GET("/application")
    fun getPasses() : Call<List<Application>>

    @POST("/application")
    fun createApplication(application: Application) : Call<Application>

    @PUT("/application/{id}")
    fun editApplication(@Path("id") applicationId : String) : Call<Application>

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