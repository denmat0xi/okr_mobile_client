package com.denmatoxi.okr_mobile

data class Pass(
    val id: Int,
    val reason: String,
    val startDate: String,
    val endDate: String,
    val status: String,
    val fileUrl: String?
)