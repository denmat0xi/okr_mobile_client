package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class CreateApplicationExtensionRequest (
    val extensionToDate: String,
    val description: String,
    val image: String
)