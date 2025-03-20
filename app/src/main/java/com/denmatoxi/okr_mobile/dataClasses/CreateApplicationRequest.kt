package com.denmatoxi.okr_mobile.dataClasses

data class CreateApplicationRequest(
    val fromDate: String,
    val toDate: String,
    val description: String,
    val image: String
)