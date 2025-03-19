package com.denmatoxi.okr_mobile.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplicationListViewModel : ViewModel() {

    private val _passes = MutableLiveData<List<Application>>()
    val passes: LiveData<List<Application>> = _passes

    fun loadPasses() {
        RetrofitClient.instance.getPasses().enqueue(object : Callback<List<Application>> {
            override fun onResponse(call: Call<List<Application>>, response: Response<List<Application>>) {
                if (response.isSuccessful) {
                    _passes.value = response.body() ?: emptyList()
                    Log.d("onResponse, success", "data is ${_passes.value}")

                } else {
                    _passes.value = emptyList()
                    Log.d("onResponse, not success", "data is ${_passes.value}")
                }
            }

            override fun onFailure(call: Call<List<Application>>, t: Throwable) {
                _passes.value = emptyList()
                Log.d("onResponse, failure", "data is ${_passes.value}, exception: ${t.message}")
            }
        })
    }
}
