package com.example.kotlin.dicodingfootball.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.kotlin.dicodingfootball.fragment.MatchTodayFragment
import com.example.kotlin.dicodingfootball.fragment.MatchTomorrowFragment

class SlideMatchAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        return when (position){
            0 -> {
                MatchTodayFragment.instance()
            }
            1 -> {
                MatchTomorrowFragment.instance()
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