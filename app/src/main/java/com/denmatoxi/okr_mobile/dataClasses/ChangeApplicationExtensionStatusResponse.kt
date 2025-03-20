package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ChangeApplicationExtensionStatusResponse (
    @SerializedName("id")
    val id: UUID,
    @SerializedName("userId")
    val userId: UUID,
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
    val image: String,
    @SerializedName("extensions")
    val extensions: List<Extension>
)
