package com.denmatoxi.okr_mobile.interceptors

import InjectAuth
import android.content.Context
import android.util.Log
import com.denmatoxi.okr_mobile.ApiService
import com.denmatoxi.okr_mobile.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import okhttp3.Request
import okhttp3.internal.http.HttpMethod
import retrofit2.http.POST
import java.lang.reflect.Method

class AuthInterceptor(val context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("AuthInterceptor", "auth interceptor entered")
        val request = chain.request()
        val token = sessionManager.fetchToken()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        Log.d("AuthInterceptor", "added header, Authorization token: $token")
        val requestString: StringBuilder = StringBuilder()
        for(name in request.headers.names()) {
            requestString.append("$name: ${request.headers[name]}\n")
        }
        Log.d("request", requestString.toString())


        Log.d("AuthInterceptor", "auth interceptor closing")
        return chain.proceed(newRequest)
    }
}