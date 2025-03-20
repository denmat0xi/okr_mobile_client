package com.denmatoxi.okr_mobile.interceptors

import InjectAuth
import android.content.Context
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
        val request = chain.request()

        sessionManager.fetchToken().let {
            if (!it.isNullOrEmpty()) {
                request.newBuilder()
                    .addHeader("Authorization", "Bearer $it")
                    .build()
            }
        }

        return chain.proceed(request)
    }
}