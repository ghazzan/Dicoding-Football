package com.example.kotlin.dicodingfootball.network

import android.util.Log
import com.example.kotlin.dicodingfootball.BuildConfig
import com.example.kotlin.dicodingfootball.network.response.MatchResponse
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

object TheSportsDBApi {

    init {
        FuelManager.instance.basePath = "https://www.thesportsdb.com/"
        Log.i("TheSportsDB", "Init constructor")
    }

    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
    }

    fun getTeam(){
        Fuel.get("api/v1/json/1/eventsnextleague.php", listOf("id" to "4328"))
                .responseObject(MatchResponse.Deserializer()) { request, response, result ->
                    Log.i("TheSportsDB", "Real Fuel Response ${result.get().events[0].idEvent}")
                }

        /*Fuel.get("api/v1/json/1/eventsnextleague.php", listOf("id" to "4328")).response { request, response, result ->
            run {
                if (response.responseMessage == "OK")
                    Log.i("TheSportsDB", "Response FUEL " + response.toString())
                else
                    Log.i("TheSportsDB", "Failed FUEL action")
            }
        }*/

        /*"/get".httpGet().responseString { request, response, result ->
            //make a GET to https://httpbin.org/get and do something with response
            val (data, error) = result
            if (error == null) {
                //do something when success
            } else {
                //error handling
            }
        }*/
    }
}