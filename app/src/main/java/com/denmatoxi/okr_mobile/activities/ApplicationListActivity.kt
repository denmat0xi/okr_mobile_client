package com.denmatoxi.okr_mobile.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.CreateApplicationDialog
import com.denmatoxi.okr_mobile.CreateApplicationDialog.Companion.IMAGE_PICK_CODE
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.RetrofitClient
import com.denmatoxi.okr_mobile.SessionManager
import com.denmatoxi.okr_mobile.adapters.ApplicationListAdapter
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationRequest
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationResponse
import com.denmatoxi.okr_mobile.dataClasses.GetApplicationResponse
import com.denmatoxi.okr_mobile.viewModels.ApplicationListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplicationListActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var passListAdapter: ApplicationListAdapter

    private lateinit var createApplicationButton: FloatingActionButton
    private lateinit var createApplicationDialog: CreateApplicationDialog

    private val applicationListViewModel: ApplicationListViewModel by viewModels()
    private lateinit var newApplication: CreateApplicationRequest
    private val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_list)


        recyclerView = findViewById(R.id.rvPasses)
        recyclerView.layoutManager = LinearLayoutManager(this)
        passListAdapter = ApplicationListAdapter(emptyList()) { application -> openPassDetails(application) }
        recyclerView.adapter = passListAdapter

        applicationListViewModel.passes.observe(this) { newData ->
            passListAdapter.updateData(newData)
        }

        createApplicationButton = findViewById(R.id.btn_add_pass)
        createApplicationButton.setOnClickListener {
            createApplicationDialog = CreateApplicationDialog(this) { application: CreateApplicationRequest ->
                application.toDate += "T23:59:59.000Z"
                application.fromDate += "T23:59:59.000Z"

                val call = RetrofitClient.instance(this).createApplication(application)
                call.enqueue(object : Callback<CreateApplicationResponse> {
                    override fun onResponse(call: Call<CreateApplicationResponse>, response: Response<CreateApplicationResponse>) {
                        if (response.isSuccessful) {
                            applicationListViewModel.loadPasses(ctx)
                            Log.d("Created application", "success")
                        }
                        else {
                            Log.d("Created application", "bad request")
                        }
                    }
                    override fun onFailure(call: Call<CreateApplicationResponse>, t: Throwable) {
                        Log.d("Created application", "failure")
                    }
                })
            }
            createApplicationDialog.show()
        }

        applicationListViewModel.loadPasses(this)
    }

    override fun onStart() {
        super.onStart()
        applicationListViewModel.loadPasses(this)
    }

    private fun openPassDetails(pass: Application) {
        val intent = Intent(this, ApplicationDetailsActivity::class.java)
        intent.putExtra("applicationId", pass.id)
//        intent.putExtra("passUserId", pass.userId)
//        intent.putExtra("passDescription", pass.description)
//        intent.putExtra("passFromDate", pass.fromDate)
//        intent.putExtra("passToDate", pass.toDate)
//        intent.putExtra("passStatus", pass.status)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            val intent = data
            intent?.let {
                val dialog = createApplicationDialog
                dialog?.handleImageResult(it)
            }
        }
    }

}

