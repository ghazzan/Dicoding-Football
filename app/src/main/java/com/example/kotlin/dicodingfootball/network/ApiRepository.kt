package com.example.kotlin.dicodingfootball.network

import java.net.URL

class ApiRepository {

    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}