package com.denmatoxi.okr_mobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {
    fun login(username: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val call = RetrofitClient.instance.login(LoginRequest(username, password))
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