package com.example.kotlin.dicodingfootball

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.dicodingfootball.adapter.SlideMatchAdapter
import kotlinx.android.synthetic.main.activity_match_event.*
import org.jetbrains.anko.support.v4.onPageChangeListener

class MatchEventActivity : AppCompatActivity(){

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_today -> {
                viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tomorrow -> {
                viewpager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_event)

        navigation.setOnNavigationItemSelectedListener(navigationListener)

        viewpager.adapter = SlideMatchAdapter(supportFragmentManager)
        viewpager.onPageChangeListener {
            this.onPageSelected {
                if (it == 0){
                    navigation.selectedItemId = R.id.navigation_today
                }else if (it == 1){
                    navigation.selectedItemId = R.id.navigation_tomorrow
                }
            }
        }
    }
}
