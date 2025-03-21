package com.denmatoxi.okr_mobile.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.R
import com.denmatoxi.okr_mobile.dataClasses.Application
import com.denmatoxi.okr_mobile.dataClasses.Extension

class ExtensionListAdapter(data: List<Extension>, val onItemClick: (View) -> Unit) :
    RecyclerView.Adapter<ExtensionListAdapter.ExtensionListViewHolder>() {

    private var extensionList: List<Extension> = data

    inner class ExtensionListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvReason: TextView = itemView.findViewById(R.id.tv_extension_item_description)
        private val tvStatus: TextView = itemView.findViewById(R.id.tv_extension_item_status)
        private val tvDate: TextView = itemView.findViewById(R.id.tv_extension_item_to_date)

        fun bind(extension: Extension) {
            tvReason.text = extension.description
            tvStatus.text = extension.status
            tvDate.text = "${extension.extensionToDate}"

            itemView.setOnClickListener {
                onItemClick(itemView)
            }
            Log.d("Bind", "binding happened with application reason ${extension.description}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtensionListViewHolder {
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.item_extension, parent, false)
        return ExtensionListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExtensionListViewHolder, position: Int) {
        holder.bind(extensionList[position])
    }

    override fun getItemCount(): Int = extensionList.size

    fun updateData(data: List<Extension>) {
        //Log.d("update data", data.toString())
        extensionList = data
        notifyDataSetChanged()
    }
}