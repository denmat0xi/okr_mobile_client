package com.denmatoxi.okr_mobile

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.contentValuesOf

class SessionManager(context: Context) {
    private var preferences: SharedPreferences = context.applicationContext.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }


    fun saveToken(token: String?) {
        if (token == null) return
        val editor = preferences.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun clearToken() {
        val editor = preferences.edit()
        editor.putString(USER_TOKEN, null)
        editor.apply()
    }

    fun fetchToken(): String? {
        return preferences.getString(USER_TOKEN, null)
    }
}