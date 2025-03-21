package com.denmatoxi.okr_mobile.activities

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

class ApplicationDetailsActivity : BaseActivity() {

    private lateinit var btnAddExtension: Button
    private lateinit var imageView: ImageView


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
        setContentView(R.layout.activity_application_details)
        btnAddExtension = findViewById(R.id.btn_edit_pass)
        imageView = findViewById(R.id.image_application)


        val call = RetrofitClient.instance(this).getApplication(intent.getStringExtra("applicationId")!!)
        call.enqueue(object : Callback<GetApplicationResponse> {
            override fun onResponse(call: Call<GetApplicationResponse>, response: Response<GetApplicationResponse>) {
                if (response.isSuccessful) {
                    findViewById<TextView>(R.id.id_value).text = response.body()?.id
                    findViewById<TextView>(R.id.description_value).text  = response.body()?.description
                    findViewById<TextView>(R.id.user_id_value).text = response.body()?.userId
                    findViewById<TextView>(R.id.from_date_value).text = response.body()?.fromDate
                    findViewById<TextView>(R.id.to_date_value).text = response.body()?.toDate
                    findViewById<TextView>(R.id.status_value).text = response.body()?.status
                    findViewById<ImageView>(R.id.image_application).setImageBitmap(base64ToBitmap(response.body()?.image!!))
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


        btnAddExtension.setOnClickListener {
            
        }

        Log.d("PassDetailsActivity", findViewById<TextView>(R.id.status_value).text.toString())
    }
    
    
}
