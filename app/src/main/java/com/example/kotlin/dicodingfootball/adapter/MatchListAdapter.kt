package com.example.kotlin.dicodingfootball.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.`interface`.OnEventClickListener
import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.example.kotlin.dicodingfootball.item.MatchEventItem
import com.example.kotlin.dicodingfootball.table.Favorite

class MatchListAdapter(): RecyclerView.Adapter<MatchEventItem>() {

    private var context: Context? = null
    private var listener: OnEventClickListener? = null
    private var eventItems: List<EventEntity>? = null
    private var favItems: List<Favorite>? = null

    constructor(mContext: Context, items: List<EventEntity>, mListener: OnEventClickListener): this(){
        context = mContext
        eventItems = items
        listener = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchEventItem {
        return MatchEventItem(LayoutInflater.from(context).inflate(R.layout.item_match_football, parent, false))
    }

    override fun getItemCount(): Int {
        return eventItems?.size ?: 0
    }

    override fun onBindViewHolder(holder: MatchEventItem, position: Int) {
        holder.bindEvent(eventItems!![position], listener!!)
    }
}