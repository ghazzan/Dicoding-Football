package com.example.kotlin.dicodingfootball.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.example.kotlin.dicodingfootball.item.MatchEventItem

class MatchListAdapter(private val context: Context, private val items: List<EventEntity>): RecyclerView.Adapter<MatchEventItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchEventItem {
        return MatchEventItem(LayoutInflater.from(context).inflate(R.layout.item_match_football, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MatchEventItem, position: Int) {
        holder.bindEvent(items[position])
    }
}