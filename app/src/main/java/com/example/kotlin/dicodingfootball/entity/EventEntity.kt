package com.example.kotlin.dicodingfootball.entity

data class EventEntity(
        val idEvent: String,
        val strDate: String,
        val strHomeTeam: String,
        val strAwayTeam: String,
        val intHomeScore: Int,
        val intAwayScore: Int
)