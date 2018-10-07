package com.example.kotlin.dicodingfootball

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.kotlin.dicodingfootball.database.database
import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.example.kotlin.dicodingfootball.entity.TeamEntity
import com.example.kotlin.dicodingfootball.presenter.EventPresenter
import com.example.kotlin.dicodingfootball.table.Favorite
import com.example.kotlin.dicodingfootball.view.EventView
import com.example.kotlin.dicodingfootball.view.MainView
import com.example.kotlin.dicodingfootball.view.TeamView
import com.squareup.picasso.Picasso
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_detail_event.*
import kotlinx.android.synthetic.main.toolbar_detail_event.*

@Parcelize
class DetailEventActivity: AppCompatActivity(), MainView, EventView.DetailEvent, TeamView {

    @IgnoredOnParcel
    private var keyTeam = 0
    @IgnoredOnParcel
    private val defaultNull = "-"

    @IgnoredOnParcel
    private var eventPresenter: EventPresenter? = null

    @IgnoredOnParcel
    private var idEvent: String = "0"

    override fun showLoading() {
        rlDetailLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rlDetailLoading.visibility = View.GONE
    }

    override fun showWarning(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showTeam(teamCode: Int, data: TeamEntity) {
        when(teamCode){
            KEY_TEAM_HOME -> {
                Picasso.get().load(data.strTeamBadge).into(ivEventHome)
            }
            KEY_TEAM_AWAY -> {
                Picasso.get().load(data.strTeamBadge).into(ivEventAway)
            }
        }
    }

    override fun favoriteResult() {
        Toast.makeText(this, "Berhasil tambahkan favorite", Toast.LENGTH_SHORT).show()
        setupFavoriteState()
    }

    override fun unfavouriteResult() {
        Toast.makeText(this, "Berhasil hapus favorite", Toast.LENGTH_SHORT).show()
        setupFavoriteState()
    }

    override fun showDetail(data: EventEntity) {

        loadTeamImage(data)

        tvEventDate.text = data.strDate ?: defaultNull
        tvEventHomeScore.text = data.intHomeScore?.toString() ?: defaultNull
        tvEventAwayScore.text = data.intAwayScore?.toString() ?: defaultNull
        tvEventHomeTitle.text = data.strHomeTeam ?: defaultNull
        tvEventAwayTitle.text = data.strAwayTeam ?: defaultNull
        tvEventHomeFormation.text = data.strHomeFormation ?: defaultNull
        tvEventAwayFormation.text = data.strAwayFormation ?: defaultNull
        tvEventGoalsHome.text = data.strHomeGoalDetails ?: defaultNull
        tvEventGoalsAway.text = data.strAwayGoalDetails ?: defaultNull
        tvEventShotsHome.text = data.intHomeShots?.toString() ?: defaultNull
        tvEventShotsAway.text = data.intAwayShots?.toString() ?: defaultNull
        tvEventGoalkeeperHome.text = data.strHomeLineupGoalkeeper ?: defaultNull
        tvEventGoalkeeperAway.text = data.strAwayLineupGoalkeeper ?: defaultNull
        tvEventDefenseHome.text = data.strHomeLineupDefense ?: defaultNull
        tvEventDefenseAway.text = data.strAwayLineupDefense ?: defaultNull
        tvEventMidfieldHome.text = data.strHomeLineupMidfield?: defaultNull
        tvEventMidfieldAway.text = data.strAwayLineupMidfield ?: defaultNull
        tvEventForwardHome.text = data.strHomeLineupForward ?: defaultNull
        tvEventForwardAway.text = data.strAwayLineupForward?: defaultNull
        tvEventSubstitutesHome.text = data.strHomeLineupSubstitutes?: defaultNull
        tvEventSubstitutesAway.text = data.strAwayLineupSubstitutes?: defaultNull

        setupFavoriteAction(data)
    }

    private fun setupFavoriteAction(param: EventEntity){
        val favoriteEntity = Favorite(
                idEvent = param.idEvent,
                strHomeTeam = param.strHomeTeam,
                strAwayTeam = param.strAwayTeam,
                intHomeScore = param.intHomeScore ?: 0,
                intAwayScore = param.intAwayScore ?: 0,
                strDate = param.strDate)

        ivEventFavorite.setOnClickListener {
            eventPresenter?.setFavoriteEvent(idEvent.toInt(), this, favoriteEntity)
        }
    }

    private fun showLog(message: String){
        Log.i(DetailEventActivity@this::class.java.simpleName, message)
    }

    private fun loadTeamImage(event: EventEntity){
        event.idHomeTeam.let {
            keyTeam = KEY_TEAM_HOME
            eventPresenter?.getDetailTeam(it.toInt(), KEY_TEAM_HOME, this)
        }
        event.idAwayTeam.let {
            keyTeam = KEY_TEAM_AWAY
            eventPresenter?.getDetailTeam(it.toInt(), KEY_TEAM_AWAY, this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)

        getParam()
        setupFavoriteState()

        eventPresenter = EventPresenter(this, this, database)
        eventPresenter?.getDetailEvent(idEvent.toInt(),this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Match Detail"
    }

    private fun getParam(){
        idEvent = intent?.getStringExtra(KEY_EVENT_ID) ?: "0"
    }

    private fun setupFavoriteState(){
        val isFavourite = eventPresenter?.checkFavoriteState(idEvent) ?: false
        if (!isFavourite){
            ivEventFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_add_favorite))
        }else{
            ivEventFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites))
        }
    }

    companion object {
        const val KEY_EVENT_ID = "key_event_id"
        const val KEY_TEAM_HOME = 1
        const val KEY_TEAM_AWAY = 2
    }
}