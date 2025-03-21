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
import com.denmatoxi.okr_mobile.CreateExtensionDialog
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.RetrofitClient
import com.denmatoxi.okr_mobile.SessionManager
import com.denmatoxi.okr_mobile.adapters.ApplicationListAdapter
import com.denmatoxi.okr_mobile.adapters.ExtensionListAdapter
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationExtensionRequest
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationExtensionResponse
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationRequest
import com.denmatoxi.okr_mobile.dataClasses.CreateApplicationResponse
import com.denmatoxi.okr_mobile.dataClasses.Extension
import com.denmatoxi.okr_mobile.dataClasses.GetApplicationResponse
import com.denmatoxi.okr_mobile.viewModels.ApplicationListViewModel
import com.denmatoxi.okr_mobile.viewModels.ExtensionListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExtensionListActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var extensionListAdapter: ExtensionListAdapter

    private lateinit var createExtensionButton: FloatingActionButton
    private lateinit var createExtensionDialog: CreateExtensionDialog

    private val extensionListViewModel: ExtensionListViewModel by viewModels()
    private val ctx = this

    private lateinit var applicationId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Get intent", "application id from intent: ${intent.getStringExtra("applicationId")}")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension_list)

        applicationId = intent.getStringExtra("applicationId")!!

        recyclerView = findViewById(R.id.rv_extensions_activity_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        extensionListAdapter = ExtensionListAdapter(emptyList()) { extensionItem -> openExtensionDetails(extensionItem) }
        recyclerView.adapter = extensionListAdapter

        extensionListViewModel.extensions.observe(this) { newData ->
            extensionListAdapter.updateData(newData)
        }

        createExtensionButton = findViewById(R.id.btn_extension_activity_add)
        createExtensionButton.setOnClickListener {
            createExtensionDialog = CreateExtensionDialog(this) { extension: CreateApplicationExtensionRequest ->
                extension.extensionToDate += "T23:59:59.000Z"

                val call = RetrofitClient.instance(this).createExtension(applicationId, extension)
                call.enqueue(object : Callback<CreateApplicationExtensionResponse> {
                    override fun onResponse(call: Call<CreateApplicationExtensionResponse>, response: Response<CreateApplicationExtensionResponse>) {
                        if (response.isSuccessful) {
                            extensionListViewModel.loadExtensions(ctx, applicationId)
                            Log.d("Created extension", "success")
                        }
                        else {
                            Log.d("Created extension", "bad request")
                        }
                    }
                    override fun onFailure(call: Call<CreateApplicationExtensionResponse>, t: Throwable) {
                        Log.d("Created extension", "failure")
                    }
                })
            }
            createExtensionDialog.show()
        }

        extensionListViewModel.loadExtensions(this, applicationId)
    }


    private fun openExtensionDetails(extensionItem: View) {
        val intent = Intent(this, ExtensionDetailsActivity::class.java)
        intent.putExtra("applicationId", applicationId)
        intent.putExtra("extensionIndex", recyclerView.getChildLayoutPosition(extensionItem))

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
                val dialog = createExtensionDialog
                dialog?.handleImageResult(it)
            }
        }
    }

}

