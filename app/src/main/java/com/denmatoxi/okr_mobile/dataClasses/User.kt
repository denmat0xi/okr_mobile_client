package com.denmatoxi.okr_mobile.dataClasses

import com.denmatoxi.okr_mobile.enums.Roles

data class User(
    val id: Int,
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val role: Roles
)
