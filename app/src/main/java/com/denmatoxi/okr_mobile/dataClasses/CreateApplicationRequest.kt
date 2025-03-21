package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class CreateApplicationRequest(
    @SerializedName("fromDate")
    var fromDate: String,
    @SerializedName("toDate")
    var toDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String
)