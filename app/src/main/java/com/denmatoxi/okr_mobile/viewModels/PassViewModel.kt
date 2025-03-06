package com.denmatoxi.okr_mobile.viewModels

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denmatoxi.okr_mobile.FileUtils
import com.denmatoxi.okr_mobile.dataClasses.FileUploadResponse
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

    // LiveData для хранения списка пропусков
    private val _passes = MutableLiveData<List<Pass>>()
    val passes: LiveData<List<Pass>> = _passes

    // LiveData для хранения состояния загрузки
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // LiveData для хранения состояния ошибки
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Метод для создания пропуска
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
        /*
        val passRequest = Pass(id, userId, reason, startDate, endDate, status, fileUrl)
        RetrofitClient.instance.createPass(passRequest).enqueue(object : Callback<Pass> {
            override fun onResponse(call: Call<PassResponse>, response: Response<PassResponse>) {
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<PassResponse>, t: Throwable) {
                onResult(false)
            }
        })
        */
    }


    // Метод для загрузки файла
    fun uploadFile(context: Context, fileUri: Uri, onResult: (Boolean, String?) -> Unit) {
        val file = FileUtils.getFileFromUri(context, fileUri) ?: return onResult(false, null)

        val requestFile = RequestBody.create("application/pdf".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val description = RequestBody.create("text/plain".toMediaTypeOrNull(), "Документ для пропуска")
        /*
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
        */
    }

    // Метод для загрузки списка пропусков
    fun loadPasses() {
        /*
        _loading.value = true // Отмечаем, что загрузка началась
        RetrofitClient.instance.getPasses().enqueue(object : Callback<PassListResponse> {
            override fun onResponse(call: Call<PassListResponse>, response: Response<PassListResponse>) {
                _loading.value = false // Завершаем загрузку
                if (response.isSuccessful) {
                    _passes.value = response.body()?.passes ?: emptyList()
                } else {
                    _passes.value = emptyList()
                    _error.value = "Не удалось загрузить пропуски"
                }
            }

            override fun onFailure(call: Call<PassListResponse>, t: Throwable) {
                _loading.value = false // Завершаем загрузку
                _passes.value = emptyList()
                _error.value = t.message ?: "Неизвестная ошибка"
            }
        })

         */
    }
}
