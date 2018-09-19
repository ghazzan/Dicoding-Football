package com.example.kotlin.dicodingfootball.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.entity.EventEntity

class MatchEventItem(view: View): RecyclerView.ViewHolder(view) {

    private val text1 = view.findViewById<TextView>(R.id.tvEvent1)

    fun bindEvent(entity: EventEntity){
        text1.text = entity.idEvent
    }
}