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
import com.example.kotlin.dicodingfootball.presenter.MainPresenter
import com.example.kotlin.dicodingfootball.view.MainView
import kotlinx.android.synthetic.main.fragment_match_today.*

class MatchTodayFragment: Fragment(), MainView {

    private var presenter: MainPresenter? = null
    private var isLoadedToday: Boolean = false

    init {
        presenter = MainPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match_today, container, false) as ViewGroup
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !isLoadedToday){
            loadData()
        }
    }

    override fun showLoading() {
        Log.i("MatchFragment", "Show Loading")
    }

    override fun hideLoading() {
        Log.i("MatchFragment", "Hide Loading")
    }

    override fun showWarning(message: String) {
        Log.i("MatchFragment", "Show Warning")
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
        presenter?.getMatchEvents()
        Log.i("MatchFragment", "Loading Data ....")
    }

    companion object {
        fun instance(): Fragment{
            return MatchTodayFragment()
        }
    }
}