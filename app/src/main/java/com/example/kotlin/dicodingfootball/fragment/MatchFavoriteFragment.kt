package com.example.kotlin.dicodingfootball.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin.dicodingfootball.DetailEventActivity
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.`interface`.OnEventClickListener
import com.example.kotlin.dicodingfootball.adapter.MatchListAdapter
import com.example.kotlin.dicodingfootball.database.DatabaseOpenHelper
import com.example.kotlin.dicodingfootball.database.database
import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.example.kotlin.dicodingfootball.presenter.EventPresenter
import com.example.kotlin.dicodingfootball.table.Favorite
import com.example.kotlin.dicodingfootball.view.EventView
import com.example.kotlin.dicodingfootball.view.MainView
import kotlinx.android.synthetic.main.fragment_match_favorite.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFavoriteFragment: Fragment(), EventView.LoveEvent, OnEventClickListener {

    private var mContext: Context? = null
    private var mainListener: MainView? = null
    private var favoritePresenter: EventPresenter? = null
    private var isLoadedFavorites: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match_favorite, container, false) as ViewGroup
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !isLoadedFavorites){
            getListener()
            showLog("Load Data presenter ${activity?.applicationContext?.database}")
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = activity?.applicationContext
        showLog("Context fragmet ${activity?.applicationContext?.database}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLog("onViewCreated ${activity?.applicationContext?.database}")
        favoritePresenter = EventPresenter(mContext?.database, this)
        loadData()
    }

    override fun showLoveList(list: List<Favorite>?) {
        isLoadedFavorites = true
        context?.takeIf { list != null }?.apply {
            list?.let {
                rvMatchFavorite.layoutManager = LinearLayoutManager(this)
                val listEvent = mutableListOf<EventEntity>()
                for (xcode in list.indices){
                    val eventEntity = EventEntity(
                            idEvent = list[xcode].idEvent,
                            strHomeTeam = list[xcode].strHomeTeam,
                            strAwayTeam =  list[xcode].strAwayTeam,
                            intHomeScore = list[xcode].intHomeScore,
                            intAwayScore = list[xcode].intAwayScore,
                            strDate = list[xcode].strDate)
                    listEvent.add(xcode, eventEntity)
                }
                showLog("List Favorite $listEvent")
                rvMatchFavorite.adapter = MatchListAdapter(this, listEvent, this@MatchFavoriteFragment)
            }
        }
    }

    private fun showLog(message: String){
        Log.i(MatchFavoriteFragment@this::class.java.simpleName, message)
    }

    override fun onClick(event: EventEntity) {
        startActivity<DetailEventActivity>(DetailEventActivity.KEY_EVENT_ID to event.idEvent)
    }

    private fun getListener(){
        arguments?.getParcelable<MainView>(KEY_FAVORITE_LISTENER)?.let {
            mainListener = it
        }
    }

    private fun loadData(){
        showLog("loadData ... ")
        mainListener?.let {
            favoritePresenter?.getListTeam()
        }
    }

    companion object {
        private const val KEY_FAVORITE_LISTENER = "key_favorite_listener"

        fun instance(mainListener: MainView): Fragment{
            val fragment = MatchFavoriteFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_FAVORITE_LISTENER, mainListener)
            fragment.arguments = bundle
            return fragment
        }
    }

}