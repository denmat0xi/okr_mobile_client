package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("surname")
    val surname: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("patronymic")
    val patronymic: String,
    @SerializedName("email")
    val email: String
)
