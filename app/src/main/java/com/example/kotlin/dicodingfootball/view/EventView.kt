package com.example.kotlin.dicodingfootball.view

import com.example.kotlin.dicodingfootball.entity.EventEntity

interface EventView {
    fun showTeamList(list: List<EventEntity>?)
}