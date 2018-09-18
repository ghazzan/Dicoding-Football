package com.example.kotlin.dicodingfootball.view

import com.example.kotlin.dicodingfootball.entity.EventEntity

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showWarning(message: String)
    fun showTeamList(list: List<EventEntity>?)
}