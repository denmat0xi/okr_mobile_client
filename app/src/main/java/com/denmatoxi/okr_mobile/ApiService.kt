package com.denmatoxi.okr_mobile

import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.ChangeApplicationExtensionStatusRequest
import com.denmatoxi.okr_mobile.dataClasses.ChangeApplicationExtensionStatusResponse
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationExtensionRequest
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationExtensionResponse
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationRequest
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationResponse
import com.denmatoxi.okr_mobile.dataClasses.EditApplicationExtensionRequest
import com.denmatoxi.okr_mobile.dataClasses.EditApplicationExtensionResponse
import com.denmatoxi.okr_mobile.dataClasses.EditApplicationRequest
import com.denmatoxi.okr_mobile.dataClasses.EditApplicationResponse
import com.denmatoxi.okr_mobile.dataClasses.EditProfileRequest
import com.denmatoxi.okr_mobile.dataClasses.EditProfileResponse
import com.denmatoxi.okr_mobile.dataClasses.GetApplicationResponse
import com.denmatoxi.okr_mobile.dataClasses.GetUserProfileResponse
import com.denmatoxi.okr_mobile.dataClasses.GetUserStatusesResponse
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
    fun register(@Body registerRequest: RegisterRequest) : Call<AuthResponse>

    @POST("/login")
    fun login(@Body loginRequest: LoginRequest) : Call<AuthResponse>

    @POST("/logout")
    fun logout() : Call<Unit>

    @GET("/profile")
    fun getProfile() : Call<GetUserProfileResponse>


    @PUT("/profile")
    fun editProfile(@Body newProfile: EditProfileRequest) : Call<EditProfileResponse>

    //Application
    //@GET("/application")
    //fun getPasses() : Call<List<Application>>

    @POST("/application")
    fun createApplication(@Body application: CreateApplicationRequest) : Call<CreateApplicationResponse>

    @PUT("/application/{id}")
    fun editApplication(@Path("id") applicationId : String, @Body newApplication: EditApplicationRequest) : Call<EditApplicationResponse>

    @GET("/application/{id}")
    fun getApplication(@Path("id") applicationId : String) : Call<GetApplicationResponse>

    @DELETE("/application/{id}")
    fun deleteApplication(@Path("id") applicationId: String) : Call<Unit>

    @GET("/application/my")
    fun getPasses() : Call<List<Application>>

    //декана функционал
    //@POST("/application/{id}/status")
    //fun changeApplication() : Call<EditApplicationRequest>

    //ExtensionApplication
    @POST("/extensionApplication/{applicationId}")
    fun createExtension(@Path("applicationId") applicationId: String,
                        @Body newExtension: CreateApplicationExtensionRequest)
        : Call<CreateApplicationExtensionResponse>

    @PUT("/extensionApplication/{id}")
    fun editExtension(@Path("applicationId") applicationId: String,
                      @Body newExtension: EditApplicationExtensionRequest)
        : Call<EditApplicationExtensionResponse>

    @DELETE("/extensionApplication/{id}")
    fun deleteExtension(@Path("id") extensionId: String)
        : Call<Unit>

//    декана функционал
//    @POST("/extensionApplication/{id}/status")
//    fun editExtensionStatus(@Path("id") extensionId: String,
//                            @Body newStatusRequest: ChangeApplicationExtensionStatusRequest)
//        : Call<ChangeApplicationExtensionStatusResponse>


    //Users
    @GET("/roles")
    fun getMyRoles() : Call<GetUserStatusesResponse>

//админа функционал
//    @POST("/user/{id}/role")
//    fun usr2()
//
//админа функционал
//    @GET("/users")
//    fun usr3()
}