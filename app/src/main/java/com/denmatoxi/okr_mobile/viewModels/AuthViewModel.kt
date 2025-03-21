package com.denmatoxi.okr_mobile.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.LoginRequest
import com.denmatoxi.okr_mobile.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    fun login(context: Context, username: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val call = RetrofitClient.instance(context).login(LoginRequest(username, password))
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    Log.d("Login operation", response.body()!!.token)
                    onResult(true, response.body()?.token)
                } else {
                    Log.d("Login operation", "No login")
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("Login operation", "Failed (onFailure invoked)")
                onResult(false, null)
            }
        })
    }
}