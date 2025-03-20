package com.denmatoxi.okr_mobile.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.dataClasses.Application

class PassListAdapter(data: List<Application>, val onItemClick: (Application) -> Unit) :
    RecyclerView.Adapter<PassListAdapter.PassListViewHolder>() {

    private var passList: List<Application> = data

    inner class PassListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvReason: TextView = itemView.findViewById(R.id.tvReason)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvDates: TextView = itemView.findViewById(R.id.tvDates)

        fun bind(pass: Application) {
            tvReason.text = pass.reason
            tvStatus.text = pass.status
            tvDates.text = "${pass.startDate} - ${pass.endDate}"

            itemView.setOnClickListener {
                onItemClick(pass)
            }
            Log.d("Bind", "binding happened with Pass reason ${pass.reason}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassListViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.item_pass, parent, false)
        return PassListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PassListViewHolder, position: Int) {
        if (position > 0 && position < passList.size)
            holder.bind(passList[position])
    }

    override fun getItemCount(): Int = passList.size

    fun updateData(data: List<Application>) {
        //Log.d("update data", data.toString())
        passList = data
        notifyDataSetChanged()
    }
}