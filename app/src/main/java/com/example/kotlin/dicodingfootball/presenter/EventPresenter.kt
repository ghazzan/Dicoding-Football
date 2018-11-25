package com.example.kotlin.dicodingfootball.presenter

import android.content.Context
import android.util.Log
import com.example.kotlin.dicodingfootball.database.DatabaseOpenHelper
import com.example.kotlin.dicodingfootball.network.response.DetailTeamResponse
import com.example.kotlin.dicodingfootball.network.response.MatchResponse
import com.example.kotlin.dicodingfootball.table.Favorite
import com.example.kotlin.dicodingfootball.view.EventView
import com.example.kotlin.dicodingfootball.view.MainView
import com.example.kotlin.dicodingfootball.view.TeamView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class EventPresenter() {

    private var context: Context? = null
    private var eventView: EventView? = null
    private var eventDetailView: EventView.DetailEvent? = null
    private var eventLoveView: EventView.LoveEvent? = null
    private var dbHelper: DatabaseOpenHelper? = null

    constructor(mEvent: EventView): this(){
        eventView = mEvent
    }

    constructor(mContext: Context, mDetailEvent: EventView.DetailEvent, mDbHelper: DatabaseOpenHelper?): this(){
        context = mContext
        eventDetailView = mDetailEvent
        dbHelper = mDbHelper
    }

    constructor(mDbEvent: DatabaseOpenHelper?, mLoveEvent: EventView.LoveEvent): this(){
        dbHelper = mDbEvent
        eventLoveView = mLoveEvent
        showLog("Create from loveFragment $dbHelper")
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

    fun setFavoriteEvent(id: Int, mainListener: MainView, favoriteEntity: Favorite){
        mainListener.showLoading()
        if (checkFavoriteState(id.toString())){
            removeFavorite(id.toString())
            mainListener.hideLoading()
            mainListener.showSnackbar("Removed to Favorite")
            eventDetailView?.unfavouriteResult()
        }else{
            addFavorite(favoriteEntity)
            mainListener.hideLoading()
            mainListener.showSnackbar("Added to Favorite")
            eventDetailView?.favoriteResult()
        }
    }

    fun checkFavoriteState(id: String): Boolean{
        var result = false
        showLog("checkFavoriteState $dbHelper")
        dbHelper?.use {
            val item = select(Favorite.TABLE_FAVORITE).whereArgs("(${Favorite.EVENT_ID} = {id})", "id" to id)
            result = item.parseList(classParser<Favorite>()).isNotEmpty()
            showLog("Item are $item")
            showLog("Favorite item ${item.parseList(classParser<Favorite>())}")
        }
        return result
    }

    private fun removeFavorite(id: String){
        dbHelper?.use {
            delete(Favorite.TABLE_FAVORITE, "(${Favorite.EVENT_ID} = {id})", "id" to id)
        }
    }

    private fun addFavorite(entity: Favorite){
        showLog("Insert Favorite $entity")
        dbHelper?.use {
            insert(Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to entity.idEvent,
                    Favorite.TEAM_AWAY_NAME to entity.strAwayTeam,
                    Favorite.TEAM_HOME_NAME to entity.strHomeTeam,
                    Favorite.TEAM_AWAY_SCORE to entity.intAwayScore,
                    Favorite.TEAM_HOME_SCORE to entity.intHomeScore,
                    Favorite.EVENT_DATE to entity.strDate)
        }
    }

    fun getListTeam(){
        dbHelper?.use {
            val item = select(Favorite.TABLE_FAVORITE)
            val result = item.parseList(classParser<Favorite>())
            showLog("getListTeam $result")
            eventLoveView?.showLoveList(result)
        }
        showLog("Database $dbHelper")
    }

    private fun showLog(message: String){
        Log.i(EventPresenter@this::class.java.simpleName, message)
    }
}