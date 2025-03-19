package com.denmatoxi.okr_mobile.dataClasses

data class ApplicationRequest(
    val reason: String,
    val startDate: String,
    val endDate: String,
    val fileUrl: String?
)