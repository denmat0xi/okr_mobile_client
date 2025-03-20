package com.denmatoxi.okr_mobile

import android.content.Context
import com.denmatoxi.okr_mobile.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private val BASE_URL = "http://51.250.46.2:1111"

    fun httpClient(context: Context) = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .addInterceptor(AuthInterceptor(context))
        .build()

    fun instance(context: Context) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(this.httpClient(context))
            .build()
            .create(ApiService::class.java)
    }
}