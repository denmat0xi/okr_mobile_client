package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val token: String
)