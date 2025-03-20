package com.denmatoxi.okr_mobile.dataClasses

import com.denmatoxi.okr_mobile.enums.Status
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class CreateApplicationResponse(
    @SerializedName("id")
    val id: UUID,
    @SerializedName("userId")
    val userId: UUID,
    @SerializedName("fromDate")
    val fromDate: String,
    @SerializedName("toDate")
    val toDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: Status,
    @SerializedName("comment")
    val comment: String
)
