package com.example.kotlin.dicodingfootball.network.response

import com.example.kotlin.dicodingfootball.entity.TeamEntity
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class DetailTeamResponse(val teams: List<TeamEntity>){
    class Deserializer: ResponseDeserializable<DetailTeamResponse> {
        override fun deserialize(content: String) = Gson().fromJson(content, DetailTeamResponse::class.java)
    }
}