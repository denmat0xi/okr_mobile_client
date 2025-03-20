package com.denmatoxi.okr_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.adapters.PassListAdapter
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.viewModels.ApplicationListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PassListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var passListAdapter: PassListAdapter

    private lateinit var addPassButton: FloatingActionButton
    private val applicationListViewModel: ApplicationListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_list)


        recyclerView = findViewById(R.id.rvPasses)
        recyclerView.layoutManager = LinearLayoutManager(this)
        passListAdapter = PassListAdapter(emptyList()) { pass -> openPassDetails(pass) }
        recyclerView.adapter = passListAdapter

        applicationListViewModel.passes.observe(this) { newData ->
            passListAdapter.updateData(newData)
            Log.d("Observe", "observer caught data update, data is ${newData}")
        }

        addPassButton = findViewById(R.id.btn_add_pass)
        addPassButton.setOnClickListener { view: View ->
            startActivity(Intent(this, CreateApplicationActivity::class.java))
        }

        applicationListViewModel.loadPasses()
    }

    private fun openPassDetails(pass: Application) {
        val intent = Intent(this, PassDetailsActivity::class.java)
        intent.putExtra("passId", pass.id.toString())
        intent.putExtra("passUserId", pass.userId.toString())
        intent.putExtra("passDescription", pass.reason)
        intent.putExtra("passFromDate", pass.startDate)
        intent.putExtra("passToDate", pass.endDate)
        intent.putExtra("passStatus", pass.status)
        startActivity(intent)
    }
}

