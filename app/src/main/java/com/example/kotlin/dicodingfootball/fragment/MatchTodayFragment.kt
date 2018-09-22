package com.example.kotlin.dicodingfootball.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin.dicodingfootball.R
import com.example.kotlin.dicodingfootball.adapter.MatchListAdapter
import com.example.kotlin.dicodingfootball.entity.EventEntity
import com.example.kotlin.dicodingfootball.presenter.EventPresenter
import com.example.kotlin.dicodingfootball.view.EventView
import com.example.kotlin.dicodingfootball.view.MainView
import kotlinx.android.synthetic.main.fragment_match_today.*

class MatchTodayFragment(): Fragment(), EventView {

    private var mainListener: MainView? = null
    private var eventPresenter: EventPresenter? = null

    private var isLoadedToday: Boolean = false

    init {
        eventPresenter = EventPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match_today, container, false) as ViewGroup
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !isLoadedToday){
            getListener()
            loadData()
        }
    }

    override fun showTeamList(list: List<EventEntity>?) {
        context?.let {
            list?.apply {
                isLoadedToday = true
                rvMatchToday.layoutManager = LinearLayoutManager(it)
                rvMatchToday.adapter = MatchListAdapter(it, this)
            }
        }
    }

    private fun loadData(){
        mainListener?.let {
            eventPresenter?.getPrevMatch(it)
        }
    }

    private fun getListener(){
        arguments?.getParcelable<MainView>(KEY_TODAY_LISTENER)?.let {
            mainListener = it
        }
    }

    companion object {

        private const val KEY_TODAY_LISTENER = "key_today_listener"

        fun instance(mainListener: MainView): Fragment{
            val fragment = MatchTodayFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_TODAY_LISTENER, mainListener)
            fragment.arguments = bundle
            return fragment
        }
    }
}