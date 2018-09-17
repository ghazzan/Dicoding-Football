package com.example.kotlin.dicodingfootball.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.`interface`.OnItemClickListener
import com.example.kotlin.dicodingfootball.entity.FootballEntity

class FootballItem(val view: View): RecyclerView.ViewHolder(view) {

    private val mainLayout = view.findViewById<RelativeLayout>(R.id.rlTeamFootball)
    private val name = view.findViewById<TextView>(R.id.tvTeamFootball)
    private val image = view.findViewById<ImageView>(R.id.ivTeamFootball)

    fun bindItem(entity: FootballEntity, listener: OnItemClickListener){
        name.text = entity.name
        Glide.with(view).load(entity.image).into(image)

        mainLayout.setOnClickListener {
            listener.onClick(entity)
        }
    }

}