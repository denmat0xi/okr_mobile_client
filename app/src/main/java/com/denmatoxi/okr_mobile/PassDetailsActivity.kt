package com.denmatoxi.okr_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denmatoxi.okr_mobile.DataClasses.Pass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassDetailsActivity : AppCompatActivity() {

    private lateinit var tvReason: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvDates: TextView
    private lateinit var btnDownloadFile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_details)

        tvReason = findViewById(R.id.tvReason)
        tvStatus = findViewById(R.id.tvStatus)
        tvDates = findViewById(R.id.tvDates)
        btnDownloadFile = findViewById(R.id.btnDownloadFile)

        val passId = intent.getIntExtra("passId", -1)

        loadPassDetails(passId)
    }

    private fun loadPassDetails(passId: Int) {
        RetrofitClient.instance.getPass(passId).enqueue(object : Callback<Pass> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Pass>, response: Response<Pass>) {
                if (response.isSuccessful) {
                    val pass = response.body()
                    pass?.let {
                        tvReason.text = "Причина пропуска: ${it.reason}"
                        tvStatus.text = "Статус: ${it.status}"
                        tvDates.text = "Дата начала: ${it.startDate}\nДата окончания: ${it.endDate}"

                        if (!it.fileUrl.isNullOrEmpty()) {
                            btnDownloadFile.visibility = View.VISIBLE
                            btnDownloadFile.setOnClickListener {
                                downloadFile(it.fileUrl!!)
                            }
                        } else {
                            btnDownloadFile.visibility = View.GONE
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Pass>, t: Throwable) {
                Toast.makeText(this@PassDetailsActivity, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
