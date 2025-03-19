package com.denmatoxi.okr_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denmatoxi.okr_mobile.dataClasses.Application
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassDetailsActivity : AppCompatActivity() {

    private lateinit var idValueView: TextView
    private lateinit var userIdValueView: TextView
    private lateinit var descriptionValueView: TextView
    private lateinit var fromDateValueView: TextView
    private lateinit var toDateValueView: TextView
    private lateinit var statusValueView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_details)

        findViewById<TextView>(R.id.id_value).text = intent.getStringExtra("passId")
        findViewById<TextView>(R.id.description_value).text = intent.getStringExtra("passDescription")
        findViewById<TextView>(R.id.user_id_value).text = intent.getStringExtra("passUserId")
        findViewById<TextView>(R.id.from_date_value).text = intent.getStringExtra("passFromDate")
        findViewById<TextView>(R.id.to_date_value).text = intent.getStringExtra("passToDate")
        findViewById<TextView>(R.id.status_value).text = intent.getStringExtra("passStatus")

        Log.d("PassDetailsActivity", findViewById<TextView>(R.id.status_value).text.toString())
        /*
        idValueView = findViewById(R.id.id_value)
        userIdValueView = findViewById(R.id.user_id_value)
        descriptionValueView = findViewById(R.id.description_value)
        fromDateValueView = findViewById(R.id.from_date_value)
        toDateValueView = findViewById(R.id.to_date_value)
        findViewById<TextView>(R.id.status_value).text =

        val passId = intent.getStringExtra("passId")
        val passUserId = intent.getStringExtra("passUserId")
        val passDescription = intent.getStringExtra("passDescription")
        val passFromDate = intent.getStringExtra("passFromDate")
        val passToDate = intent.getStringExtra("passToDate")
        val passStatus = intent.getStringExtra("passStatus")
        */

    }
    /*
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
    */


}
