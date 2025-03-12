package com.denmatoxi.okr_mobile.viewModels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.FileUtils
import com.denmatoxi.okr_mobile.dataClasses.FileUploadResponse
import com.denmatoxi.okr_mobile.dataClasses.Pass
import com.denmatoxi.okr_mobile.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassViewModel : ViewModel() {

    private val _passes = MutableLiveData<List<Pass>>()
    val passes: LiveData<List<Pass>> = _passes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun createPass(
        id: Int,
        userId: Int,
        reason: String,
        startDate: String,
        endDate: String,
        status: String,
        fileUrl: String,
        onResult: (Boolean) -> Unit
    ) {
        val passRequest = Pass(id, userId, reason, startDate, endDate, status, fileUrl)
        RetrofitClient.instance.createPass(passRequest).enqueue(object : Callback<Pass> {
            override fun onResponse(call: Call<Pass>, response: Response<Pass>) {
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }
            override fun onFailure(call: Call<Pass>, t: Throwable) {
                onResult(false)
            }
        })
    }

    fun uploadFile(context: Context, fileUri: Uri, onResult: (Boolean, String?) -> Unit) {
        val file = FileUtils.getFileFromUri(context, fileUri) ?: return onResult(false, null)

        val requestFile = RequestBody.create("application/pdf".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val description =
            RequestBody.create("text/plain".toMediaTypeOrNull(), "Документ для пропуска")
        /*
        RetrofitClient.instance.uploadFile(body, description)
            .enqueue(object : Callback<FileUploadResponse> {
                override fun onResponse(
                    call: Call<FileUploadResponse>,
                    response: Response<FileUploadResponse>
                ) {
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

         */
    }



    fun loadPasses() {
        _loading.value = true
        RetrofitClient.instance.getPasses().enqueue(object : Callback<List<Pass>> {
            override fun onResponse(call: Call<List<Pass>>, response: Response<List<Pass>>) {
                if (response.isSuccessful) {
                    _passes.value = response.body() ?: emptyList()
                } else {
                    _passes.value = emptyList()
                    _error.value = "Не удалось загрузить пропуски"
                }
            }
            override fun onFailure(call: Call<List<Pass>>, t: Throwable) {
                _loading.value = false // Завершаем загрузку
                _passes.value = emptyList()
                _error.value = t.message ?: "Неизвестная ошибка"
            }
        })
    }
}
