package com.example.kotlin.dicodingfootball.view

import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.example.kotlin.dicodingfootball.table.Favorite

interface EventView {
    fun showTeamList(list: List<EventEntity>?)

    interface DetailEvent {
        fun showDetail(data: EventEntity)
        fun favoriteResult()
        fun unfavouriteResult()
    }

    interface LoveEvent{
        fun showLoveList(list: List<Favorite>?)
    }
}