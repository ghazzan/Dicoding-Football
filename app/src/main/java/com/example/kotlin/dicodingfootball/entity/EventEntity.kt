package com.example.kotlin.dicodingfootball.entity

data class EventEntity(
        val idEvent: String? = null,
        val strDate: String? = null,
        val strHomeTeam: String? = null,
        val strAwayTeam: String? = null,
        val idHomeTeam: String,
        val idAwayTeam: String,
        val strHomeFormation: String?,
        val strAwayFormation: String?,
        val strHomeGoalDetails: String?,
        val strAwayGoalDetails: String?,
        val strHomeLineupGoalkeeper: String?,
        val strAwayLineupGoalkeeper: String?,
        val strHomeLineupDefense: String?,
        val strAwayLineupDefense: String?,
        val strHomeLineupMidfield: String?,
        val strAwayLineupMidfield: String?,
        val strHomeLineupForward: String?,
        val strAwayLineupForward: String?,
        val strHomeLineupSubstitutes: String?,
        val strAwayLineupSubstitutes: String?,
        val intHomeScore: Int? = null,
        val intAwayScore: Int? = null,
        val intHomeShots: Int? = null,
        val intAwayShots: Int? = null
)