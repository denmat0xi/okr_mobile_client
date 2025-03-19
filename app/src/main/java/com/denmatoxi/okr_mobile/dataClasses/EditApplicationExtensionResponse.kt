package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class EditApplicationExtensionResponse(
    @SerializedName("id") val id: UUID,
    @SerializedName("applicationId") val applicationId: UUID,
    @SerializedName("extensionToDate") val extensionToDate: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String
)
