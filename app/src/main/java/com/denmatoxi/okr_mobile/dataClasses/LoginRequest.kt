package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val username: String,
    @SerializedName("password")
    val password: String
)