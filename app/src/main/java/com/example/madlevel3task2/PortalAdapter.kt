package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3task2.databinding.ItemPortalBinding
import com.example.madlevel3task2.models.Portal

class PortalAdapter (private val portals: List<Portal>)
    : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PortalAdapter.ViewHolder, position: Int) {
        holder.bind(portals[position])
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemPortalBinding.bind(itemView)

        fun bind(portal: Portal) {
            binding.tvName.text = portal.name
            binding.tvUrl.text = portal.url
        }

    }


}





