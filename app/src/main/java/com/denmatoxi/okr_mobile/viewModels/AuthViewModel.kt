package com.denmatoxi.okr_mobile.viewModels

import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.LoginRequest
import com.denmatoxi.okr_mobile.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    fun login(username: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val call = RetrofitClient.instance.getPass(LoginRequest(username, password))
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    onResult(true, response.body()?.token)
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                onResult(false, null)
            }
        })
    }
}