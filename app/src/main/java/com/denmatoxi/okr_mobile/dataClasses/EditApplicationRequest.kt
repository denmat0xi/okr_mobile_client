package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class EditApplicationRequest(
    @SerializedName("fromDate")
    val fromDate: String,
    @SerializedName("toDate")
    val toDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String
)
