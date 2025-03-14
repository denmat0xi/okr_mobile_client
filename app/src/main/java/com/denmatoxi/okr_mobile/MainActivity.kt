package com.denmatoxi.okr_mobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.denmatoxi.okr_mobile.dataClasses.Pass
import com.denmatoxi.okr_mobile.viewModels.AuthViewModel
import com.denmatoxi.okr_mobile.viewModels.PassListViewModel
import com.denmatoxi.okr_mobile.viewModels.PassViewModel

class MainActivity : AppCompatActivity() {
//    private val passListViewModel: PassListViewModel by viewModels()
//
//    private lateinit var passRecyclerView: RecyclerView
//    private lateinit var passAdapter: PassAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        passRecyclerView = findViewById(R.id.rvPasses)
//        passRecyclerView.layoutManager = LinearLayoutManager(this)
//
//
//        passAdapter = PassAdapter(passListViewModel.passes?.value ?: emptyList(), {pass:Pass -> Unit})

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
            val intent = Intent(this, CreatePassActivity::class.java)
            startActivity(intent)
        }
    }
}