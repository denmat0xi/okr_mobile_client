package com.denmatoxi.okr_mobile.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.dataClasses.Pass
import com.denmatoxi.okr_mobile.dataClasses.PassListResponse
import com.denmatoxi.okr_mobile.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassListViewModel : ViewModel() {

    private val _passes = MutableLiveData<List<Pass>>()
    val passes: LiveData<List<Pass>> = _passes

    fun loadPasses() {
        RetrofitClient.instance.getPasses().enqueue(object : Callback<PassListResponse> {
            override fun onResponse(
                call: Call<PassListResponse>,
                response: Response<PassListResponse>
            ) {
                if (response.isSuccessful) {
                    _passes.value = response.body()?.passes ?: emptyList()
                } else {
                    _passes.value = emptyList()
                }
            }

            override fun onFailure(call: Call<PassListResponse>, t: Throwable) {
                _passes.value = emptyList()
            }
        })
    }
}