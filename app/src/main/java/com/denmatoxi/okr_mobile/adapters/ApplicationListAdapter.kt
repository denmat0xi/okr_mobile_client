package com.denmatoxi.okr_mobile.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.dataClasses.Application

class ApplicationListAdapter(data: List<Application>, val onItemClick: (Application) -> Unit) :
    RecyclerView.Adapter<ApplicationListAdapter.ApplicationListViewHolder>() {

    private var applicationList: List<Application> = data

    inner class ApplicationListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvReason: TextView = itemView.findViewById(R.id.tvReason)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvDates: TextView = itemView.findViewById(R.id.tvDates)

        fun bind(application: Application) {
            tvReason.text = application.description
            tvStatus.text = application.status
            tvDates.text = "${application.fromDate} - ${application.toDate}"

            itemView.setOnClickListener {
                onItemClick(application)
            }
            Log.d("Bind", "binding happened with application reason ${application.description}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationListViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.item_application, parent, false)
        return ApplicationListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApplicationListViewHolder, position: Int) {
        holder.bind(applicationList[position])
    }

    override fun getItemCount(): Int = applicationList.size

    fun updateData(data: List<Application>) {
        //Log.d("update data", data.toString())
        applicationList = data
        notifyDataSetChanged()
    }
}