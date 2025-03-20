package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class EditApplicationExtensionRequest(
    @SerializedName("extensionToDate")
    val extensionToDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String
)
