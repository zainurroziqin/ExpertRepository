package com.rozi.footballteam.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rozi.core.domain.model.Team
import com.rozi.footballteam.R
import com.rozi.footballteam.databinding.ItemTeamsBinding
import java.util.ArrayList

class ListTeamAdapter : RecyclerView.Adapter<ListTeamAdapter.ListViewHolder>() {

    private var listData = ArrayList<Team>()
    var onItemClick: ((Team) -> Unit)? = null

    fun setData(newListData: List<Team>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTeamsBinding.bind(itemView)
        fun bind(data: Team) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.teamFanart1)
                    .into(imgItemPhoto)
                tvItemName.text = data.team
                tvItemDescription.text = data.stadium
            }
        }

        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_teams, parent, false)
        )


    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}