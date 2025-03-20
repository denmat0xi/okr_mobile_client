package com.denmatoxi.okr_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.ApiService
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.RetrofitClient
import com.denmatoxi.okr_mobile.SessionManager
import com.denmatoxi.okr_mobile.adapters.ApplicationListAdapter
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.dataClasses.AuthResponse
import com.denmatoxi.okr_mobile.dataClasses.LoginRequest
import com.denmatoxi.okr_mobile.viewModels.ApplicationListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    private val passListViewModel: ApplicationListViewModel by viewModels()
//
    private lateinit var applicationRecyclerView: RecyclerView
    private lateinit var applicationAdapter: ApplicationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        passRecyclerView = findViewById(R.id.rvPasses)
//        passRecyclerView.layoutManager = LinearLayoutManager(this)
//
//
        val ctx = this
        applicationAdapter = ApplicationListAdapter(passListViewModel.passes.value ?: emptyList(), {application: Application -> Unit})

        val loginButton = findViewById<Button>(R.id.btn_to_login)
        val passListButton = findViewById<Button>(R.id.btn_to_pass_list)
        val createPassButton = findViewById<Button>(R.id.btn_to_create_pass)
        val registerButton = findViewById<Button>(R.id.btn_main_register)
        val testButton = findViewById<Button>(R.id.btn_test)
        val logoutButton = findViewById<Button>(R.id.btn_main_logout)

        testButton.setOnClickListener {
            val call = RetrofitClient.instance(this).getPasses()
            call.enqueue(object : Callback<List<Application>> {
                override fun onResponse(call: Call<List<Application>>, response: Response<List<Application>>) {
                    if (response.isSuccessful) {
                        Log.d("List of applications", "got ${response.body().toString()}")
                    } else {
                        Log.d("List of applications", "No list")
                    }
                }

                override fun onFailure(call: Call<List<Application>>, t: Throwable) {
                    Log.d("List of applications", "Failed (onFailure invoked)")
                }
            })
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        passListButton.setOnClickListener {
            val intent = Intent(this, ApplicationListActivity::class.java)
            startActivity(intent)
        }
        createPassButton.setOnClickListener {
            val intent = Intent(this, CreateApplicationActivity::class.java)
            startActivity(intent)
        }
        logoutButton.setOnClickListener {
            val call = RetrofitClient.instance(this).logout()
            call.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Log.d("Logout", "got logout")
                        SessionManager(ctx).clearToken()
                        startActivity(Intent(ctx, AccessActivity::class.java))
                    } else {
                        SessionManager(ctx).clearToken()
                        Log.d("Logout", "logout unsuccessful")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    SessionManager(ctx).clearToken()
                    Log.d("Logout", "Failed (onFailure invoked)")
                }
            })
        }
    }

    override fun onStart()
    {
        super.onStart()
        Log.d("onStart MainActivity", "current token ${SessionManager(this).fetchToken()}")

        if (SessionManager(this).fetchToken().isNullOrEmpty()) {
            Log.d("onStart MainActivity", "current token ${SessionManager(this).fetchToken()}")
            val intent = Intent(this, AccessActivity::class.java)
            startActivity(intent)
        }

    }
}