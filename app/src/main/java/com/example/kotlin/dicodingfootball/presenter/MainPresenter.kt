package com.example.kotlin.dicodingfootball.presenter

import android.util.Log
import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.example.kotlin.dicodingfootball.network.response.MatchResponse
import com.example.kotlin.dicodingfootball.view.MainView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class MainPresenter(mView: MainView) {

    private var view: MainView? = null
    private var model: EventEntity? = null

    init {
        this.view = mView
        FuelManager.instance.basePath = "https://www.thesportsdb.com/"
    }

    fun getMatchEvents(){
        view?.showLoading()
        Fuel.get("api/v1/json/1/eventsnextleague.php", listOf("id" to "4328"))
                .responseObject(MatchResponse.Deserializer()) { request, response, result ->
                    Log.i("TheSportsDB", "Real Fuel Response ${result.get().events[0].idEvent}")
                    if (response.responseMessage == "OK"){
                        view?.showTeamList(result.get().events)
                    }else{

                    }
                    view?.hideLoading()
                }
    }
}