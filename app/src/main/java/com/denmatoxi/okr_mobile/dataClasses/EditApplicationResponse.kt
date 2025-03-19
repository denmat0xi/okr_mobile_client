package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class EditApplicationResponse(
    @SerializedName("id") val id: UUID,
    @SerializedName("userId") val userId: UUID,
    @SerializedName("fromDate") val fromDate: String,
    @SerializedName("toDate") val toDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String
)
