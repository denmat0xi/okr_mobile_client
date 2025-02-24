package com.denmatoxi.okr_mobile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class CreatePassActivity : AppCompatActivity() {

    private lateinit var etReason: EditText
    private lateinit var btnStartDate: Button
    private lateinit var btnEndDate: Button
    private lateinit var tvSelectedDates: TextView
    private lateinit var btnAttachFile: Button
    private lateinit var tvFileName: TextView
    private lateinit var btnSubmit: Button

    private var startDate: String? = null
    private var endDate: String? = null
    private var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pass)

        etReason = findViewById(R.id.etReason)
        btnStartDate = findViewById(R.id.btnStartDate)
        btnEndDate = findViewById(R.id.btnEndDate)
        tvSelectedDates = findViewById(R.id.tvSelectedDates)
        btnAttachFile = findViewById(R.id.btnAttachFile)
        tvFileName = findViewById(R.id.tvFileName)
        btnSubmit = findViewById(R.id.btnSubmit)

        btnStartDate.setOnClickListener { selectDate(true) }
        btnEndDate.setOnClickListener { selectDate(false) }
        btnAttachFile.setOnClickListener { pickFile() }
        btnSubmit.setOnClickListener { submitPassRequest() }
    }

    @SuppressLint("SetTextI18n")
    private fun selectDate(isStart: Boolean) {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                .format(GregorianCalendar(year, month, dayOfMonth).time)

            if (isStart) {
                startDate = selectedDate
            } else {
                endDate = selectedDate
            }
            tvSelectedDates.text = "Даты: $startDate - $endDate"
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePicker.show()
    }

    private fun pickFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, FILE_PICKER_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICKER_REQUEST && resultCode == RESULT_OK) {
            fileUri = data?.data
            tvFileName.text = fileUri?.lastPathSegment ?: "Файл выбран"
        }
    }

    private fun submitPassRequest() {
        val reason = etReason.text.toString()
        if (reason.isEmpty() || startDate == null || endDate == null) {
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Реализовать отправку через ViewModel с Retrofit
        Toast.makeText(this, "Заявка отправлена!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val FILE_PICKER_REQUEST = 100
    }
}
