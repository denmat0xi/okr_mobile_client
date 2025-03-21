package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class EditApplicationStatusResponse(
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("fromDate") val fromDate: String,
    @SerializedName("toDate") val toDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String,
    @SerializedName("extensions") val extensions: List<Extension>
)
