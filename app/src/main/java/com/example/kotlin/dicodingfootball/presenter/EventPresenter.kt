package com.example.kotlin.dicodingfootball.presenter

import com.example.kotlin.dicodingfootball.network.response.DetailTeamResponse
import com.example.kotlin.dicodingfootball.network.response.MatchResponse
import com.example.kotlin.dicodingfootball.view.EventView
import com.example.kotlin.dicodingfootball.view.MainView
import com.example.kotlin.dicodingfootball.view.TeamView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class EventPresenter() {

    private var eventView: EventView? = null
    private var eventDetailView: EventView.DetailEvent? = null

    constructor(mEvent: EventView): this(){
        eventView = mEvent
    }

    constructor(mDetailEvent: EventView.DetailEvent): this(){
        eventDetailView = mDetailEvent
    }

    init {
        FuelManager.instance.basePath = "https://www.thesportsdb.com/"
    }

    fun getNextMatch(mainListener: MainView){
        mainListener.showLoading()
        Fuel.get("api/v1/json/1/eventsnextleague.php", listOf("id" to "4328"))
                .responseObject(MatchResponse.Deserializer()) { request, response, result ->
                    if (response.responseMessage == "OK"){
                        eventView?.showTeamList(result.get().events)
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
                        eventView?.showTeamList(result.get().events)
                    }else{
                        mainListener.showWarning("Terjadi kesalahan ambil data")
                    }
                    mainListener.hideLoading()
                }
    }

    fun getDetailEvent(id: Int, mainListener: MainView){
        mainListener.showLoading()
        Fuel.get("api/v1/json/1/lookupevent.php", listOf("id" to id))
                .responseObject(MatchResponse.Deserializer()){request, response, result ->
                    if (response.responseMessage == "OK"){
                        eventDetailView?.showDetail(result.get().events[0])
                    }else{
                        mainListener.showWarning("Gagal load detail event ...")
                    }
                    mainListener.hideLoading()
                }
    }

    fun getDetailTeam(id: Int, teamCode: Int, detailEvent: TeamView){
        Fuel.get("api/v1/json/1/lookupteam.php", listOf("id" to id))
                .responseObject(DetailTeamResponse.Deserializer()){ request, response, result ->
                    if (response.responseMessage == "OK"){
                        detailEvent.showTeam(teamCode, result.get().teams[0])
                    }
                }
    }
}