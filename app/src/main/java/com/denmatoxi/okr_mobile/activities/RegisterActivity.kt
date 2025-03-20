package com.denmatoxi.okr_mobile.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.RetrofitClient
import com.denmatoxi.okr_mobile.SessionManager
import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.LoginRequest
import com.denmatoxi.okr_mobile.dataClasses.RegisterRequest
import com.denmatoxi.okr_mobile.viewModels.AuthViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()


    fun register(context: Context, registerRequest: RegisterRequest, onResult: (Boolean, String?) -> Unit) {
        val call = RetrofitClient.instance(context).register(registerRequest)
        call.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    Log.d("Register operation", response.body()!!.token)
                    onResult(true, response.body()?.token)
                } else {
                    Log.d("Register operation", "No login")
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("Register operation", "Failed (onFailure invoked)")
                onResult(false, null)
            }
        })
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etName = findViewById<EditText>(R.id.et_register_name)
        val etSurname = findViewById<EditText>(R.id.et_register_surname)
        val etPatronymic = findViewById<EditText>(R.id.et_register_patronymic)
        val etEmail = findViewById<EditText>(R.id.et_register_email)
        val etPassword = findViewById<EditText>(R.id.et_register_password)
        val etConfirmPassword = findViewById<EditText>(R.id.et_register_confirm_password)

        val btnCancel = findViewById<Button>(R.id.btn_register_cancel)
        val btnRegister = findViewById<Button>(R.id.btn_register_ok)

        val tvStatus = findViewById<TextView>(R.id.tv_register_status)


        btnRegister.setOnClickListener {
            //TODO("Ввести валидации, верстку")
            //TODO("Ввести обработки разных ошибок (неверный email, пароли не совпадают, итд итп)")
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            val name = etName.text.toString()
            val surname = etSurname.text.toString()
            val patronymic = etPatronymic.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                val requestBody = RegisterRequest(
                    email = email,
                    password = password,
                    name = name,
                    surname = surname,
                    patronymic = patronymic,
                    confirmPassword = confirmPassword)

                this.register(this, requestBody) { success, token ->
                    if (success) {
                        val sessionManager = SessionManager(this)
                        sessionManager.saveToken(token)
                        tvStatus.text = "Вход успешен"
                        startActivity(Intent(this, MainActivity::class.java))
                        Log.w("Register Activity","Вошел")
                        finish()
                    } else {
                        tvStatus.text = "Ошибка регистрации"
                        Log.w("Register Activity","Не вошел")
                    }
                }

                //startActivity(Intent(this, TempActivity::class.java))
            } else {
                tvStatus.text = "Введите логин и пароль"
            }
        }
    }
}