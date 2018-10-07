package com.example.kotlin.dicodingfootball.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.kotlin.dicodingfootball.database.DatabaseOpenHelper
import com.example.kotlin.dicodingfootball.fragment.MatchFavoriteFragment
import com.example.kotlin.dicodingfootball.fragment.MatchTodayFragment
import com.example.kotlin.dicodingfootball.fragment.MatchTomorrowFragment
import com.example.kotlin.dicodingfootball.view.MainView

class SlideMatchAdapter(private val viewListener: MainView,
                        fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        return when (position){
            KEY_FRAGMENT_TODAY -> {
                MatchTodayFragment.instance(viewListener)
            }
            KEY_FRAGMENT_TOMORROW -> {
                MatchTomorrowFragment.instance(viewListener)
            }
            KEY_FRAGMENT_FAVORITE -> {
                MatchFavoriteFragment.instance(viewListener)
            }
            else -> {
                ArrayList<Fragment>()[position].targetFragment
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    companion object {
        const val KEY_FRAGMENT_TODAY = 0
        const val KEY_FRAGMENT_TOMORROW = 1
        const val KEY_FRAGMENT_FAVORITE = 2
    }
}