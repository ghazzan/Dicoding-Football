package com.example.kotlin.dicodingfootball.table

data class Favorite(
        val id: Int = 0,
        val idEvent: String?,
        val strHomeTeam: String?,
        val strAwayTeam: String?,
        val intHomeScore: Int?,
        val intAwayScore: Int?,
        val strDate: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "table_favorite"
        const val ID: String = "id"
        const val EVENT_ID: String = "event_id"
        const val TEAM_HOME_NAME: String = "team_home_name"
        const val TEAM_AWAY_NAME: String = "team_away_name"
        const val TEAM_HOME_SCORE: String = "team_home_score"
        const val TEAM_AWAY_SCORE: String = "team_away_score"
        const val EVENT_DATE: String = "event_date"
    }
}