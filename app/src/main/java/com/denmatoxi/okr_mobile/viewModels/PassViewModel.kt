package com.denmatoxi.okr_mobile.viewModels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.dataClasses.FileUploadResponse
import com.denmatoxi.okr_mobile.FileUtils
import com.denmatoxi.okr_mobile.dataClasses.Pass
import com.denmatoxi.okr_mobile.dataClasses.PassListResponse
import com.denmatoxi.okr_mobile.dataClasses.PassRequest
import com.denmatoxi.okr_mobile.dataClasses.PassResponse
import com.denmatoxi.okr_mobile.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassViewModel : ViewModel() {

    fun createPass(
        reason: String,
        startDate: String,
        endDate: String,
        fileUrl: String?,
        onResult: (Boolean) -> Unit
    ) {
        val call = RetrofitClient.instance.createPass(PassRequest(reason, startDate, endDate, fileUrl))
        call.enqueue(object : Callback<PassResponse> {
            override fun onResponse(call: Call<PassResponse>, response: Response<PassResponse>) {
                onResult(response.isSuccessful)
            }

            override fun onFailure(call: Call<PassResponse>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun uploadFile(context: Context, fileUri: Uri, onResult: (Boolean, String?) -> Unit) {
        val file = FileUtils.getFileFromUri(context, fileUri) ?: return onResult(false, null)

        val requestFile = RequestBody.create("application/pdf".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val description = RequestBody.create("text/plain".toMediaTypeOrNull(), "Документ для пропуска")

        RetrofitClient.instance.uploadFile(body, description).enqueue(object : Callback<FileUploadResponse> {
            override fun onResponse(call: Call<FileUploadResponse>, response: Response<FileUploadResponse>) {
                if (response.isSuccessful) {
                    onResult(true, response.body()?.fileUrl)
                } else {
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<FileUploadResponse>, t: Throwable) {
                onResult(false, null)
            }
        })
    }

    private val _passes = MutableLiveData<List<Pass>>()
    val passes: LiveData<List<Pass>> = _passes

    fun loadPasses() {
        RetrofitClient.instance.getPasses().enqueue(object : Callback<PassListResponse> {
            override fun onResponse(call: Call<PassListResponse>, response: Response<PassListResponse>) {
                if (response.isSuccessful) {
                    _passes.value = response.body()?.passes
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
