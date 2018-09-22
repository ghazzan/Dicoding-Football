package com.example.kotlin.dicodingfootball.presenter

import android.util.Log
import com.example.kotlin.dicodingfootball.network.response.MatchResponse
import com.example.kotlin.dicodingfootball.view.EventView
import com.example.kotlin.dicodingfootball.view.MainView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class EventPresenter(val eventView: EventView) {

    init {
        FuelManager.instance.basePath = "https://www.thesportsdb.com/"
    }

    fun getNextMatch(mainListener: MainView){
        mainListener.showLoading()
        Fuel.get("api/v1/json/1/eventsnextleague.php", listOf("id" to "4328"))
                .responseObject(MatchResponse.Deserializer()) { request, response, result ->
                    if (response.responseMessage == "OK"){
                        eventView.showTeamList(result.get().events)
                    }else{
                        mainListener.showWarning("Terjadi kesalahan ambil data")
                    }
                    mainListener.hideLoading()
                }
    }

    fun getPrevMatch(mainListener: MainView){
        mainListener.showLoading()
        Fuel.get("api/v1/json/1/eventspastleague.php", listOf("id" to "4328"))
                .responseObject(MatchResponse.Deserializer()) { request, response, result ->
                    if (response.responseMessage == "OK"){
                        eventView.showTeamList(result.get().events)
                    }else{
                        mainListener.showWarning("Terjadi kesalahan ambil data")
                    }
                    mainListener.hideLoading()
                }
    }
}