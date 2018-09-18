package com.example.kotlin.dicodingfootball.network.response

import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class MatchResponse(val events: List<EventEntity>) {

    class Deserializer: ResponseDeserializable<MatchResponse>{
        override fun deserialize(content: String) = Gson().fromJson(content, MatchResponse::class.java)
    }
}