package com.example.kotlin.dicodingfootball

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.kotlin.dicodingfootball.adapter.SlideMatchAdapter
import com.example.kotlin.dicodingfootball.view.MainView
import kotlinx.android.synthetic.main.activity_match_event.*
import org.jetbrains.anko.support.v4.onPageChangeListener

class MatchEventActivity() : AppCompatActivity(), MainView {

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

        viewpager.adapter = SlideMatchAdapter(this, supportFragmentManager)
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

    override fun showLoading() {
        rlMainLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rlMainLoading.visibility = View.GONE
    }

    override fun showWarning(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    constructor(parcel: Parcel) : this() {}

    companion object CREATOR : Parcelable.Creator<MatchEventActivity> {
        override fun createFromParcel(parcel: Parcel): MatchEventActivity {
            return MatchEventActivity(parcel)
        }

        override fun newArray(size: Int): Array<MatchEventActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {}

    override fun describeContents(): Int {
        return 0
    }
}
