package com.example.kotlin.dicodingfootball.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.`interface`.OnItemClickListener
import com.example.kotlin.dicodingfootball.entity.FootballEntity
import com.example.kotlin.dicodingfootball.item.FootballItem

class FootballTeamAdapter(private val context: Context, private val items: List<FootballEntity>, private val listener: OnItemClickListener):
        RecyclerView.Adapter<FootballItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballItem {
        return FootballItem(LayoutInflater.from(context).inflate(R.layout.item_team_football, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FootballItem, position: Int) {
        holder.bindItem(items[position], listener)
    }

}