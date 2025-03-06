package com.denmatoxi.okr_mobile.dataClasses

data class User(
    val id: Int,
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val password: String
)
