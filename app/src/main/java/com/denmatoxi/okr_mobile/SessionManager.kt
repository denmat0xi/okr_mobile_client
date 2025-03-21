package com.denmatoxi.okr_mobile

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.contentValuesOf
import java.util.UUID

class SessionManager(context: Context) {
    private var preferences: SharedPreferences = context.applicationContext.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)


    companion object {
        const val USER_TOKEN = "user_token"
        const val USER_ID = "user_id"
    }
    val isAuthorized: Boolean
        get() = !fetchToken().isNullOrEmpty()


    fun saveToken(token: String?) {
        if (token == null) return
        val editor = preferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun saveUserId(id: String?) {
        if (id == null) return
        val editor = preferences.edit()
        editor.putString(USER_ID, id)
        editor.apply()
    }

    fun clearToken() {
        val editor = preferences.edit()
        editor.putString(USER_TOKEN, null)
        editor.putString(USER_ID, null)
        editor.apply()
    }


    fun getUserId() : String? {
        return preferences.getString(USER_ID, null)
    }

    fun fetchToken(): String? {

        return preferences.getString(USER_TOKEN, null)
    }
}