package com.denmatoxi.okr_mobile.dataClasses

import com.denmatoxi.okr_mobile.enums.Status
import com.google.gson.annotations.SerializedName

data class EditApplicationStatusRequest(
    @SerializedName("status")
    val status: Status,
    @SerializedName("comment")
    val comment: String
)
