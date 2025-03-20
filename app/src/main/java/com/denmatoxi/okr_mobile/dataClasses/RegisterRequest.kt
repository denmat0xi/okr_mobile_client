package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("surname")
    val surname: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("patronymic")
    val patronymic: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirmPassword")
    @Expose(deserialize=false, serialize=false)
    val confirmPassword: String
)
