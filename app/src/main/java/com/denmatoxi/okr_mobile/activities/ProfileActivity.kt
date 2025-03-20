package com.denmatoxi.okr_mobile.activities

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.RetrofitClient
import com.denmatoxi.okr_mobile.dataClasses.ProfileResponse
import com.denmatoxi.okr_mobile.dataClasses.EditProfileRequest
import com.denmatoxi.okr_mobile.dataClasses.EditProfileResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvSurname: TextView = findViewById(R.id.tv_surname)
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvPatronymic: TextView = findViewById(R.id.tv_patronymic)
        val tvEmail: TextView = findViewById(R.id.tv_email)
        val fabEdit: FloatingActionButton = findViewById(R.id.fab_edit)

        getProfileData(tvSurname, tvName, tvPatronymic, tvEmail)

        fabEdit.setOnClickListener {
            showEditProfileDialog(tvSurname, tvName, tvPatronymic, tvEmail)
        }
    }

    private fun getProfileData(
        tvSurname: TextView,
        tvName: TextView,
        tvPatronymic: TextView,
        tvEmail: TextView
    ) {
        val call = RetrofitClient.instance(this).getProfileDetails()

        call.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    val profile = response.body()
                    if (profile != null) {
                        tvSurname.text = profile.surname
                        tvName.text = profile.name
                        tvPatronymic.text = profile.patronymic
                        tvEmail.text = profile.email
                    }
                } else {
                    Toast.makeText(this@ProfileActivity, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Toast.makeText(this@ProfileActivity, "Ошибка сети", Toast.LENGTH_SHORT).show()
                Log.e("ProfileActivity", "Ошибка загрузки данных", t)
            }
        })
    }

    private fun showEditProfileDialog(tvSurname: TextView, tvName: TextView, tvPatronymic: TextView, tvEmail: TextView) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_profile, null)
        val etSurname: EditText = dialogView.findViewById(R.id.et_surname)
        val etName: EditText = dialogView.findViewById(R.id.et_name)
        val etPatronymic: EditText = dialogView.findViewById(R.id.et_patronymic)

        etSurname.setText(tvSurname.text)
        etName.setText(tvName.text)
        etPatronymic.setText(tvPatronymic.text)

        AlertDialog.Builder(this)
            .setTitle("Редактировать профиль")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val newSurname = etSurname.text.toString()
                val newName = etName.text.toString()
                val newPatronymic = etPatronymic.text.toString()
                updateProfileData(newSurname, newName, newPatronymic, tvSurname, tvName, tvPatronymic)
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun updateProfileData(
        surname: String,
        name: String,
        patronymic: String,
        tvSurname: TextView,
        tvName: TextView,
        tvPatronymic: TextView
    ) {
        val request = EditProfileRequest(surname, name, patronymic)
        val call = RetrofitClient.instance(this).updateProfile(request)

        call.enqueue(object : Callback<EditProfileResponse> {
            override fun onResponse(
                call: Call<EditProfileResponse>,
                response: Response<EditProfileResponse>
            ) {
                if (response.isSuccessful) {
                    tvSurname.text = surname
                    tvName.text = name
                    tvPatronymic.text = patronymic
                    Toast.makeText(this@ProfileActivity, "Данные обновлены", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProfileActivity, "Ошибка обновления данных", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                Toast.makeText(this@ProfileActivity, "Ошибка сети", Toast.LENGTH_SHORT).show()
                Log.e("ProfileActivity", "Ошибка обновления данных", t)
            }
        })
    }
}
