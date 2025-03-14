package com.denmatoxi.okr_mobile.dataClasses

data class PassResponse(
    val id: Int,
    val userId: Int,
    val reason: String,
    val startDate: String,
    val endDate: String,
    val status: String,
    val fileUrl: String?
)