package com.example.kotlin.dicodingfootball.entity

data class EventEntity(
        val idEvent: String? = null,
        val strDate: String? = null,
        val strHomeTeam: String? = null,
        val strAwayTeam: String? = null,
        val idHomeTeam: String = "",
        val idAwayTeam: String = "",
        val strHomeFormation: String? = null,
        val strAwayFormation: String? = null,
        val strHomeGoalDetails: String? = null,
        val strAwayGoalDetails: String? = null,
        val strHomeLineupGoalkeeper: String? = null,
        val strAwayLineupGoalkeeper: String? = null,
        val strHomeLineupDefense: String? = null,
        val strAwayLineupDefense: String? = null,
        val strHomeLineupMidfield: String? = null,
        val strAwayLineupMidfield: String? = null,
        val strHomeLineupForward: String? = null,
        val strAwayLineupForward: String? = null,
        val strHomeLineupSubstitutes: String? = null,
        val strAwayLineupSubstitutes: String? = null,
        val intHomeScore: Int? = null,
        val intAwayScore: Int? = null,
        val intHomeShots: Int? = null,
        val intAwayShots: Int? = null
)