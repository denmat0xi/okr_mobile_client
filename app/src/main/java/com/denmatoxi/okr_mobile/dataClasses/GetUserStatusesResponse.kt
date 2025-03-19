package com.denmatoxi.okr_mobile.dataClasses

import com.google.gson.annotations.SerializedName

data class GetUserStatusesResponse(
    @SerializedName("isStudent") val isStudent: Boolean,
    @SerializedName("isTeacher") val isTeacher: Boolean,
    @SerializedName("isDean") val isDean: Boolean,
    @SerializedName("isAdmin") val isAdmin: Boolean
)
