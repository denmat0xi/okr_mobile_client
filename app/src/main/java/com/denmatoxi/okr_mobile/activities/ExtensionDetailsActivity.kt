package com.denmatoxi.okr_mobile.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.RetrofitClient
import com.denmatoxi.okr_mobile.SessionManager
import com.denmatoxi.okr_mobile.dataClasses.GetApplicationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtensionDetailsActivity : BaseActivity() {

    private lateinit var btnBack: Button
    private lateinit var imageView: ImageView

    private lateinit var applicationId: String
    private var extensionIndex: Int = 0

    private val ctx = this

    private fun base64ToBitmap(string: String) : Bitmap {
        return try {
            val decodedString = Base64.decode(string, Base64.DEFAULT)

            BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        } catch (e: IllegalArgumentException) {
            Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_extension_details)
        btnBack = findViewById(R.id.btn_extension_details_back)
        imageView = findViewById(R.id.iv_extension_details_image_application)
        applicationId = intent.getStringExtra("applicationId").toString()
        extensionIndex = intent.getIntExtra("extensionIndex", extensionIndex)

        val call = RetrofitClient.instance(this).getApplication(intent.getStringExtra("applicationId")!!)
        call.enqueue(object : Callback<GetApplicationResponse> {
            override fun onResponse(call: Call<GetApplicationResponse>, response: Response<GetApplicationResponse>) {
                if (response.isSuccessful) {
                    findViewById<TextView>(R.id.tv_extension_details_description_value).text  =
                        response.body()!!.extensions[extensionIndex].description
                    findViewById<TextView>(R.id.tv_extension_details_to_date_value).text =
                        response.body()!!.extensions[extensionIndex].extensionToDate
                    findViewById<TextView>(R.id.tv_extension_details_status_value).text =
                        response.body()!!.extensions[extensionIndex].status
                    findViewById<ImageView>(R.id.iv_extension_details_image_application).setImageBitmap(base64ToBitmap(
                        response.body()!!.extensions[extensionIndex].image))
                }
                else {
                    Log.d("ApplicationActivity","Failure")
                }
            }

            override fun onFailure(call: Call<GetApplicationResponse>, t: Throwable) {
                SessionManager(ctx).clearToken()
                Log.d("Logout", "Failed (onFailure invoked)")
            }
        })


        btnBack.setOnClickListener {
            finish()
        }
    }
    
    
}
