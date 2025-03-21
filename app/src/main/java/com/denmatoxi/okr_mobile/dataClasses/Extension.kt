package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Extension (
    @SerializedName("id")
    val id: String,
    @SerializedName("applicationId")
    val applicationId: String,
    @SerializedName("extensionToDate")
    val extensionToDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("comment")
    val comment: String
)
