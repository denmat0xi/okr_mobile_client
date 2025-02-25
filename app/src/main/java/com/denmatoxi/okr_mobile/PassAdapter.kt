package com.denmatoxi.okr_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denmatoxi.okr_mobile.DataClasses.Pass

class PassAdapter(
    private var passes: List<Pass>,
    private val onItemClick: (Pass) -> Unit
) : RecyclerView.Adapter<PassAdapter.PassViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pass, parent, false)
        return PassViewHolder(view)
    }

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {
        val pass = passes[position]
        holder.bind(pass)
    }

    override fun getItemCount() = passes.size

    fun updateData(newPasses: List<Pass>) {
        passes = newPasses
        notifyDataSetChanged()
    }

    inner class PassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvReason: TextView = itemView.findViewById(R.id.tvReason)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvDates: TextView = itemView.findViewById(R.id.tvDates)

        fun bind(pass: Pass) {
            tvReason.text = pass.reason
            tvStatus.text = pass.status
            tvDates.text = "${pass.startDate} - ${pass.endDate}"

            itemView.setOnClickListener {
                onItemClick(pass)
            }
        }
    }
}

