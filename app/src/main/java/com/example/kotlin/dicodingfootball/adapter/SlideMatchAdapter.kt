package com.example.kotlin.dicodingfootball.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.kotlin.dicodingfootball.fragment.MatchTodayFragment
import com.example.kotlin.dicodingfootball.fragment.MatchTomorrowFragment
import com.example.kotlin.dicodingfootball.view.MainView

class SlideMatchAdapter(val viewListener: MainView, fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        return when (position){
            0 -> {
                MatchTodayFragment.instance(viewListener)
            }
            1 -> {
                MatchTomorrowFragment.instance(viewListener)
            }
            else -> {
                ArrayList<Fragment>()[position].targetFragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}