/*package com.denmatoxi.okr_mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.dataClasses.Pass
import com.denmatoxi.okr_mobile.viewModels.PassListViewModel

class PassListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var passAdapter: PassAdapter
    private lateinit var passListViewModel: PassListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_list)

        recyclerView = findViewById(R.id.rvPasses)
        recyclerView.layoutManager = LinearLayoutManager(this)

        passAdapter = PassAdapter(emptyList()) { pass ->
            openPassDetails(pass)
        }
        recyclerView.adapter = passAdapter

        passListViewModel = ViewModelProvider(this)[PassListViewModel::class.java]

        passListViewModel.passes.observe(this, Observer { passes ->
            passAdapter.updateData(passes)
        })

        passListViewModel.loadPasses()
    }

    private fun openPassDetails(pass: Pass) {
        val intent = Intent(this, PassDetailsActivity::class.java)
        intent.putExtra("passId", pass.id)
        startActivity(intent)
    }
}*/

