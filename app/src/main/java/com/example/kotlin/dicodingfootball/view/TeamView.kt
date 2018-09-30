package com.example.kotlin.dicodingfootball.view

import com.example.kotlin.dicodingfootball.entity.TeamEntity

interface TeamView {
    fun showTeam(teamCode: Int, data: TeamEntity)
}