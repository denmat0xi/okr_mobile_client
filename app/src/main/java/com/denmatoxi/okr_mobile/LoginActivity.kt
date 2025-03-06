package com.denmatoxi.okr_mobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.denmatoxi.okr_mobile.viewModels.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.login(username, password) { success, token ->
                    if (success) {
                        tvStatus.text = "Вход успешен"
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        tvStatus.text = "Ошибка входа"
                    }
                }
            } else {
                tvStatus.text = "Введите логин и пароль"
            }
        }
    }
}
