package com.denmatoxi.okr_mobile.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.RetrofitClient
import com.denmatoxi.okr_mobile.dataClasses.Extension
import com.denmatoxi.okr_mobile.dataClasses.GetApplicationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtensionListViewModel : ViewModel() {

    private val _extensions = MutableLiveData<List<Extension>>()
    val extensions: LiveData<List<Extension>> = _extensions

    fun loadExtensions(context: Context, applicationId: String) {
        RetrofitClient.instance(context).getApplication(applicationId).enqueue(object : Callback<GetApplicationResponse> {
            override fun onResponse(
                call: Call<GetApplicationResponse>,
                response: Response<GetApplicationResponse>
            ) {
                if (response.isSuccessful) {
                    _extensions.value = response.body()?.extensions ?: emptyList()
                    Log.d("onResponse, success", "data is ${_extensions.value}")
                } else {
                    _extensions.value = emptyList()
                    Log.d("onResponse, not success", "data is ${_extensions.value}")
                }
            }

            override fun onFailure(call: Call<GetApplicationResponse>, t: Throwable) {
                _extensions.value = emptyList()
                Log.d("onResponse, failure", "data is ${_extensions.value}, exception: ${t.message}")
            }
        })
    }
}
