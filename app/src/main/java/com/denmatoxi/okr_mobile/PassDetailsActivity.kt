package com.denmatoxi.okr_mobile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
        // TODO реализовать логику загрузки подробностей пропуска
        tvReason.text = "Причина пропуска: Болезнь"
        tvStatus.text = "Статус: На проверке"
        tvDates.text = "Дата начала: 2025-02-01\nДата окончания: 2025-02-03"

        btnDownloadFile.setOnClickListener {
            // TODO реализовать логику загрузки файла при наличии
        }
    }
}
