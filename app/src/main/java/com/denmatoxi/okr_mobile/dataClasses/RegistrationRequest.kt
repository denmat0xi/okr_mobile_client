package com.denmatoxi.okr_mobile.dataClasses

data class RegistrationRequest(
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
