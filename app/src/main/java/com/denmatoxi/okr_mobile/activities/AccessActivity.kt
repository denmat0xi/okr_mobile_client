package com.denmatoxi.okr_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.SessionManager

class AccessActivity : AppCompatActivity() {

    private lateinit var btnSignIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access)

        btnSignUp = findViewById(R.id.btn_access_sign_up)
        btnSignIn = findViewById(R.id.btn_access_sign_in)
        sessionManager = SessionManager(this)

        btnSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart()
    {
        super.onStart()
        if (!sessionManager.fetchToken().isNullOrEmpty()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }



}