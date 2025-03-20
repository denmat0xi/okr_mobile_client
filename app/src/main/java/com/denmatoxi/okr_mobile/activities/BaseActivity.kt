package com.denmatoxi.okr_mobile.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denmatoxi.okr_mobile.SessionManager

open class BaseActivity : AppCompatActivity() {
    private fun checkForAuthentication() {
        if (!SessionManager(this).isAuthorized) {
            val intent = Intent(this, AccessActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForAuthentication()
    }
    override fun onStart() {
        super.onStart()
        checkForAuthentication()
    }
}