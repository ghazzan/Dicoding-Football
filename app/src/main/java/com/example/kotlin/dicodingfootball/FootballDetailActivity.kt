package com.example.kotlin.dicodingfootball

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kotlin.dicodingfootball.layout.FootballDetailLayout
import org.jetbrains.anko.setContentView

class FootballDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FootballDetailLayout(intent?.extras?.getParcelable(KEY_FOOTBALL_VALUE)).setContentView(this)
    }

    companion object {
        const val KEY_FOOTBALL_VALUE = "key_football_value"
    }
}