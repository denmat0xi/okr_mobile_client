package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Application(
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fromDate")
    val fromDate: String,
    @SerializedName("toDate")
    val toDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("extensions")
    val extensions: List<Extension>
)