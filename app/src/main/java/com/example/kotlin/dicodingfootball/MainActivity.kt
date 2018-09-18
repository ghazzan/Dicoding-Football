package com.example.kotlin.dicodingfootball

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.kotlin.dicodingfootball.`interface`.OnItemClickListener
import com.example.kotlin.dicodingfootball.adapter.FootballTeamAdapter
import com.example.kotlin.dicodingfootball.entity.FootballEntity
import com.example.kotlin.dicodingfootball.network.ApiRepository
import com.example.kotlin.dicodingfootball.network.TheSportsDBApi
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var items: MutableList<FootballEntity> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        rvFootballTeam.layoutManager = LinearLayoutManager(this)
        rvFootballTeam.adapter = FootballTeamAdapter(this, items, this)
    }

    override fun onClick(item: FootballEntity) {
        toast("You clicked ${item.name}")
        startActivity<FootballDetailActivity>(FootballDetailActivity.KEY_FOOTBALL_VALUE to item)
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val description = resources.getStringArray(R.array.content_club)
        items.clear()

        for (i in name.indices) {
            items.add(FootballEntity(name[i], image.getResourceId(i, 0), description[i]))
        }

        Thread().run {
            /*val repo = ApiRepository()
            Log.i("MainActivity", "ApiRepository ${repo.doRequest(TheSportsDBApi.getTeams("English"))}")*/
            TheSportsDBApi.getTeam()
        }


    }
}
