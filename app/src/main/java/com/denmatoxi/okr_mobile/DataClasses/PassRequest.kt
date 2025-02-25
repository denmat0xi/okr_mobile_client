package com.denmatoxi.okr_mobile.DataClasses

data class PassRequest(
    val reason: String,
    val startDate: String,
    val endDate: String,
    val fileUrl: String?
)