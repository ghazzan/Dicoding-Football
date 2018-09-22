package com.example.kotlin.dicodingfootball.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.entity.EventEntity

class MatchEventItem(view: View): RecyclerView.ViewHolder(view) {

    private val tvDate = view.findViewById<TextView>(R.id.tvEventDate)
    private val tvNameHome = view.findViewById<TextView>(R.id.tvHomeName)
    private val tvNameAway = view.findViewById<TextView>(R.id.tvAwayName)
    private val tvScoreHome = view.findViewById<TextView>(R.id.tvScoreHome)
    private val tvScoreAway = view.findViewById<TextView>(R.id.tvScoreAway)

    fun bindEvent(entity: EventEntity){
        tvDate.text = entity.strDate
        tvNameHome.text = entity.strHomeTeam
        tvNameAway.text = entity.strAwayTeam
        tvScoreHome.text = entity.intHomeScore.toString()
        tvScoreAway.text = entity.intAwayScore.toString()
    }
}