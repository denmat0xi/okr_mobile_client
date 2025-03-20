package com.denmatoxi.okr_mobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.viewModels.AuthViewModel

class MainActivity : AppCompatActivity() {

    private var createApplicationDialog: CreateApplicationDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.btn_to_login)
        val passListButton = findViewById<Button>(R.id.btn_to_pass_list)
        val createPassButton = findViewById<Button>(R.id.btn_to_create_pass)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        passListButton.setOnClickListener {
            val intent = Intent(this, PassListActivity::class.java)
            startActivity(intent)
        }
        createPassButton.setOnClickListener {
            showCreatePassDialog()
        }
    }

    private fun showCreatePassDialog() {
        createApplicationDialog = CreateApplicationDialog(this) { fromDate, toDate, description, imageBase64 ->
            //TODO реализовать загрузку на бэк
            Toast.makeText(this, "Пропуск создан", Toast.LENGTH_SHORT).show()
        }
        createApplicationDialog?.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CreateApplicationDialog.IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            createApplicationDialog?.handleImageResult(data)
        }
    }
}
