package com.rozi.footballteam.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rozi.core.domain.model.Team
import com.rozi.footballteam.R
import com.rozi.footballteam.databinding.ItemTeamsBinding

class ListTeamAdapter(private val listTeam : List<Team>) : RecyclerView.Adapter<ListTeamAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback : OnItemClickCallBack

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_teams, parent, false)
        )


    override fun getItemCount() = listTeam.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listTeam[position]
        holder.bind(data)
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listTeam[position])}
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Team)
    }
}