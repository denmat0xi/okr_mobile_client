package com.denmatoxi.okr_mobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PassListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var passAdapter: PassAdapter
    private val passes = mutableListOf<Pass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_list)

        recyclerView = findViewById(R.id.rvPasses)
        recyclerView.layoutManager = LinearLayoutManager(this)

        passAdapter = PassAdapter(passes) { pass ->
            openPassDetails(pass)
        }

        recyclerView.adapter = passAdapter

        loadPasses()
    }

    private fun loadPasses() {
        // TODO реализовать логику загрузки пропусков
        // пример
        passes.add(Pass(1, "Болезнь", "2025-02-01", "2025-02-03", "На проверке", null))
        passes.add(Pass(2, "Семинар", "2025-02-10", "2025-02-12", "Одобрено", null))
        passAdapter.notifyDataSetChanged()
    }

    private fun openPassDetails(pass: Pass) {
        val intent = Intent(this, PassDetailsActivity::class.java)
        intent.putExtra("passId", pass.id)
        startActivity(intent)
    }
}
