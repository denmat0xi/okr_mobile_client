package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class CreateApplicationResponse (
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("description")
    val reason: String,
    @SerializedName("fromDate")
    val startDate: String,
    @SerializedName("toDate")
    val endDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("image")
    val image: String
)